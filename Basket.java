public class Basket extends MyObject{
    private MyObject content;

    public Basket(){
        content = Nothing.getInstance();
    }

    public Basket(MyObject obj){
        super();
        content = obj;
    }

    public void putSmtIn(MyObject obj) throws BasketIsFullException{
        if (content instanceof Nothing){
            content = obj;
            System.out.println(obj + " put on busket");
        }
        else{
            throw  new BasketIsFullException("Basket is full now!");
        }
    }

    public MyObject getContent() throws  BasketIsEmptyException{
        if (content instanceof Nothing){
            throw  new BasketIsEmptyException("There is nothing on the basket!");
        }
        else{
            MyObject prvContent = content;
            content = Nothing.getInstance();
            return prvContent;
        }
    }
    public MyObject knowConten(){
        return content;
    }
}