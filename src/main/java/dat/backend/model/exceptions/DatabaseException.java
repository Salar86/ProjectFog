package dat.backend.model.exceptions;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseException extends Exception { // Open/Closed principle. Using the Exception class as Parent and extending its use without modifying the Parent.
    public DatabaseException(String message) {
        super(message);
        Logger.getLogger("web").log(Level.SEVERE, message);
    }
    public DatabaseException(Exception ex, String message) {
        super(message);
        Logger.getLogger("web").log(Level.SEVERE, message, ex.getMessage());
    }
}
