package com.itsyunaya.crescent.features.inventory;

import com.itsyunaya.crescent.mixin.HandledScreenAccessor;
import com.itsyunaya.crescent.util.DataBuilder;
import com.itsyunaya.crescent.util.Utils;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.util.Set;

import static com.itsyunaya.crescent.util.Utils.mc;

public class SlotBinding {

    // debug, ill find a better method to execute this later i swear
    public void piss() {
        captureSlots();
    }

    private static final Set<Integer> ILLEGAL_SLOTS = Set.of(0, 1, 2, 3, 4);
    private static final Set<Integer> ARMOUR_SLOTS = Set.of(5, 6, 7, 8);

    private static boolean wasDown = false;
    private static int lastSlot = -1;

    private static void captureSlots() {
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
            Utils.sendChatNotif(Text.literal("Cannot bind slot to itself."), 2);
            Utils.playErrorSound();

        } else {
            System.out.println("Pair captured: " + press + " -> " + release);

            if (!DataBuilder.pairExists(press, release)) {
                if (!ILLEGAL_SLOTS.contains(press) && !ILLEGAL_SLOTS.contains(release)) {
                    // did you know that java has an XOR operator? me neither :3c
                    if (ARMOUR_SLOTS.contains(press) ^ ARMOUR_SLOTS.contains(release)) {
                        // register bind
                        DataBuilder.addPair(press, release);
                        Utils.playConfirmSound();

                    } else if (ARMOUR_SLOTS.contains(press) && ARMOUR_SLOTS.contains(release)) {
                        // error

                        Utils.playErrorSound();

                    } else {
                        // register bind
                        DataBuilder.addPair(press, release);
                        Utils.playConfirmSound();
                    }
                } else {
                    // error
                    Utils.playErrorSound();
                }
            } else {
                // if bind already exists, delete it again
                DataBuilder.removePair(press, release);
                Utils.playConfirmSound();
            }
        }
    }
}
