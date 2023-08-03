package de.geobaer.gvqol.mixin;

import de.geobaer.gvqol.config.ConfigurationHandler;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class ItemStackMixin {

    @Inject(method = "getRepairCost", at = @At("RETURN"), cancellable = true)
    public void reducedRepairCost(CallbackInfoReturnable<Integer> cir) {
        if(!ConfigurationHandler.isAnvilCost()) return;
        if(cir.getReturnValue() != 0) cir.setReturnValue(1);
    }

}
