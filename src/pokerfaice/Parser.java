package pokerfaice;

public class Parser {
    private static final String[] messages = {
      "", //0
      "", //1
      "", //2
      "", //3
      "", //4
      "", //5       
    };
    
    public static final String getMessage(int internalCode){
        return messages[internalCode];
    }
}
