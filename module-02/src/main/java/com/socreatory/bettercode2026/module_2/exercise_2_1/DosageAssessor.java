package com.socreatory.bettercode2026.module_2.exercise_2_1;

/// Übung 2.1 – Dosierungen bewerten: Type Patterns, `when`, `case null` und Dominanz
///
/// Die Methode [#assess(Dosage)] ordnet eine [Dosage] einer Risikostufe zu (NIEDRIG, MITTEL, HOCH).
/// Sie ist im Stil vor Java 21 geschrieben: mit einer `if`/`instanceof`-Kette und einem
/// `null`-Check am Anfang.
///
/// Grenzwerte (frei erfunden, keine medizinische Beratung):
///
///   - Tabletten: mehr als 6 pro Tag = HOCH, mehr als 3 = MITTEL, sonst NIEDRIG
///   - Infusion: mehr als 100 ml/h = HOCH, mehr als 50 ml/h = MITTEL, sonst NIEDRIG
///
/// **Dein Job:**
///
///   - Arbeite die `TODO`s von oben nach unten ab und ersetze die Kette durch einen `switch`
///     mit Type Patterns, `when` und `case null`.
///
///   - Lass [Main] vor und nach der Umstellung laufen: Die Ausgabe muss identisch bleiben.
public class DosageAssessor {

    static String assess(Dosage dosage) {
        // TODO 1: Ersetze die ganze Kette durch `return switch (dosage) { ... };`.
        //         Ein Type Pattern bindet die Variable gleich mit: `case Tablet tablet -> ...`.
        //         Der Compiler prüft die Vollständigkeit, ein `default` ist nicht nötig.

        // TODO 2: Der `null`-Check am Anfang wird zu `case null -> ...`.
        if (dosage == null) {
            return "Keine Dosierung hinterlegt";
        }

        // TODO 3: Die verschachtelten `if`s werden zu Guards: `case Tablet tablet when tablet.perDay() > 6 -> ...`.
        //         Die Cases werden von oben nach unten geprüft. Spezielle Fälle mit Guard kommen
        //         *vor* den allgemeinen. Achtung: `> 3` vor `> 6` kompiliert, liefert aber
        //         falsche Ergebnisse – sieh dir die Ausgabe für Tablet(2, 2, 3) an.
        if (dosage instanceof Tablet) {
            Tablet tablet = (Tablet) dosage;
            if (tablet.perDay() > 6) {
                return "HOCH: mehr als 6 Tabletten pro Tag";
            }
            if (tablet.perDay() > 3) {
                return "MITTEL: mehr als 3 Tabletten pro Tag";
            }
            return "NIEDRIG: unauffällig";
        }
        if (dosage instanceof Infusion) {
            Infusion infusion = (Infusion) dosage;
            if (infusion.speed() > 100) {
                return "HOCH: Infusionsrate über 100 ml/h";
            }
            if (infusion.speed() > 50) {
                return "MITTEL: erhöhte Infusionsrate über 50 ml/h";
            }
            return "NIEDRIG: unauffällig";
        }
        throw new IllegalStateException("Unbekannte Dosierung: " + dosage);

        // TODO 4 (Dominanz): Stelle in deinem `switch` testweise ein allgemeines `case Tablet tablet`
        //         *vor* einen Guard. Lies die Fehlermeldung des Compilers und stelle die
        //         Reihenfolge wieder her.
    }
}
