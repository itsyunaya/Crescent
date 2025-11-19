package com.itsyunaya.crescent.util;

import net.minecraft.text.Style;
import net.minecraft.text.Text;

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

    public static final Text CRESCENT_ERROR = Text.literal("[").setStyle(Style.EMPTY.withColor(16748451))
            .append(Text.literal("!").setStyle(Style.EMPTY.withBold(true).withColor(13178954)))
            .append("] ").setStyle(Style.EMPTY.withBold(false).withColor(16748451));

    // TODO: fix colouring
    public static final Text CRESCENT_SUCCESS = Text.literal("[").setStyle(Style.EMPTY.withBold(false).withColor(11558908))
            .append("✔").setStyle(Style.EMPTY.withBold(true).withColor(7565820))
            .append("] ").setStyle(Style.EMPTY.withBold(false).withColor(11558908));

    public static final Text CRESCENT_MARK = Text.literal("[").setStyle(Style.EMPTY.withBold(false).withColor(11558908))
            .append(Text.literal("Crescent").setStyle(Style.EMPTY.withBold(true).withColor(7565820)))
            .append(Text.literal("]").setStyle(Style.EMPTY.withBold(false)).setStyle(Style.EMPTY.withColor(11558908)))
            .append(Text.literal(": "));
}
