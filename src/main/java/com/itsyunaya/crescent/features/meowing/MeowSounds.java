package com.itsyunaya.crescent.features.meowing;

import com.itsyunaya.crescent.config.CrescentConfig;
import com.itsyunaya.crescent.util.MeowUtils;
import net.fabricmc.fabric.api.client.message.v1.ClientSendMessageEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvents;

public class MeowSounds {

    private static boolean meowSoundsEnabled = CrescentConfig.meowSoundsEnabled;

    public static void register() {
        if (!meowSoundsEnabled) {
            return;
        }

        ClientSendMessageEvents.CHAT.register((message) -> {
            if (MeowUtils.hasMeow(message)) {
                MinecraftClient.getInstance().getSoundManager().play(
                        PositionedSoundInstance.master(
                                SoundEvents.ENTITY_CAT_AMBIENT,
                                1.0F
                        )
                );
            }
        });

    }
}
