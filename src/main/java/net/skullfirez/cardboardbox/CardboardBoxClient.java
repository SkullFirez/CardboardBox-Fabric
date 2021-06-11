package net.skullfirez.cardboardbox;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.skullfirez.cardboardbox.blocks.box.BoxScreen;
import net.skullfirez.cardboardbox.setup.Registration;

@Environment(EnvType.CLIENT)
public class CardboardBoxClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(Registration.CARDBOARD_BOX_SCREEN_HANDLER, BoxScreen::new);
    }
}
