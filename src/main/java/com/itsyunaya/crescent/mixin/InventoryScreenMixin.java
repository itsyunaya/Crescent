package com.itsyunaya.crescent.mixin;

import com.itsyunaya.crescent.util.InventoryRenderCallback;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryScreen.class)
public class InventoryScreenMixin {

    @Inject(method = "render", at = @At("TAIL"))
    private void crescent$render(DrawContext context, int mouseX, int mouseY, float deltaTicks, CallbackInfo ci) {
        InventoryRenderCallback.EVENT.invoker().draw(context);
    }

    // TODO: add ctrl click to take one item from stack
}
