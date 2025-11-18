package com.itsyunaya.crescent.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundEvents;

public class Utils {

    public static MinecraftClient mc = MinecraftClient.getInstance();

    public static boolean isPlayerIngame() {
        return MinecraftClient.getInstance().player != null;
    }

    //TODO: make volume of sounds adjustable

    public static void playConfirmSound() {
        mc.player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING.value(), 1.0F, 2.0F);
    }

    public static void playErrorSound() {
        mc.player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_BASS.value(), 1.0F, 0.0F);
    }

    //TODO: clean up the naming and location of this
    public static boolean keyPressed = false;
}
