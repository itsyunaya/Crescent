package com.itsyunaya.crescent.util;

import com.itsyunaya.crescent.mixin.HandledScreenAccessor;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.render.*;
import net.minecraft.screen.slot.Slot;

import java.util.List;

import static com.itsyunaya.crescent.util.Utils.mc;

public class InvRenderer {

    public static void render(DrawContext context) {
        if (!(mc.currentScreen instanceof InventoryScreen inv)) return;

        Slot focusedSlot = ((HandledScreenAccessor) inv).getFocusedSlot();
        if (focusedSlot == null) return;

        if (DataBuilder.hasPairs(focusedSlot.id)) {
            List<Integer> binds = DataBuilder.getOtherPairValues(focusedSlot.id);
            for (int i : binds) {
                // this is where id put my line renderer, IF I HAD ONE

                // render a line from the focusedslot to every other bound slot
                // eventually make the line colour customizable
                // maybe even rainbow, idk

            }
        }
    }
}
