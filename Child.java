public class Child extends Human implements Subscriber, Publisher{
    private int cryPower;
    private ArrayList<Object> subscribers;


    public Child(String name, State state, boolean isSleep, int fearLevel){
        super(String name, State state, boolean isSleep, int fearLevel);
        this.subscribers = new ArrayList<Object>();

    public void subscribe(Object subscriber){
        this.subscribers.add(subscriber);
    }

    public void unsubscribe(Object subscriber){
        int indexObject = this.subscribers.getIndex(subscriber);
        this.subscribers.remove(indexObject);
    }

    public void notifySubscribers(Stimul stimul){
        for (Subscriber subscriber: this.subscribers){
            subscriber.update(stimul);
        }
    }

    public void update(Stimul stimul){
        this.stimul = stimul;
    }



    @Overrite
    public Action react(Stimul stimul){

        switch (stimul){

            case CRY:
                return Action.GET_SCARED;
                break;
                
            case CALM:
                return Action.IGNORE;
                break;

            case SHAKE:
                return Action.CRY_LOUD;
                break;

            case THROWN:
                return Action.DIE;
                break;


            default:
                System.out.println("the stimulus is not perceived");
                return Action.IGNORE;
                break;
        }
    }

    @Overrite
    public doSmt(Action action){


        switch (action){
            case IGNORE:
                System.out.println(name + "ничего не делает");
                break;

            case GET_SCARED:
                System.out.println(name + "испугался");
                this.fearLevel += 10;
                System.out.println("Уровень страха у " + name + "повысился");
                this.cry()
                break;

            case DIE:
                this.die()
                break;

        }
    }

    private void cry(){
        if 
    }

    @Overrite
    private void die(){
        notifySubscribers(Stimul.DEATH);
        for (Object subscriber: subscribers){
            unsubscribe(subscriber);
        }
    }

    @Overritepublic String toString(){
        return "Child" + name;
    }
}