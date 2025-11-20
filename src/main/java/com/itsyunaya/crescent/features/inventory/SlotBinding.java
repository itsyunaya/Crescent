package com.itsyunaya.crescent.features.inventory;

import com.itsyunaya.crescent.mixin.HandledScreenAccessor;
import com.itsyunaya.crescent.util.DataBuilder;
import com.itsyunaya.crescent.util.Utils;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.util.List;
import java.util.Set;

import static com.itsyunaya.crescent.util.Utils.*;

public class SlotBinding {

    // debug, ill find a better method to execute this later i swear
    public void piss() {
        captureSlots();
        executeMove();
    }

    private static final Set<Integer> ILLEGAL_SLOTS = Set.of(0, 1, 2, 3, 4);
    private static final Set<Integer> ARMOUR_SLOTS = Set.of(5, 6, 7, 8);

    private static boolean wasDown = false;
    private static int lastSlot = -1;

    private static void captureSlots() {
        if (!keyPressed) return;
        if (!(mc.currentScreen instanceof InventoryScreen inventory)) return;

        Slot focusedSlot = ((HandledScreenAccessor) inventory).getFocusedSlot();
        if (focusedSlot == null) return;

        int state = GLFW.glfwGetMouseButton(mc.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_LEFT);
        boolean down = state == GLFW.GLFW_PRESS;

        if (!wasDown && down) {
            lastSlot = focusedSlot.id;
            System.out.println("Pressed slot: " + lastSlot);
        }

        if (wasDown && !down) {
            int releaseSlot = focusedSlot.id;
            System.out.println("Released slot: " + releaseSlot);

            if (lastSlot != -1) {
                registerBind(lastSlot, releaseSlot);
            }
            lastSlot = -1;
        }
        wasDown = down;
    }

    private static void registerBind(int press, int release) {
        if (press == release) {
            Utils.sendChatNotif(Text.literal("Cannot bind slot to itself."), 1);
            Utils.playErrorSound();
        } else {
            System.out.println("Pair captured: " + press + " -> " + release);

            if (!DataBuilder.pairExists(press, release)) {
                if (!ILLEGAL_SLOTS.contains(press) && !ILLEGAL_SLOTS.contains(release)) {
                    if (ARMOUR_SLOTS.contains(press) && ARMOUR_SLOTS.contains(release)) {
                        // error
                        Utils.sendChatNotif(Text.literal("Cannot bind two armour slots together."), 1);
                        Utils.playErrorSound();
                    } else {
                        // register bind
                        Utils.sendChatNotif(Text.literal("Bind registered."), 2);
                        DataBuilder.addPair(press, release);
                        Utils.playConfirmSound();
                    }
                } else {
                    // error
                    Utils.sendChatNotif(Text.literal("Cannot bind crafting grid."), 1);
                    Utils.playErrorSound();
                }
            } else {
                // if bind already exists, delete it again
                DataBuilder.removePair(press, release);
                Utils.sendChatNotif(Text.literal("Bind removed."), 2);
                Utils.playConfirmSound();
            }
        }
    }

    private void executeMove() {
        // check if instanceof inventory
        // check if action is lmb and shift on slot

        // if slotid is present in data, get ALL other slots its bound to
        // if only one is present, move item from focusedslot to that one

        if (mc.currentScreen instanceof InventoryScreen inventoryScreen) {
            if (Screen.hasShiftDown() && keyPressed2) {
                Slot focusedSlot = ((HandledScreenAccessor) inventoryScreen).getFocusedSlot();

                if (DataBuilder.hasPairs(focusedSlot.id)) {
                    // I HAVE NO IDEA WHAT IM DOING !!!!
                    List<Integer> meow = DataBuilder.getOtherPairValues(focusedSlot.id);
                    //moveItemBetweenSlots(focusedSlot.id, meow.getFirst());
                    moveItemBetweenSlots(38, 20);
                }
            }
        }

        // ill take care of this part later

        // if multiple are present (oh great heavens), check what slot of them the item got moved to last
        // if no slot has previously been moved to, choose first out of the list, and move item there
        // if one of the slots has been moved to previously, use that one
        // update prev moved to SOMEHOW
    }

    public static void moveItemBetweenSlots(int slotA, int slotB) {
        ScreenHandler sh = mc.player.currentScreenHandler;
        ClientPlayerEntity player = mc.player;

        // oh great heavens
        // id do this with slotActionType.SWAP but thats reserved for hotbar only, thank you mojang
        mc.interactionManager.clickSlot(sh.syncId, slotA, 0, SlotActionType.PICKUP, player);
        mc.interactionManager.clickSlot(sh.syncId, slotB, 0, SlotActionType.PICKUP, player);
        mc.interactionManager.clickSlot(sh.syncId, slotA, 0, SlotActionType.PICKUP, player);
    }
}
