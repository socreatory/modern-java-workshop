package com.socreatory.bettercode2026.module_4_solutions.solution_4_1;

/// Ein Ereignis der Event-Historie: Quelle (z. B. ein Dienst) und Nachricht.
public record Event(String source, String message) {

    @Override
    public String toString() {
        return source + ": " + message;
    }
}
