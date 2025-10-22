package net.manny.pockettools.init;

import net.manny.pockettools.PocketTools;
import net.manny.pockettools.common.menu.PocketCraftingMenu;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENU_TYPES =
            DeferredRegister.create(BuiltInRegistries.MENU.key(), PocketTools.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<PocketCraftingMenu>> POCKET_CRAFTING_MENU =
            MENU_TYPES.register("pocket_crafting_menu",
                    () -> new MenuType<>(PocketCraftingMenu::new , FeatureFlags.VANILLA_SET));



    public static void register(IEventBus eventBus){
        MENU_TYPES.register(eventBus);
    }

}
