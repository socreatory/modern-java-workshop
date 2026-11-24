package com.socreatory.bettercode2026.module_2_solutions.solution_2_3;

/// Lösung 2.3 – Wirkstoffmengen berechnen mit verschachtelten Record Patterns
///
/// Berechnet die Wirkstoffmenge pro Tag in mg:
///
///   - Tabletten: Stückzahl pro Tag × mg pro Tablette ([Drug#mgPerTablet()])
///   - Infusion: Geschwindigkeit (ml/h) × Dauer (min) ÷ 60 × mg pro ml ([Drug#mgPerMl()])
///   - Kombination: Summe der beiden Teile
///
/// Der `switch` kommt ohne `default` aus: Der Compiler prüft, dass die verschachtelten
/// Patterns alle Kombinationen aus Wirkstoff und Dosierung abdecken.
public class DoseCalculator {

    /// Die Wirkstoffmenge eines Medikaments pro Tag in mg.
    static double dailyMg(Medication medication) {
        return switch (medication) {
            case Medication(var drug, Tablet(var morning, var midday, var evening)) ->
                    (morning + midday + evening) * drug.mgPerTablet();
            case Medication(var drug, Infusion(var speed, var duration)) ->
                    speed * (duration / 60.0) * drug.mgPerMl();
            case Medication(var drug, Combined(var first, var second)) ->
                    dailyMg(new Medication(drug, first)) + dailyMg(new Medication(drug, second));
        };
    }

    /// Bewertet ein Medikament gegen die Grenzwerte (frei erfunden).
    static String assess(Medication medication) {
        return switch (medication) {
            case Medication(_, Infusion(var speed, _)) when speed > 100 ->
                    "Infusionsrate über 100 ml/h";
            case Medication(var drug, _) when dailyMg(medication) > drug.maxDailyMg() ->
                    "Tageshöchstmenge von %d mg überschritten".formatted(drug.maxDailyMg());
            case Medication _ -> "im Rahmen";
        };
    }

    /// Die gesamte Wirkstoffmenge aller Medikamente eines Plans pro Tag in mg.
    static double totalMg(MedicationPlan plan) {
        // Für List<Medication> gibt es kein Pattern: Hier hilft ein Stream.
        return plan.medications().stream()
                .mapToDouble(DoseCalculator::dailyMg)
                .sum();
    }
}
