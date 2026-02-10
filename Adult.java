import java.util.ArrayList;

public class Adult extends Human implements Subscriber, Publisher, Smellable{
    private int selfControl;
    private int disgustLevel;
    private int panicLevel;
    //private ArrayList<Object> listObjectsHands;
    private ArrayList<Smell> smells;
    private ArrayList<Subscriber> subscribers;
    private State state;
    private  String name;
    private boolean isSleep;
    private boolean isAlive;
    private int fearLevel;  
    private int ID;
    private int freezingLevel;
    public Nose adultNose;
    public Eyes adultEyes;

    public Adult(String name, State state, boolean isSleep, int fearLevel){
        super(name, state, isSleep, fearLevel);
        this.name = name;
        this.state = state;
        this.disgustLevel = 0;
        this.panicLevel = 0;
        this.selfControl = 100;
        this.freezingLevel = 0;
      //  this.listObjectsHands = new ArrayList<Object>();
        this.subscribers = new ArrayList<Subscriber>();
        this.ID = MyObject.maxID++;
        this.smells = new ArrayList<Smell>();
        this.adultEyes = new Eyes();
        this.adultNose = new Nose();
    }
    
    public int getSelfControl(){
        return this.selfControl;
    }
    
    @Override
    public void subscribe(Subscriber subscriber){
        this.subscribers.add(subscriber);
    }

    @Override
    public void unsubscribe(Subscriber subscriber){
        int indexObject = this.subscribers.indexOf(subscriber);
        this.subscribers.remove(indexObject);
    }

    @Override
    public void notifySubscribers(Stimul stimul){
        System.out.println("Stimul is " + stimul);
        for (Subscriber subscriber: this.subscribers){
            subscriber.update(stimul);
        }
    }

    @Override
    public void update(Stimul stimul){
        System.out.println("Update adult");
        react(stimul);
    }

    @Override
    public void react(Stimul stimul){
        try{

            switch (stimul){

                case CRY:
                    System.out.println("Stimul CRY ADULT REACT");
                    selfControl -= 50;
                    if (selfControl > 10 && disgustLevel < 70){
                        tryComfort();
                        break;
                    }
                    else{
                        throwChild();
                        break;
                    }

                case SHOUT:
                    switch(state){
                        case AGRESSIVE:
                            shake();
                            break;
                        case FURIOUS:
                            throwChild();
                            break;
                        case PANIC:
                            getScared();
                            break;
                        case CALM:
                            tryComfort();
                    }


                    
                case CALM:
                    ignore();
                    break;

                default:
                    System.out.println("the stimulus is not perceived");
                    ignore();
            }
        }
        catch (ChildDiedException e){
            System.out.println(e.getMessage());
            System.out.println("Level of self control of adult decreased to " + getSelfControl());
        }
    }


    private void comrfortWords(){
        System.out.println(name + " try to comfort with words");
        //notifySubscribers(Stimul.COMFORT_WITH_WORDS);
    }

    private void tryComfort(){
        comrfortWords();
        shake();
    }

    public void haveImpression(){
        System.out.println(toString() + " has an impression");
    }

    public void expirienceSensation(){
        System.out.println(this + " expirienced a sensation");
    }

    public void seem(){
        System.out.println("It seems to " + this);
    }

    @Override
    public ArrayList<Smell> getSmell(){
        if (Math.random() > 40){
            changeDisgustingLevel(20);
        }
        System.out.println(smells);
        return this.smells;
    }
    
    public void shake(){
        System.out.println(this + " is shaking now!");
        notifySubscribers(Stimul.SHAKE);
    }

    private void throwChild() throws ChildDiedException{
        System.out.println(this + " has thrown the child!!!");
        throw new ChildDiedException("Child was thrown and died!!!");
    }

    public void putSmtInBasket(Basket basket, MyObject obj){
        try{
            basket.putSmtIn(obj);
        }
        catch (BasketIsFullException e){
            System.out.println(e.getMessage());
            System.out.println("There is in the basket" + basket.knowConten());
        }
    }

    public void setSmtOnTable(MyObject obj, Table tbl){
        try{
          tbl.setSmt(obj);  
        }
        catch(TableIsFullException e){
            System.out.println(e.getMessage());
            System.out.println("There are on the table ");
            for (MyObject elem : tbl.getListOfContent()) {
                System.out.print(elem + " ");                
            }
        }      
    }

    public void changeDisgustingLevel(int chn){
        disgustLevel += chn;
        System.out.println("Disgusting level of " + this + "changed on " + chn);
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof Adult){
            return obj.hashCode() == this.hashCode();
        }
        return false;
    }
    
    @Override
    public String toString(){
        return "Adult " + name;
    }

    @Override
    public int hashCode(){
        return "Adult".length() * name.length() * ID;
    }

}