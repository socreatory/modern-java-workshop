package com.socreatory.bettercode2026.module_4.exercise_4_1;

import java.util.ArrayList;
import java.util.List;

/// Bonus zu Übung 4.1 – `reversed()` ist eine View
///
/// **Dein Job:**
///
///   - Beantworte die Fragen in den `TODO`s, *bevor* du die Zeilen einkommentierst und
///     das Programm startest.
///
///   - Wo hilft eine View, wo ist eine Kopie (`new ArrayList<>(...)`) die bessere Wahl?
public class BonusReversedView {

    void main() {
        List<String> letters = new ArrayList<>(List.of("A", "B", "C"));

        // TODO B1: Was steht in `letters`, nachdem du über die View das erste Element entfernt hast?
        // letters.reversed().removeFirst();
        // IO.println("Nach reversed().removeFirst(): " + letters);

        // TODO B2: Die View sieht spätere Änderungen am Original. Stimmt das? Probiere es aus.
        // var view = letters.reversed();
        // letters.add("D");
        // IO.println("View nach letters.add(\"D\"): " + view);

        // TODO B3: `List.of(...)` ist unveränderlich. Was passiert hier? Fange die Ausnahme
        //          und gib ihren Typ aus.
        // List.of("X", "Y").reversed().add("Z");
    }
}
