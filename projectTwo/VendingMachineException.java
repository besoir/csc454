import java.lang.Exception;

public class VendingMachineException extends Exception {
    
    public static final long serialVersionUID = 1L;
    public VendingMachineException(String in) {
        super(in);
    }
}