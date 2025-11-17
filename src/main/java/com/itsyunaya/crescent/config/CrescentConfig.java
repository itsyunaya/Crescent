package com.itsyunaya.crescent.config;

import io.wispforest.owo.config.annotation.Config;

@Config(name = "crescent-config", wrapperName = "CrescentConfigWrap")
public class CrescentConfig {
    public int mrrraow = 10;

    public static boolean autoMeow = true;
    public static boolean autoMeowBack = true;
    public static int autoMeowBackCooldown = 100;

    public static boolean randomMeowsEnabled = true;
    public static int randomMeowsTime = 1;
    public static int randomMeowsChance = 95;
}
