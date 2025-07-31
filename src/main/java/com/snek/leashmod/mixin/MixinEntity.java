package com.snek.leashmod.mixin;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.snek.leashmod.LeashImpl;
import com.snek.leashmod.LeashProxyEntity;

@Mixin(Entity.class)
public class MixinEntity {
    @Inject(at = @At("HEAD"), method = "canUsePortals", cancellable = true)
    private void canUsePortals(CallbackInfoReturnable<Boolean> cir) {
        if ((Object) this instanceof LeashProxyEntity) {
            cir.setReturnValue(false);
            cir.cancel();
        } else if ((Object) this instanceof LeashImpl impl) {
            if (impl.leashplayers$getHolder() != null) {
                cir.setReturnValue(false);
                cir.cancel();
            }
        }
    }
}
