# Modernes Java in der Praxis – die Features, die wirklich zählen

Veranstalter: betterCode() Java 2026 (iX / dpunkt.verlag)

Trainer:

- [Falk Sippach](https://www.embarc.de/falk-sippach/) ([LinkedIn](https://www.linkedin.com/in/falk-sippach/)), [embarc Software Consulting GmbH](https://www.embarc.de/)
- [Sven Woltmann](https://www.happycoders.eu/de/ueber-sven-woltmann/) ([LinkedIn](https://www.linkedin.com/in/sven-woltmann/)), [HappyCoders GmbH](https://www.happycoders.eu/de/)

Ein Workshop-Tag zu den Java-Features seit Java 17, die im Entwicklungsalltag einen Unterschied machen: weniger Boilerplate, Datenmodellierung mit Records, Sealed Classes und Pattern Matching, Nebenläufigkeit mit Virtual Threads und Structured Concurrency sowie Collections, Streams und ein Ausblick. Kurze Übungen in jedem Modul, in deiner eigenen IDE.

Die Trainingsmodule sind teilweise aus der [„Modern Java Masterclass“](https://www.socreatory.com/en/trainings/modern-java) von Socreatory übernommen.

## Vorbereitung

1. Installiere ein JDK 25. JDK 26 geht auch: Setz dann `maven.compiler.release` in der `pom.xml` im Wurzelverzeichnis auf 26. JDK 27 geht nicht, siehe Modul 3.
2. Öffne dieses Projekt in deiner IDE als Maven-Projekt.
3. Prüfe, dass alles kompiliert: `mvn compile` im Wurzelverzeichnis.

Die Übungen liegen in den `exercise`-Paketen, die Musterlösungen in den `solution`-Paketen. Übung 1.1 und die Demos zu Modul 1, Teil 1 und 2 liegen unter `module-01/scripts/` und laufen direkt mit dem `java`-Launcher aus dem Terminal, nicht über Maven.

## Modul 1 – Weniger Zeremonie

- Compact Source Files + Instance Main Methods
- Launch Multi-File Source-Code Programs
- Module Import Declarations
- Flexible Constructor Bodies
- Unnamed Variables

## Modul 2 – Die Domäne im Code

Alle drei Übungen nutzen denselben Medikationsplan (Wirkstoffe, Tabletten, Infusionen). Das Modell wächst von Übung zu Übung, jede Übung hat aber ihr eigenes, lauffähiges Paket. Alle Daten sind frei erfunden und keine medizinische Beratung.

- Type Patterns, `when`, `case null` und Dominanz (Übung 2.1: Dosierungen bewerten)
- Exhaustiveness: `switch` ohne `default` über `Result<T>`, neuen Subtyp einführen (Übung 2.2)
- Record Patterns, verschachtelt und mit `when`; Wirkstoffmengen berechnen (Übung 2.3)

Jede Übung hat eine kleine Bonus-Aufgabe (`Bonus…`-Klasse) für Schnelle. Die Demos liegen in den `module_2_demos`-Paketen. Die Exhaustiveness-Demo (`demo_2_3`) zeigt den Compile-Fehler: `Patch` im `sealed`-Interface und im Record einkommentieren, dann meldet der `switch` ohne `default` einen Fehler. Die Demo `demo_2_4` baut eine verkettete Liste als algebraischen Datentyp (Sealed Interface plus Records) und wertet sie mit Record Patterns aus. Die Demo zu Primitive Types in Patterns (JEP 507) liegt unter `module-02/scripts/demos/demo_2_5/` und läuft nicht über Maven, sondern mit JDK 25: `java --enable-preview --source 25 PrimitivePatternsDemo.java`. Das Feature ist in Java 25 ein Preview-Feature (drittes Preview). In JDK 28 wird die Preview-Implementierung laut OpenJDK entfernt; das Feature soll neu entworfen werden.

## Modul 3 – Nebenläufigkeit im Alltag

- Virtual Threads
- Stolperfallen und Best Practices
- `StructuredTaskScope`
- Scoped Values

`StructuredTaskScope` ist in Java 25 ein Preview-Feature. Das Maven-Modul `module-03` aktiviert dafür `--enable-preview`; zeigt deine IDE trotzdem Fehler, stell den Language Level dieses Moduls auf „25 (Preview)“. Mit JDK 26 geht es auch: Setz `maven.compiler.release` in der `pom.xml` auf 26 und den Language Level auf „26 (Preview)“ – die Teile der API, die die Übungen nutzen, sind dort unverändert.

Mit JDK 27 kompiliert `module-03` nicht, denn in Java 27 hat sich die API noch einmal geändert: `StructuredTaskScope` hat einen dritten Typparameter, und `join()` wirft eine checked `ExecutionException` statt der `FailedException`. Mit dieser API wird `StructuredTaskScope` in Java 28 final (JEP 543).

## Modul 4 – Collections, Streams und Ausblick

- Sequenced Collections (Übung 4.1)
- Stream Gatherers (Übung 4.2)
- Überblick: HTTP/3 im HTTP Client

Die HTTP/3-Demo liegt unter `module-04/scripts/demos/demo_4_3/` und läuft nicht über Maven, sondern mit JDK 26 oder neuer: `java Http3Demo.java`. `HttpClient.Version.HTTP_3` gibt es erst ab Java 26 (JEP 517).
