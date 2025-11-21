package com.itsyunaya.crescent.mixin;

import net.minecraft.client.Mouse;
import net.minecraft.client.input.MouseInput;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.itsyunaya.crescent.util.Utils.keyPressed2;

@Mixin(Mouse.class)
public class MouseMixin {

    // i profusely dislike this
    @Inject(method = "onMouseButton", at = @At("HEAD"))
    private void onMouseButton(long window, MouseInput input, int action, CallbackInfo ci) {
        if (input.button() != GLFW.GLFW_MOUSE_BUTTON_LEFT) return;

        if (action == GLFW.GLFW_PRESS) {
            keyPressed2 = true;
        } else if (action == GLFW.GLFW_RELEASE) {
            keyPressed2 = false;
        }
    }
}
