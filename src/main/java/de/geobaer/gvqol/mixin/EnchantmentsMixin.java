package de.geobaer.gvqol.mixin;

import de.geobaer.gvqol.enchantment.CustomLootingEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantments.class)
public class EnchantmentsMixin {

    @Inject(method = "register(Ljava/lang/String;Lnet/minecraft/enchantment/Enchantment;)Lnet/minecraft/enchantment/Enchantment;", at = @At("HEAD"), cancellable = true)
    private static void changeLooting(String name, Enchantment toRegister, CallbackInfoReturnable<Enchantment> cir) {
        if (name.equalsIgnoreCase("LOOTING")) {
            cir.setReturnValue(Registry.register(Registries.ENCHANTMENT, name, new CustomLootingEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND)));
        }
    }

}
