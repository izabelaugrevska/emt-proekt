package mk.ukim.finki.userservice.domain.models;

public enum ActivityLevel {
    NONE(1), LOW(1.2), MEDIUM(1.375), HIGH(1.55), EXTREMELY_HIGH(1.725);

    public double value;

    ActivityLevel(double value) {
        this.value = value;
    }
}
