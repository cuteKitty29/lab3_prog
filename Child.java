import java.util.ArrayList;


public class Child extends Human implements Subscriber, Publisher{
    private int cryPower;
    private ArrayList<Subscriber> subscribers;

    private static int id = 0;

    private int selfId;


    public Child(String name, State state, boolean isSleep, int fearLevel){
        super(name, state, isSleep, fearLevel);
        this.subscribers = new ArrayList<Subscriber>();
        this.selfId = Child.id + 1;
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

        doSmt(react(stimul));
    }



    @Override
    public Action react(Stimul stimul){

        switch (stimul){

            case CRY:
                System.out.println("CRY");

                return Action.GET_SCARED;
                
            case CALM:
                System.out.println("CALM");

                return Action.IGNORE;

            case SHAKE:
                System.out.println("SHAKE");


                return Action.CRY_LOUD;

            case THROWN:
                System.out.println("THROWN");

                return Action.DIE;


            default:
                System.out.println("DEFAULT");

                System.out.println("the stimulus is not perceived");
                return Action.IGNORE;
        }
    }

    @Override
    public void doSmt(Action action){


        switch (action){
            case IGNORE:
                System.out.println(name + " do nothing");

            case GET_SCARED:
                System.out.println(name + " has got scared");
                this.fearLevel += 10;
                System.out.println("The level fear of " + name + " has risen");
                this.cry();

            case DIE:
                this.die();

        }
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