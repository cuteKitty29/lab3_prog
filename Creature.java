public class Creature implements Publisher, Subscriber{
    private State state;
    private String name;
    private boolean isSleep;
    private boolean isAlive;
    private int fearLevel;

    public Creature(String name, State state, boolean isSleep, int fearLevel){
        this.name = name;
        this.state = state;
        this.isAlive = true;
        this.isSleep = isSleep;
        this.fearLevel = fearLevel;
    }


    public void subscribe(Object subscriber){
        this.subscribers.add(subscriber);
    }

    public void unsubscribe(Object subscriber){
        int indexObject = this.subscribers.getIndex(subscriber);
        this.subscribers.remove(indexObject);
    }

    public void notifySubscribers(Stimul stumul){
        for (Subscriber subscriber: this.subscribers){
            subscriber.update(stimul);
        }
    }

    public void update(Stimul stimul){
        this.stimul = stimul;
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
                            break;
                        case AGRESSIVE, FURIOUS:
                            return Action.SHOUT;
                            break;
                        case PANIC:
                            return Action.GET_SCARED;
                            break;
                    }
        
                    break;

                case SMELL:
                    return Action.GET_SMELL;
                    break;

                case SHAKE:
                    switch(this.state){
                        case CRY, PANIC:
                            return Action.CRY;
                            break;
                        case AGRESSIVE, FURIOUS:
                            return Action.SHOUT;
                            break;
                        case CALM:
                            return Action.IGNORE;
                            break;
                        
                    }
                case CALM:
                    return Action.IGNORE;
                    break;
                case SHOUT:
                    switch(this.state){
                        case CRY, PANIC, CALM:
                            return Action.GET_SCARED;
                            break;
                        case AGRESSIVE, FURIOUS:
                            return Action.SHOUT;
                            break;
                    }
            } 
        }
        else{
            System.out.println("Creature" + name + "is died!!!")
        }

 
    }

    public doSmt(Action action){
        if (isAlive){
            switch (action){
                case IGNORE:
                    System.out.println(name + "ничего не делает");
                    break;

                case GET_SCARED:
                    System.out.println("Creature " name + " got scared");
                    this.fearLevel += 10;
                    System.out.println("Creature " + name + "\'s level of  fear has risen");
                    break;
                case CRY:

            }
        }
        else{
            System.out.println("Creature is died!!!")
        }


    }

    public void die(){
        this.isAlive = false;

    }
    public void cry(){
        System.out.println("AAAAAAAAAA");
        System.out.println("Creature " + name + " is crying!")
    }

    @Overrite
    public String toString(){
        return "Creature" + name;
    }
}