package com.socreatory.bettercode2026.module_2_solutions.solution_2_2;

/// Das Ergebnis einer Operation: Erfolg, Erfolg mit Hinweisen oder Fehler.
///
/// Die Typen sind generisch: `T` ist der Typ des Wertes im Erfolgsfall.
public sealed interface Result<T> permits Success, Failure, Warning {
}

/// Die Operation war erfolgreich und hat einen Wert geliefert.
record Success<T>(T value) implements Result<T> {
}

/// Die Operation ist gescheitert, `message` nennt den Grund.
record Failure<T>(String message) implements Result<T> {
}
