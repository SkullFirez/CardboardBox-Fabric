package net.skullfirez.cardboardbox;

import net.fabricmc.api.ModInitializer;
import net.skullfirez.cardboardbox.setup.EventHandler;
import net.skullfirez.cardboardbox.setup.Registration;

public class CardboardBox implements ModInitializer {
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		Registration.init();
		EventHandler.init();

		System.out.println("CardboardBox Initialized!");
	}
}
