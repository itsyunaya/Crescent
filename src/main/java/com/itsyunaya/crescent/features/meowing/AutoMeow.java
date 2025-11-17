package com.itsyunaya.crescent.features.meowing;

import com.itsyunaya.crescent.config.CrescentConfig;
import com.itsyunaya.crescent.util.MeowUtils;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;

public class AutoMeow {

    private static boolean autoMeowEnabled = CrescentConfig.autoMeow;
    private static boolean autoMeowBackEnabled = CrescentConfig.autoMeowBack;
    private static int autoMeowBackCooldown = CrescentConfig.autoMeowBackCooldown;

    public static void register() {
        if (!autoMeowEnabled) {
            return;
        }

        AtomicLong lastMeow = new AtomicLong();
        long now = Instant.now().getEpochSecond();

        // for system messages, does not include player sent stuff
        ClientReceiveMessageEvents.GAME.register(((message, overlay) -> {
            // for some reason the code shits itself when nh is not inside this function, im too stupid to figure out why
            ClientPlayNetworkHandler nh = MinecraftClient.getInstance().getNetworkHandler();

            // player join
            if (hasTranslationKey(message, "multiplayer.player.joined") || hasTranslationKey(message, "multiplayer.player.joined.renamed")) {
                nh.sendChatMessage(MeowUtils.rollMeow());
            }
        }));

        // for player-sent messages
        ClientReceiveMessageEvents.CHAT.register(((message, signedMessage, sender, params, receptionTimestamp) -> {
            ClientPlayNetworkHandler nh = MinecraftClient.getInstance().getNetworkHandler();

            // automatic meow back
            // TODO: actually test this
            if (!autoMeowBackEnabled) {
                return;
            }

            if (now - lastMeow.get() < autoMeowBackCooldown) {
                MinecraftClient.getInstance().player.sendMessage(Text.literal("AutoMeow is on cooldown, you meowed too hard..."), false);
            } else if (MeowUtils.hasMeow(message.getString())) {
                nh.sendChatMessage(MeowUtils.rollMeow());
                lastMeow.set(now);
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
