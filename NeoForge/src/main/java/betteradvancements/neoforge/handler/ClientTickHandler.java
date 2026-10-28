package betteradvancements.neoforge.handler;

import betteradvancements.common.CriteriaOverlayManager;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;

public class ClientTickHandler {
    @SubscribeEvent
    public void onClientTick(ClientTickEvent.Post event) {
        CriteriaOverlayManager.tick();
    }
}
