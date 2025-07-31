package com.snek.leashmod.mixin;

import net.minecraft.entity.decoration.LeashKnotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.snek.leashmod.LeashImpl;

@Mixin(LeashKnotEntity.class)
public class MixinLeashKnotEntity {
    @Inject(at = @At("HEAD"), method = "interact", cancellable = true)
    private void leashplayers$onInteractBlockDefaultTriger(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (player instanceof LeashImpl impl && impl.leashplayers$shouldCancel()) {
            cir.setReturnValue(ActionResult.FAIL);
            cir.cancel();
        }
    }
}
