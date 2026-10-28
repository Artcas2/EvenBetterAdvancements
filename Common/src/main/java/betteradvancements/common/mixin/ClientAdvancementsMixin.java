package betteradvancements.common.mixin;

import betteradvancements.common.CriteriaOverlayManager;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.advancements.AdvancementNode;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.client.multiplayer.ClientAdvancements;
import net.minecraft.network.protocol.game.ClientboundUpdateAdvancementsPacket;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

@Mixin(ClientAdvancements.class)
public class ClientAdvancementsMixin {
    @Unique
    private final Map<Identifier, String> evenBetterAdvancements$obtainedCriteria = new HashMap<>();

    @Inject(method = "update", at = @At("HEAD"))
    private void onAdvancementUpdateHead(ClientboundUpdateAdvancementsPacket packet, CallbackInfo ci) {
        if (packet.shouldReset()) {
            evenBetterAdvancements$obtainedCriteria.clear();
        }

        packet.removed().forEach(evenBetterAdvancements$obtainedCriteria::remove);
    }

    @Inject(method = "update", at = @At(value = "INVOKE", target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"))
    private void onAdvancementProgress(ClientboundUpdateAdvancementsPacket packet, CallbackInfo ci, @Local(name = "node") AdvancementNode node, @Local(name = "progress") AdvancementProgress progress) {
        for (String criterion : progress.getRemainingCriteria()) {
            evenBetterAdvancements$obtainedCriteria.remove(node.holder().id(), criterion);
        }

        for (String criterion : progress.getCompletedCriteria()) {
            if (!packet.shouldReset() && !evenBetterAdvancements$obtainedCriteria.containsValue(criterion)) {
                CriteriaOverlayManager.showCriterion(node.holder(), criterion);
            }

            evenBetterAdvancements$obtainedCriteria.put(node.holder().id(), criterion);
        }
    }
}
