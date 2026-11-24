package com.socreatory.bettercode2026.module_2.exercise_2_2;

import java.util.function.Function;

/// Bonus zu Übung 2.2 – Hilfsmethoden für `Result<T>`
///
/// Erledige zuerst die Übung inklusive `Warning`.
///
/// **Dein Job:**
///
///   - Implementiere `map` und `orElse` jeweils mit einem `switch` ohne `default`.
///     Die Methoden sind generisch: Ein `Result<T>` geht hinein, ein `Result<R>` kommt heraus.
public class BonusResultOps {

    /// Wendet `function` auf den Wert an. Fehler bleiben Fehler.
    static <T, R> Result<R> map(Result<T> result, Function<T, R> function) {
        // TODO B1: `switch` über `result`:
        //           - `Success<T> success` -> `new Success<>(function.apply(success.value()))`
        //           - `Failure<T> failure` -> `new Failure<>(failure.message())`
        //           - `Warning<T> warning` -> `new Warning<>(function.apply(warning.value()), warning.warnings())`
        //         Das Typargument von `Failure` ist neu: Warum kann man den Fehler nicht einfach
        //         unverändert zurückgeben?
        return new Failure<>("TODO B1: noch nicht umgesetzt");
    }

    /// Liefert den Wert oder `fallback`, wenn es ein Fehler war.
    static <T> T orElse(Result<T> result, T fallback) {
        // TODO B2: `switch` über `result`. Erfolg und Warnung liefern ihren Wert,
        //          ein Fehler den `fallback`.
        return fallback;
    }

    void main() {
        Result<MedicationPlan> ben = PlanValidator.validate(new MedicationPlan("Ben", java.util.List.of(
                new Medication(Drug.IBUPROFEN, new Tablet(2, 1, 2)))));
        Result<MedicationPlan> clara = PlanValidator.validate(new MedicationPlan("Clara", java.util.List.of(
                new Medication(Drug.CAFFEINE, new Infusion(150, 30)))));

        IO.println(map(ben, MedicationPlan::patientName));
        IO.println(map(clara, MedicationPlan::patientName));
        IO.println(orElse(map(ben, MedicationPlan::patientName), "unbekannt"));
        IO.println(orElse(map(clara, MedicationPlan::patientName), "unbekannt"));
    }
}
