package com.itsyunaya.crescent.mixin;

import com.itsyunaya.crescent.util.Utils;
import net.minecraft.client.Keyboard;
import net.minecraft.client.input.KeyInput;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public abstract class KeyboardMixin {

    //TODO: Make keybind dynamically changeable
    @Inject(method = "onKey", at = @At("HEAD"))
    private void onKey(long window, int action, KeyInput input, CallbackInfo ci) {
        // how am i gonna set a custom GLFW.GLFW_KEY? idk! am i gonna figure it out? maybe!
        if (input.key() == GLFW.GLFW_KEY_L) {
            if (action == GLFW.GLFW_PRESS || action == GLFW.GLFW_REPEAT) {
                Utils.keyPressed = true;
            } else if (action == GLFW.GLFW_RELEASE) {
                Utils.keyPressed = false;
            }
        }

        if (input.key() == GLFW.GLFW_KEY_G) {
            if (action == GLFW.GLFW_PRESS || action == GLFW.GLFW_REPEAT) {
                Utils.keyPressed1 = true;
            } else if (action == GLFW.GLFW_RELEASE) {
                Utils.keyPressed1 = false;
            }
        }
    }
}
