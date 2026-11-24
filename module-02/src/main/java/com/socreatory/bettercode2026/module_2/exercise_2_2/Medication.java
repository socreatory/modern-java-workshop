package com.socreatory.bettercode2026.module_2.exercise_2_2;

import java.util.List;
import java.util.Objects;

/// Ein Medikament mit Wirkstoff und Dosierung (Produkt, „und“).
public record Medication(Drug drug, Dosage dosage) {

    public Medication {
        Objects.requireNonNull(drug);
        Objects.requireNonNull(dosage);
    }
}

/// Der Medikationsplan eines Patienten.
record MedicationPlan(String patientName, List<Medication> medications) {

    MedicationPlan {
        Objects.requireNonNull(patientName);
        medications = List.copyOf(medications);
    }
}
