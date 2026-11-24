package com.socreatory.bettercode2026.module_2_solutions.solution_2_1;

/// Lösung 2.1 – Dosierungen bewerten mit `switch`
///
/// Die `if`/`instanceof`-Kette ist durch einen `switch` mit Type Patterns, `when` und
/// `case null` ersetzt. Die Cases werden von oben nach unten geprüft: Die Guards stehen
/// jeweils *vor* dem allgemeinen Fall, und zwar der strengere Grenzwert zuerst.
public class DosageAssessor {

    static String assess(Dosage dosage) {
        return switch (dosage) {
            case null -> "Keine Dosierung hinterlegt";
            case Tablet tablet when tablet.perDay() > 6 -> "HOCH: mehr als 6 Tabletten pro Tag";
            case Tablet tablet when tablet.perDay() > 3 -> "MITTEL: mehr als 3 Tabletten pro Tag";
            case Tablet tablet -> "NIEDRIG: unauffällig";
            case Infusion infusion when infusion.speed() > 100 -> "HOCH: Infusionsrate über 100 ml/h";
            case Infusion infusion when infusion.speed() > 50 -> "MITTEL: erhöhte Infusionsrate über 50 ml/h";
            case Infusion infusion -> "NIEDRIG: unauffällig";
        };
    }

    // Dominanz: Steht der allgemeine Fall zuerst, ist der Guard dahinter nie erreichbar.
    // Der Compiler meldet das als Fehler:
    //
    //     return switch (dosage) {
    //         case Tablet tablet -> "NIEDRIG: unauffällig";
    //         case Tablet tablet when tablet.perDay() > 6 -> "HOCH: ...";
    //         // error: this case label is dominated by a preceding case label
    //         ...
    //     };
    //
    // Vertauschte Guards dagegen (`> 3` vor `> 6`) sind für den Compiler korrekt und
    // liefern still falsche Ergebnisse: Tablet(2, 2, 3) wäre dann nur MITTEL.
}
