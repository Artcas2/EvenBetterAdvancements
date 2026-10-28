package betteradvancements.common;

import betteradvancements.common.util.CriterionGrid;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2fStack;

public class CriteriaOverlay {
    public static float scale = 0f;

    public static void extractRenderState(@NotNull GuiGraphicsExtractor graphics, @NotNull DeltaTracker ignoredDeltaTracker) {
        if (scale == 0f) {
            return;
        }

        Minecraft minecraft = Minecraft.getInstance();
        Matrix3x2fStack matrices = graphics.pose();
        int i = 0;

        matrices.pushMatrix();
        matrices.scale(scale, scale);

        for (CriteriaOverlayManager.CriteriaOverlayInstance criteriaOverlayInstance : CriteriaOverlayManager.getVisibleCriteria()) {
            DisplayInfo display = criteriaOverlayInstance.getAdvancementHolder().value().display().orElse(null);

            if (display != null) {
                MutableComponent message = Component.literal("+ ").withColor(TextColor.GREEN);

                message.append(Component.literal(criteriaOverlayInstance.getCriterion() + " for ")
                        .withColor(CriterionGrid.colorWholeCriteriaText ? TextColor.GREEN : TextColor.WHITE)
                        .append(display.title()));
                graphics.text(minecraft.font, message, 10, 10 + i * minecraft.font.lineHeight, 0xFFFFFFFF, false);
                i++;
            }
        }

        matrices.popMatrix();
    }
}
