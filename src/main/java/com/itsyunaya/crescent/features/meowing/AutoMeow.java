package com.itsyunaya.crescent.features.meowing;

import com.itsyunaya.crescent.Crescent;
import com.itsyunaya.crescent.config.CrescentConfig;
import com.itsyunaya.crescent.util.MeowGenerator;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;

public class AutoMeow {

    private static boolean enabled = CrescentConfig.AutoMeow;

    public static void register() {
        if (!enabled) {
            return;
        }

        // I can for the life of me not figure out what the boolean here is doing ;w;
        ClientReceiveMessageEvents.GAME.register(((message, mysteryBoolean) -> {
            // debug
            Crescent.LOGGER.info(message.getString());

            String msg = message.getString();
            ClientPlayNetworkHandler nh = MinecraftClient.getInstance().getNetworkHandler();

            // person join
//            if (message instanceof TranslatableTextContent translatable) {
//                if (translatable.getKey().equals("multiplayer.player.joined") || translatable.getKey().equals("multiplayer.player.joined.renamed")) {
//                    // intellij please im begging player cant be null if you're logged into the fucking game and listening for chat messages, im genuinely going to kill someone
//                    assert MinecraftClient.getInstance().player != null;
//                    MinecraftClient.getInstance().player.networkHandler.sendChatMessage("meow");
//                }
//            }

            if (msg.contains(" joined the game")) {
                nh.sendChatMessage(MeowGenerator.rollMeow());
            }
        }));
    }
}
