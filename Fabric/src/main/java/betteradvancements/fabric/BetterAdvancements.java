package betteradvancements.fabric;

import betteradvancements.common.CriteriaOverlay;
import betteradvancements.common.CriteriaOverlayManager;
import betteradvancements.common.reference.Constants;
import betteradvancements.fabric.config.ConfigFileHandler;
import betteradvancements.fabric.handler.GuiOpenHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.resources.Identifier;

public class BetterAdvancements implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Constants.log.info("Loaded");
        ConfigFileHandler.readFromConfig();
        GuiOpenHandler.instance.registerEventHandlers();

        ClientTickEvents.END_CLIENT_TICK.register(_ -> CriteriaOverlayManager.tick());
        HudElementRegistry.attachElementBefore(VanillaHudElements.CHAT,
                Identifier.fromNamespaceAndPath(Constants.ID, "criteria_overlay"),
                CriteriaOverlay::extractRenderState);
    }
}
