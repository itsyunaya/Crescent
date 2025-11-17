package com.itsyunaya.crescent.features.meowing;

import com.itsyunaya.crescent.Crescent;
import com.itsyunaya.crescent.config.CrescentConfig;
import com.itsyunaya.crescent.util.MeowUtils;
import com.itsyunaya.crescent.util.Utils;
import net.minecraft.client.MinecraftClient;

public class RandomMeows {
    private static boolean randomMeowsEnabled = CrescentConfig.randomMeowsEnabled;
    private static int randomMeowsTime = CrescentConfig.randomMeowsTime;
    private static int randomMeowsChance = CrescentConfig.randomMeowsChance;

    // counter in ticks
    static int counter = 0;

    public static void register() {
        if (!randomMeowsEnabled || !Utils.isPlayerIngame()) {
            return;
        }

        counter++;

        if (counter >= randomMeowsTime * 20) {
            counter = 0;

            if (Math.random() < (double) randomMeowsChance / 100) {
                MinecraftClient.getInstance().player.networkHandler.sendChatMessage(MeowUtils.rollMeow());
            }
        }
    }
}
