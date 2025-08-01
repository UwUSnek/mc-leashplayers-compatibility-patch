package com.snek.leashmod.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.snek.leashmod.LeashImpl;

@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity {
    @Inject(method = "interact(Lnet/minecraft/entity/Entity;Lnet/minecraft/util/Hand;)Lnet/minecraft/util/ActionResult;", at = @At("RETURN"), cancellable = true)
    private void leashplayers$onInteract(Entity entity, Hand hand, CallbackInfoReturnable<ActionResult> info) {
        if (info.getReturnValue() != ActionResult.PASS) return;

        //noinspection ConstantValue // This is not constant at runtime
        if ((Object) this instanceof ServerPlayerEntity player && entity instanceof LeashImpl impl) {
            info.setReturnValue(impl.leashplayers$interact(player, hand));
            info.cancel();
        }
    }
}
