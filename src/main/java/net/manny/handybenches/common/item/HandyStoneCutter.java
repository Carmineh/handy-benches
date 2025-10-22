package net.manny.handybenches.common.item;

import net.manny.handybenches.common.menu.HandyStoneCutterMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class HandyStoneCutter extends Item {

    private static final Component CONTAINER_TITLE = Component.translatable("container.stonecutter");

    public HandyStoneCutter(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack itemStack = player.getItemInHand(usedHand);

        if(!level.isClientSide() && player instanceof ServerPlayer serverPlayer){
            MenuProvider menuProvider = new MenuProvider() {
                @Override
                public Component getDisplayName() {
                    return CONTAINER_TITLE;
                }

                @Override
                public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
                    return new HandyStoneCutterMenu(i, inventory, player);
                }
            };
            serverPlayer.openMenu(menuProvider);

            return InteractionResultHolder.consume(itemStack);
        }

        return InteractionResultHolder.success(itemStack);
    }

}
