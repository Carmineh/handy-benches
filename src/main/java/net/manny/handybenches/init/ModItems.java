package net.manny.handybenches.init;

import net.manny.handybenches.HandyBenches;
import net.manny.handybenches.common.item.HandyCartographyTable;
import net.manny.handybenches.common.item.HandyCraftingTable;
import net.manny.handybenches.common.item.HandyStoneCutter;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(BuiltInRegistries.ITEM.key(), HandyBenches.MODID);

    public static final DeferredHolder<Item, Item> HANDY_CRAFTING_TABLE = ITEMS.register("handy_crafting_table",
            () -> new HandyCraftingTable(new Item.Properties().stacksTo(1)));

    public static final DeferredHolder<Item, Item> HANDY_STONECUTTER = ITEMS.register("handy_stonecutter",
            () -> new HandyStoneCutter(new Item.Properties().stacksTo(1)));

    public static final DeferredHolder<Item, Item> HANDY_CARTOGRAPHY_TABLE = ITEMS.register("handy_cartography_table",
            () -> new HandyCartographyTable(new Item.Properties().stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}