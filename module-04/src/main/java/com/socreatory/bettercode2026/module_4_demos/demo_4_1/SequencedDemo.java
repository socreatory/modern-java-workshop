package com.socreatory.bettercode2026.module_4_demos.demo_4_1;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.SequencedCollection;
import java.util.SequencedMap;
import java.util.SequencedSet;
import java.util.TreeSet;

/// Demo 4.1 – Sequenced Collections (Java 21)
///
/// Zeigt die neue Typhierarchie und dass `reversed()` eine View liefert, keine Kopie.
///
///   - `SequencedCollection` ← `List`, `Deque`, `SequencedSet` (← `LinkedHashSet`, `SortedSet`/`TreeSet`)
///   - `SequencedMap` ← `LinkedHashMap`, `SortedMap`/`TreeMap`
public class SequencedDemo {

    void main() {
        typeHierarchy();
        reversedIsAView();
        notEverythingWorksEverywhere();
    }

    /// Ein Parameter vom Typ `SequencedCollection` akzeptiert Listen, Deques und geordnete Sets.
    void typeHierarchy() {
        IO.println("== Eine Methode für alle Typen mit Reihenfolge ==");
        announce(new ArrayList<>(List.of("Keynote", "Gatherers", "HTTP/3")));
        announce(new ArrayDeque<>(List.of("Keynote", "Gatherers", "HTTP/3")));
        announce(new LinkedHashSet<>(List.of("Keynote", "Gatherers", "HTTP/3")));
        announce(new TreeSet<>(List.of("Keynote", "Gatherers", "HTTP/3")));

        SequencedSet<String> set = new LinkedHashSet<>(List.of("a", "b", "c"));
        SequencedMap<String, Integer> map = new LinkedHashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.putFirst("z", 0);
        IO.println("SequencedSet.reversed():  " + set.reversed());
        IO.println("SequencedMap nach putFirst: " + map + ", firstEntry = " + map.firstEntry());
    }

    void announce(SequencedCollection<String> sessions) {
        IO.println("%-16s Eröffnung: %s, Abschluss: %s".formatted(
                sessions.getClass().getSimpleName(), sessions.getFirst(), sessions.getLast()));
    }

    /// Änderungen wirken in beide Richtungen (write-through).
    void reversedIsAView() {
        IO.println();
        IO.println("== reversed() ist eine View ==");
        List<String> original = new ArrayList<>(List.of("A", "B", "C"));
        List<String> view = original.reversed();
        IO.println("original = " + original + ", view = " + view);

        original.add("D");
        IO.println("nach original.add(\"D\"):    original = " + original + ", view = " + view);

        view.removeFirst();
        IO.println("nach view.removeFirst():   original = " + original + ", view = " + view);

        view.addFirst("X");
        IO.println("nach view.addFirst(\"X\"):   original = " + original + ", view = " + view);

        List<String> snapshot = new ArrayList<>(original.reversed());
        original.add("Y");
        IO.println("Kopie bleibt stabil:       snapshot = " + snapshot + ", original = " + original);
    }

    /// Lesende Methoden gehen überall, schreibende nur, wenn sie zur Sortierung passen.
    void notEverythingWorksEverywhere() {
        IO.println();
        IO.println("== Nicht jede Operation funktioniert überall ==");
        SequencedSet<String> sorted = new TreeSet<>(List.of("B", "A", "C"));
        IO.println("TreeSet getFirst/getLast: " + sorted.getFirst() + "/" + sorted.getLast());
        try {
            sorted.addLast("D");
        } catch (UnsupportedOperationException e) {
            IO.println("TreeSet.addLast(...):     " + e.getClass().getSimpleName());
        }
        try {
            List.of("X", "Y").reversed().add("Z");
        } catch (UnsupportedOperationException e) {
            IO.println("List.of(...).reversed().add(...): " + e.getClass().getSimpleName());
        }
    }
}
