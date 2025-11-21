package com.itsyunaya.crescent.util;

import com.itsyunaya.crescent.mixin.HandledScreenAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.screen.slot.Slot;

import java.util.List;

import static com.itsyunaya.crescent.util.Utils.mc;

public class InvRenderer {

    public static void render() {
        if (mc.currentScreen == null) return;
        VertexConsumerProvider.Immediate immediate = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();
        DrawContext meow = new DrawContext(mc, immediate);
        meow.drawHorizontalLine(10, 2000, 50, 7565820);
        meow.fill(10, 10, mc.currentScreen.width, mc.currentScreen.height, 7565820);

        if (!(mc.currentScreen instanceof InventoryScreen inv)) return;



        Slot focusedSlot = ((HandledScreenAccessor) inv).getFocusedSlot();
        if (focusedSlot == null) return;

        if (DataBuilder.hasPairs(focusedSlot.id)) {
            List<Integer> binds = DataBuilder.getOtherPairValues(focusedSlot.id);
            for (int i : binds) {
                // render a line from the focusedslot to every other bound slot
                // eventually make the line colour customizable
                // maybe even rainbow, idk

            }
        }
    }


}
