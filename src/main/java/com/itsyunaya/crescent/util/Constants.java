package com.itsyunaya.crescent.util;

import java.util.regex.Pattern;

public final class Constants {

    // oh great heavens
    public static final Pattern MEOW_REGEX = Pattern.compile(
            "\\b(" +
                    "m[eoaru]{1,6}(w+r*)*[opr]?" +
                    "|" +
                    "n+y+a+" +
                    "|" +
                    "p+u+r{2,}" +
                    ")[~!?.]*\\b",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

    public static final String[] REGEX_BLACKLIST = {"more", "mer", "mur", "me", "ma", "mop", "map", "more."};

    public static final String[] MEOWS = {
            "meow",
            "mraow",
            "mreow",
            "mrrp",
            "nyaa",
            "purrr",
            "mrrroew",
            "mewo",
            "mrwow",
            "meowwr",
            "mraowr"
    };

    public static final String[] MEOW_ADDONS = {
            "",
            "~",
            "~~",
            " :3",
            " :3c",
            " !",
            "~!",
    };
}
