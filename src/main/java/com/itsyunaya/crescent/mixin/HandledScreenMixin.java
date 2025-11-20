package com.itsyunaya.crescent.mixin;

import com.itsyunaya.crescent.util.DataBuilder;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.itsyunaya.crescent.util.Utils.mc;

@Mixin(HandledScreen.class)
public abstract class HandledScreenMixin {

    @Inject(method = "onMouseClick(Lnet/minecraft/screen/slot/Slot;IILnet/minecraft/screen/slot/SlotActionType;)V", at = @At("HEAD"), cancellable = true)
    private void cancelQuickMove(Slot slot, int slotId, int button, SlotActionType actionType, CallbackInfo ci) {
        if (mc.currentScreen instanceof InventoryScreen) {
            if (DataBuilder.hasPairs(slotId)) {
                if (actionType == SlotActionType.QUICK_MOVE) {
                    ci.cancel();
                }
            }
        }
    }
}
