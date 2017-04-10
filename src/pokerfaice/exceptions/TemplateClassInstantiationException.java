package pokerfaice.exceptions;

public class TemplateClassInstantiationException extends RuntimeException {
    private static final long serialVersionUID = 3426482988368836996L;
    
    public TemplateClassInstantiationException(){
        super("Cannot Instantiate a Template Class!");
    }
}
