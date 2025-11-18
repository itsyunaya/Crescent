package com.itsyunaya.crescent.features.meowing;

import com.itsyunaya.crescent.config.CrescentConfig;
import com.itsyunaya.crescent.util.MeowUtils;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;

import static com.itsyunaya.crescent.util.Utils.mc;

public class AutoMeow {

    private static boolean autoMeowEnabled = CrescentConfig.autoMeow;
    private static boolean autoMeowBackEnabled = CrescentConfig.autoMeowBack;
    private static int autoMeowBackCooldown = CrescentConfig.autoMeowBackCooldown;

    private static long lastMeow = 0;

    public static void register() {
        if (!autoMeowEnabled) {
            return;
        }

        long now = System.nanoTime();
        long elapsedMs = (now - lastMeow) / 1_000_000;

        // for system messages, does not include player sent stuff
        ClientReceiveMessageEvents.GAME.register(((message, overlay) -> {
            // for some reason the code shits itself when nh is not inside this function, im too stupid to figure out why
            ClientPlayNetworkHandler nh = mc.getNetworkHandler();

            // player join
            if (hasTranslationKey(message, "multiplayer.player.joined") || hasTranslationKey(message, "multiplayer.player.joined.renamed")) {
                nh.sendChatMessage(MeowUtils.rollMeow(true));
            }
        }));

        // for player-sent messages
        ClientReceiveMessageEvents.CHAT.register(((message, signedMessage, sender, params, receptionTimestamp) -> {
            ClientPlayNetworkHandler nh = mc.getNetworkHandler();

            // automatic meow back
            // TODO: test this (it might work)
            if (!autoMeowBackEnabled) {
                return;
            }

            if (elapsedMs < autoMeowBackCooldown) {
                mc.player.sendMessage(Text.literal("AutoMeow is on cooldown, you meowed too hard..."), false);
            } else if (MeowUtils.hasMeow(message.getString())) {
                nh.sendChatMessage(MeowUtils.rollMeow(true));
                lastMeow = now;
            }
        }));
    }

    public static boolean hasTranslationKey(Text text, String key) {
        if (text.getContent() instanceof TranslatableTextContent translatable) {
            if (translatable.getKey().equals(key)) return true;
        }
        for (Text sibling : text.getSiblings()) {
            if (hasTranslationKey(sibling, key)) return true;
        }
        return false;
    }
}
