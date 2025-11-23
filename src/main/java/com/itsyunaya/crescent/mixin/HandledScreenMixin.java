package com.itsyunaya.crescent.mixin;

import com.itsyunaya.crescent.util.DataBuilder;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.itsyunaya.crescent.util.Utils.mc;

@Mixin(HandledScreen.class)
public abstract class HandledScreenMixin {

    @Shadow
    @Nullable
    protected Slot focusedSlot;

    @Inject(method = "onMouseClick(Lnet/minecraft/screen/slot/Slot;IILnet/minecraft/screen/slot/SlotActionType;)V", at = @At("HEAD"), cancellable = true)
    private void crescent$cancelQuickMove(Slot slot, int slotId, int button, SlotActionType actionType, CallbackInfo ci) {
        if (mc.currentScreen instanceof InventoryScreen) {
            if (DataBuilder.hasPairs(slotId)) {
                if (actionType == SlotActionType.QUICK_MOVE) {
                    ci.cancel();
                }
            }
        }
    }

    // maybe redundant
    @Inject(method = "mouseClicked", at = @At("HEAD"))
    private void crescent$ctrlClickTakeItem(Click click, boolean doubled, CallbackInfoReturnable<Boolean> cir) {
        if (mc.currentScreen instanceof InventoryScreen
                && focusedSlot != null
                && focusedSlot.hasStack()
                && click.isLeft()
                && click.hasCtrl()
        ) {

        }
    }
}
