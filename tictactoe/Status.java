package tictactoe;

public enum Status {
    NOT_BEGUN ("Game is not started"),
    IN_PLAY ("Game in progress"),
    X_WON ("X wins"),
    O_WON ("O wins"),
    DRAW ("Draw");

    private final String message;

    String getMessage() {
        return message;
    }

    Status(String msg) {
        this.message = msg;
    }
}
