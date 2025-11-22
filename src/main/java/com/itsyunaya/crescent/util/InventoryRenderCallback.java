package com.itsyunaya.crescent.util;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.gui.DrawContext;

public interface InventoryRenderCallback {

    Event<InventoryRenderCallback> EVENT = EventFactory.createArrayBacked(InventoryRenderCallback.class, (listeners) -> (context) -> {
        for (InventoryRenderCallback event : listeners) {
            event.draw(context);
        }
    });

    void draw(DrawContext context);
}
