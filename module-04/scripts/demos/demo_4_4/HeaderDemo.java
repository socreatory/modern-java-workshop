/// Demo 4.4 – Compact Object Headers (JEP 519, Java 25)
///
/// Legt 10 Millionen kleine Objekte an und misst den belegten Heap.
/// Mit Compact Object Headers schrumpft der Objekt-Header von 12 auf 8 Byte –
/// ein `Point` mit zwei `int` belegt dann 16 statt 24 Byte.
///
/// Start (zweimal, Ergebnis vergleichen):
///
///     java HeaderDemo.java
///     java -XX:+UseCompactObjectHeaders HeaderDemo.java
record Point(int x, int y) {}

static final int COUNT = 10_000_000;

long usedHeap() {
    for (int i = 0; i < 3; i++) {
        System.gc();
    }
    var runtime = Runtime.getRuntime();
    return runtime.totalMemory() - runtime.freeMemory();
}

boolean compactHeadersEnabled() {
    var bean = java.lang.management.ManagementFactory
            .getPlatformMXBean(com.sun.management.HotSpotDiagnosticMXBean.class);
    return Boolean.parseBoolean(bean.getVMOption("UseCompactObjectHeaders").getValue());
}

void main() {
    long before = usedHeap();
    Point[] points = new Point[COUNT];
    for (int i = 0; i < COUNT; i++) {
        points[i] = new Point(i, i);
    }
    long after = usedHeap();

    // Das Array enthält nur Referenzen (komprimierte Oops: 4 Byte pro Eintrag)
    long arrayBytes = 4L * COUNT;
    long bytesPerPoint = (after - before - arrayBytes) / COUNT;
    boolean compact = compactHeadersEnabled();

    IO.println("Compact Object Headers: " + (compact ? "AN" : "AUS"));
    IO.println("Heap für %,d Objekte: %,d MB".formatted(COUNT, (after - before) / (1024 * 1024)));
    IO.println("≈ Bytes pro Point:      " + bytesPerPoint + " (erwartet: " + (compact ? 16 : 24) + ")");
    IO.println("Kontrolle:              " + points[COUNT - 1]);
}
