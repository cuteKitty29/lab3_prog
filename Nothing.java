public class Nothing extends MyObject{
    private final String name = "nothing"; 
    private static Nothing INSTANCE;

    private Nothing(){}

  public static Nothing getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new Nothing();
    }
    return INSTANCE;
  }
    
    @Override
    public String toString(){
        return name;
    }

    @Override
    public boolean equals(Object obj){
        return false;
    } 
}


