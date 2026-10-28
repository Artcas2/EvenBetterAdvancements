package betteradvancements.neoforge.handler;

import betteradvancements.common.CriteriaOverlay;
import betteradvancements.common.reference.Constants;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

public class GuiLayersHandler {
    @SubscribeEvent
    public void onRegisterGuiLayers(RegisterGuiLayersEvent event) {
        event.registerBelow(VanillaGuiLayers.CHAT,
                Identifier.fromNamespaceAndPath(Constants.ID, "criteria_overlay"),
                CriteriaOverlay::extractRenderState);
    }
}
