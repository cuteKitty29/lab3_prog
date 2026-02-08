public enum Stimul{
    CRY("Cry"), SMELL("Smell"), SHAKE("Shake"), CALM("Calm"), DEATH("Death"),
    COMFORT_WITH_WORDS("Comfort with words"), THROWN("Thrown"), SHOUT("Shout"), 
    RUN_AWAY("Run away");

    private final String name;

    Stimul(String name){
        this.name = name;
    }
    @Override
    public String toString(){
        return  name;
    }

}