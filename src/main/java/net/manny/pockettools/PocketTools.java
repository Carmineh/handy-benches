package net.manny.pockettools;

import net.manny.pockettools.init.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(PocketTools.MODID)
public class PocketTools {

    public static final String MODID = "pockettools";
    public static final Logger LOGGER = LogManager.getLogger();

    public PocketTools(IEventBus modEventBus) {
        LOGGER.info("Loading Pocket Tools Mod...");

        ModItems.register(modEventBus);
        ModCreativeTabs.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        LOGGER.info("Mod Pocket Tools successfully loaded!");
    }
}