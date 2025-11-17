package com.itsyunaya.crescent.mixin;

import com.itsyunaya.crescent.util.Utils;
import net.minecraft.client.Keyboard;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public abstract class KeyboardMixin {

    //TODO: Make keybind dynamically changeable
    //TODO: Add action on release key
    @Inject(method = "onKey", at = @At("HEAD"))
    private void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        // how am i gonna set a custom GLFW.GLFW_KEY? idk! am i gonna figure it out? maybe!
        if (key == GLFW.GLFW_KEY_L) {
            if (action == GLFW.GLFW_PRESS || action == GLFW.GLFW_REPEAT) {
                Utils.keyPressed = true;
            } else if (action == GLFW.GLFW_RELEASE) {
                Utils.keyPressed = false;
            }
        }
    }
}
