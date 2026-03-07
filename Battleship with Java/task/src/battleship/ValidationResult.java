package battleship;

public class ValidationResult {
    boolean isValid;
    String error;


    public ValidationResult(boolean isValid, String error) {
        this.isValid = isValid;
        this.error = error;
    }
    public ValidationResult(boolean isValid) {
        this.isValid = isValid;
    }
}
