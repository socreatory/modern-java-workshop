package com.socreatory.bettercode2026.module_2_demos.demo_2_3;

/// Demo 2.3 – Exhaustiveness: Der Compiler findet jeden `switch`, der einen neuen Fall braucht
///
/// Die sealed Hierarchie `Dosage` kennt `Tablet` und `Infusion`. Das Pflaster `Patch` ist
/// zunächst auskommentiert.
///
/// So läuft die Demo:
///
///   1. Starten: Beide Subtypen sind abgedeckt, der `switch` kommt ohne `default` aus.
///   2. `Patch` einkommentieren, und zwar an **beiden** Stellen: in `permits` und der
///      Record-Deklaration.
///   3. Compile-Fehler: `describe` ist nicht mehr vollständig
///      („the switch expression does not cover all possible input values“).
///   4. Den Fall `case Patch` in `describe` einkommentieren, dann läuft alles wieder.
///
/// `describeWithDefault` zeigt die Gegenprobe: Ein `default` kompiliert immer, verschluckt
/// `Patch` aber stillschweigend. Der Compiler sagt hier nichts.
///
/// Alle Daten sind frei erfunden und keine medizinische Beratung.
public class ExhaustivenessDemo {

    sealed interface Dosage permits Tablet, Infusion /*, Patch */ {
    }

    record Tablet(int morning, int midday, int evening) implements Dosage {
    }

    record Infusion(int speed, int duration) implements Dosage {
    }

    // record Patch(int changeEveryHours) implements Dosage {
    // }

    void main() {
        IO.println("== Ohne default: der Compiler prüft Vollständigkeit ==");
        IO.println(describe(new Tablet(1, 0, 1)));
        IO.println(describe(new Infusion(50, 30)));

        IO.println();
        IO.println("== Mit default: neue Fälle werden stillschweigend verschluckt ==");
        IO.println(describeWithDefault(new Tablet(1, 0, 1)));
        IO.println(describeWithDefault(new Infusion(50, 30)));
    }

    /// Kein `default`: Kommt ein Subtyp dazu, schlägt die Kompilierung fehl.
    static String describe(Dosage dosage) {
        return switch (dosage) {
            case Tablet tablet -> "%d-%d-%d Tabletten".formatted(
                    tablet.morning(), tablet.midday(), tablet.evening());
            case Infusion infusion -> "%d ml/h für %d min".formatted(
                    infusion.speed(), infusion.duration());
            // case Patch patch -> "Pflaster, Wechsel alle %d h".formatted(patch.changeEveryHours());
        };
    }

    /// Mit `default`: kompiliert auch mit `Patch`, liefert dafür aber einen falschen Text.
    static String describeWithDefault(Dosage dosage) {
        return switch (dosage) {
            case Tablet tablet -> "%d-%d-%d Tabletten".formatted(
                    tablet.morning(), tablet.midday(), tablet.evening());
            case Infusion infusion -> "%d ml/h für %d min".formatted(
                    infusion.speed(), infusion.duration());
            default -> "unbekannte Dosierung";
        };
    }
}
