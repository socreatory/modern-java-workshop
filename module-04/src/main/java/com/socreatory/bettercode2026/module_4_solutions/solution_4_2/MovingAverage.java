package com.socreatory.bettercode2026.module_4_solutions.solution_4_2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

/// Lösung 4.2 – Gleitender Durchschnitt mit `Gatherers.windowSliding` (Java 24)
///
/// `average(...)` ersetzt die Index-Schleife durch drei Zeilen und bleibt lazy:
/// Der Stream wird nicht vorab in eine Liste gesammelt.
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
        return readings
                .gather(Gatherers.windowSliding(size))
                .map(window -> window.stream().mapToDouble(SensorReading::value).average().orElseThrow())
                .toList();
    }
}
