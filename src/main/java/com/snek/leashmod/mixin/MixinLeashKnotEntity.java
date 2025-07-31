package me.snek.leashmod.mixin;

import net.minecraft.entity.decoration.LeashKnotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import me.snek.leashmod.LeashImpl;
import me.snek.leashmod.LeashPlayers;
import me.snek.leashmod.LeashSettings;

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
