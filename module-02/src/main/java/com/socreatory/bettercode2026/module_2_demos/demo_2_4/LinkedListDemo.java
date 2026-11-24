package com.socreatory.bettercode2026.module_2_demos.demo_2_4;

import java.util.Objects;

/// Demo 2.4 – Algebraische Datentypen: eine verkettete Liste aus Records und Sealed Types
///
/// Eine `LinkedList` ist entweder leer (`Empty`) **oder** ein `Element` aus einem Wert **und** einer
/// weiteren `LinkedList`:
///
///   - Summentyp („oder“): `sealed interface` mit `permits`
///   - Produkttyp („und“): `record`
///
/// Operationen sind `switch`-Ausdrücke mit Record Patterns und `when`. Der Compiler prüft die
/// Vollständigkeit, ein `default` ist nicht nötig.
///
/// Zuletzt dieselbe Operation mit unbenannten Mustern (`_`, Java 22).
public class LinkedListDemo {

    sealed interface LinkedList<T> permits LinkedList.Element, LinkedList.Empty {

        record Element<T>(T value, LinkedList<T> next) implements LinkedList<T> {
        }

        final class Empty<T> implements LinkedList<T> {
        }

        @SafeVarargs
        static <T> LinkedList<T> of(T... values) {
            LinkedList<T> current = new Empty<>();
            for (int i = values.length - 1; i >= 0; i--) {
                current = new Element<>(values[i], current);
            }
            return current;
        }
    }

    void main() {
        LinkedList<Integer> list = LinkedList.of(1, 2, 3);

        IO.println("== contains ==");
        IO.println("contains(5): " + contains(5, list));
        IO.println("contains(1): " + contains(1, list));

        IO.println();
        IO.println("== containsUnnamed (mit _) ==");
        IO.println("contains(5): " + containsUnnamed(5, list));
        IO.println("contains(1): " + containsUnnamed(1, list));

        IO.println();
        IO.println("== weitere Operationen ==");
        IO.println("size: " + size(list));
        IO.println("sum:  " + sum(list));
        IO.println("leere Liste, size: " + size(LinkedList.of()));
    }

    /// Type Pattern, Record Pattern und `when`-Guard: ohne `default`, der Compiler prüft die Vollständigkeit.
    static <T> boolean contains(T value, LinkedList<T> list) {
        return switch (list) {
            case LinkedList.Empty<T> empty -> false;
            case LinkedList.Element<T>(T v, var tail) when Objects.equals(v, value) -> true;
            case LinkedList.Element<T>(T v, var tail) -> contains(value, tail);
        };
    }

    /// Dieselbe Logik mit unbenannten Mustern: Nicht benötigte Komponenten heißen `_`.
    static <T> boolean containsUnnamed(T value, LinkedList<T> list) {
        return switch (list) {
            case LinkedList.Empty<T> _ -> false;
            case LinkedList.Element<T>(T v, _) when Objects.equals(v, value) -> true;
            case LinkedList.Element<T>(_, var tail) -> containsUnnamed(value, tail);
        };
    }

    static <T> int size(LinkedList<T> list) {
        return switch (list) {
            case LinkedList.Empty<T> _ -> 0;
            case LinkedList.Element<T>(_, var tail) -> 1 + size(tail);
        };
    }

    static int sum(LinkedList<Integer> list) {
        return switch (list) {
            case LinkedList.Empty<Integer> _ -> 0;
            case LinkedList.Element<Integer>(var value, var tail) -> value + sum(tail);
        };
    }
}
