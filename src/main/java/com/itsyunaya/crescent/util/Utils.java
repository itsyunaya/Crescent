package com.itsyunaya.crescent.util;

import com.itsyunaya.crescent.features.notifiers.toasts.CrescentToast;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;

public class Utils {

    public static MinecraftClient mc = MinecraftClient.getInstance();

    public static boolean isPlayerIngame() {
        return MinecraftClient.getInstance().player != null;
    }

    //TODO: change this to translatables
    public void sendToastNotif(Text title, Text description) {
        mc.getToastManager().add(new CrescentToast(title, description));
    }

    public static void sendChatNotif(Text input, int type) {
        // types guide
        // default = 0
        // error = 1
        // success = 2
        Text full = null;
        switch (type) {
            case 0: {
                full = Constants.CRESCENT_MARK.copy().append(input);
                break;
            }
            case 1: {
                full = Constants.CRESCENT_ERROR.copy().append(Constants.CRESCENT_MARK).append(input);
                break;
            }
            case 2: {
                full = Constants.CRESCENT_SUCCESS.copy().append(Constants.CRESCENT_MARK).append(input);
                break;
            }
        }
        mc.player.sendMessage(full, false);
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
    public static boolean keyPressed1 = false;
    public  static boolean keyPressed2 = false;
}
