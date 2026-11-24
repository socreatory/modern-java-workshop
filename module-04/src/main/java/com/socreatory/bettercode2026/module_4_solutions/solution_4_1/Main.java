package com.socreatory.bettercode2026.module_4_solutions.solution_4_1;

/// Lösung 4.1 – Event-Historie nach der Umstellung (Java 21)
///
/// Startet die [EventHistory] und gibt alle Abfragen aus. Die Ausgabe muss vor und nach der
/// Umstellung identisch sein.
public class Main {

    void main() {
        EventHistory history = new EventHistory();
        history.record(new Event("auth", "Login von anna"));
        history.record(new Event("billing", "Rechnung 1001 erstellt"));
        history.record(new Event("auth", "Login von ben"));
        history.record(new Event("search", "Index neu aufgebaut"));
        history.pin(new Event("ops", "Wartungsfenster am Freitag"));

        IO.println("Ältestes Ereignis:  " + history.oldest());
        IO.println("Neuestes Ereignis:  " + history.latest());
        IO.println("Neueste zuerst:     " + history.newestFirst());
        IO.println("Erste Quelle:       " + history.firstSource());
        IO.println("Letzte Quelle:      " + history.lastSource());
        IO.println("Quellen rückwärts:  " + history.sourcesNewestFirst());
        IO.println("Erster Eintrag:     " + history.firstEntry());
        IO.println("Letzter Eintrag:    " + history.lastEntry());

        IO.println("Rückgängig:         " + history.undoLast());
        IO.println("Neuestes danach:    " + history.latest());
        IO.println("Anzahl:             " + history.size());
    }
}
