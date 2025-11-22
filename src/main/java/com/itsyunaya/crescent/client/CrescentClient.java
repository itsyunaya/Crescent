package com.itsyunaya.crescent.client;

import com.itsyunaya.crescent.features.inventory.SlotBinding;
import com.itsyunaya.crescent.features.meowing.*;
import com.itsyunaya.crescent.util.InventoryRenderCallback;
import io.wispforest.owo.ui.core.Color;
import io.wispforest.owo.ui.renderstate.LineElementRenderState;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.ScreenRect;

public class CrescentClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        AutoMeow.register();
        MeowSounds.register();
        MeowSpeak.register();
        MeowFilter.register();

        // this is an AWFUL way to do this and i NEED to rewrite it
        // TODO: make less evil
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            RandomMeows.register();
            SlotBinding meow = new SlotBinding();
            meow.wawa();
        });

        int x0 = 0;
        int y0 = 0;
        int x1 = 10;
        int y1 = 10;

        InventoryRenderCallback.EVENT.register(ctx -> {
            ScreenRect screenRect = new ScreenRect(x0, y0, x1 - x0, y1 - y0).transformEachVertex(ctx.getMatrices());

            ctx.state.addSimpleElement(
                    new LineElementRenderState(
                            RenderPipelines.GUI,
                            ctx.getMatrices(),
                            screenRect,
                            x0, y0, x1, y1,
                            1,
                            Color.BLACK
                    )
            );
        });
    }
}
