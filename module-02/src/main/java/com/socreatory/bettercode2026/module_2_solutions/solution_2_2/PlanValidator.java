package com.socreatory.bettercode2026.module_2_solutions.solution_2_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/// Validiert einen [MedicationPlan] und liefert ein [Result].
///
/// Regeln (frei erfunden, keine medizinische Beratung):
///
///   - Der Plan muss mindestens ein Medikament enthalten, sonst ein Fehler.
///   - Tabletten: mehr als 6 pro Tag ist ein Fehler, mehr als 3 ein Hinweis.
///   - Infusion: mehr als 100 ml/h ist ein Fehler, mehr als 50 ml/h ein Hinweis.
public class PlanValidator {

    static Result<MedicationPlan> validate(MedicationPlan plan) {
        if (plan.medications().isEmpty()) {
            return new Failure<>("Der Plan enthält keine Medikation");
        }

        List<String> errors = new ArrayList<>();
        List<String> warnings = new ArrayList<>();
        for (Medication medication : plan.medications()) {
            error(medication).ifPresent(errors::add);
            warning(medication).ifPresent(warnings::add);
        }

        if (!errors.isEmpty()) {
            return new Failure<>(String.join("; ", errors));
        }
        if (!warnings.isEmpty()) {
            return new Warning<>(plan, warnings);
        }
        return new Success<>(plan);
    }

    private static Optional<String> error(Medication medication) {
        return switch (medication.dosage()) {
            case Tablet tablet when tablet.perDay() > 6 ->
                    Optional.of(medication.drug() + ": mehr als 6 Tabletten pro Tag");
            case Infusion infusion when infusion.speed() > 100 ->
                    Optional.of(medication.drug() + ": Infusionsrate über 100 ml/h");
            case Tablet _, Infusion _ -> Optional.empty();
        };
    }

    private static Optional<String> warning(Medication medication) {
        return switch (medication.dosage()) {
            case Tablet tablet when tablet.perDay() > 3 ->
                    Optional.of(medication.drug() + ": mehr als 3 Tabletten pro Tag");
            case Infusion infusion when infusion.speed() > 50 ->
                    Optional.of(medication.drug() + ": erhöhte Infusionsrate über 50 ml/h");
            case Tablet _, Infusion _ -> Optional.empty();
        };
    }
}
