package dev.robertbian.ai;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlanCatalogToolsTest {
    private final PlanCatalogTools tools = new PlanCatalogTools();

    @Test void returnsDeterministicPrice() {
        assertEquals("Team: EUR 29/month, up to 10 seats. No discounts are offered.",
                tools.lookupPlan(" TEAM "));
    }

    @Test void refusesToInventUnknownPlans() {
        assertTrue(tools.lookupPlan("super-premium").startsWith("Unknown plan"));
    }

    @Test void rejectsMissingInput() {
        assertEquals("Plan name is required", tools.lookupPlan("  "));
    }
}
