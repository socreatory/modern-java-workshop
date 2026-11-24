package com.socreatory.bettercode2026.module_4.exercise_4_1;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/// Übung 4.1 – Event-Historie auf Sequenced Collections umstellen (Java 21)
///
/// Die Klasse verwaltet Ereignisse in drei Strukturen mit Reihenfolge:
///
///   - `events`: alle Ereignisse in der Reihenfolge ihres Eintreffens ([List])
///   - `sources`: die Quellen in der Reihenfolge, in der sie zum ersten Mal auftauchten ([LinkedHashSet])
///   - `latestBySource`: das jeweils letzte Ereignis pro Quelle ([LinkedHashMap])
///
/// Die Methoden sind im Stil vor Java 21 geschrieben: mit `size() - 1`, Index-Schleifen
/// und `iterator().next()`.
///
/// **Dein Job:**
///
///   - Arbeite die `TODO`s von oben nach unten ab und ersetze die Umwege durch
///     `getFirst()`, `getLast()`, `reversed()`, `addFirst(...)` und `removeLast()`.
///
///   - Ändere die Typen der Felder und Rückgabewerte auf [java.util.SequencedCollection],
///     [java.util.SequencedSet] und [java.util.SequencedMap], wo die Implementierung egal ist.
///
///   - Lass `Main` vor und nach der Umstellung laufen: Die Ausgabe muss identisch bleiben.
public class EventHistory {

    // TODO 1: Welcher Typ reicht hier? `SequencedCollection<Event>` genügt für die Felder,
    //         die nur die Reihenfolge brauchen. Ändere die Deklarationen (nicht die Konstruktoren).
    private final List<Event> events = new ArrayList<>();
    private final LinkedHashSet<String> sources = new LinkedHashSet<>();
    private final LinkedHashMap<String, Event> latestBySource = new LinkedHashMap<>();

    public void record(Event event) {
        events.add(event);
        sources.add(event.source());
        // Ein erneutes put ändert bei LinkedHashMap die Reihenfolge nicht – deshalb vorher entfernen.
        // TODO 12 (optional): `putLast(key, value)` erledigt Entfernen und Einfügen in einem Schritt.
        latestBySource.remove(event.source());
        latestBySource.put(event.source(), event);
    }

    /// Ein wichtiges Ereignis wird ganz nach vorne gestellt.
    public void pin(Event event) {
        // TODO 2: `events.add(0, event)` durch `addFirst(event)` ersetzen.
        events.add(0, event);
    }

    public Event oldest() {
        // TODO 3: `getFirst()` statt `get(0)`.
        return events.get(0);
    }

    public Event latest() {
        // TODO 4: `getLast()` statt `get(size() - 1)`.
        return events.get(events.size() - 1);
    }

    /// Die Ereignisse, das neueste zuerst.
    public List<Event> newestFirst() {
        // TODO 5: Die Rückwärts-Schleife über den Index durch `reversed()` ersetzen.
        //         Kopie oder View? Entscheide, was für den Rückgabetyp nötig ist.
        List<Event> result = new ArrayList<>();
        for (int i = events.size() - 1; i >= 0; i--) {
            result.add(events.get(i));
        }
        return result;
    }

    /// Entfernt das neueste Ereignis und gibt es zurück (z. B. für „Rückgängig“).
    public Event undoLast() {
        // TODO 6: `removeLast()` statt `remove(size() - 1)`.
        return events.remove(events.size() - 1);
    }

    public String firstSource() {
        // TODO 7: `getFirst()` statt `iterator().next()`.
        return sources.iterator().next();
    }

    public String lastSource() {
        // TODO 8: `getLast()` statt der Schleife über alle Elemente.
        String last = null;
        for (String source : sources) {
            last = source;
        }
        return last;
    }

    /// Die Quellen, die zuletzt neu aufgetaucht sind, zuerst.
    public Iterable<String> sourcesNewestFirst() {
        // TODO 9: `reversed()` auf dem Set. Welchen Rückgabetyp hat das jetzt?
        List<String> copy = new ArrayList<>(sources);
        List<String> result = new ArrayList<>();
        for (int i = copy.size() - 1; i >= 0; i--) {
            result.add(copy.get(i));
        }
        return result;
    }

    public Map.Entry<String, Event> firstEntry() {
        // TODO 10: `SequencedMap.firstEntry()` statt `entrySet().iterator().next()`.
        return latestBySource.entrySet().iterator().next();
    }

    public Map.Entry<String, Event> lastEntry() {
        // TODO 11: `SequencedMap.lastEntry()` statt der Schleife über alle Einträge.
        Map.Entry<String, Event> last = null;
        for (Map.Entry<String, Event> entry : latestBySource.entrySet()) {
            last = entry;
        }
        return last;
    }

    public int size() {
        return events.size();
    }
}
