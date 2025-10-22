package net.manny.handybenches.init;

import net.manny.handybenches.HandyBenches;
import net.manny.handybenches.common.menu.HandyCraftingMenu;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENU_TYPES =
            DeferredRegister.create(BuiltInRegistries.MENU.key(), HandyBenches.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<HandyCraftingMenu>> HANDY_CRAFTING_MENU =
            MENU_TYPES.register("handy_crafting_menu",
                    () -> new MenuType<>(HandyCraftingMenu::new , FeatureFlags.VANILLA_SET));



    public static void register(IEventBus eventBus){
        MENU_TYPES.register(eventBus);
    }

}
