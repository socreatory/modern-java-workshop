package com.socreatory.bettercode2026.module_2_solutions.solution_2_1;

import java.util.Arrays;
import java.util.List;

/// Lösung zum Bonus 2.1 – wirkstoffabhängige Bewertung und `case null, default`
public class BonusDrugSpecific {

    /// Bewertet die Dosierung abhängig vom Wirkstoff.
    static String assess(Drug drug, Dosage dosage) {
        return switch (dosage) {
            case Tablet tablet when drug == Drug.IBUPROFEN && tablet.perDay() > 3 ->
                    "HOCH: Ibuprofen: mehr als 3 Tabletten pro Tag";
            case Tablet tablet when drug == Drug.CAFFEINE && tablet.perDay() > 2 ->
                    "MITTEL: Koffein: mehr als 2 Tabletten pro Tag";
            case null -> "Keine Dosierung hinterlegt";
            case Dosage other -> DosageAssessor.assess(other);
        };
    }

    /// Beschreibt einen beliebigen Wert.
    static String describeAny(Object value) {
        return switch (value) {
            case Drug drug -> "Wirkstoff " + drug;
            case Integer mg when mg > 1000 -> mg + " mg: ungewöhnlich hoch";
            case Integer mg -> mg + " mg";
            case Dosage dosage -> DosageAssessor.assess(dosage);
            case null, default -> "unbekannt";
        };
    }

    // B3: `Object` hat unendlich viele Subtypen, der Compiler kann sie nicht aufzählen.
    //     Bei einem sealed Interface kennt er alle Subtypen: Dort ist `default` überflüssig
    //     und sogar schädlich, weil ein neuer Subtyp nicht mehr auffallen würde.

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
