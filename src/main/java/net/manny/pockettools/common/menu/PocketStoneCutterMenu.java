package net.manny.pockettools.common.menu;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.StonecutterMenu;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PocketStoneCutterMenu extends StonecutterMenu {

    public PocketStoneCutterMenu(int containerId, Inventory playerInventory) {
        super(containerId, playerInventory);
    }

    public PocketStoneCutterMenu(int containerId, Inventory playerInventory, Player player) {
        super(containerId, playerInventory);
    }

    public PocketStoneCutterMenu(int containerId, Inventory playerInventory, ContainerLevelAccess access) {
        super(containerId, playerInventory, access);
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return true;
    }

    @Override
    public void removed(@NotNull Player player) {
        super.removed(player);

        if (!player.level().isClientSide()) {
            Slot inputSlot = this.slots.getFirst();
            if(inputSlot != null && inputSlot.hasItem()) {
                ItemStack stack = inputSlot.remove(inputSlot.getMaxStackSize());
                if(!stack.isEmpty()) {
                    player.addItem(stack);
                }
            }

        }
    }
}
