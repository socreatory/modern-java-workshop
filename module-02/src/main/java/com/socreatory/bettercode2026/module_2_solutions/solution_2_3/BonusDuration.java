package com.socreatory.bettercode2026.module_2_solutions.solution_2_3;

import java.util.List;

/// Lösung zum Bonus 2.3 – Dauer einer Dosierung
public class BonusDuration {

    static int durationMinutes(Dosage dosage) {
        return switch (dosage) {
            case Tablet _ -> 0;
            case Infusion(_, var duration) -> duration;
            case Combined(var first, var second) -> durationMinutes(first) + durationMinutes(second);
        };
    }

    static int totalMinutes(MedicationPlan plan) {
        return plan.medications().stream()
                .mapToInt(medication -> durationMinutes(medication.dosage()))
                .sum();
    }

    void main() {
        Dosage combined = new Combined(new Tablet(1, 0, 0), new Combined(new Infusion(10, 30), new Infusion(20, 45)));
        IO.println("Dauer Kombination: " + durationMinutes(combined) + " min");

        MedicationPlan plan = new MedicationPlan("Anna", List.of(
                new Medication(Drug.PARACETAMOL, new Infusion(50, 60)),
                new Medication(Drug.CAFFEINE, combined)));
        IO.println("Dauer Plan:        " + totalMinutes(plan) + " min");
    }
}
