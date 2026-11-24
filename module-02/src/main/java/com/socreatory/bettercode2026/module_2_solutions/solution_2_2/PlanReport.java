package com.socreatory.bettercode2026.module_2_solutions.solution_2_2;

/// Wertet ein [Result] aus – ohne `default`.
///
/// Ein neuer Subtyp von `Result` führt hier zu einem Compile-Fehler, nicht zu einem
/// stillschweigend falschen Verhalten. Mit einem `default` wäre `Warning` einfach
/// verschluckt worden.
public class PlanReport {

    static String describe(Result<MedicationPlan> result) {
        return switch (result) {
            case Success<MedicationPlan> success ->
                    "OK: Plan für " + success.value().patientName() + " ist gültig";
            case Warning<MedicationPlan> warning ->
                    "WARNUNG: Plan für " + warning.value().patientName() + " mit Hinweisen: "
                            + String.join("; ", warning.warnings());
            case Failure<MedicationPlan> failure ->
                    "FEHLER: " + failure.message();
        };
    }

    /// Ein Exit-Code für Skripte: 0 = OK, 1 = Warnung, 2 = Fehler.
    static int exitCode(Result<?> result) {
        return switch (result) {
            case Success<?> success -> 0;
            case Warning<?> warning -> 1;
            case Failure<?> failure -> 2;
        };
    }
}
