/// Demo 2.5 – Primitive Types in Patterns, `instanceof` und `switch` (JEP 507)
///
/// Status: Preview in Java 23 (JEP 455), 24 (JEP 488) und 25 (JEP 507, drittes Preview).
/// Das Feature ist nicht final. Laut OpenJDK-Mailingliste (core-libs-dev, November 2026)
/// wird die Preview-Implementierung in JDK 28 entfernt; das Feature wird zurückgezogen und
/// soll neu entworfen werden. Verlass dich also nicht darauf, dass es in dieser Form final wird.
/// (Quelle: JEP 507 auf openjdk.org und der Patch „Remove Preview Implementation for
/// Primitive Types in Patterns, instanceof, and switch“; Stand bitte vor dem Vortrag prüfen.)
///
/// Gezeigt wird am Medikationsplan:
///
///   - `switch` über `boolean`, `long`, `float` und `double`
///   - `instanceof` mit primitiven Typen: Ist die Umwandlung verlustfrei (exakt)?
///   - Primitive Patterns in Record Patterns
///
/// Läuft nicht über Maven, sondern direkt mit JDK 25 (ein JDK 28 oder neuer lehnt das Feature ab):
///
///     java --enable-preview --source 25 PrimitivePatternsDemo.java
///
/// Alle Daten sind frei erfunden und keine medizinische Beratung.
record Dose(double mg) {
}

record Tablet(int morning, int midday, int evening) {
}

void main() {
    switchOnPrimitives();
    exactConversions();
    primitivePatternsInRecords();
}

/// `switch` konnte bisher nur `int`, `char`, `short`, `byte` und deren Wrapper.
void switchOnPrimitives() {
    IO.println("== switch über boolean, long, float und double ==");

    boolean fasting = true;
    IO.println("Nüchtern: " + switch (fasting) {
        case true -> "ja, Tablette erst nach dem Essen";
        case false -> "nein, Einnahme egal";
    });

    long patientId = 4711L;
    IO.println("Patient " + patientId + ": " + switch (patientId) {
        case 0L -> "unbekannt";
        case 4711L -> "Testpatient Anna";
        case long id when id < 0 -> "ungültige ID";
        case long id -> "Patient Nr. " + id;
    });

    float temperature = 38.6f;
    IO.println("Temperatur " + temperature + " °C: " + switch (temperature) {
        case float t when t >= 39.0f -> "hohes Fieber";
        case float t when t >= 38.0f -> "Fieber";
        case float t -> "normal";
    });

    double concentration = 0.5;
    IO.println("Konzentration " + concentration + " mg/ml: " + switch (concentration) {
        case 0.0 -> "keine";
        case double c when c < 1.0 -> "niedrig";
        case double c -> "hoch";
    });
}

/// `x instanceof T t` ist hier wahr, wenn sich der Wert verlustfrei nach `T` umwandeln lässt.
void exactConversions() {
    IO.println();
    IO.println("== instanceof mit verlustfreier Umwandlung ==");

    for (int mg : new int[]{50, 127, 500, 1000}) {
        if (mg instanceof byte b) {
            IO.println(mg + " mg passen in ein byte: " + b);
        } else {
            IO.println(mg + " mg passen NICHT in ein byte");
        }
    }

    for (double mg : new double[]{0.5, 0.1, 250.0, 16_777_217.0}) {
        if (mg instanceof float f) {
            IO.println(mg + " mg als float verlustfrei: " + f);
        } else {
            IO.println(mg + " mg als float nicht exakt darstellbar");
        }
    }

    double rawMg = 250.0;
    if (rawMg instanceof int whole) {
        IO.println(rawMg + " mg sind genau " + whole + " mg (ganzzahlig)");
    }
}

/// In Record Patterns darf das Pattern einer Komponente einen anderen primitiven Typ haben.
void primitivePatternsInRecords() {
    IO.println();
    IO.println("== Primitive Patterns in Record Patterns ==");

    for (Dose dose : new Dose[]{new Dose(250.0), new Dose(0.5), new Dose(0.1)}) {
        String text = switch (dose) {
            case Dose(int whole) -> whole + " mg (ganze Zahl)";
            case Dose(float f) -> f + " mg (als float exakt)";
            case Dose(double d) -> d + " mg (nur als double exakt)";
        };
        IO.println(text);
    }

    // Die Komponenten von Tablet sind int, das Pattern fragt byte ab: Kleine Zahlen passen.
    Tablet tablet = new Tablet(1, 0, 1);
    if (tablet instanceof Tablet(byte morning, byte midday, byte evening)) {
        IO.println("Alle Tablettenzahlen passen in ein byte: " + morning + "-" + midday + "-" + evening);
    }
}
