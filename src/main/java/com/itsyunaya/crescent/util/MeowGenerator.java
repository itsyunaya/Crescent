package com.itsyunaya.crescent.util;

import java.util.Random;

public class MeowGenerator {

    public static String rollMeow() {
        Random random = new Random();

        String meow = Constants.meows[random.nextInt(Constants.meows.length)];
        String meowAddon = Constants.meowAddons[random.nextInt(Constants.meowAddons.length)];
        return  meow + meowAddon;
    }
}
