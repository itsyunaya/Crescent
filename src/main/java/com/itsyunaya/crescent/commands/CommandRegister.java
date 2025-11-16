package com.itsyunaya.crescent.commands;

import com.itsyunaya.crescent.config.ConfigScreen;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;

public class CommandRegister {
    public static void register() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(ClientCommandManager.literal("ca")
                    .executes(commandContext -> {
                        MinecraftClient client = MinecraftClient.getInstance();
                        client.send(() -> {
                            client.setScreen(new ConfigScreen());
                        });
                        return 1;
                    })
                    .then(ClientCommandManager.literal("test")
                            .executes(context -> {

                                return 1;
                            })
                    )
            );
        });
    }
}
