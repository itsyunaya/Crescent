package com.itsyunaya.crescent.features.meowing;

import com.itsyunaya.crescent.config.CrescentConfig;
import com.itsyunaya.crescent.util.MeowUtils;
import net.fabricmc.fabric.api.client.message.v1.ClientSendMessageEvents;

public class MeowSpeak {

    private static boolean meowSpeakEnabled = CrescentConfig.meowSpeakEnabled;

    public static void register() {
        if (!meowSpeakEnabled) {
            return;
        }

        ClientSendMessageEvents.MODIFY_CHAT.register(((message) -> message.concat(" " + MeowUtils.rollMeow(true))));
    }
}
