import java.util.ArrayList;


public class Child extends Human implements Subscriber, Publisher{
    private int cryPower;
    private ArrayList<Subscriber> subscribers;
    private State state;
    private  String name;
    private boolean isSleep;
    private boolean isAlive;
    private int fearLevel;  
    public Nose childNose;
    public Eyes childEyes;


    public Child(String name, State state, boolean isSleep, int fearLevel){
        super(name, state, isSleep, fearLevel);
        this.name = name;
        this.subscribers = new ArrayList<Subscriber>();
        childNose = new Nose();
        EyeColor[] colors = new EyeColor[]{EyeColor.MIXED, EyeColor.OPAL_CREAM, EyeColor.OYSTER_GRAY};
        childEyes = new Eyes(colors, Nothing.getInstance());
        this.isAlive = true;
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
        for (Subscriber subscriber: this.subscribers){
            subscriber.update(stimul);
        }
    }

    @Override
    public void update(Stimul stimul){
        System.out.println("Update child on stimul " + stimul);
        react(stimul);
    }



    @Override
    public void react(Stimul stimul){
        if (isAlive){
            switch (stimul){

                case CRY:
                    getScared();
                    break;
                    
                case CALM:                
                    ignore();
                    break;

                case SHAKE:
                    if (Math.random() > 0.55){
                        cry();
                    }
                    else{
                        calmDown();
                    }
                    break;

                /*case THROWN:
                    die();
                    break;*/


                default:
                    System.out.println("DEFAULT");

                    System.out.println("the stimulus is not perceived");
                    ignore();
            }
        }
    }

    @Override
    public void wakeUp(){
        System.out.println(toString() + " has woken up");
        changeState(State.CALM);
        childNose.wakeUp();
        childEyes.openEyes();
    }
    @Override
    public void cry(){
        System.out.println("AAAAAAAAA");
        System.out.println("Child " + name + " is crying!");
        notifySubscribers(Stimul.CRY);
    }


/*    private void die() {
        System.out.println(this +  " died");
        isAlive = false;
        notifySubscribers(Stimul.DEATH);


        
    }*/

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