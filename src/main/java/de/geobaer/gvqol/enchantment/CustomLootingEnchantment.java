package de.geobaer.gvqol.enchantment;

import de.geobaer.gvqol.config.ConfigurationHandler;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.LuckEnchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class CustomLootingEnchantment extends LuckEnchantment {


    public CustomLootingEnchantment(Rarity rarity, EquipmentSlot... equipmentSlots) {
        super(rarity, EnchantmentTarget.WEAPON, equipmentSlots);
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return EnchantmentTarget.WEAPON.isAcceptableItem(stack.getItem()) || (ConfigurationHandler.isBowLooting() && EnchantmentTarget.BOW.isAcceptableItem(stack.getItem()));
    }
}
