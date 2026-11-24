package com.socreatory.bettercode2026.module_4.exercise_4_1;

/// Ein Ereignis der Event-Historie: Quelle (z. B. ein Dienst) und Nachricht.
public record Event(String source, String message) {

    @Override
    public String toString() {
        return source + ": " + message;
    }
}
