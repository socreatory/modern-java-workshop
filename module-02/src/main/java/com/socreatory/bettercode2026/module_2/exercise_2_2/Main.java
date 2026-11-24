package com.socreatory.bettercode2026.module_2.exercise_2_2;

import java.util.List;

/// Übung 2.2 – Exhaustiveness: `switch` über `Result<T>` ohne `default`
///
/// Validiert Medikationspläne mit dem [PlanValidator] und gibt das Ergebnis über den
/// [PlanReport] aus.
///
/// **Dein Job:**
///
///   1. Entferne in [PlanReport] beide `default`-Zweige (`TODO 1`). Es muss weiterhin
///      kompilieren, und die Ausgabe darf sich nicht ändern.
///
///   2. Neuer Subtyp: In `Warning.java` steht ein Warnung-Typ (Erfolg mit Hinweisen),
///      auskommentiert. Entferne die Kommentarzeichen (`TODO 3`) und ergänze `Warning` in
///      der `permits`-Klausel von `Result.java` (`TODO 2`).
///
///   3. Lies die Compile-Fehler: Der Compiler zeigt dir jeden `switch`, der noch nicht
///      alle Fälle abdeckt („follow the errors“). Ergänze in beiden Methoden von
///      [PlanReport] einen Zweig für `Warning`: `describe` gibt `"WARNUNG: ..."` mit den
///      Hinweisen aus, `exitCode` liefert 1.
///
///   4. Gib im [PlanValidator] ein `Warning` zurück (`TODO 4`). Danach zeigt die
///      Ausgabe für Ben und Fritz eine Warnung statt „OK“.
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
