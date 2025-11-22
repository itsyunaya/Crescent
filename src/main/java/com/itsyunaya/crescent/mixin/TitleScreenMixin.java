package com.itsyunaya.crescent.mixin;

import com.itsyunaya.crescent.config.CrescentConfig;
import net.minecraft.SharedConstants;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {

    @Inject(method = "addDevelopmentWidgets", at = @At("HEAD"))
    private void crescent$enableTestWorldButton(int y, int spacingY, CallbackInfoReturnable<Integer> cir) {
        SharedConstants.isDevelopment = CrescentConfig.enableTestWorldCreationButton;
    }
}
