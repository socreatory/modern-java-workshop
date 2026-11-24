package com.socreatory.bettercode2026.module_2.exercise_2_1;

import java.util.Arrays;
import java.util.List;

/// Bonus zu Übung 2.1 – wirkstoffabhängige Bewertung und `case null, default`
///
/// **Dein Job:**
///
///   - Arbeite die `TODO`s ab. Die Methoden liefern zunächst nur einen Platzhalter.
///
///   - Beantworte vorher die Frage in `B3`, bevor du das Programm startest.
public class BonusDrugSpecific {

    /// Bewertet die Dosierung abhängig vom Wirkstoff.
    static String assess(Drug drug, Dosage dosage) {
        // TODO B1: Schreibe einen `switch` über `dosage` mit zwei Guards, die `drug` verwenden:
        //           - `case Tablet tablet when drug == Drug.IBUPROFEN && tablet.perDay() > 3`
        //             -> "HOCH: Ibuprofen: mehr als 3 Tabletten pro Tag"
        //           - `case Tablet tablet when drug == Drug.CAFFEINE && tablet.perDay() > 2`
        //             -> "MITTEL: Koffein: mehr als 2 Tabletten pro Tag"
        //         Alle anderen Fälle delegieren an `DosageAssessor.assess(dosage)`:
        //         ein allgemeines `case Dosage other` am Ende. Und `null`? Siehe `case null`.
        return "TODO B1";
    }

    /// Beschreibt einen beliebigen Wert.
    static String describeAny(Object value) {
        // TODO B2: Schreibe einen `switch` über `Object` mit diesen Cases (in dieser Reihenfolge sinnvoll!):
        //           - `case Drug drug` -> "Wirkstoff " + drug
        //           - `case Integer mg when mg > 1000` -> mg + " mg: ungewöhnlich hoch"
        //           - `case Integer mg` -> mg + " mg"
        //           - `case Dosage dosage` -> `DosageAssessor.assess(dosage)`
        //           - `case null, default` -> "unbekannt"
        // TODO B3: Warum braucht ein `switch` über `Object` zwingend einen `default`
        //          (oder ein anderes allgemeines Pattern), ein `switch` über `Dosage` aber nicht?
        return "TODO B2";
    }

    void main() {
        List<Dosage> dosages = Arrays.asList(new Tablet(2, 1, 1), new Tablet(1, 1, 1), new Infusion(120, 30), null);
        for (Drug drug : Drug.values()) {
            for (Dosage dosage : dosages) {
                IO.println("%-12s %-36s -> %s".formatted(drug, dosage, assess(drug, dosage)));
            }
        }

        IO.println();
        for (Object value : Arrays.asList(Drug.CAFFEINE, 500, 2000, new Tablet(1, 0, 0), "Aspirin", null)) {
            IO.println("%-30s -> %s".formatted(value, describeAny(value)));
        }
    }
}
