package com.itsyunaya.crescent.util;

import java.util.regex.Pattern;

public final class Constants {

    public static final Pattern MEOW_REGEX = Pattern.compile("/\\b(m[e,o,a,r,u]{1,6}(w+r*)*(o|p|r)*|n+y+a+|p+u+r{2,})[~!?.]*\\b/i");
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
