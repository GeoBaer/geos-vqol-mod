package de.geobaer.gvqol.mixin;

import de.geobaer.gvqol.config.ConfigurationHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Map;

@Mixin(AnvilScreenHandler.class)
public class AnvilScreenHandlerMixin {


    @Inject(method = "updateResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getCount()I", ordinal = 1), locals = LocalCapture.CAPTURE_FAILHARD)
    public void noLevelCalc(CallbackInfo ci, ItemStack itemStack, int i, int r, int s) {
        if(!ConfigurationHandler.isAnvilCost()) return;
        i -= r * s;
        i += s;
    }

    @Inject(method = "getNextCost", at = @At("HEAD"), cancellable = true)
    private static void constantRepairCost(int cost, CallbackInfoReturnable<Integer> cir) {
        if(!ConfigurationHandler.isAnvilCost()) return;
        cir.setReturnValue(1);
    }

    @ModifyArg(method = "updateResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;setCustomName(Lnet/minecraft/text/Text;)Lnet/minecraft/item/ItemStack;"), index = 0)
    private Text changeText(@Nullable Text name) {
        if(!ConfigurationHandler.isAnvilRename() || name == null) return name;
        return name.copyContentOnly().fillStyle(Style.EMPTY.withItalic(false));
    }

    // For Some Reason I gotta Include every captured Variable because IntelliJ is stupid sometimes
    @Inject(method = "updateResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;setCustomName(Lnet/minecraft/text/Text;)Lnet/minecraft/item/ItemStack;"), locals = LocalCapture.CAPTURE_FAILHARD)
    public void removeOneLevelFromCost(CallbackInfo ci, ItemStack itemStack, int i, int j, int k, ItemStack itemStack2, ItemStack itemStack3, Map map) {
        if(!ConfigurationHandler.isAnvilCost()) return;
        i -= 1;
    }

    @Inject(method = "updateResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;removeCustomName()V"), locals = LocalCapture.CAPTURE_FAILHARD)
    public void removeOneLevelFromCostAgain(CallbackInfo ci, ItemStack itemStack, int i, int j, int k, ItemStack itemStack2, ItemStack itemStack3, Map map) {
        if(!ConfigurationHandler.isAnvilCost()) return;
        i -= 1;
    }

}
