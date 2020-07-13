package io.github.plusls.DisableIntegratedServerOnlineMode;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DisableIntegratedServerOnlineModeMod implements ModInitializer {
	public static final String MODID = "disable_integrated_server_online_mode_mod";
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		System.out.println("DisableIntegratedServerOnlineMode.");
	}
}
