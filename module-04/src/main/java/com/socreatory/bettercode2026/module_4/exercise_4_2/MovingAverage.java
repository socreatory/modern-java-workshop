package com.socreatory.bettercode2026.module_4.exercise_4_2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/// Übung 4.2 – Gleitender Durchschnitt mit `Gatherers.windowSliding` (Java 24)
///
/// Der gleitende Durchschnitt glättet Messwerte: Jeder Wert ist der Durchschnitt der letzten
/// `size` Messungen. Bei 5 Werten und `size = 3` entstehen 3 Durchschnitte (Fenster 1–3, 2–4, 3–5).
///
/// **Dein Job:**
///
///   - `averageOld(...)` ist die Lösung vor Java 24: Der Stream wird in eine Liste gesammelt
///     und mit einer Index-Schleife durchlaufen. Lies sie und lass sie laufen.
///
///   - Implementiere `average(...)` mit `Gatherers.windowSliding(size)` und `map(...)`.
///     Das Ergebnis muss mit `averageOld(...)` übereinstimmen. `Main` prüft das.
///
///   - Der Stream darf dabei lazy bleiben: Es gibt kein `toList()` vor dem Gatherer.
public final class MovingAverage {

    private MovingAverage() {}

    public static List<Double> averageOld(Stream<SensorReading> readings, int size) {
        List<SensorReading> all = readings.toList();
        List<Double> result = new ArrayList<>();
        for (int start = 0; start + size <= all.size(); start++) {
            double sum = 0;
            for (int i = start; i < start + size; i++) {
                sum += all.get(i).value();
            }
            result.add(sum / size);
        }
        return result;
    }

    public static List<Double> average(Stream<SensorReading> readings, int size) {
        // TODO 1: Mit `.gather(Gatherers.windowSliding(size))` entstehen Fenster vom Typ
        //         `List<SensorReading>`. (Import: java.util.stream.Gatherers)

        // TODO 2: Bilde mit `.map(...)` jedes Fenster auf seinen Durchschnitt ab:
        //         `window.stream().mapToDouble(SensorReading::value).average().orElseThrow()`

        // TODO 3: Mit `.toList()` abschließen und das Ergebnis zurückgeben.
        return List.of();
    }
}
