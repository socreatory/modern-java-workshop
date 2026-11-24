package com.socreatory.bettercode2026.module_4_solutions.solution_4_2;

import java.util.HashSet;
import java.util.function.Function;
import java.util.stream.Gatherer;

/// Bonus-Lösung 4.2 – Eigene Gatherer
///
/// Beide Gatherer sind sequenziell und zustandsbehaftet: Der Initializer legt den Zustand an,
/// der Integrator entscheidet pro Element, ob es weitergereicht wird.
public final class BonusGatherers {

    private BonusGatherers() {}

    /// Zustand: der zuletzt behaltene Wert (`null`, solange noch keiner gesehen wurde).
    static final class LastKept {
        Double value;
    }

    /// Verwirft Messwerte, die mehr als `maxJump` vom letzten behaltenen Wert abweichen.
    public static Gatherer<SensorReading, ?, SensorReading> dropJumps(double maxJump) {
        return Gatherer.ofSequential(
                LastKept::new,
                (state, element, downstream) -> {
                    if (state.value == null || Math.abs(element.value() - state.value) <= maxJump) {
                        state.value = element.value();
                        return downstream.push(element);
                    }
                    return true;
                });
    }

    /// Lässt pro Schlüssel nur das erste Element durch.
    public static <T, K> Gatherer<T, ?, T> distinctBy(Function<? super T, ? extends K> key) {
        return Gatherer.ofSequential(
                HashSet<K>::new,
                (seen, element, downstream) ->
                        seen.add(key.apply(element))
                                ? downstream.push(element)
                                : true);
    }
}
