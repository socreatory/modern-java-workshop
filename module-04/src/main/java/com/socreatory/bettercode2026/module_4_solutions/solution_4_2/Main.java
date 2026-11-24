package com.socreatory.bettercode2026.module_4_solutions.solution_4_2;

import java.util.List;
import java.util.stream.Stream;

/// Lösung 4.2 – Gleitender Durchschnitt mit `Gatherers.windowSliding` (Java 24)
///
/// Berechnet den gleitenden Durchschnitt (Fenstergröße 3) einmal mit der Index-Schleife und
/// einmal mit der Gatherer-Lösung und vergleicht beide.
public class Main {

    void main() {
        IO.println("Altlösung:     " + format(MovingAverage.averageOld(readings(), 3)));

        List<Double> viaGatherer = MovingAverage.average(readings(), 3);
        IO.println("windowSliding: " + format(viaGatherer));
        IO.println("Gleich: " + viaGatherer.equals(MovingAverage.averageOld(readings(), 3)));

        IO.println();
        IO.println("Bonus – ohne Ausreißer (Sprung > 20):");
        List<SensorReading> cleaned = readings().gather(BonusGatherers.dropJumps(20)).toList();
        IO.println("  " + cleaned.stream().map(SensorReading::value).toList());
    }

    static Stream<SensorReading> readings() {
        double[] values = {20.0, 21.0, 22.5, 95.0, 23.0, 24.0, 23.5, 25.0};
        return Stream.iterate(0, i -> i < values.length, i -> i + 1)
                .map(i -> new SensorReading(i, values[i]));
    }

    static String format(List<Double> values) {
        return values.stream().map("%.2f"::formatted).toList().toString();
    }
}
