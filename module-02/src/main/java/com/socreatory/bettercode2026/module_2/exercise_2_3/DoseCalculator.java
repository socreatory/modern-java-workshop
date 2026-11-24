package com.socreatory.bettercode2026.module_2.exercise_2_3;

/// Übung 2.3 – Wirkstoffmengen berechnen: verschachtelte Record Patterns und `when`
///
/// Berechnet die Wirkstoffmenge pro Tag in mg:
///
///   - Tabletten: Stückzahl pro Tag × mg pro Tablette ([Drug#mgPerTablet()])
///   - Infusion: Geschwindigkeit (ml/h) × Dauer (min) ÷ 60 × mg pro ml ([Drug#mgPerMl()])
///   - Kombination: Summe der beiden Teile
///
/// Zu Beginn ist nur der Fall „Tabletten“ umgesetzt, dahinter steht ein `default`.
///
/// **Dein Job:**
///
///   - Arbeite die `TODO`s von oben nach unten ab. Zerlege die Medikation direkt
///     im Pattern: `case Medication(var drug, Tablet(var morning, var midday, var evening))`.
///
///   - Lass [Main] zwischendurch laufen und vergleiche die Zahlen mit dem Taschenrechner.
public class DoseCalculator {

    /// Die Wirkstoffmenge eines Medikaments pro Tag in mg.
    static double dailyMg(Medication medication) {
        return switch (medication) {
            case Medication(var drug, Tablet(var morning, var midday, var evening)) ->
                    (morning + midday + evening) * drug.mgPerTablet();
            // TODO 1: Ergänze `Infusion(var speed, var duration)`.
            //         Rate und Dauer haben verschiedene Einheiten: ml/h und min – rechne in Stunden um.
            // TODO 2: Ergänze `Combined(var first, var second)`. Der Typ ist rekursiv: Rufe
            //         `dailyMg(new Medication(drug, first))` und mit `second` auf und addiere.
            // TODO 3: Entferne das `default`. Dann prüft der Compiler, ob die Patterns
            //         alle Kombinationen aus Wirkstoff und Dosierung abdecken.
            default -> 0.0;
        };
    }

    /// Bewertet ein Medikament gegen die Grenzwerte (frei erfunden).
    static String assess(Medication medication) {
        // TODO 4: Schreibe einen `switch` mit Guards und verschachtelten Patterns:
        //          - Infusion mit mehr als 100 ml/h: "Infusionsrate über 100 ml/h"
        //            `case Medication(_, Infusion(var speed, _)) when speed > 100`
        //          - Tageswirkstoffmenge über `drug.maxDailyMg()`: "Tageshöchstmenge von N mg überschritten"
        //            `case Medication(var drug, _) when dailyMg(medication) > drug.maxDailyMg()`
        //          - sonst: "im Rahmen" (`case Medication _`)
        //         Die Reihenfolge ist wichtig: Der allgemeine Fall steht zuletzt.
        return "TODO 4";
    }

    /// Die gesamte Wirkstoffmenge aller Medikamente eines Plans pro Tag in mg.
    static double totalMg(MedicationPlan plan) {
        // Für List<Medication> gibt es kein Pattern: Hier hilft ein Stream.
        return plan.medications().stream()
                .mapToDouble(DoseCalculator::dailyMg)
                .sum();
    }
}
