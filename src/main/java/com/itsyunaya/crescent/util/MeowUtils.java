package com.itsyunaya.crescent.util;

import java.util.Random;
import java.util.regex.Pattern;

import static com.itsyunaya.crescent.util.Constants.MEOW_REGEX;
import static com.itsyunaya.crescent.util.Constants.REGEX_BLACKLIST;

public class MeowUtils {

    public static String rollMeow(boolean addAddon) {
        Random random = new Random();

        String meow = Constants.MEOWS[random.nextInt(Constants.MEOWS.length)];
        String meowAddon = Constants.MEOW_ADDONS[random.nextInt(Constants.MEOW_ADDONS.length)];
        if (addAddon) {
            return meow + meowAddon;
        } else  {
            return meow;
        }
    }

    public static boolean hasMeow(String input) {
        if (input == null || input.isBlank()) return false;

        String lower = input.toLowerCase();

        for (String word : REGEX_BLACKLIST) {
            Pattern p = Pattern.compile("\\b" + Pattern.quote(word) + "\\b",
                    Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
            if (p.matcher(lower).find()) {
                return false;
            }
        }

        return MEOW_REGEX.matcher(lower).find();
    }
}
