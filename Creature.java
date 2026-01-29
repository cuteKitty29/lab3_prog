import java.util.ArrayList;

public class Creature implements Publisher, Subscriber{
    public ArrayList<Object> listObjectsHands;
    public ArrayList<Subscriber> subscribers;  
    public State state;
    public final String name;
    public boolean isSleep;
    public boolean isAlive;
    public int fearLevel;
  //  public Stimul stimul;


    public Creature(String name, State state, boolean isSleep, int fearLevel){
        this.name = name;
        this.state = state;
        this.isAlive = true;
        this.isSleep = isSleep;
        this.fearLevel = fearLevel;
    }


    public void subscribe(Subscriber subscriber){
        this.subscribers.add(subscriber);
    }

    @Override
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
        react(stimul);
    }

    public String getName(){
        return name;
    }

    public State getState(){
        return state;
    }

    // public void changeState(State chState){
        // this.state = chState;
    // }

    public Action react(Stimul stimul){
        if (isAlive){
            switch(stimul){
                case CRY:
                    switch(this.state){
                        case CRY:
                            return Action.CRY;
                        case AGRESSIVE, FURIOUS:
                            return Action.SHOUT;
                        case PANIC:
                            return Action.GET_SCARED;
                    }

                case SMELL:
                    return Action.GET_SMELL;

                case SHAKE:
                    switch(this.state){
                        case CRY, PANIC:
                            return Action.CRY;
                        case AGRESSIVE, FURIOUS:
                            return Action.SHOUT;
                        case CALM:
                            return Action.IGNORE;
                        
                    }
                case CALM:
                    return Action.IGNORE;
                case SHOUT:
                    switch(this.state){
                        case CRY, PANIC, CALM:
                            return Action.GET_SCARED;
                        case AGRESSIVE, FURIOUS:
                            return Action.SHOUT;
                    }
            } 
        }
        else{
            System.out.println("Creature" + name + "is died!!!");
        }

        return Action.IGNORE;    
    }

    public void doSmt(Action action){
        if (isAlive){
            switch (action){
                case IGNORE:
                    System.out.println(name + " do nothing");

                case GET_SCARED:
                    System.out.println("Creature " + name + " got scared");
                    this.fearLevel += 10;
                    System.out.println("Creature " + name + "\'s level of  fear has risen");
                case CRY:
                    cry();
                case SHOUT:
                    shout();

            }
        }
        else{
            System.out.println("Creature is died!!!");
        }


    }

    private void die(){
        this.isAlive = false;

    }
    private void cry(){
        System.out.println("UA UA UA UA");
        System.out.println("Creature " + name + " is crying!");
        this.state = State.CRY;
        notifySubscribers(Stimul.CRY);
    }

    private void shout(){
        System.out.println("AAAAAAAA");
        System.out.println("Creature " + name + " is shouting!");
        notifySubscribers(Stimul.SHOUT);
    }

    @Override 
    public boolean equals(Object obj){
        if (obj instanceof Creature){
            return true;
        }
        return false;
    }
    @Override
    public String toString(){
        return "Creature " + name;
    }
}