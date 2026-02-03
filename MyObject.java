public class MyObject{
    static int maxID = 0;
    private final int ID;
    private String name;

    public MyObject(){
        ID = MyObject.maxID++;
    }

    public int getID(){
        return ID;
    }

    public String getName(){
        return name;
    }
    @Override
    public String toString(){
        return "My object " + name;
    }
    @Override
    public int hashCode(){
        return ID * name.length();
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof  MyObject){
            return obj.hashCode() == this.hashCode();
        }
        return  false;
    }
}