package com.itsyunaya.crescent.features.meowing;

import com.itsyunaya.crescent.config.CrescentConfig;

public class RandomMeows {
    private static boolean randomMeowsEnabled = CrescentConfig.randomMeowsEnabled;
    private static int randomMeowsTime = CrescentConfig.randomMeowsTime;
    private static int randomMeowsChance = CrescentConfig.randomMeowsChance;

    public void register() {
        if (!randomMeowsEnabled) {
            return;
        }

        int counter = 0;
    }
}
