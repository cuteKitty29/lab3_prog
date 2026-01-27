public class Adult extends Human implements Subscriber, Publisher{
    private int selfControl;
    private int disgustLevel;
    private int panicLevel;
    private ArrayList<Object> listObjectsHands;
    private ArrayList<Object> subscribers;  

    public Adult(String name, State state, boolean isSleep, int fearLevel){
        super(String name, State state, boolean isSleep, int fearLevel);
        this.disgustLevel = 0;
        this.panicLevel = 0;
        this.selfControl = 100;
        this.listObjectsHands = new ArrayList<Object>();
        this.subscribers = new ArrayList<Object>();
    }
    
    public int getSelfControl(){
        return this.selfControl;
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

    @Overrite
    public Action react(Stimul stimul){

        switch (stimul){

            case CRY:
                if (selfControl > 0){
                    return Action.COMFORT;
                }
                else{
                    return Action.THROW;
                }
                break;

                
            case CALM:
                return Action.IGNORE;
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
        System.out.println(name + "try to comfort with words");
        notifySubscribers(COMFORT_WITH_WORDS);
    }

    private void tryComfort(){
        comrfortWords();
        shake();
    }

    private void throwChild(){
        System.out.println(name + "threw the child!!!");
        notifySubscribers(THROWN);
    }
    
    @Overrite
    public String toString(){
        return "Adult" + name;
    }

}