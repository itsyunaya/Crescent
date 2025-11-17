package com.itsyunaya.crescent.util;

import java.util.Random;

public class MeowUtils {

    public static String rollMeow() {
        Random random = new Random();

        String meow = Constants.MEOWS[random.nextInt(Constants.MEOWS.length)];
        String meowAddon = Constants.MEOW_ADDONS[random.nextInt(Constants.MEOW_ADDONS.length)];
        return  meow + meowAddon;
    }

    // bad code
    public static boolean hasMeow(String input) {
        String inp = input.toLowerCase();
        boolean hasMeow = false;
        boolean bl = false;

        for (String word : Constants.REGEX_BLACKLIST) {
            if (inp.contains(word)) {
                bl = true;
                break;
            }
        }

        if (!bl && Constants.MEOW_REGEX.matcher(inp).matches()) {
            hasMeow = true;
        }

        return hasMeow;
    }
}
