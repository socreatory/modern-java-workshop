package com.socreatory.bettercode2026.module_2_solutions.solution_2_2;

/// Die Dosierung eines Medikaments: entweder Tabletten oder eine Infusion (Summe, „oder“).
///
/// Das Modell kennst du aus Übung 2.1.
public sealed interface Dosage permits Tablet, Infusion {
}

/// Tabletten pro Tageszeit (Stückzahl).
record Tablet(int morning, int midday, int evening) implements Dosage {

    public Tablet {
        if (morning < 0 || midday < 0 || evening < 0) {
            throw new IllegalArgumentException("Counts must not be negative");
        }
    }

    /// Die Tabletten pro Tag insgesamt.
    int perDay() {
        return morning + midday + evening;
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
