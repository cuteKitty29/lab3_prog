public enum EyeColor{
    OYSTER_GRAY("Oyester gray"),
    OPAL_CREAM("Opal cream"),
    MIXED("Mixed");

    private String name;

    EyeColor(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}