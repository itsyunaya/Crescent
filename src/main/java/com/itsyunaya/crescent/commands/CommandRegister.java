package com.itsyunaya.crescent.commands;

import com.itsyunaya.crescent.config.ConfigScreen;
import com.itsyunaya.crescent.features.inventory.SlotBinding;
import com.itsyunaya.crescent.features.notifiers.toasts.CrescentToast;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

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
                                MinecraftClient.getInstance().getToastManager().add(new CrescentToast(Text.literal("maowww"), Text.literal("mrrp")));
                                return 1;
                            })
                    )
                    //debug
                    .then(ClientCommandManager.literal("test2").executes(context -> {
                        SlotBinding.moveItemBetweenSlots(20, 25);
                        return 1;
                    }))
            );
        });
    }
}
