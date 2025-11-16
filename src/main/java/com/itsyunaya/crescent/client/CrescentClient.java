package com.itsyunaya.crescent.client;

import com.itsyunaya.crescent.features.meowing.AutoMeow;
import net.fabricmc.api.ClientModInitializer;

public class CrescentClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        AutoMeow.register();
    }
}
