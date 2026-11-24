package com.socreatory.bettercode2026.module_4_solutions.solution_4_1;

import java.util.ArrayList;
import java.util.List;

/// Bonus-Lösung 4.1 – `reversed()` ist eine View
///
/// - Schreibzugriffe über die View wirken auf das Original (write-through).
/// - Spätere Änderungen am Original sind in der View sichtbar.
/// - Die View einer unveränderlichen Liste ist selbst unveränderlich.
public class BonusReversedView {

    void main() {
        List<String> letters = new ArrayList<>(List.of("A", "B", "C"));

        letters.reversed().removeFirst();
        IO.println("Nach reversed().removeFirst(): " + letters);   // [A, B]

        var view = letters.reversed();
        letters.add("D");
        IO.println("View nach letters.add(\"D\"): " + view);        // [D, B, A]

        try {
            List.of("X", "Y").reversed().add("Z");
        } catch (UnsupportedOperationException e) {
            IO.println("List.of(...).reversed().add(...): " + e.getClass().getSimpleName());
        }

        // Eine Momentaufnahme erhält man nur über eine Kopie.
        var snapshot = new ArrayList<>(letters.reversed());
        letters.add("E");
        IO.println("Kopie bleibt unverändert: " + snapshot);        // [D, B, A]
    }
}
