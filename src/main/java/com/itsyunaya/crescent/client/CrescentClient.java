package com.itsyunaya.crescent.client;

import com.itsyunaya.crescent.features.inventory.SlotBinding;
import com.itsyunaya.crescent.features.meowing.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

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
            meow.piss();
        });
    }
}
