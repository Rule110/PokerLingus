package ai.exceptions;

public class OffTheScaleException extends RuntimeException {
    public OffTheScaleException(){
        super("Scale is from 0 to 9");
    }
    
    private static final long serialVersionUID = -2543821449267285742L;
}
