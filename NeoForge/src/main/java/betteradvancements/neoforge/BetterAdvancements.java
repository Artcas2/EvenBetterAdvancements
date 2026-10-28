package betteradvancements.neoforge;

import betteradvancements.common.reference.Constants;
import betteradvancements.neoforge.config.Config;
import betteradvancements.neoforge.handler.ClientTickHandler;
import betteradvancements.neoforge.handler.GuiLayersHandler;
import betteradvancements.neoforge.handler.GuiOpenHandler;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;

@Mod(value = Constants.ID, dist = Dist.CLIENT)
public class BetterAdvancements {
    public BetterAdvancements(IEventBus modBus, ModContainer container) {
        container.registerConfig(ModConfig.Type.CLIENT, Config.CLIENT);
        NeoForge.EVENT_BUS.register(GuiOpenHandler.instance);
        NeoForge.EVENT_BUS.register(new ClientTickHandler());
        modBus.register(Config.instance);
        modBus.register(new GuiLayersHandler());
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
}
