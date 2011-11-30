package CustomExceptions;

public class NegativeSizeException extends Exception {
    private static final long serialVersionUID = 7092308381925248369L;
    
    private String errMsg = "";

    public NegativeSizeException()
    {
      super();             // call superclass constructor
      errMsg = "Size values must be >= 0";
    }
    
    public NegativeSizeException(String err)
    {
      super(err);     // call super class constructor
      errMsg = err;  // save message
    }
    
    public String getMessage() {
        return errMsg;
    }
}
