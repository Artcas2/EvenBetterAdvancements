package betteradvancements.common;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.util.Util;

import java.util.ArrayList;
import java.util.List;

public class CriteriaOverlayManager {
    private static final List<CriteriaOverlayInstance> visibleCriteria = new ArrayList<>();

    public static void showCriterion(AdvancementHolder advancementHolder, String criterion) {
        visibleCriteria.add(new CriteriaOverlayInstance(advancementHolder, criterion));
    }

    public static List<CriteriaOverlayInstance> getVisibleCriteria() {
        return visibleCriteria;
    }

    public static void tick() {
        for (CriteriaOverlayInstance visibleCriterion : new ArrayList<>(visibleCriteria)) {
            visibleCriterion.tick();
        }
    }

    public static class CriteriaOverlayInstance {
        private final AdvancementHolder advancementHolder;
        private final String criterion;
        private long becameFullyVisibleAt;

        public CriteriaOverlayInstance(AdvancementHolder advancementHolder, String criterion) {
            this.advancementHolder = advancementHolder;
            this.criterion = criterion;
        }

        public AdvancementHolder getAdvancementHolder() {
            return advancementHolder;
        }

        public String getCriterion() {
            return criterion;
        }

        private void tick() {
            if (becameFullyVisibleAt == 0) {
                becameFullyVisibleAt = Util.getMillis();
            } else if (Util.getMillis() - becameFullyVisibleAt > 5000) {
                CriteriaOverlayManager.visibleCriteria.remove(this);
                becameFullyVisibleAt = 0;
            }
        }
    }
}
