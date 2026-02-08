import java.util.ArrayList;

public class Creature extends MyObject implements Publisher, Subscriber, Smellable{
    public ArrayList<Subscriber> subscribers;  
    private ArrayList<Smell> smells;
    private State state;
    private String name;
    private boolean isSleep;
    private boolean isAlive;
    private int fearLevel;
    private final int ID;
    private int freezingLevel;


    public Creature(String name, State state, boolean isSleep, int fearLevel){
        this.name = name;
        this.state = state;
        this.isAlive = true;
        this.isSleep = isSleep;
        this.fearLevel = fearLevel;
        this.ID = MyObject.maxID++;
        this.freezingLevel = 0;
    }


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
        react(stimul);
    }

    public void changeState(State changeState){
        state = changeState;
        System.out.println("The State of " + this + " was changed on " + state);
    }

    public State getState(){
        return state;
    }

    public ArrayList<Smell> stink(){
        for (Smell s : smells) {
            System.out.println(this + " skinks " + s);                    
        }
        return smells;
    }

    public void wakeUp(){
        System.out.println(toString() + " has woken up");
        changeState(State.CALM);
        
    }

    public void react(Stimul stimul){
        if (isAlive){
            switch(stimul){
                case CRY:
                    switch(state){
                        case CRY:
                            cry();
                            break;
                        case AGRESSIVE, FURIOUS, PANIC:
                            shout();
                            break;
                        default:
                            ignore();
                    }

                case SMELL:
                    getSmell();
                    break;

                case SHAKE:
                    double chance = Math.random();
                    switch(state){
                        case CRY, PANIC:
                            if (chance > 0.5){
                                cry();
                            }
                            else{
                                calmDown();
                            }
                            break;
                        case AGRESSIVE, FURIOUS:
                            if (chance > 0.70){
                                shout();
                            }
                            else{
                                calmDown();
                            }
                            break;
                        case CALM:
                            ignore();
                            break;
                        default:
                            ignore();
                        
                    }
                case CALM:
                    ignore();
                    break;

                
                case SHOUT:
                    switch(state){
                        case CALM:
                            getScared();
                            break;
                        case CRY, PANIC:
                            run();
                            break;
                        case AGRESSIVE, FURIOUS:
                            shout();
                            break;
                        default:
                            ignore();
                    }
            } 
        }
        else{
            System.out.println("Creature" + name + "is died!!!");
        }  
    }
    @Override
    public ArrayList<Smell> getSmell(){
        return smells;
    }


    public void ignore(){
        System.out.println("Creature" + name + " does nothing");
    }

    public void getScared(){
        System.out.println("Creature " + name + " got scared");
        this.fearLevel += 10;
        if (fearLevel > 78){
            changeState(State.PANIC);
        }
    }

    public void run(){
        System.out.println(toString() + " ran away!");
        changeState(State.IGNORE);
        notifySubscribers(Stimul.RUN_AWAY);
    }

    private void die(){
        this.isAlive = false;

    }
    public void cry(){
        changeState(State.CRY);
        System.out.println("UA UA UA UA");
        System.out.println("Creature " + name + " is crying!");
        notifySubscribers(Stimul.CRY);
    }

    public void shout(){
        System.out.println("AAAAAAAA");
        System.out.println("Creature " + name + " is shouting!");

        notifySubscribers(Stimul.SHOUT);
    }

    public void calmDown(){
        changeState(State.CALM);
    }

    @Override
    public int hashCode(){
        return "Creature".length() * name.length() * ID;
    }

    @Override 
    public boolean equals(Object obj){
        if (obj instanceof Creature){
            return obj.hashCode() == hashCode();
        }
        return false;
    }
    @Override
    public String toString(){
        return "Creature " + name;
    }
}