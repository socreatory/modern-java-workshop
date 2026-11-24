package com.socreatory.bettercode2026.module_2_demos.demo_2_1;

/// Demo 2.1 – Type Patterns, `case null` und Dominanz (Java 21)
///
/// Zeigt am Medikationsplan:
///
///   - Type Patterns im `switch` statt `instanceof`-Kette
///   - `case null` und `case null, default`
///   - Dominanz: Ein allgemeiner Fall vor einem speziellen ist ein Compile-Fehler
///
/// Alle Daten sind frei erfunden und keine medizinische Beratung.
public class TypePatternsDemo {

    sealed interface Dosage permits Tablet, Infusion {
    }

    record Tablet(int morning, int midday, int evening) implements Dosage {
    }

    record Infusion(int speed, int duration) implements Dosage {
    }

    void main() {
        typePatterns();
        caseNull();
        dominance();
    }

    /// Ein Zweig pro Alternative, ohne Cast und ohne `default`.
    void typePatterns() {
        IO.println("== Type Patterns ==");
        IO.println(describe(new Tablet(1, 0, 1)));
        IO.println(describe(new Infusion(50, 30)));
    }

    static String describe(Dosage dosage) {
        return switch (dosage) {
            case Tablet tablet -> "%d-%d-%d Tabletten".formatted(
                    tablet.morning(), tablet.midday(), tablet.evening());
            case Infusion infusion -> "%d ml/h für %d min".formatted(
                    infusion.speed(), infusion.duration());
        };
    }

    /// Ohne `case null` wirft der `switch` bei `null` eine `NullPointerException`.
    void caseNull() {
        IO.println();
        IO.println("== case null ==");

        try {
            describe(null);
        } catch (NullPointerException e) {
            IO.println("describe(null) -> NullPointerException");
        }

        IO.println(describeNullable(null));
        IO.println(describeNullable(new Infusion(50, 30)));

        // `case null, default` ist die Kombination für Typen, die nicht sealed sind.
        for (Object value : new Object[]{"Aspirin", 500, new Tablet(1, 0, 0), null}) {
            IO.println(describeAny(value));
        }
    }

    static String describeNullable(Dosage dosage) {
        return switch (dosage) {
            case null -> "keine Dosierung";
            case Tablet _ -> "Tabletten";
            case Infusion _ -> "Infusion";
        };
    }

    static String describeAny(Object value) {
        return switch (value) {
            case String text -> "Text: " + text;
            case Dosage dosage -> "Dosierung: " + describeNullable(dosage);
            case null, default -> "etwas anderes";
        };
    }

    /// Die Reihenfolge der Cases entscheidet. Spezielle Fälle mit Guard kommen vor die allgemeinen.
    void dominance() {
        IO.println();
        IO.println("== Dominanz ==");
        IO.println(warn(new Tablet(2, 2, 3)));
        IO.println(warn(new Tablet(1, 0, 1)));
        IO.println(warn(new Infusion(150, 30)));
    }

    static String warn(Dosage dosage) {
        return switch (dosage) {
            case Tablet t when t.morning() + t.midday() + t.evening() > 6 -> "Zu viele Tabletten";
            case Tablet t -> "OK";
            case Infusion i when i.speed() > 100 -> "Zu schnell";
            case Infusion i -> "OK";
        };
    }

    // Kompiliert NICHT: Der allgemeine Fall `Tablet t` steht vor dem Guard. Jeder `Tablet`
    // würde schon beim ersten Case landen, der zweite Case wäre nie erreichbar. Der Compiler
    // erkennt das als Dominanz und meldet einen Fehler (Zeile mit dem zweiten Case):
    //
    //     static String warnBroken(Dosage dosage) {
    //         return switch (dosage) {
    //             case Tablet t -> "OK";
    //             case Tablet t when t.morning() > 6 -> "Zu viele Tabletten";
    //             // error: this case label is dominated by a preceding case label
    //             case Infusion i -> "OK";
    //         };
    //     }
}
