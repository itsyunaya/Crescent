package com.itsyunaya.crescent.util;

import net.minecraft.client.MinecraftClient;

public class Utils {

    public static boolean isPlayerIngame() {
        return MinecraftClient.getInstance().player != null;
    }

    //TODO: clean up the naming and location of this
    public static boolean keyPressed = false;
}
