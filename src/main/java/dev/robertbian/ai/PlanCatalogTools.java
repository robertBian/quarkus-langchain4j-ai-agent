package dev.robertbian.ai;

import dev.langchain4j.agent.tool.Tool;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Map;
import java.util.Locale;

/** Deterministic business logic remains outside the LLM. */
@ApplicationScoped
public class PlanCatalogTools {
    private static final Map<String, Plan> PLANS = Map.of(
            "starter", new Plan("Starter", 9, 2),
            "team", new Plan("Team", 29, 10),
            "enterprise", new Plan("Enterprise", 99, 100)
    );

    @Tool("Find an official subscription plan, its monthly EUR price, and seat limit; no discounts")
    public String lookupPlan(String planName) {
        if (planName == null || planName.isBlank()) return "Plan name is required";
        Plan plan = PLANS.get(planName.trim().toLowerCase(Locale.ROOT));
        if (plan == null) return "Unknown plan. Available: Starter, Team, Enterprise.";
        return "%s: EUR %d/month, up to %d seats. No discounts are offered."
                .formatted(plan.name(), plan.eurPerMonth(), plan.maxSeats());
    }

    record Plan(String name, int eurPerMonth, int maxSeats) {}
}
