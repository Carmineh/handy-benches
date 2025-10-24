package net.manny.handybenches.init;

import net.manny.handybenches.HandyBenches;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), HandyBenches.MODID);

    public static final Supplier<CreativeModeTab> HANDY_TOOLS_TAB = CREATIVE_TABS.register("handy_benches_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.HANDY_CRAFTING_TABLE.get()))
                    .title(Component.translatable("creativetab.handybenches"))
                    .displayItems(
                            (itemDisplayParameters, output) -> {
                                output.accept(ModItems.HANDY_CRAFTING_TABLE.get());
                                output.accept(ModItems.HANDY_STONECUTTER.get());
                                output.accept(ModItems.HANDY_CARTOGRAPHY_TABLE.get());
                            }
                    )
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_TABS.register(eventBus);
    }
}