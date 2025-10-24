package net.manny.handybenches;

import net.manny.handybenches.init.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(HandyBenches.MODID)
public class HandyBenches {

    public static final String MODID = "handybenches";
    public static final Logger LOGGER = LogManager.getLogger();

    public HandyBenches(IEventBus modEventBus) {
        LOGGER.info("Loading Handy Benches Mod...");

        ModItems.register(modEventBus);
        ModCreativeTabs.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        LOGGER.info("Mod Handy Benches successfully loaded!");
    }
}