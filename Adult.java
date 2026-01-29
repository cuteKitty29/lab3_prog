import java.util.ArrayList;

public class Adult extends Human implements Subscriber, Publisher{
    private int selfControl;
    private int disgustLevel;
    private int panicLevel;
    private ArrayList<Object> listObjectsHands;
    private ArrayList<Subscriber> subscribers;  

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
        doSmt(react(stimul));
    }

    @Override
    public Action react(Stimul stimul){

        switch (stimul){

            case CRY:
                System.out.println("Stimul CRY ADULT REACT");
                if (selfControl > 10){
                    return Action.COMFORT;
                }
                else{
                    return Action.THROW;
                }

            case SHOUT:
                switch(this.state){
                    case AGRESSIVE:
                        return Action.SHAKE;
                    case FURIOUS:
                        return Action.THROW;
                    case PANIC:
                        return Action.GET_SCARED;
                    case CALM:
                        return Action.COMFORT;
                }


                
            case CALM:
                return Action.IGNORE;

            default:
                System.out.println("the stimulus is not perceived");
                return Action.IGNORE;
        }
    }

    @Override
    public void doSmt(Action action){


        switch (action){
            case IGNORE:
                System.out.println(name + " do nothing");
                break;

            case GET_SCARED:
                System.out.println(name + " got scared");
                this.fearLevel += 10;
                System.out.println("The level fear of " + name + " has risen");
                break;

            case COMFORT:
                this.tryComfort();  
                break;
            
            case THROW:
                this.throwChild();
                break;

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

    private void shake(){
        System.out.println("Adult " + name + " is shaking now!");
        notifySubscribers(Stimul.SHAKE);
    }

    private void throwChild(){
        System.out.println("adult " + name + " has thrown the child!!!");
        notifySubscribers(Stimul.THROWN);
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