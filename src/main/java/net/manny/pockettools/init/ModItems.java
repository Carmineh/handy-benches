package net.manny.pockettools.init;

import net.manny.pockettools.PocketTools;
import net.manny.pockettools.common.item.PocketCraftingTable;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(BuiltInRegistries.ITEM.key(), PocketTools.MODID);

    public static final DeferredHolder<Item, Item> POCKET_CRAFTING_TABLE = ITEMS.register("pocket_crafting_table",
            () -> new PocketCraftingTable(new Item.Properties().stacksTo(1)));

    public static final DeferredHolder<Item, Item> POCKET_STONECUTTER = ITEMS.register("pocket_stonecutter",
            () -> new net.manny.pockettools.common.item.PocketStoneCutter(new Item.Properties().stacksTo(1)));

    public static final DeferredHolder<Item, Item> POCKET_CARTOGRAPHY_TABLE = ITEMS.register("pocket_cartography_table",
            () -> new net.manny.pockettools.common.item.PocketCartographyTable(new Item.Properties().stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}