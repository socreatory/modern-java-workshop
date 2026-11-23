---
theme: ./theme
title: Ausblick und Abschluss
training:
  name: Modernes Java in der Praxis
  subtitle: Die Features, die wirklich zählen
colorSchema: light
canvasWidth: 960
fonts:
  provider: none
codeCopy: false
htmlAttrs:
  lang: de
layout: module-title
eyebrow: "Modul 5:"
---

# Ausblick und Abschluss

<div class="hc-text" id="subtitle">

<span class="hc-nobr">AOT-Cache</span> <span class="hc-dot">·</span> <span class="hc-nobr">Compact Object Headers</span> <span class="hc-dot">·</span> <span class="hc-nobr">Lazy Constants</span> <span class="hc-dot">·</span> <span class="hc-nobr">Value Objects</span>

</div>

---
layout: content-plain
title: Herzlichen Glückwunsch und vielen Dank
---

<div class="hc-statement" id="statement"><div class="hc-statement-line"><strong>Herzlichen Glückwunsch!</strong></div><div class="hc-statement-line hc-statement-minor">und</div><div class="hc-statement-line"><strong>Vielen Dank!</strong></div></div>

---
layout: content
title: Das haben wir heute geschafft
---

# Das haben wir heute geschafft

<div class="hc-text" id="module1" v-click="1">

**Modul 1 – Weniger Zeremonie**  
Compact Source Files <span class="hc-dot">·</span> <span class="hc-nobr">Multi-File Programs</span> <span class="hc-dot">·</span> <span class="hc-nobr">Module Imports</span> <span class="hc-dot">·</span> <span class="hc-nobr">Flexible Constructor Bodies</span> <span class="hc-dot">·</span> <span class="hc-nobr">Unnamed Variables</span>

</div>

<div class="hc-text" id="module2" v-click="2">

**Modul 2 – Die Domäne im Code**  
Records <span class="hc-dot">·</span> <span class="hc-nobr">Sealed Classes</span> <span class="hc-dot">·</span> <span class="hc-nobr">Pattern Matching for `switch`</span> <span class="hc-dot">·</span> <span class="hc-nobr">Guarded Patterns</span> <span class="hc-dot">·</span> <span class="hc-nobr">Exhaustiveness</span> <span class="hc-dot">·</span> <span class="hc-nobr">Record Patterns</span>

</div>

<div class="hc-text" id="module3" v-click="3">

**Modul 3 – Nebenläufigkeit im Alltag**  
Virtual Threads <span class="hc-dot">·</span> <span class="hc-nobr">Pinning</span> <span class="hc-dot">·</span> `StructuredTaskScope` (Preview) <span class="hc-dot">·</span> <span class="hc-nobr">Scoped Values</span>

</div>

<div class="hc-text" id="module4" v-click="4">

**Modul 4 – Collections, Streams und Ausblick**  
Sequenced Collections <span class="hc-dot">·</span> <span class="hc-nobr">Stream Gatherers</span>

</div>

---
layout: content
title: Schneller starten, weniger Speicher – ohne Codeänderung
---

# Schneller starten, weniger Speicher – ohne Codeänderung

<div class="hc-table hc-table-widths" id="aot"><table><colgroup><col style="width: 36.00%"><col style="width: 18.00%"><col style="width: 46.00%"></colgroup><thead><tr><th>Ahead-of-Time-Features (AOT)</th><th>Java</th><th>Was im AOT-Cache liegt</th></tr></thead><tbody><tr data-part="aot#1" v-click="1"><td data-part="aot#1[0]">Class Loading &amp; Linking</td><td data-part="aot#1[1]">24</td><td data-part="aot#1[2]">Klassen, fertig geladen und gelinkt</td></tr><tr data-part="aot#2" v-click="2"><td data-part="aot#2[0]">Method Profiling</td><td data-part="aot#2[1]">25</td><td data-part="aot#2[2]">Profile: Welche Methoden sind heiß?</td></tr><tr data-part="aot#3" v-click="3"><td data-part="aot#3[0]">Code Compilation</td><td data-part="aot#3[1]">28</td><td data-part="aot#3[2]">fertig kompilierter Maschinencode</td></tr></tbody></table></div>

<div class="hc-code" id="commands" v-click="4">

```shell
# Trainingslauf: erzeugt den Cache (in einem Schritt ab Java 25)
java -XX:AOTCacheOutput=app.aot -jar app.jar

# Produktion: startet mit dem Cache
java -XX:AOTCache=app.aot -jar app.jar
```

</div>

<div class="hc-apart hc-table hc-table-widths" id="headers" v-click="5"><table><colgroup><col style="width: 36.00%"><col style="width: 18.00%"><col style="width: 46.00%"></colgroup><thead><tr><th>Compact Object Headers</th><th>Java</th><th>Wirkung</th></tr></thead><tbody><tr data-part="headers#1"><td data-part="headers#1[0]">Header: 64 statt 96 Bit</td><td data-part="headers#1[1]">25 (Flag), 27</td><td data-part="headers#1[2]">SPECjbb2015: 22 % weniger Heap</td></tr></tbody></table></div>

---
layout: content
title: "Lazy Constants (Java 28: vierte Preview)"
---

# Lazy Constants (Java 28: vierte Preview)

<div class="hc-row hc-row-pair-split">

<div class="hc-row-main">

<div class="hc-code" id="code" data-blank-line style="--blank-line: 1">

```java
// Klassisch: Double-Checked Locking
private volatile ExpensiveThing thing;

ExpensiveThing getThing() {
  ExpensiveThing local = thing;
  if (local == null) {
    synchronized (this) {
      local = thing;
      if (local == null) {
        local = computeExpensiveThing();
        thing = local;
      }
    }
  }
  return local;
}
```

</div>

</div>

<div class="hc-row-aside">

<div class="hc-code" id="code2" data-blank-line style="--blank-line: 1" v-click="1">

```java
// Mit Lazy Constant
private final LazyConstant<ExpensiveThing> thing =
  LazyConstant.of(this::computeExpensiveThing);

ExpensiveThing getThing() {
  return thing.get();
}
```

</div>

</div>

</div>

---
layout: content
title: "Value Objects (Java 28: erste Preview)"
---

# Value Objects (Java 28: erste Preview)

<div class="hc-code" id="code">

```java
// Zwei Integer mit gleichem Wert
Integer i = 1996, j = 1996;
IO.println(i == j);   // heute: false
```

<CodeLabel id="label" :line="3" :row="3" :col="40" pointer="none" v-click="1">Java 28 mit <code>--⁠enable-preview</code>: <strong>true</strong></CodeLabel>
</div>

<div class="hc-apart hc-code" id="code2" v-click="2">

```java
// Eigene Value Objects: Record mit dem Schlüsselwort value
value record Point(int x, int y) { }

IO.println(new Point(2, 3) == new Point(2, 3));   // true
```

</div>

<ul class="hc-list" id="list"><li data-part="list[0]" v-click="3">Keine Identität: <code>==</code> vergleicht die Felder.</li><li data-part="list[1]" v-click="4">Die JVM darf sie flach ablegen – ohne Header, ohne Pointer.</li><li data-part="list[2]" v-click="5">Eine Million <code>LocalDate</code> im Array: 8 statt 20 MB Heap.</li><li data-part="list[3]" v-click="6">Aber: flach nur bis 8 Byte inklusive Null-Marker.</li></ul>

---
layout: content
title: Weiteres
---

# Weiteres

<div class="hc-columns" id="columns"><div class="hc-column"><div class="hc-column-head"><strong>Schon da</strong></div><div class="hc-column-body"><ul class="hc-column-items"><li data-part="columns[0][0]" v-click="1"><strong>HTTP/3</strong> im HTTP Client (Java 26), per <code>.version(HTTP_3)</code></li><li data-part="columns[0][1]" v-click="2"><strong>Quantensichere Kryptografie:</strong> ML-KEM und ML-DSA (Java 24)</li><li data-part="columns[0][2]" v-click="3"><strong>TLS 1.3</strong> mit quantensicherem Schlüsselaustausch (Java 27)</li></ul></div></div><div class="hc-column"><div class="hc-column-head"><strong>Java 28 (März 2027)</strong></div><div class="hc-column-body"><ul class="hc-column-items"><li data-part="columns[1][0]" v-click="4"><strong>Structured Concurrency:</strong> final</li><li data-part="columns[1][1]" v-click="5"><strong>JSON-API:</strong> Incubator</li></ul></div></div><div class="hc-column"><div class="hc-column-head"><strong>Abhängig von Valhalla</strong></div><div class="hc-column-body"><ul class="hc-column-items"><li data-part="columns[2][0]" v-click="6"><strong>Vector API:</strong> zwölfter Incubator in Java 27</li><li data-part="columns[2][1]" v-click="7"><strong>Primitive Types in Patterns:</strong> nach fünf Previews zurückgezogen</li></ul></div></div></div>

---
layout: content
title: Feedback-Runde
---

# Feedback-Runde

<ul class="hc-list hc-list-spaced" id="list"><li data-part="list[0]">Was habt ihr mitgenommen?</li><li data-part="list[1]">Welches neue Feature begeistert euch am meisten?</li><li data-part="list[2]">Was war euer größter Aha-Moment?</li><li data-part="list[3]">Was werdet ihr im Projekt als Erstes ausprobieren?</li><li data-part="list[4]">Gibt es ein Thema, bei dem ihr noch tiefer einsteigen möchtet?</li></ul>

---
layout: content-plain
title: Das Ende – Vielen Dank!
---

<div class="hc-statement" id="statement"><div class="hc-statement-line"><strong>Das Ende – Vielen Dank!</strong></div></div>

<div class="hc-diagram hc-diagram-center" id="kontakt"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 840 290" width="840" height="290" role="img" aria-labelledby="t-kontakt d-kontakt">
  <title id="t-kontakt">Kontakt zu Falk Sippach und Sven Woltmann</title>
  <desc id="d-kontakt">Je ein QR-Code zum LinkedIn-Profil, darunter Name, Website und E-Mail-Adresse.</desc>
  <g id="falk">
    <g transform="translate(138 0) scale(4.96552)" shape-rendering="crispEdges">
      <rect width="29" height="29" style="fill: #fff"/>
      <path style="fill: #000" d="M0 0h1v1h-1zM1 0h1v1h-1zM2 0h1v1h-1zM3 0h1v1h-1zM4 0h1v1h-1zM5 0h1v1h-1zM6 0h1v1h-1zM10 0h1v1h-1zM11 0h1v1h-1zM14 0h1v1h-1zM16 0h1v1h-1zM17 0h1v1h-1zM18 0h1v1h-1zM19 0h1v1h-1zM20 0h1v1h-1zM22 0h1v1h-1zM23 0h1v1h-1zM24 0h1v1h-1zM25 0h1v1h-1zM26 0h1v1h-1zM27 0h1v1h-1zM28 0h1v1h-1zM0 1h1v1h-1zM6 1h1v1h-1zM10 1h1v1h-1zM12 1h1v1h-1zM15 1h1v1h-1zM17 1h1v1h-1zM20 1h1v1h-1zM22 1h1v1h-1zM28 1h1v1h-1zM0 2h1v1h-1zM2 2h1v1h-1zM3 2h1v1h-1zM4 2h1v1h-1zM6 2h1v1h-1zM8 2h1v1h-1zM9 2h1v1h-1zM10 2h1v1h-1zM15 2h1v1h-1zM19 2h1v1h-1zM22 2h1v1h-1zM24 2h1v1h-1zM25 2h1v1h-1zM26 2h1v1h-1zM28 2h1v1h-1zM0 3h1v1h-1zM2 3h1v1h-1zM3 3h1v1h-1zM4 3h1v1h-1zM6 3h1v1h-1zM8 3h1v1h-1zM9 3h1v1h-1zM13 3h1v1h-1zM14 3h1v1h-1zM15 3h1v1h-1zM16 3h1v1h-1zM17 3h1v1h-1zM22 3h1v1h-1zM24 3h1v1h-1zM25 3h1v1h-1zM26 3h1v1h-1zM28 3h1v1h-1zM0 4h1v1h-1zM2 4h1v1h-1zM3 4h1v1h-1zM4 4h1v1h-1zM6 4h1v1h-1zM8 4h1v1h-1zM9 4h1v1h-1zM10 4h1v1h-1zM11 4h1v1h-1zM12 4h1v1h-1zM16 4h1v1h-1zM17 4h1v1h-1zM18 4h1v1h-1zM19 4h1v1h-1zM20 4h1v1h-1zM22 4h1v1h-1zM24 4h1v1h-1zM25 4h1v1h-1zM26 4h1v1h-1zM28 4h1v1h-1zM0 5h1v1h-1zM6 5h1v1h-1zM8 5h1v1h-1zM9 5h1v1h-1zM15 5h1v1h-1zM16 5h1v1h-1zM22 5h1v1h-1zM28 5h1v1h-1zM0 6h1v1h-1zM1 6h1v1h-1zM2 6h1v1h-1zM3 6h1v1h-1zM4 6h1v1h-1zM5 6h1v1h-1zM6 6h1v1h-1zM8 6h1v1h-1zM10 6h1v1h-1zM12 6h1v1h-1zM14 6h1v1h-1zM16 6h1v1h-1zM18 6h1v1h-1zM20 6h1v1h-1zM22 6h1v1h-1zM23 6h1v1h-1zM24 6h1v1h-1zM25 6h1v1h-1zM26 6h1v1h-1zM27 6h1v1h-1zM28 6h1v1h-1zM8 7h1v1h-1zM9 7h1v1h-1zM14 7h1v1h-1zM15 7h1v1h-1zM19 7h1v1h-1zM20 7h1v1h-1zM0 8h1v1h-1zM2 8h1v1h-1zM3 8h1v1h-1zM4 8h1v1h-1zM5 8h1v1h-1zM6 8h1v1h-1zM9 8h1v1h-1zM10 8h1v1h-1zM12 8h1v1h-1zM13 8h1v1h-1zM14 8h1v1h-1zM17 8h1v1h-1zM18 8h1v1h-1zM19 8h1v1h-1zM22 8h1v1h-1zM23 8h1v1h-1zM24 8h1v1h-1zM25 8h1v1h-1zM26 8h1v1h-1zM1 9h1v1h-1zM3 9h1v1h-1zM4 9h1v1h-1zM7 9h1v1h-1zM9 9h1v1h-1zM16 9h1v1h-1zM17 9h1v1h-1zM18 9h1v1h-1zM19 9h1v1h-1zM21 9h1v1h-1zM22 9h1v1h-1zM23 9h1v1h-1zM24 9h1v1h-1zM28 9h1v1h-1zM3 10h1v1h-1zM6 10h1v1h-1zM7 10h1v1h-1zM11 10h1v1h-1zM13 10h1v1h-1zM15 10h1v1h-1zM16 10h1v1h-1zM17 10h1v1h-1zM22 10h1v1h-1zM1 11h1v1h-1zM2 11h1v1h-1zM3 11h1v1h-1zM5 11h1v1h-1zM8 11h1v1h-1zM10 11h1v1h-1zM14 11h1v1h-1zM19 11h1v1h-1zM20 11h1v1h-1zM21 11h1v1h-1zM23 11h1v1h-1zM25 11h1v1h-1zM27 11h1v1h-1zM0 12h1v1h-1zM2 12h1v1h-1zM3 12h1v1h-1zM4 12h1v1h-1zM6 12h1v1h-1zM7 12h1v1h-1zM8 12h1v1h-1zM9 12h1v1h-1zM10 12h1v1h-1zM11 12h1v1h-1zM12 12h1v1h-1zM13 12h1v1h-1zM14 12h1v1h-1zM15 12h1v1h-1zM16 12h1v1h-1zM17 12h1v1h-1zM19 12h1v1h-1zM25 12h1v1h-1zM26 12h1v1h-1zM1 13h1v1h-1zM2 13h1v1h-1zM3 13h1v1h-1zM5 13h1v1h-1zM9 13h1v1h-1zM12 13h1v1h-1zM13 13h1v1h-1zM16 13h1v1h-1zM17 13h1v1h-1zM18 13h1v1h-1zM19 13h1v1h-1zM20 13h1v1h-1zM21 13h1v1h-1zM22 13h1v1h-1zM24 13h1v1h-1zM28 13h1v1h-1zM0 14h1v1h-1zM4 14h1v1h-1zM5 14h1v1h-1zM6 14h1v1h-1zM7 14h1v1h-1zM8 14h1v1h-1zM11 14h1v1h-1zM12 14h1v1h-1zM15 14h1v1h-1zM16 14h1v1h-1zM18 14h1v1h-1zM20 14h1v1h-1zM21 14h1v1h-1zM22 14h1v1h-1zM23 14h1v1h-1zM25 14h1v1h-1zM26 14h1v1h-1zM0 15h1v1h-1zM2 15h1v1h-1zM5 15h1v1h-1zM7 15h1v1h-1zM8 15h1v1h-1zM15 15h1v1h-1zM16 15h1v1h-1zM23 15h1v1h-1zM24 15h1v1h-1zM27 15h1v1h-1zM0 16h1v1h-1zM6 16h1v1h-1zM13 16h1v1h-1zM17 16h1v1h-1zM19 16h1v1h-1zM23 16h1v1h-1zM25 16h1v1h-1zM26 16h1v1h-1zM0 17h1v1h-1zM2 17h1v1h-1zM3 17h1v1h-1zM8 17h1v1h-1zM9 17h1v1h-1zM13 17h1v1h-1zM17 17h1v1h-1zM18 17h1v1h-1zM19 17h1v1h-1zM20 17h1v1h-1zM21 17h1v1h-1zM22 17h1v1h-1zM23 17h1v1h-1zM24 17h1v1h-1zM26 17h1v1h-1zM28 17h1v1h-1zM0 18h1v1h-1zM4 18h1v1h-1zM6 18h1v1h-1zM11 18h1v1h-1zM13 18h1v1h-1zM14 18h1v1h-1zM15 18h1v1h-1zM16 18h1v1h-1zM17 18h1v1h-1zM22 18h1v1h-1zM23 18h1v1h-1zM26 18h1v1h-1zM0 19h1v1h-1zM3 19h1v1h-1zM4 19h1v1h-1zM5 19h1v1h-1zM7 19h1v1h-1zM8 19h1v1h-1zM10 19h1v1h-1zM11 19h1v1h-1zM14 19h1v1h-1zM18 19h1v1h-1zM20 19h1v1h-1zM21 19h1v1h-1zM22 19h1v1h-1zM23 19h1v1h-1zM24 19h1v1h-1zM27 19h1v1h-1zM0 20h1v1h-1zM2 20h1v1h-1zM4 20h1v1h-1zM5 20h1v1h-1zM6 20h1v1h-1zM12 20h1v1h-1zM17 20h1v1h-1zM18 20h1v1h-1zM20 20h1v1h-1zM21 20h1v1h-1zM22 20h1v1h-1zM23 20h1v1h-1zM24 20h1v1h-1zM26 20h1v1h-1zM27 20h1v1h-1zM28 20h1v1h-1zM8 21h1v1h-1zM10 21h1v1h-1zM13 21h1v1h-1zM16 21h1v1h-1zM17 21h1v1h-1zM18 21h1v1h-1zM20 21h1v1h-1zM24 21h1v1h-1zM25 21h1v1h-1zM26 21h1v1h-1zM27 21h1v1h-1zM28 21h1v1h-1zM0 22h1v1h-1zM1 22h1v1h-1zM2 22h1v1h-1zM3 22h1v1h-1zM4 22h1v1h-1zM5 22h1v1h-1zM6 22h1v1h-1zM11 22h1v1h-1zM12 22h1v1h-1zM13 22h1v1h-1zM14 22h1v1h-1zM15 22h1v1h-1zM17 22h1v1h-1zM19 22h1v1h-1zM20 22h1v1h-1zM22 22h1v1h-1zM24 22h1v1h-1zM25 22h1v1h-1zM26 22h1v1h-1zM0 23h1v1h-1zM6 23h1v1h-1zM8 23h1v1h-1zM9 23h1v1h-1zM13 23h1v1h-1zM15 23h1v1h-1zM16 23h1v1h-1zM19 23h1v1h-1zM20 23h1v1h-1zM24 23h1v1h-1zM0 24h1v1h-1zM2 24h1v1h-1zM3 24h1v1h-1zM4 24h1v1h-1zM6 24h1v1h-1zM8 24h1v1h-1zM14 24h1v1h-1zM17 24h1v1h-1zM20 24h1v1h-1zM21 24h1v1h-1zM22 24h1v1h-1zM23 24h1v1h-1zM24 24h1v1h-1zM26 24h1v1h-1zM27 24h1v1h-1zM28 24h1v1h-1zM0 25h1v1h-1zM2 25h1v1h-1zM3 25h1v1h-1zM4 25h1v1h-1zM6 25h1v1h-1zM8 25h1v1h-1zM9 25h1v1h-1zM12 25h1v1h-1zM16 25h1v1h-1zM18 25h1v1h-1zM19 25h1v1h-1zM23 25h1v1h-1zM25 25h1v1h-1zM26 25h1v1h-1zM27 25h1v1h-1zM28 25h1v1h-1zM0 26h1v1h-1zM2 26h1v1h-1zM3 26h1v1h-1zM4 26h1v1h-1zM6 26h1v1h-1zM8 26h1v1h-1zM9 26h1v1h-1zM11 26h1v1h-1zM12 26h1v1h-1zM13 26h1v1h-1zM14 26h1v1h-1zM15 26h1v1h-1zM16 26h1v1h-1zM17 26h1v1h-1zM19 26h1v1h-1zM21 26h1v1h-1zM22 26h1v1h-1zM23 26h1v1h-1zM24 26h1v1h-1zM25 26h1v1h-1zM26 26h1v1h-1zM27 26h1v1h-1zM0 27h1v1h-1zM6 27h1v1h-1zM11 27h1v1h-1zM12 27h1v1h-1zM15 27h1v1h-1zM18 27h1v1h-1zM20 27h1v1h-1zM21 27h1v1h-1zM22 27h1v1h-1zM23 27h1v1h-1zM25 27h1v1h-1zM27 27h1v1h-1zM0 28h1v1h-1zM1 28h1v1h-1zM2 28h1v1h-1zM3 28h1v1h-1zM4 28h1v1h-1zM5 28h1v1h-1zM6 28h1v1h-1zM8 28h1v1h-1zM10 28h1v1h-1zM11 28h1v1h-1zM13 28h1v1h-1zM15 28h1v1h-1zM17 28h1v1h-1zM19 28h1v1h-1zM22 28h1v1h-1zM23 28h1v1h-1zM24 28h1v1h-1zM25 28h1v1h-1zM26 28h1v1h-1z"/>
    </g>
    <text id="falk-name" class="dg-label dg-label-bold" x="210" y="186" style="font-size: 22px">Falk Sippach</text>
    <text id="falk-web" class="dg-label" x="210" y="218" style="font-size: 18px">embarc.de</text>
    <text id="falk-mail" class="dg-label" x="210" y="247" style="font-size: 18px">falk.sippach@embarc.de</text>
  </g>

  <g id="sven">
    <g transform="translate(558 0) scale(4.96552)" shape-rendering="crispEdges">
      <rect width="29" height="29" style="fill: #fff"/>
      <path style="fill: #000" d="M0 0h1v1h-1zM1 0h1v1h-1zM2 0h1v1h-1zM3 0h1v1h-1zM4 0h1v1h-1zM5 0h1v1h-1zM6 0h1v1h-1zM9 0h1v1h-1zM10 0h1v1h-1zM14 0h1v1h-1zM16 0h1v1h-1zM17 0h1v1h-1zM18 0h1v1h-1zM19 0h1v1h-1zM20 0h1v1h-1zM22 0h1v1h-1zM23 0h1v1h-1zM24 0h1v1h-1zM25 0h1v1h-1zM26 0h1v1h-1zM27 0h1v1h-1zM28 0h1v1h-1zM0 1h1v1h-1zM6 1h1v1h-1zM12 1h1v1h-1zM14 1h1v1h-1zM15 1h1v1h-1zM17 1h1v1h-1zM20 1h1v1h-1zM22 1h1v1h-1zM28 1h1v1h-1zM0 2h1v1h-1zM2 2h1v1h-1zM3 2h1v1h-1zM4 2h1v1h-1zM6 2h1v1h-1zM8 2h1v1h-1zM11 2h1v1h-1zM15 2h1v1h-1zM16 2h1v1h-1zM19 2h1v1h-1zM22 2h1v1h-1zM24 2h1v1h-1zM25 2h1v1h-1zM26 2h1v1h-1zM28 2h1v1h-1zM0 3h1v1h-1zM2 3h1v1h-1zM3 3h1v1h-1zM4 3h1v1h-1zM6 3h1v1h-1zM8 3h1v1h-1zM9 3h1v1h-1zM11 3h1v1h-1zM13 3h1v1h-1zM14 3h1v1h-1zM17 3h1v1h-1zM22 3h1v1h-1zM24 3h1v1h-1zM25 3h1v1h-1zM26 3h1v1h-1zM28 3h1v1h-1zM0 4h1v1h-1zM2 4h1v1h-1zM3 4h1v1h-1zM4 4h1v1h-1zM6 4h1v1h-1zM8 4h1v1h-1zM13 4h1v1h-1zM16 4h1v1h-1zM17 4h1v1h-1zM18 4h1v1h-1zM19 4h1v1h-1zM20 4h1v1h-1zM22 4h1v1h-1zM24 4h1v1h-1zM25 4h1v1h-1zM26 4h1v1h-1zM28 4h1v1h-1zM0 5h1v1h-1zM6 5h1v1h-1zM8 5h1v1h-1zM12 5h1v1h-1zM14 5h1v1h-1zM15 5h1v1h-1zM16 5h1v1h-1zM22 5h1v1h-1zM28 5h1v1h-1zM0 6h1v1h-1zM1 6h1v1h-1zM2 6h1v1h-1zM3 6h1v1h-1zM4 6h1v1h-1zM5 6h1v1h-1zM6 6h1v1h-1zM8 6h1v1h-1zM10 6h1v1h-1zM12 6h1v1h-1zM14 6h1v1h-1zM16 6h1v1h-1zM18 6h1v1h-1zM20 6h1v1h-1zM22 6h1v1h-1zM23 6h1v1h-1zM24 6h1v1h-1zM25 6h1v1h-1zM26 6h1v1h-1zM27 6h1v1h-1zM28 6h1v1h-1zM8 7h1v1h-1zM10 7h1v1h-1zM12 7h1v1h-1zM14 7h1v1h-1zM16 7h1v1h-1zM19 7h1v1h-1zM20 7h1v1h-1zM0 8h1v1h-1zM2 8h1v1h-1zM3 8h1v1h-1zM4 8h1v1h-1zM5 8h1v1h-1zM6 8h1v1h-1zM9 8h1v1h-1zM10 8h1v1h-1zM11 8h1v1h-1zM13 8h1v1h-1zM14 8h1v1h-1zM17 8h1v1h-1zM18 8h1v1h-1zM19 8h1v1h-1zM22 8h1v1h-1zM23 8h1v1h-1zM24 8h1v1h-1zM25 8h1v1h-1zM26 8h1v1h-1zM0 9h1v1h-1zM1 9h1v1h-1zM3 9h1v1h-1zM8 9h1v1h-1zM10 9h1v1h-1zM11 9h1v1h-1zM12 9h1v1h-1zM16 9h1v1h-1zM17 9h1v1h-1zM18 9h1v1h-1zM19 9h1v1h-1zM21 9h1v1h-1zM22 9h1v1h-1zM23 9h1v1h-1zM24 9h1v1h-1zM28 9h1v1h-1zM1 10h1v1h-1zM4 10h1v1h-1zM6 10h1v1h-1zM7 10h1v1h-1zM9 10h1v1h-1zM11 10h1v1h-1zM12 10h1v1h-1zM15 10h1v1h-1zM16 10h1v1h-1zM17 10h1v1h-1zM22 10h1v1h-1zM0 11h1v1h-1zM1 11h1v1h-1zM2 11h1v1h-1zM3 11h1v1h-1zM9 11h1v1h-1zM12 11h1v1h-1zM14 11h1v1h-1zM16 11h1v1h-1zM19 11h1v1h-1zM20 11h1v1h-1zM21 11h1v1h-1zM23 11h1v1h-1zM25 11h1v1h-1zM27 11h1v1h-1zM2 12h1v1h-1zM3 12h1v1h-1zM5 12h1v1h-1zM6 12h1v1h-1zM7 12h1v1h-1zM11 12h1v1h-1zM12 12h1v1h-1zM13 12h1v1h-1zM14 12h1v1h-1zM15 12h1v1h-1zM16 12h1v1h-1zM17 12h1v1h-1zM19 12h1v1h-1zM25 12h1v1h-1zM26 12h1v1h-1zM1 13h1v1h-1zM2 13h1v1h-1zM3 13h1v1h-1zM4 13h1v1h-1zM8 13h1v1h-1zM9 13h1v1h-1zM10 13h1v1h-1zM11 13h1v1h-1zM12 13h1v1h-1zM13 13h1v1h-1zM16 13h1v1h-1zM17 13h1v1h-1zM18 13h1v1h-1zM19 13h1v1h-1zM20 13h1v1h-1zM21 13h1v1h-1zM22 13h1v1h-1zM24 13h1v1h-1zM28 13h1v1h-1zM3 14h1v1h-1zM4 14h1v1h-1zM5 14h1v1h-1zM6 14h1v1h-1zM7 14h1v1h-1zM9 14h1v1h-1zM13 14h1v1h-1zM14 14h1v1h-1zM15 14h1v1h-1zM16 14h1v1h-1zM18 14h1v1h-1zM20 14h1v1h-1zM21 14h1v1h-1zM22 14h1v1h-1zM23 14h1v1h-1zM25 14h1v1h-1zM26 14h1v1h-1zM0 15h1v1h-1zM1 15h1v1h-1zM3 15h1v1h-1zM4 15h1v1h-1zM5 15h1v1h-1zM7 15h1v1h-1zM10 15h1v1h-1zM12 15h1v1h-1zM15 15h1v1h-1zM16 15h1v1h-1zM23 15h1v1h-1zM24 15h1v1h-1zM27 15h1v1h-1zM0 16h1v1h-1zM2 16h1v1h-1zM3 16h1v1h-1zM5 16h1v1h-1zM6 16h1v1h-1zM8 16h1v1h-1zM9 16h1v1h-1zM13 16h1v1h-1zM14 16h1v1h-1zM17 16h1v1h-1zM19 16h1v1h-1zM23 16h1v1h-1zM25 16h1v1h-1zM26 16h1v1h-1zM0 17h1v1h-1zM2 17h1v1h-1zM3 17h1v1h-1zM4 17h1v1h-1zM5 17h1v1h-1zM7 17h1v1h-1zM8 17h1v1h-1zM9 17h1v1h-1zM13 17h1v1h-1zM17 17h1v1h-1zM18 17h1v1h-1zM19 17h1v1h-1zM20 17h1v1h-1zM21 17h1v1h-1zM22 17h1v1h-1zM23 17h1v1h-1zM24 17h1v1h-1zM26 17h1v1h-1zM28 17h1v1h-1zM0 18h1v1h-1zM2 18h1v1h-1zM4 18h1v1h-1zM5 18h1v1h-1zM6 18h1v1h-1zM11 18h1v1h-1zM12 18h1v1h-1zM13 18h1v1h-1zM14 18h1v1h-1zM15 18h1v1h-1zM17 18h1v1h-1zM22 18h1v1h-1zM23 18h1v1h-1zM26 18h1v1h-1zM0 19h1v1h-1zM2 19h1v1h-1zM4 19h1v1h-1zM5 19h1v1h-1zM7 19h1v1h-1zM8 19h1v1h-1zM9 19h1v1h-1zM11 19h1v1h-1zM16 19h1v1h-1zM18 19h1v1h-1zM20 19h1v1h-1zM21 19h1v1h-1zM22 19h1v1h-1zM23 19h1v1h-1zM24 19h1v1h-1zM27 19h1v1h-1zM0 20h1v1h-1zM5 20h1v1h-1zM6 20h1v1h-1zM10 20h1v1h-1zM11 20h1v1h-1zM12 20h1v1h-1zM13 20h1v1h-1zM17 20h1v1h-1zM18 20h1v1h-1zM20 20h1v1h-1zM21 20h1v1h-1zM22 20h1v1h-1zM23 20h1v1h-1zM24 20h1v1h-1zM26 20h1v1h-1zM27 20h1v1h-1zM28 20h1v1h-1zM8 21h1v1h-1zM13 21h1v1h-1zM16 21h1v1h-1zM17 21h1v1h-1zM18 21h1v1h-1zM20 21h1v1h-1zM24 21h1v1h-1zM25 21h1v1h-1zM26 21h1v1h-1zM27 21h1v1h-1zM28 21h1v1h-1zM0 22h1v1h-1zM1 22h1v1h-1zM2 22h1v1h-1zM3 22h1v1h-1zM4 22h1v1h-1zM5 22h1v1h-1zM6 22h1v1h-1zM9 22h1v1h-1zM10 22h1v1h-1zM13 22h1v1h-1zM14 22h1v1h-1zM15 22h1v1h-1zM16 22h1v1h-1zM17 22h1v1h-1zM19 22h1v1h-1zM20 22h1v1h-1zM22 22h1v1h-1zM24 22h1v1h-1zM25 22h1v1h-1zM26 22h1v1h-1zM0 23h1v1h-1zM6 23h1v1h-1zM8 23h1v1h-1zM9 23h1v1h-1zM10 23h1v1h-1zM11 23h1v1h-1zM12 23h1v1h-1zM14 23h1v1h-1zM15 23h1v1h-1zM16 23h1v1h-1zM19 23h1v1h-1zM20 23h1v1h-1zM24 23h1v1h-1zM27 23h1v1h-1zM28 23h1v1h-1zM0 24h1v1h-1zM2 24h1v1h-1zM3 24h1v1h-1zM4 24h1v1h-1zM6 24h1v1h-1zM8 24h1v1h-1zM10 24h1v1h-1zM15 24h1v1h-1zM17 24h1v1h-1zM20 24h1v1h-1zM21 24h1v1h-1zM22 24h1v1h-1zM23 24h1v1h-1zM24 24h1v1h-1zM26 24h1v1h-1zM27 24h1v1h-1zM28 24h1v1h-1zM0 25h1v1h-1zM2 25h1v1h-1zM3 25h1v1h-1zM4 25h1v1h-1zM6 25h1v1h-1zM8 25h1v1h-1zM10 25h1v1h-1zM12 25h1v1h-1zM16 25h1v1h-1zM19 25h1v1h-1zM23 25h1v1h-1zM25 25h1v1h-1zM26 25h1v1h-1zM27 25h1v1h-1zM28 25h1v1h-1zM0 26h1v1h-1zM2 26h1v1h-1zM3 26h1v1h-1zM4 26h1v1h-1zM6 26h1v1h-1zM8 26h1v1h-1zM12 26h1v1h-1zM15 26h1v1h-1zM17 26h1v1h-1zM18 26h1v1h-1zM19 26h1v1h-1zM21 26h1v1h-1zM22 26h1v1h-1zM23 26h1v1h-1zM24 26h1v1h-1zM25 26h1v1h-1zM26 26h1v1h-1zM27 26h1v1h-1zM0 27h1v1h-1zM6 27h1v1h-1zM12 27h1v1h-1zM13 27h1v1h-1zM14 27h1v1h-1zM16 27h1v1h-1zM20 27h1v1h-1zM21 27h1v1h-1zM22 27h1v1h-1zM23 27h1v1h-1zM25 27h1v1h-1zM27 27h1v1h-1zM0 28h1v1h-1zM1 28h1v1h-1zM2 28h1v1h-1zM3 28h1v1h-1zM4 28h1v1h-1zM5 28h1v1h-1zM6 28h1v1h-1zM8 28h1v1h-1zM9 28h1v1h-1zM10 28h1v1h-1zM11 28h1v1h-1zM14 28h1v1h-1zM15 28h1v1h-1zM16 28h1v1h-1zM17 28h1v1h-1zM19 28h1v1h-1zM22 28h1v1h-1zM23 28h1v1h-1zM24 28h1v1h-1zM25 28h1v1h-1zM26 28h1v1h-1z"/>
    </g>
    <text id="sven-name" class="dg-label dg-label-bold" x="630" y="186" style="font-size: 22px">Sven Woltmann</text>
    <text id="sven-web" class="dg-label" x="630" y="218" style="font-size: 18px">HappyCoders.eu</text>
    <text id="sven-mail" class="dg-label" x="630" y="247" style="font-size: 18px">sven@happycoders.eu</text>
  </g>
</svg></div>
