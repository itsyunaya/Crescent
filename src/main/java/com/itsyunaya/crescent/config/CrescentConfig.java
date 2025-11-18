package com.itsyunaya.crescent.config;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.RegexConstraint;

@Config(name = "crescent-config", wrapperName = "CrescentConfigWrap")
public class CrescentConfig {

    public static boolean autoMeow = true;
    public static boolean autoMeowBack = false;
    // in milliseconds
    public static int autoMeowBackCooldown = 500;

    public static boolean randomMeowsEnabled = false;
    public static int randomMeowsTime = 1;
    public static int randomMeowsChance = 95;

    public static boolean meowSoundsEnabled = true;

    public static boolean meowSpeakEnabled = true;

    public static boolean meowFilterEnabled = true;
    @RegexConstraint("[a-z]{1,10}")
    public static String[] meowFilterWords = {"bark", "test"};

    // utils
    public static boolean enableTestWorldCreationButton = true;
}
