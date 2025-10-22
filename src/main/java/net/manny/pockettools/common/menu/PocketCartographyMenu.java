package net.manny.pockettools.common.menu;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CartographyTableMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.item.component.MapPostProcessing;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import org.jetbrains.annotations.NotNull;

public class PocketCartographyMenu extends CartographyTableMenu {

    private final Level level;
    private final Player player;
    private final Container container;
    private final ResultContainer resultContainer;

    public PocketCartographyMenu(int containerId, Inventory playerInventory) {
        super(containerId, playerInventory);
        this.player = playerInventory.player;
        this.level = player.level();
        this.container = super.container;
        this.resultContainer = super.resultContainer;
    }

    public PocketCartographyMenu(int containerId, Inventory playerInventory, Player player) {
        super(containerId, playerInventory);
        this.player = player;
        this.level = player.level();
        this.container = super.container;
        this.resultContainer = super.resultContainer;
    }

    public PocketCartographyMenu(int containerId, Inventory playerInventory, ContainerLevelAccess access) {
        super(containerId, playerInventory, access);
        this.player = playerInventory.player;
        this.level = player.level();
        this.container = super.container;
        this.resultContainer = super.resultContainer;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return true;
    }

    @Override
    public void removed(@NotNull Player player) {
        super.removed(player);

        if (!player.level().isClientSide()) {
            for(int i = 0; i < 2; i ++) {
                Slot slot = this.slots.get(i);
                if(slot.hasItem()) {
                    ItemStack stack = slot.remove(slot.getMaxStackSize());
                    if(!stack.isEmpty()) {
                        player.addItem(stack);
                    }
                }
            }
        }
    }

    @Override
    public void slotsChanged(@NotNull Container inventory) {
        super.slotsChanged(inventory);

        if(inventory == this.container && !this.level.isClientSide()) {
            ItemStack map = this.container.getItem(0);
            ItemStack firstSlotStack = this.container.getItem(1);
            ItemStack resultOutput = this.resultContainer.getItem(2);

            MapItemSavedData mapitemsaveddata = MapItem.getSavedData(map, this.level);

            // Copy of the original method's logic with adjustments for pocket cartography
            if (mapitemsaveddata != null) {
                ItemStack itemstack;
                if (firstSlotStack.is(Items.PAPER) && !mapitemsaveddata.locked && mapitemsaveddata.scale < 4) {
                    itemstack = map.copyWithCount(1);
                    itemstack.set(DataComponents.MAP_POST_PROCESSING, MapPostProcessing.SCALE);
                    this.broadcastChanges();
                } else if (firstSlotStack.is(Items.GLASS_PANE) && !mapitemsaveddata.locked) {
                    itemstack = map.copyWithCount(1);
                    itemstack.set(DataComponents.MAP_POST_PROCESSING, MapPostProcessing.LOCK);
                    this.broadcastChanges();
                } else {
                    if (!firstSlotStack.is(Items.MAP)) {
                        this.resultContainer.removeItemNoUpdate(2);
                        this.broadcastChanges();
                        return;
                    }

                    itemstack = map.copyWithCount(2);
                    this.broadcastChanges();
                }

                if (!ItemStack.matches(itemstack, resultOutput)) {
                    this.resultContainer.setItem(2, itemstack);
                    this.broadcastChanges();
                }
            }
        }
    }
}
