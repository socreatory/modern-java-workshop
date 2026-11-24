package com.socreatory.bettercode2026.module_2_solutions.solution_2_1;

import java.util.Arrays;
import java.util.List;

/// Lösung 2.1 – Dosierungen bewerten mit `switch`
///
/// Bewertet Testdaten mit dem [DosageAssessor], darunter Randfälle an den Grenzwerten und
/// ein `null`. Die Ausgabe ist identisch zur Ausgabe vor der Umstellung auf `switch`.
///
/// Alle Daten sind frei erfunden und keine medizinische Beratung.
public class Main {

    void main() {
        List<Dosage> dosages = Arrays.asList(
                new Tablet(1, 0, 1),
                new Tablet(2, 1, 1),
                new Tablet(3, 3, 0),
                new Tablet(2, 2, 3),
                new Infusion(50, 30),
                new Infusion(80, 60),
                new Infusion(100, 60),
                new Infusion(150, 30),
                null);

        for (Dosage dosage : dosages) {
            IO.println("%-42s -> %s".formatted(dosage, DosageAssessor.assess(dosage)));
        }
    }
}
