package com.socreatory.bettercode2026.module_4.exercise_4_2;

import java.util.stream.Gatherer;

/// Bonus zu Übung 4.2 – Ein eigener Gatherer
///
/// Ein Gatherer besteht aus einem `initializer` (Zustand anlegen), einem `integrator`
/// (pro Element aufgerufen) und optional einem `combiner` und `finisher`.
/// `Gatherer.ofSequential(...)` ist die einfache Variante für Zustand ohne Parallelität.
///
/// **Dein Job:**
///
///   - Implementiere `dropJumps(maxJump)`: Ein Messwert wird verworfen, wenn er sich um mehr als
///     `maxJump` vom *letzten behaltenen* Wert unterscheidet. Der erste Wert wird immer behalten.
///     Für die Beispieldaten in `Main` bleibt dann der Ausreißer 95.0 draußen.
///
///   - Der Integrator bekommt `(state, element, downstream)` und gibt `true` zurück, solange der
///     Stream weiterlaufen soll. Mit `downstream.push(element)` reichst du ein Element weiter.
///
///   - Zusatz: Schreibe `distinctBy(Function<T, K>)` aus den Folien nach.
///
///   - Weitere Ideen: `Gatherers.windowFixed(n)` für Stapel, `Gatherers.scan(...)` für eine
///     laufende Summe.
public final class BonusGatherers {

    private BonusGatherers() {}

    /// Zustand: der zuletzt behaltene Wert (`null`, solange noch keiner gesehen wurde).
    static final class LastKept {
        Double value;
    }

    public static Gatherer<SensorReading, ?, SensorReading> dropJumps(double maxJump) {
        // TODO B1: Vergleiche `element.value()` mit `state.value`. Ist `state.value` null oder
        //          der Abstand höchstens `maxJump`, speichere den Wert in `state.value` und
        //          reiche das Element mit `downstream.push(element)` weiter. Sonst: überspringen.
        return Gatherer.ofSequential(
                LastKept::new,
                (state, element, downstream) -> downstream.push(element));
    }
}
