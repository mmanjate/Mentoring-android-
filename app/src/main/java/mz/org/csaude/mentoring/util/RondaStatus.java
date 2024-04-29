package mz.org.csaude.mentoring.util;

import androidx.annotation.NonNull;

public enum RondaStatus {
    FINISHED("Terminado"),
    ON_GOING("Em curso");

    private String value;

    RondaStatus(final String value) {
        this.value = value;
    }

    @NonNull
    @Override
    public String toString() {
        return this.value;
    }
}
