import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpOption;
import java.net.http.HttpOption.Http3DiscoveryMode;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/// Demo 4.3 – HTTP/3 im HTTP Client (JEP 517, Java 26)
///
/// Compact Source File außerhalb von Maven, denn `HttpClient.Version.HTTP_3` gibt es erst ab Java 26.
/// Start im Terminal (JDK 26 oder neuer, Netzwerkzugang nötig):
///
///     java Http3Demo.java
///
/// Gezeigt werden die Strategien, mit denen sich HTTP/3 aushandeln lässt. Entscheidend ist
/// nicht die *angefragte*, sondern die *tatsächlich verwendete* Version: `response.version()`.
///
///   - Der Client-Default ist HTTP/2 mit Fallback auf HTTP/1.1. HTTP/3 gibt es nur auf Wunsch.
///   - `Http3DiscoveryMode.ANY`: HTTP/3 und HTTP/2 parallel versuchen, HTTP/3 gewinnt, wenn es klappt.
///   - `Http3DiscoveryMode.ALT_SVC`: erst HTTP/2, HTTP/3 nur, wenn der Server es per `Alt-Svc` ankündigt.
///   - `Http3DiscoveryMode.HTTP_3_URI_ONLY`: ausschließlich HTTP/3, kein Fallback.

static final Duration TIMEOUT = Duration.ofSeconds(5);

record Row(String strategy, String requested, String actual, String result) {}

final List<Row> rows = new ArrayList<>();

void main() {
    IO.println("Java " + Runtime.version() + " – Request/Response-Versionen im Vergleich");
    IO.println();

    String google = "https://www.google.com/";
    String cloudflare = "https://cloudflare-quic.com/";
    String noH3 = "https://github.com/";

    // (a) Client-Default: HTTP/2, Fallback auf HTTP/1.1
    try (var client = newClient(null)) {
        run("a) Client-Default", "(HTTP_2)", client, request(google, null, null));
    }

    // (b) Client-Präferenz HTTP_3 – gilt für alle Requests dieses Clients. Ohne Alt-Svc-Wissen
    //     ist das Ergebnis beim ersten Kontakt offen: HTTP/3 und HTTP/2 laufen im Rennen (ANY).
    try (var client = newClient(Version.HTTP_3)) {
        run("b) Client-Präferenz HTTP_3", "HTTP_3", client, request(google, null, null));
        run("   … Host ohne HTTP/3", "HTTP_3", client, request(noH3, null, null));
    }

    // (c) Request-Präferenz: nur dieser Request fragt HTTP/3 an
    try (var client = newClient(null)) {
        run("c) Request-Präferenz HTTP_3", "HTTP_3", client, request(cloudflare, Version.HTTP_3, null));
        run("   … zweiter Request ohne", "(HTTP_2)", client, request(cloudflare, null, null));
    }

    // (d) HTTP/3 only: kein Fallback
    try (var client = newClient(null)) {
        run("d) HTTP_3_URI_ONLY", "HTTP_3", client,
                request(cloudflare, Version.HTTP_3, Http3DiscoveryMode.HTTP_3_URI_ONLY));
        run("   … Host ohne HTTP/3", "HTTP_3", client,
                request(noH3, Version.HTTP_3, Http3DiscoveryMode.HTTP_3_URI_ONLY));
        run("   … Port ohne QUIC", "HTTP_3", client,
                request("https://localhost:8443/", Version.HTTP_3, Http3DiscoveryMode.HTTP_3_URI_ONLY));
    }

    // (e) Alt-Svc-Entdeckung: erst HTTP/2, danach HTTP/3, sobald der Server es angekündigt hat
    try (var client = newClient(null)) {
        var altSvc = request(google, Version.HTTP_3, Http3DiscoveryMode.ALT_SVC);
        run("e) ALT_SVC, 1. Aufruf", "HTTP_3", client, altSvc);
        run("   ALT_SVC, 2. Aufruf", "HTTP_3", client, altSvc);
    }

    // (f) ANY auf einem Host ohne HTTP/3: Fallback auf HTTP/2 statt Fehler
    try (var client = newClient(null)) {
        run("f) ANY, Host ohne HTTP/3", "HTTP_3", client,
                request(noH3, Version.HTTP_3, Http3DiscoveryMode.ANY));
    }

    printTable();
}

HttpClient newClient(Version preferred) {
    var builder = HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.NORMAL)
            .connectTimeout(TIMEOUT);
    if (preferred != null) {
        builder.version(preferred);
    }
    return builder.build();
}

HttpRequest request(String uri, Version version, Http3DiscoveryMode mode) {
    var builder = HttpRequest.newBuilder(URI.create(uri)).timeout(TIMEOUT).GET();
    if (version != null) {
        builder.version(version);
    }
    if (mode != null) {
        builder.setOption(HttpOption.H3_DISCOVERY, mode);
    }
    return builder.build();
}

void run(String strategy, String requested, HttpClient client, HttpRequest request) {
    var label = request.uri().getHost()
            + (request.uri().getPort() > 0 ? ":" + request.uri().getPort() : "");
    try {
        HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());
        rows.add(new Row(strategy + " [" + label + "]", requested, String.valueOf(response.version()),
                "HTTP " + response.statusCode()));
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        rows.add(new Row(strategy + " [" + label + "]", requested, "–", "unterbrochen"));
    } catch (Exception e) {
        var message = e.getMessage() == null ? "" : ": " + shorten(e.getMessage());
        rows.add(new Row(strategy + " [" + label + "]", requested, "–",
                "FEHLER " + e.getClass().getSimpleName() + message));
    }
}

String shorten(String text) {
    return text.length() > 50 ? text.substring(0, 47) + "..." : text;
}

void printTable() {
    var format = "%-52s %-10s %-10s %s";
    IO.println(format.formatted("Strategie [Host]", "angefragt", "tatsächlich", "Ergebnis"));
    IO.println("-".repeat(110));
    rows.forEach(row -> IO.println(format.formatted(row.strategy(), row.requested(), row.actual(), row.result())));
}
