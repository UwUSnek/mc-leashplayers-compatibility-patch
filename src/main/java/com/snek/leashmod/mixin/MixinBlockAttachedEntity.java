package me.snek.leashmod.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.BlockAttachedEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import me.snek.leashmod.LeashImpl;

@Mixin(BlockAttachedEntity.class)
public class MixinBlockAttachedEntity {
    @Inject(at = @At("HEAD"),method = "handleAttack", cancellable = true)
    private void leashplayers$onInteractBlockDefaultTriger(Entity attacker, CallbackInfoReturnable<Boolean> cir) {
        if (attacker instanceof LeashImpl impl && impl.leashplayers$shouldCancel()) {
            cir.setReturnValue(true);
            cir.cancel();
        }
    }
}
