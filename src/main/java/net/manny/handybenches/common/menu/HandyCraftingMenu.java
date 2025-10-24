package net.manny.handybenches.common.menu;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;


public class HandyCraftingMenu extends CraftingMenu {

    private final Level level;

    public HandyCraftingMenu(int containerId, Inventory playerInventory) {
        super(containerId, playerInventory);
        this.level = this.player.level();
    }

    public HandyCraftingMenu(int containerId, Inventory playerInventory, Player player) {
        super(containerId, playerInventory);
        this.level = this.player.level();
    }

    public HandyCraftingMenu(int containerId, Inventory playerInventory, ContainerLevelAccess access) {
        super(containerId, playerInventory, access);
        this.level = this.player.level();
    }

    // Overriding the Removed method to give the player their items back when they close the crafting menu
    // Instead of dropping them on the ground
    @Override
    public void removed(@NotNull Player player) {
        super.removed(player);

        if (!player.level().isClientSide()) {
            // Forced for loop to only get the first 9 slots (the crafting grid)
            // The rest are the result slots or inventory slots of the player
            for(int i = 0; i < 9; i ++){
                Slot slot = this.slots.get(i);
                if(slot.hasItem()){
                    // Removing the Max Stack Size because we always want to remove all items in the slot
                    ItemStack itemstack = slot.remove(slot.getMaxStackSize());
                    if(!itemstack.isEmpty()){
                        player.addItem(itemstack);
                    }
                }

            }
            /*
            for (Slot slot : this.slots) {
                ItemStack itemstack = slot.remove(slot.getMaxStackSize());
                if (!itemstack.isEmpty()) {
                    player.drop(itemstack, false);
                }
            }*/
        }

    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return true;
    }


    @Override
    public void slotsChanged(@NotNull Container inventory) {

        if(!this.level.isClientSide() && player instanceof ServerPlayer serverPlayer && inventory == this.craftSlots) {
            ItemStack itemstack = ItemStack.EMPTY;

            Optional<RecipeHolder<CraftingRecipe>> optionalRecipe = this.level.getRecipeManager()
                    .getRecipeFor(RecipeType.CRAFTING, this.craftSlots.asCraftInput(), this.level);

            if(optionalRecipe.isPresent()) {
                RecipeHolder<CraftingRecipe> recipeHolder = optionalRecipe.get();
                CraftingRecipe recipeValue = recipeHolder.value();

                if(this.resultSlots.setRecipeUsed(level, serverPlayer, recipeHolder)){
                    itemstack = recipeValue.assemble(this.craftSlots.asCraftInput(), this.level.registryAccess());
                }
            }

            this.resultSlots.setItem(0, itemstack);

            this.broadcastChanges();
        }else{
            super.slotsChanged(inventory);
            return;
        }
    }
}
