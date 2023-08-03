package de.geobaer.gvqol.mixin;

import de.geobaer.gvqol.config.ConfigurationHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @Inject(method = "dropInventory", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerInventory;dropAll()V"), cancellable = true)
    public void onDeathChestGrave(CallbackInfo ci) {
        if(!ConfigurationHandler.isChestGraves()) return;
        PlayerEntity instance = (PlayerEntity) (Object) this;
        World world = instance.getWorld();
        BlockPos diePos = instance.getBlockPos();
        if(diePos.getY() < -64) diePos = diePos.withY(-64);
        if(diePos.getY() > 255) diePos = diePos.withY(255);
        BlockState chestState = Blocks.CHEST.getDefaultState();
        world.setBlockState(diePos, chestState);
        world.setBlockState(diePos.add(0,1,0), chestState);
        PlayerInventory inventory = instance.getInventory();
        ChestBlockEntity chest = (ChestBlockEntity) world.getBlockEntity(diePos);
        ChestBlockEntity secondChest = (ChestBlockEntity) world.getBlockEntity(diePos.add(0,1,0));
        chest.setCustomName(inventory.player.getDisplayName());
        secondChest.setCustomName(inventory.player.getDisplayName());
        storeInChests(chest, secondChest, inventory.main, inventory.armor, inventory.offHand);
        inventory.clear();
        ci.cancel();
    }

    @SafeVarargs
    @Unique
    private void storeInChests(ChestBlockEntity chest, ChestBlockEntity secondChest, DefaultedList<ItemStack>... inventories) {
        int stored = 0;
        for(DefaultedList<ItemStack> items : inventories) {
            for(ItemStack is : items) {
                if(is == null || is == ItemStack.EMPTY) continue;
                chest.setStack(stored, is);
                stored++;
                if(stored >= 27) {
                    stored = 0;
                    chest = secondChest;
                }
            }
        }
    }

}
