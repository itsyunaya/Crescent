package com.itsyunaya.crescent.features.meowing;

import com.itsyunaya.crescent.config.CrescentConfig;
import com.itsyunaya.crescent.util.MeowUtils;
import net.fabricmc.fabric.api.client.message.v1.ClientSendMessageEvents;

public class MeowFilter {

    private static boolean meowFilterEnabled = CrescentConfig.meowFilterEnabled;
    private static String[] meowFilterWords = CrescentConfig.meowFilterWords;

    public static void register() {
        if (!meowFilterEnabled) {
            return;
        }

        ClientSendMessageEvents.MODIFY_CHAT.register((message) -> {
            String[] split = message.split(" ");

            for (int i = 0; i < split.length; i++) {
                for (String meowFilterWord : meowFilterWords) {
                    if (split[i].contains(meowFilterWord)) {
                        split[i] = MeowUtils.rollMeow(false);
                    }
                }
            }

            return String.join(" ", split);
        });
    }
}
