package com.socreatory.bettercode2026.module_2_solutions.solution_2_2;

import java.util.List;

/// Die Operation war im Prinzip erfolgreich, es gibt aber Hinweise.
record Warning<T>(T value, List<String> warnings) implements Result<T> {

    Warning {
        warnings = List.copyOf(warnings);
    }
}
