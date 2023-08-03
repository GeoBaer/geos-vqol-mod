package de.geobaer.gvqol.mixin;

import de.geobaer.gvqol.config.ConfigurationHandler;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {

    @Unique
    private static int callCooldown = 10;

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    public void damageCheck(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if(!ConfigurationHandler.isVoidTravel()) return;
        ServerPlayerEntity instance = (ServerPlayerEntity) (Object) this;
        if(instance.isDead()) return;
        if(source.isOf(DamageTypes.OUT_OF_WORLD)) {
            if(callCooldown-- == 0) {
                callCooldown = 10;
                float f = instance.getRandom().nextFloat();
                if(f < 0.5f) {
                    instance.setHealth(1f);
                    double x = instance.getX() * 16;
                    double z = instance.getZ() * 16;
                    instance.teleport(instance.getServer().getWorld(ServerWorld.OVERWORLD), x, 600, z, 0, 0);
                    cir.setReturnValue(false);
                }
            }
        }
    }

}
