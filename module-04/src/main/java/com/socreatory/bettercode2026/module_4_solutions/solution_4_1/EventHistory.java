package com.socreatory.bettercode2026.module_4_solutions.solution_4_1;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.SequencedCollection;
import java.util.SequencedMap;
import java.util.SequencedSet;

/// Lösung 4.1 – Event-Historie auf Sequenced Collections umgestellt (Java 21)
///
/// Die Felder und Rückgabewerte nutzen die Interfaces [SequencedCollection], [SequencedSet]
/// und [SequencedMap]. Die konkreten Klassen stehen nur noch bei der Erzeugung.
///
/// Die Methoden sind teils nur noch Einzeiler. `newestFirst()` und `sourcesNewestFirst()`
/// liefern eine View auf die Originaldaten, keine Kopie: Wer eine unabhängige Momentaufnahme
/// braucht, kopiert selbst (`new ArrayList<>(events.reversed())`).
public class EventHistory {

    private final SequencedCollection<Event> events = new ArrayList<>();
    private final SequencedSet<String> sources = new LinkedHashSet<>();
    private final SequencedMap<String, Event> latestBySource = new LinkedHashMap<>();

    public void record(Event event) {
        events.addLast(event);
        sources.add(event.source());
        latestBySource.putLast(event.source(), event);
    }

    /// Ein wichtiges Ereignis wird ganz nach vorne gestellt.
    public void pin(Event event) {
        events.addFirst(event);
    }

    public Event oldest() {
        return events.getFirst();
    }

    public Event latest() {
        return events.getLast();
    }

    /// Die Ereignisse, das neueste zuerst – als View auf `events`.
    public SequencedCollection<Event> newestFirst() {
        return events.reversed();
    }

    /// Entfernt das neueste Ereignis und gibt es zurück (z. B. für „Rückgängig“).
    public Event undoLast() {
        return events.removeLast();
    }

    public String firstSource() {
        return sources.getFirst();
    }

    public String lastSource() {
        return sources.getLast();
    }

    /// Die Quellen, die zuletzt neu aufgetaucht sind, zuerst – als View auf `sources`.
    public SequencedSet<String> sourcesNewestFirst() {
        return sources.reversed();
    }

    public Map.Entry<String, Event> firstEntry() {
        return latestBySource.firstEntry();
    }

    public Map.Entry<String, Event> lastEntry() {
        return latestBySource.lastEntry();
    }

    public int size() {
        return events.size();
    }
}
