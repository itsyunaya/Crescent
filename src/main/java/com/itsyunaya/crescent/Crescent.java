package com.itsyunaya.crescent;

import com.itsyunaya.crescent.commands.CommandRegister;
import com.itsyunaya.crescent.config.CrescentConfigWrap;
import com.itsyunaya.crescent.util.DataBuilder;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Crescent implements ModInitializer {

    public static final String MOD_ID = "crescent";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final CrescentConfigWrap CONFIG = CrescentConfigWrap.createAndLoad();

    @Override
    public void onInitialize() {
        CommandRegister.register();
        DataBuilder.init();
        LOGGER.info("[{}] Initialized!", MOD_ID);
    }
}
