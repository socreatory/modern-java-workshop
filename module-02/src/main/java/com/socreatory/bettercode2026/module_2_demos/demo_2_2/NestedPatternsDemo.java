package com.socreatory.bettercode2026.module_2_demos.demo_2_2;

import java.util.List;

/// Demo 2.2 – Verschachtelte Record Patterns, `when` und Exhaustiveness bei generischen sealed Types
///
/// Zeigt am Medikationsplan:
///
///   - Record Patterns, die sich wie die Daten verschachteln, plus `when`
///   - Ein generisches `Result<T>` mit Record Patterns: `Success(Medication(...))`
///   - Exhaustiveness berücksichtigt die Typargumente: Für `Box<Integer>` genügt `IntBox`
///
/// Alle Daten sind frei erfunden und keine medizinische Beratung.
public class NestedPatternsDemo {

    enum Drug { PARACETAMOL, IBUPROFEN, CAFFEINE }

    sealed interface Dosage permits Tablet, Infusion {
    }

    record Tablet(int morning, int midday, int evening) implements Dosage {
    }

    record Infusion(int speed, int duration) implements Dosage {
    }

    record Medication(Drug drug, Dosage dosage) {
    }

    sealed interface Result<T> permits Success, Failure {
    }

    record Success<T>(T value) implements Result<T> {
    }

    record Failure<T>(String message) implements Result<T> {
    }

    sealed interface Box<T> permits IntBox, TextBox {
    }

    record IntBox(int value) implements Box<Integer> {
    }

    record TextBox(String value) implements Box<String> {
    }

    void main() {
        nestedPatterns();
        genericResult();
        typeArguments();
    }

    void nestedPatterns() {
        IO.println("== Verschachtelte Record Patterns und when ==");
        List<Medication> medications = List.of(
                new Medication(Drug.PARACETAMOL, new Tablet(1, 1, 1)),
                new Medication(Drug.IBUPROFEN, new Tablet(2, 1, 1)),
                new Medication(Drug.CAFFEINE, new Infusion(20, 30)),
                new Medication(Drug.PARACETAMOL, new Infusion(50, 60)));
        for (Medication medication : medications) {
            IO.println("%-45s %s".formatted(format(medication), warn(medication)));
        }
    }

    static String format(Medication medication) {
        return switch (medication) {
            case Medication(var drug, Tablet(var morning, var midday, var evening)) ->
                    "%s: %d-%d-%d".formatted(drug, morning, midday, evening);
            case Medication(var drug, Infusion(var speed, var duration)) ->
                    "%s: %d ml/h für %d min".formatted(drug, speed, duration);
        };
    }

    static String warn(Medication medication) {
        return switch (medication) {
            case Medication(var drug, Infusion(var speed, _))
                    when drug == Drug.CAFFEINE && speed > 10 -> "Koffein: zu schnell";
            case Medication(var drug, Tablet(var m, var d, var e))
                    when drug == Drug.IBUPROFEN && m + d + e > 3 -> "Ibuprofen: zu viele Tabletten";
            case Medication _ -> "OK";
        };
    }

    /// Der `switch` über `Result<Dosage>` ist ohne `default` vollständig: Success mit Tablet,
    /// Success mit Infusion und Failure decken alle Möglichkeiten ab.
    void genericResult() {
        IO.println();
        IO.println("== Generisches Result<T> ==");
        IO.println(describe(new Success<>(new Tablet(1, 0, 1))));
        IO.println(describe(new Success<>(new Infusion(50, 30))));
        IO.println(describe(new Failure<>("Dosierung nicht lesbar")));
    }

    static String describe(Result<Dosage> result) {
        return switch (result) {
            case Success<Dosage>(Tablet(var morning, var midday, var evening)) ->
                    "Tabletten, %d pro Tag".formatted(morning + midday + evening);
            case Success<Dosage>(Infusion(var speed, _)) -> "Infusion mit %d ml/h".formatted(speed);
            case Failure<Dosage>(var message) -> "Fehler: " + message;
        };
    }

    /// `IntBox` implementiert nur `Box<Integer>`. Für einen `Box<Integer>` kann `TextBox`
    /// gar nicht vorkommen: Der Compiler lässt den Zweig weg, der `switch` bleibt vollständig.
    void typeArguments() {
        IO.println();
        IO.println("== Exhaustiveness mit Typargumenten ==");
        IO.println(unwrap(new IntBox(500)));
        IO.println(unwrapAny(new TextBox("Aspirin")));
    }

    static int unwrap(Box<Integer> box) {
        return switch (box) {
            case IntBox(var value) -> value;
        };
    }

    static String unwrapAny(Box<?> box) {
        return switch (box) {
            case IntBox(var value) -> "Zahl " + value;
            case TextBox(var value) -> "Text " + value;
        };
    }
}
