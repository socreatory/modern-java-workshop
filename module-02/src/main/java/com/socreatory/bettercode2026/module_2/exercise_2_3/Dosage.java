package com.socreatory.bettercode2026.module_2.exercise_2_3;

import java.util.Objects;

/// Die Dosierung eines Medikaments: Tabletten, Infusion oder eine Kombination aus zwei Dosierungen.
///
/// Neu gegenüber Übung 2.2: [Combined] enthält selbst wieder eine [Dosage] – der Typ ist rekursiv.
public sealed interface Dosage permits Tablet, Infusion, Combined {
}

/// Tabletten pro Tageszeit (Stückzahl).
record Tablet(int morning, int midday, int evening) implements Dosage {

    public Tablet {
        if (morning < 0 || midday < 0 || evening < 0) {
            throw new IllegalArgumentException("Counts must not be negative");
        }
    }
}

/// Eine Infusion: Geschwindigkeit in ml/h, Dauer in Minuten.
record Infusion(int speed, int duration) implements Dosage {

    public Infusion {
        if (speed < 0 || duration < 0) {
            throw new IllegalArgumentException("Speed and duration must not be negative");
        }
    }
}

/// Zwei Dosierungen desselben Wirkstoffs am selben Tag, z. B. Tabletten und eine Infusion.
record Combined(Dosage first, Dosage second) implements Dosage {

    public Combined {
        Objects.requireNonNull(first);
        Objects.requireNonNull(second);
    }
}
