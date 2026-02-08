public class Basket extends MyObject{
    private MyObject content;

    public Basket(){
        content = new Nothing();
    }

    public Basket(MyObject obj){
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
            content = new Nothing();
            return prvContent;
        }
    }
}