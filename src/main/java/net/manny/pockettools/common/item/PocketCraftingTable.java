package net.manny.pockettools.common.item;

import net.manny.pockettools.common.menu.PocketCraftingMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class PocketCraftingTable extends Item {

    private static final Component CONTAINER_TITLE = Component.translatable("container.crafting");

    public PocketCraftingTable(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if(!level.isClientSide() && player instanceof ServerPlayer serverPlayer){

            MenuProvider menuProvider = new MenuProvider() {
                @Override
                public Component getDisplayName() {
                    return CONTAINER_TITLE;
                }

                @Override
                public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
                    return new PocketCraftingMenu(i, inventory, player);
                }
            };

            serverPlayer.openMenu(menuProvider);

            player.awardStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
            return InteractionResultHolder.consume(player.getItemInHand(usedHand));
        }

        return InteractionResultHolder.success(player.getItemInHand(usedHand));
    }





}
