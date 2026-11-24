package com.socreatory.bettercode2026.module_2_solutions.solution_2_3;

/// Der Wirkstoff eines Medikaments mit Stärke und Tageshöchstmenge.
///
/// Alle Daten in diesen Übungen sind frei erfunden und keine medizinische Beratung.
public enum Drug {
    PARACETAMOL(500, 10, 4000),
    IBUPROFEN(400, 4, 1200),
    CAFFEINE(50, 1, 400);

    private final int mgPerTablet;
    private final int mgPerMl;
    private final int maxDailyMg;

    Drug(int mgPerTablet, int mgPerMl, int maxDailyMg) {
        this.mgPerTablet = mgPerTablet;
        this.mgPerMl = mgPerMl;
        this.maxDailyMg = maxDailyMg;
    }

    /// Wirkstoffmenge pro Tablette in mg.
    public int mgPerTablet() {
        return mgPerTablet;
    }

    /// Wirkstoffkonzentration in der Infusionslösung in mg/ml.
    public int mgPerMl() {
        return mgPerMl;
    }

    /// Die Tageshöchstmenge in mg.
    public int maxDailyMg() {
        return maxDailyMg;
    }
}
