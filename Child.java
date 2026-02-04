import java.util.ArrayList;


public class Child extends Human implements Subscriber, Publisher{
    private int cryPower;
    private ArrayList<Subscriber> subscribers;
    private State state;
    private  String name;
    private boolean isSleep;
    private boolean isAlive;
    private int fearLevel;  
    private Nose childNose;
    private Eyes childEyes;


    public Child(String name, State state, boolean isSleep, int fearLevel){
        super(name, state, isSleep, fearLevel);
        this.subscribers = new ArrayList<Subscriber>();
        childNose = new Nose();
        childEyes = new Eyes(30, new Nothing());
    }

    public void subscribe(Subscriber subscriber){
        this.subscribers.add(subscriber);
    }

    public void unsubscribe(Subscriber subscriber){
        int indexObject = this.subscribers.indexOf(subscriber);
        this.subscribers.remove(indexObject);
    }

    public void notifySubscribers(Stimul stimul){
        for (Subscriber subscriber: this.subscribers){
            subscriber.update(stimul);
        }
    }

    public void update(Stimul stimul){
        System.out.println("Update child");
        if (stimul == Stimul.SHAKE){
            System.out.println("IT IS ");
        }
        react(stimul);
    }



    @Override
    public void react(Stimul stimul){

        switch (stimul){

            case CRY:
                getScared();
                break;
                
            case CALM:                
                ignore();
                break;

            case SHAKE:
                cry();

            case THROWN:
                die();
                break;


            default:
                System.out.println("DEFAULT");

                System.out.println("the stimulus is not perceived");
                ignore();
        }
    }

    @Override
    public void wakeUp(){
        System.out.println(toString() + " has woken up");
        changeState(State.CALM);
        childNose.wakeUp();
        childEyes.openEyes();
    }

    public void cry(){
        System.out.println("AAAAAAAAA");
        System.out.println("Child " + name + " is crying!");
        notifySubscribers(Stimul.CRY);
    }

  //  @Override
    private void die(){
        notifySubscribers(Stimul.DEATH);
        for (Subscriber subscriber: subscribers){
            unsubscribe(subscriber);
        }
    }

    @Override 
    public boolean equals(Object obj){
        if (obj instanceof Child){
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return "Child" + name;
    }
}