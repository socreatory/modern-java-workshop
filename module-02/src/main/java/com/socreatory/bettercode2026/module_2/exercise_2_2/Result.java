package com.socreatory.bettercode2026.module_2.exercise_2_2;

/// Das Ergebnis einer Operation: entweder ein Erfolg oder ein Fehler.
///
/// Die Typen `Success` und `Failure` sind generisch: `T` ist der Typ des Wertes im Erfolgsfall.
// TODO 2: Ergänze `Warning` in der `permits`-Klausel (siehe `Main`).
public sealed interface Result<T> permits Success, Failure {
}

/// Die Operation war erfolgreich und hat einen Wert geliefert.
record Success<T>(T value) implements Result<T> {
}

/// Die Operation ist gescheitert, `message` nennt den Grund.
record Failure<T>(String message) implements Result<T> {
}
