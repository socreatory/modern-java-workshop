package com.socreatory.bettercode2026.module_2.exercise_2_2;

/// Wertet ein [Result] aus.
///
/// Beide Methoden enthalten noch ein `default`. Genau das ist der Stolperstein dieser Übung.
public class PlanReport {

    static String describe(Result<MedicationPlan> result) {
        return switch (result) {
            case Success<MedicationPlan> success ->
                    "OK: Plan für " + success.value().patientName() + " ist gültig";
            case Failure<MedicationPlan> failure ->
                    "FEHLER: " + failure.message();
            // TODO 1: Entferne das `default`. Der Compiler kennt alle Subtypen von `Result`,
            //         der `switch` bleibt auch ohne `default` vollständig.
            default -> "unbekanntes Ergebnis";
        };
    }

    /// Ein Exit-Code für Skripte: 0 = OK, 2 = Fehler.
    static int exitCode(Result<?> result) {
        return switch (result) {
            case Success<?> success -> 0;
            case Failure<?> failure -> 2;
            // TODO 1: Entferne auch hier das `default`.
            default -> 99;
        };
    }
}
