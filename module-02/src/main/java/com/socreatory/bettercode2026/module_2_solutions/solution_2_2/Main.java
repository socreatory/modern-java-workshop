package com.socreatory.bettercode2026.module_2_solutions.solution_2_2;

import java.util.List;

/// Lösung 2.2 – Exhaustiveness: `switch` über `Result<T>` ohne `default`
///
/// Validiert Medikationspläne mit dem [PlanValidator] und gibt das Ergebnis über den
/// [PlanReport] aus. Nach dem Schritt „neuer Subtyp `Warning`“ zeigen Ben und Fritz
/// eine Warnung statt „OK“.
///
/// Alle Daten sind frei erfunden und keine medizinische Beratung.
public class Main {

    void main() {
        List<MedicationPlan> plans = List.of(
                new MedicationPlan("Anna", List.of(
                        new Medication(Drug.PARACETAMOL, new Tablet(1, 1, 1)),
                        new Medication(Drug.IBUPROFEN, new Tablet(1, 0, 1)))),
                new MedicationPlan("Ben", List.of(
                        new Medication(Drug.IBUPROFEN, new Tablet(2, 1, 2)))),
                new MedicationPlan("Clara", List.of(
                        new Medication(Drug.CAFFEINE, new Infusion(150, 30)))),
                new MedicationPlan("Dora", List.of()),
                new MedicationPlan("Emil", List.of(
                        new Medication(Drug.PARACETAMOL, new Tablet(3, 3, 3)))),
                new MedicationPlan("Fritz", List.of(
                        new Medication(Drug.PARACETAMOL, new Infusion(80, 60)))));

        for (MedicationPlan plan : plans) {
            Result<MedicationPlan> result = PlanValidator.validate(plan);
            IO.println("%-6s %s (Exit-Code %d)".formatted(
                    plan.patientName(), PlanReport.describe(result), PlanReport.exitCode(result)));
        }
    }
}
