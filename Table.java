import java.lang.reflect.Array;
import java.util.ArrayList;

public class Table extends  MyObject{
    private String name = "Table";
    private final int ID;
    private ArrayList<MyObject> listOfContent;
    private boolean isTableFool;

    public Table(){
        this.ID = MyObject.maxID++;
        this.listOfContent = new ArrayList<MyObject>();
        this.isTableFool = false;
    }

    public ArrayList<MyObject> getListOfContent(){
        return listOfContent;
    }

    public void setSmt(MyObject obj) throws TableIsFullException{
        listOfContent.add(obj);
        if (!isTableFool){
            System.out.println(obj + " was set on the table");
            if (listOfContent.size() > 3){
                isTableFool = true;
            }
        }
        else{
            throw new TableIsFullException("Table is full now!!!");
        }
    }

    public MyObject getSmt(MyObject obj){
        if (listOfContent.remove(obj)){
            return obj;
        }
        return new Nothing();
    }
}