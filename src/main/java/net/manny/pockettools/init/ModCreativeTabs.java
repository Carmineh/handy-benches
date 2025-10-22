package net.manny.pockettools.init;

import net.manny.pockettools.PocketTools;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), PocketTools.MODID);

    public static final Supplier<CreativeModeTab> POCKET_TOOLS_TAB = CREATIVE_TABS.register("pocket_tools_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.POCKET_CRAFTING_TABLE.get()))
                    .title(Component.translatable("creativetab.pockettools"))
                    .displayItems(
                            (itemDisplayParameters, output) -> {
                                output.accept(ModItems.POCKET_CRAFTING_TABLE.get());
                                output.accept(ModItems.POCKET_STONECUTTER.get());
                                output.accept(ModItems.POCKET_CARTOGRAPHY_TABLE.get());
                            }
                    )
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_TABS.register(eventBus);
    }
}