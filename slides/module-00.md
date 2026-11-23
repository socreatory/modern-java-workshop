---
theme: ./theme
title: Titelslide
training:
  name: Modernes Java in der Praxis
  subtitle: Die Features, die wirklich zählen
speaker:
  name: Falk Sippach & Sven Woltmann
  org: betterCode() Java 2026
  date: 24.11.2026
colorSchema: light
canvasWidth: 960
fonts:
  provider: none
codeCopy: false
htmlAttrs:
  lang: de
layout: title
---

---
layout: content
title: Copyright-Hinweis
---

# Copyright-Hinweis

<div class="hc-text" id="text">

**Module 1 und 3: © 2026 Sven Woltmann, HappyCoders GmbH.**  
**Module 2 und 4: © 2026 Falk Sippach, embarc Software Consulting GmbH.**  
Alle Rechte vorbehalten.

Dieses Schulungsmaterial ist urheberrechtlich geschützt. Ohne ausdrückliche, vorherige und schriftliche Zustimmung der jeweiligen Rechteinhaber darf kein Teil dieses Werkes – weder vollständig noch auszugsweise – in irgendeiner Form vervielfältigt, verbreitet, öffentlich zugänglich gemacht, übersetzt oder auf sonstige Weise verwertet werden.

Die im Kurs vermittelten Inhalte, Konzepte und Ideen dürfen selbstverständlich für private und berufliche Zwecke genutzt werden, soweit dadurch keine Vervielfältigung oder Weitergabe des Schulungsmaterials selbst erfolgt.

</div>

---
layout: content
title: "Eure Trainer: Falk Sippach und Sven Woltmann"
---

# Eure Trainer: Falk Sippach und Sven Woltmann

<div class="hc-row hc-row-pictures">

<div class="hc-row-main">

<div class="hc-figure" id="falk-figure"><div class="hc-image hc-image-sized" id="falk" style="width: 240px"><img src="/images/module-00/falk-sippach.webp" alt=""></div><div class="hc-text hc-source"><strong>Falk Sippach</strong><br>Software Architect and Consultant at embarc</div></div>

</div>

<div class="hc-row-aside">

<div class="hc-figure" id="sven-figure"><div class="hc-image hc-image-sized" id="sven" style="width: 240px"><img src="/images/module-00/sven-woltmann.webp" alt=""></div><div class="hc-text hc-source"><strong>Sven Woltmann</strong><br>Java Expert, Trainer, Speaker &amp; Author at HappyCoders.eu</div></div>

</div>

</div>

---
layout: content
title: Fragen? Jederzeit.
---

# Fragen? Jederzeit.

<div class="hc-text" id="text">

Bitte unterbrecht uns jederzeit, wenn euch etwas unklar ist.  
Es gibt **keine dummen Fragen** – nur verpasste Chancen, etwas zu verstehen.

**Fragt, wenn …**

</div>

<ul class="hc-list" id="list"><li data-part="list[0]">ihr etwas nicht ganz verstanden habt.</li><li data-part="list[1]">ihr wissen wollt, warum wir etwas so machen.</li><li data-part="list[2]">ihr eine andere Sichtweise habt oder etwas ergänzen möchtet.</li><li data-part="list[3]">ihr merkt, dass wir zu schnell sind oder zu viel auf einmal erklären.</li></ul>

<div class="hc-text" id="text2">

Fragen helfen **nicht nur euch**, sondern auch allen anderen – oft denkt jemand anderes dasselbe!

</div>

---
layout: content
title: Ablauf und Agenda
clicks: 6
---

# Ablauf und Agenda

<div class="hc-table hc-table-dense hc-table-widths" id="table"><table><colgroup><col style="width: 16.00%"><col style="width: 64.00%"><col style="width: 20.00%"></colgroup><thead><tr><th>Zeit</th><th>Modul</th><th>Wer</th></tr></thead><tbody><tr data-part="table#1" :class="{ 'hc-row-dim': ($clicks &gt;= 1 &amp;&amp; $clicks &lt; 6) }"><td data-part="table#1[0]">09:00</td><td data-part="table#1[1]">Modul 0: Einführung</td><td data-part="table#1[2]">Sven</td></tr><tr data-part="table#2" :class="{ 'hc-row-dim': ($clicks &gt;= 2 &amp;&amp; $clicks &lt; 6) }"><td data-part="table#2[0]">09:10</td><td data-part="table#2[1]"><strong>Modul 1: Weniger Zeremonie</strong></td><td data-part="table#2[2]">Sven</td></tr><tr data-part="table#3" :class="{ 'hc-row-dim': ($clicks &gt;= 1 &amp;&amp; $clicks &lt; 6) }"><td data-part="table#3[0]">10:10</td><td data-part="table#3[1]">Pause</td><td data-part="table#3[2]"></td></tr><tr data-part="table#4" :class="{ 'hc-row-dim': ($clicks &gt;= 1 &amp;&amp; $clicks &lt; 2) || ($clicks &gt;= 3 &amp;&amp; $clicks &lt; 6) }"><td data-part="table#4[0]">10:25</td><td data-part="table#4[1]"><strong>Modul 2: Die Domäne im Code</strong></td><td data-part="table#4[2]">Falk</td></tr><tr data-part="table#5" :class="{ 'hc-row-dim': ($clicks &gt;= 1 &amp;&amp; $clicks &lt; 6) }"><td data-part="table#5[0]">12:00</td><td data-part="table#5[1]">Mittagspause</td><td data-part="table#5[2]"></td></tr><tr data-part="table#6" :class="{ 'hc-row-dim': ($clicks &gt;= 1 &amp;&amp; $clicks &lt; 3) || ($clicks &gt;= 4 &amp;&amp; $clicks &lt; 6) }"><td data-part="table#6[0]">12:45</td><td data-part="table#6[1]"><strong>Modul 3: Nebenläufigkeit im Alltag</strong></td><td data-part="table#6[2]">Sven</td></tr><tr data-part="table#7" :class="{ 'hc-row-dim': ($clicks &gt;= 1 &amp;&amp; $clicks &lt; 6) }"><td data-part="table#7[0]">14:30</td><td data-part="table#7[1]">Pause</td><td data-part="table#7[2]"></td></tr><tr data-part="table#8" :class="{ 'hc-row-dim': ($clicks &gt;= 1 &amp;&amp; $clicks &lt; 4) || ($clicks &gt;= 5 &amp;&amp; $clicks &lt; 6) }"><td data-part="table#8[0]">14:45</td><td data-part="table#8[1]"><strong>Modul 4: Collections, Streams und Ausblick</strong></td><td data-part="table#8[2]">Falk</td></tr><tr data-part="table#9" :class="{ 'hc-row-dim': ($clicks &gt;= 1 &amp;&amp; $clicks &lt; 5) }"><td data-part="table#9[0]">15:45</td><td data-part="table#9[1]"><strong>Modul 5: Ausblick und Abschluss</strong></td><td data-part="table#9[2]">Falk</td></tr><tr data-part="table#10" :class="{ 'hc-row-dim': ($clicks &gt;= 1 &amp;&amp; $clicks &lt; 6) }"><td data-part="table#10[0]">16:00</td><td data-part="table#10[1]">Ende</td><td data-part="table#10[2]"></td></tr></tbody></table></div>

---
layout: content
title: Hinweise zu den Übungen
---

# Hinweise zu den Übungen

<ul class="hc-list hc-list-spaced" id="list"><li data-part="list[0]" v-click="1">Git-Repository habt ihr vorab bekommen – ein Maven-Projekt, ein Modul pro Workshop-Modul, für JDK 25 – genau 25, weil Modul 3 ein Preview-Feature nutzt.</li><li data-part="list[1]" v-click="2">Mehrere Übungen pro Modul – Skelett in den <code>exercise</code>-Paketen, Musterlösung in den <code>solution</code>-Paketen.</li><li data-part="list[2]" v-click="3">Die Aufgabenstellung erklären wir direkt vor der Übung; ausführlich steht sie im Kommentar der Übungsklasse.</li><li data-part="list[3]" v-click="4">Fragt jederzeit – gerade, wenn ihr feststeckt.</li><li data-part="list[4]" v-click="5">In die Musterlösung erst schauen, nachdem ihr es selbst versucht habt.</li><li data-part="list[5]" v-click="6">KI-Assistenz in der IDE ausschalten – sonst schlägt sie euch die Musterlösung aus den <code>solution</code>-Paketen vor.</li><li data-part="list[6]" v-click="7">Am Ende zeigen und erklären wir die Musterlösung – und diskutieren sie gemeinsam.</li></ul>

---
layout: content
title: "Mehr davon: die Modern Java Masterclass"
---

# Mehr davon: die Modern Java Masterclass

<div class="hc-diagram hc-diagram-center" id="module"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 840 270" width="840" height="270" role="img" aria-labelledby="t-masterclass d-masterclass">
  <title id="t-masterclass">Die drei Module der Modern Java Masterclass</title>
  <desc id="d-masterclass">Modul 1, Java 12 bis 17, ein Tag, plus Modul 2, Java 18 bis 21, ein oder zwei Tage, plus Modul 3, Java 22 bis 25, ein oder zwei Tage.</desc>
  <text id="kopf" class="dg-label dg-label-left" x="0" y="18" style="font-size: 22px">Drei Module – einzeln buchbar oder als Gesamtpaket</text>

  <g id="modul-1">
    <rect x="0" y="50" width="220" height="210" style="fill: #fff1a8"/>
    <text id="modul-1-name" class="dg-label" x="110" y="95" style="font-size: 21px">Modul 1</text>
    <text id="modul-1-java" class="dg-label dg-label-bold" x="110" y="155" style="font-size: 30px">Java 12–17</text>
    <text id="modul-1-dauer" class="dg-label" x="110" y="215" style="font-size: 21px">1 Tag</text>
  </g>

  <text class="dg-label dg-label-bold" x="265" y="152" style="font-size: 56px">+</text>

  <g id="modul-2">
    <rect x="310" y="50" width="220" height="210" style="fill: #93effd"/>
    <text id="modul-2-name" class="dg-label" x="420" y="95" style="font-size: 21px">Modul 2</text>
    <text id="modul-2-java" class="dg-label dg-label-bold" x="420" y="155" style="font-size: 30px">Java 18–21</text>
    <text id="modul-2-dauer" class="dg-label" x="420" y="215" style="font-size: 21px">1 oder 2 Tage</text>
  </g>

  <text class="dg-label dg-label-bold" x="575" y="152" style="font-size: 56px">+</text>

  <g id="modul-3">
    <rect x="620" y="50" width="220" height="210" style="fill: #00d7f7"/>
    <text id="modul-3-name" class="dg-label" x="730" y="95" style="font-size: 21px">Modul 3</text>
    <text id="modul-3-java" class="dg-label dg-label-bold" x="730" y="155" style="font-size: 30px">Java 22–25</text>
    <text id="modul-3-dauer" class="dg-label" x="730" y="215" style="font-size: 21px">1 oder 2 Tage</text>
  </g>
</svg></div>

<div class="hc-text" id="text">

<span class="hc-nobr">Online oder vor Ort</span> <span class="hc-dot">·</span> auch inhouse für euer Team  
→ [www.socreatory.com/de/trainings/modern-java](https://www.socreatory.com/de/trainings/modern-java)

</div>
