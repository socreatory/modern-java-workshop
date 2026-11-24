package com.socreatory.bettercode2026.module_4_demos.demo_4_2;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Gatherer;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

/// Demo 4.2 – Stream Gatherers im Überblick (Java 24)
///
/// Gatherers sind für Intermediate Operations, was Collectors für Terminal Operations sind.
/// Gezeigt werden die eingebauten Gatherers `windowFixed`, `windowSliding`, `scan`, `fold` und
/// `mapConcurrent` sowie ein eigener Gatherer `distinctBy`.
public class GatherersDemo {

    void main() throws Exception {
        IO.println("windowFixed(2):   " + Stream.of("A", "C", "F", "B", "S")
                .gather(Gatherers.windowFixed(2)).toList());

        IO.println("windowSliding(3): " + Stream.of(1, 2, 3, 4, 5)
                .gather(Gatherers.windowSliding(3)).toList());

        IO.println("scan (Summe):     " + Stream.of(1, 2, 3, 4)
                .gather(Gatherers.scan(() -> 0, Integer::sum)).toList());

        IO.println("fold (Text):      " + Stream.of(1, 2, 3, 4, 5)
                .gather(Gatherers.fold(() -> "", (s, n) -> s + n)).findFirst());

        IO.println("distinctBy(len):  " + Stream.of("a", "bb", "cc", "ddd", "e")
                .gather(distinctBy(String::length)).toList());

        IO.println("Verkettet:        " + Stream.of("a", "bb", "cc", "ddd", "e", "ff", "gggg")
                .gather(distinctBy(String::length))
                .gather(Gatherers.windowFixed(2))
                .toList());

        mapConcurrentDemo();
    }

    /// Acht Aufrufe à 200 ms mit höchstens vier gleichzeitig: ca. 400 ms statt 1600 ms.
    /// Die Reihenfolge der Ergebnisse bleibt erhalten.
    void mapConcurrentDemo() throws Exception {
        long start = System.nanoTime();
        List<String> results = Stream.of(1, 2, 3, 4, 5, 6, 7, 8)
                .gather(Gatherers.mapConcurrent(4, this::slowLookup))
                .toList();
        long millis = Duration.ofNanos(System.nanoTime() - start).toMillis();
        IO.println("mapConcurrent(4): " + results);
        IO.println("                  %d ms (sequenziell wären es ca. 1600 ms)".formatted(millis));
    }

    String slowLookup(int id) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(e);
        }
        return "#" + id + (Thread.currentThread().isVirtual() ? "(v)" : "");
    }

    static <T, K> Gatherer<T, ?, T> distinctBy(Function<? super T, ? extends K> key) {
        return Gatherer.ofSequential(
                HashSet<K>::new,
                (seen, element, downstream) ->
                        seen.add(key.apply(element))
                                ? downstream.push(element)
                                : true);
    }
}
