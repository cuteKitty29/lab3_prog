import java.util.ArrayList;

public class Adult extends Human implements Subscriber, Publisher{
    private int selfControl;
    private int disgustLevel;
    private int panicLevel;
    private ArrayList<Object> listObjectsHands;
    private ArrayList<Subscriber> subscribers;
    private State state;
    private  String name;
    private boolean isSleep;
    private boolean isAlive;
    private int fearLevel;  

    public Adult(String name, State state, boolean isSleep, int fearLevel){
        super(name, state, isSleep, fearLevel);
        this.disgustLevel = 0;
        this.panicLevel = 0;
        this.selfControl = 100;
        this.listObjectsHands = new ArrayList<Object>();
        this.subscribers = new ArrayList<Subscriber>();
    }
    
    public int getSelfControl(){
        return this.selfControl;
    }
    
    public void subscribe(Subscriber subscriber){
        this.subscribers.add(subscriber);
    }

    public void unsubscribe(Subscriber subscriber){
        int indexObject = this.subscribers.indexOf(subscriber);
        this.subscribers.remove(indexObject);
    }

    public void notifySubscribers(Stimul stimul){
        if (stimul == Stimul.SHAKE){
            System.out.println("IT IS SHAKING");
        }
        for (Subscriber subscriber: this.subscribers){
            subscriber.update(stimul);
        }
    }

    public void update(Stimul stimul){
        System.out.println("Update adult");
        react(stimul);
    }

    @Override
    public void react(Stimul stimul){

        switch (stimul){

            case CRY:
                System.out.println("Stimul CRY ADULT REACT");
                if (selfControl > 10){
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
    
    public void shake(){
        System.out.println(this + " is shaking now!");
        notifySubscribers(Stimul.SHAKE);
    }

    private void throwChild(){
        System.out.println(this + " has thrown the child!!!");
        notifySubscribers(Stimul.THROWN);
    }

    public void setSmtOnTable(MyObject obj, Table tbl){
        tbl.setSmt(obj);        
    }



    @Override
    public boolean equals(Object obj){
        if (obj instanceof Adult){
            return true;
        }
        return false;
    }
    
    @Override
    public String toString(){
        return "Adult" + name;
    }

}