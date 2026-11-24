package com.socreatory.bettercode2026.module_2.exercise_2_3;

/// Bonus zu Übung 2.3 – Dauer einer Dosierung
///
/// **Dein Job:**
///
///   - Berechne die Dauer in Minuten: Tabletten dauern 0 min, eine Infusion ihre `duration`,
///     bei einer Kombination zählt die Summe (die Teile laufen nacheinander).
///
///   - Nutze `_` für alle Komponenten, die du nicht brauchst.
public class BonusDuration {

    static int durationMinutes(Dosage dosage) {
        // TODO B1: `switch` über `dosage` mit `Tablet _`, `Infusion(_, var duration)` und
        //          `Combined(var first, var second)` – rekursiv, ohne `default`.
        return 0;
    }

    static int totalMinutes(MedicationPlan plan) {
        // TODO B2: Summiere `durationMinutes` über alle Medikamente des Plans (Stream).
        //          Auf die Dosierung kommst du über `medication.dosage()`.
        return 0;
    }

    void main() {
        Dosage combined = new Combined(new Tablet(1, 0, 0), new Combined(new Infusion(10, 30), new Infusion(20, 45)));
        IO.println("Dauer Kombination: " + durationMinutes(combined) + " min");

        MedicationPlan plan = new MedicationPlan("Anna", java.util.List.of(
                new Medication(Drug.PARACETAMOL, new Infusion(50, 60)),
                new Medication(Drug.CAFFEINE, combined)));
        IO.println("Dauer Plan:        " + totalMinutes(plan) + " min");
    }
}
