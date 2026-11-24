package com.socreatory.bettercode2026.module_2_solutions.solution_2_2;

import java.util.List;
import java.util.function.Function;

/// Lösung zum Bonus 2.2 – Hilfsmethoden für `Result<T>`
public class BonusResultOps {

    /// Wendet `function` auf den Wert an. Fehler bleiben Fehler.
    static <T, R> Result<R> map(Result<T> result, Function<T, R> function) {
        // Der Fehler muss neu gebaut werden: `Failure<T>` ist kein `Result<R>`.
        return switch (result) {
            case Success<T> success -> new Success<>(function.apply(success.value()));
            case Warning<T> warning -> new Warning<>(function.apply(warning.value()), warning.warnings());
            case Failure<T> failure -> new Failure<>(failure.message());
        };
    }

    /// Liefert den Wert oder `fallback`, wenn es ein Fehler war.
    static <T> T orElse(Result<T> result, T fallback) {
        return switch (result) {
            case Success<T> success -> success.value();
            case Warning<T> warning -> warning.value();
            case Failure<T> failure -> fallback;
        };
    }

    void main() {
        Result<MedicationPlan> ben = PlanValidator.validate(new MedicationPlan("Ben", List.of(
                new Medication(Drug.IBUPROFEN, new Tablet(2, 1, 2)))));
        Result<MedicationPlan> clara = PlanValidator.validate(new MedicationPlan("Clara", List.of(
                new Medication(Drug.CAFFEINE, new Infusion(150, 30)))));

        IO.println(map(ben, MedicationPlan::patientName));
        IO.println(map(clara, MedicationPlan::patientName));
        IO.println(orElse(map(ben, MedicationPlan::patientName), "unbekannt"));
        IO.println(orElse(map(clara, MedicationPlan::patientName), "unbekannt"));
    }
}
