public enum Smell{
    SWEAT("Sweet"),
    SOUR_CABBAGE("Sour cabbage"),
    UNWASHED_DRESS("Unwashed dress"),
    VINEGAR("Vinegar");

    private String name;

    Smell(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
    
}