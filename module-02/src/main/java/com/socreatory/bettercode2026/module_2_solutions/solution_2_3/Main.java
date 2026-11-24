package com.socreatory.bettercode2026.module_2_solutions.solution_2_3;

import java.util.List;
import java.util.Locale;

/// Lösung 2.3 – Wirkstoffmengen berechnen mit verschachtelten Record Patterns
///
/// Berechnet Wirkstoffmengen für Testdaten mit dem [DoseCalculator].
///
/// Alle Daten sind frei erfunden und keine medizinische Beratung.
public class Main {

    void main() {
        show("Paracetamol 1-1-1 Tabletten", new Medication(Drug.PARACETAMOL, new Tablet(1, 1, 1)));
        show("Ibuprofen 2-2-2 Tabletten", new Medication(Drug.IBUPROFEN, new Tablet(2, 2, 2)));
        show("Paracetamol Infusion 50 ml/h, 60 min", new Medication(Drug.PARACETAMOL, new Infusion(50, 60)));
        show("Paracetamol Infusion 120 ml/h, 30 min", new Medication(Drug.PARACETAMOL, new Infusion(120, 30)));
        show("Koffein 1-0-0 Tabletten + Infusion 10 ml/h, 30 min", new Medication(Drug.CAFFEINE,
                new Combined(new Tablet(1, 0, 0), new Infusion(10, 30))));
        show("Ibuprofen 1-1-1 Tabletten + Infusion 20 ml/h, 60 min", new Medication(Drug.IBUPROFEN,
                new Combined(new Combined(new Tablet(1, 0, 1), new Tablet(0, 1, 0)), new Infusion(20, 60))));

        MedicationPlan anna = new MedicationPlan("Anna", List.of(
                new Medication(Drug.PARACETAMOL, new Tablet(1, 1, 1)),
                new Medication(Drug.CAFFEINE, new Combined(new Tablet(1, 0, 0), new Infusion(10, 30)))));
        IO.println();
        IO.println(String.format(Locale.GERMANY, "Plan %s: %.1f mg Wirkstoff pro Tag",
                anna.patientName(), DoseCalculator.totalMg(anna)));
    }

    void show(String label, Medication medication) {
        IO.println(String.format(Locale.GERMANY, "%-55s %8.1f mg  %s",
                label, DoseCalculator.dailyMg(medication), DoseCalculator.assess(medication)));
    }
}
