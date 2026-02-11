import java.lang.ArrayList;

public class Script implements Seglenton, Publisher, Subscriber{
    private static Script INSTANCE;
    private ArrayList<Subscriber> subscribers;
    private Stimul triger;

    private Script(){
        this.subscribers = new ArrayList<Subscriber>();
    }

    private void changeTriger(Stimul triger){
        this.triger = triger;
    }

    @Override
    getInstance(){
        if (Script.INSTANCE == null){
            Script.INSTANCE = new Script();
        }
        return Script.INSTANCE;
    }

    @Override
    public void subscribe(Subscriber sb){
        subscribers.add(sb);
    }

    @Override
    public void unsubscribe(Subscriber sb){
        subscribers.remove(sb);
    }

    @Override
    private void notifySubscribers(Stimul triger){
        for (Subscriber sb: subscribers){
            sb.update(Stimul triger);
        }
    }

    @Override
    public void update(Stimul triger){
        changeTriger(triger);
    }
    


    public static void main(String[] args) {
        Child myChild = new Child("Petya", State.CALM, false, 10);
        Adult myAdult = new Adult("Danil", State.CALM, false, 10);
        Basket myBasket = new Basket();


        myChild.childNose.changeTarget(myAdult);
        myAdult.stink(Smell.SWEAT);
        myAdult.stink(Smell.VINEGAR);
        myAdult.stink(Smell.SOUR_CABBAGE);
        myAdult.stink(Smell.UNWASHED_DRESS);
        myAdult.putSmtInBasket(myBasket, myChild);
        myChild.subscribe(myAdult);
        myAdult.subscribe(myChild);

        myChild.wakeUp();
        myAdult.haveImpression();
        System.out.println("is Adult a child's eyes' target? "+ myChild.childEyes.isObjectTarget(myAdult));
        myAdult.expirienceSensation();
        System.out.println("The child's nose's target is " + myChild.childNose.getTarget());
        myChild.childNose.swell();
        myChild.sniff(myAdult);
        myAdult.feelChill();
        myChild.shout();

    }
}

interface Seglenton{
    Seglenton getInstance();
}

interface Publisher{
    void subscribe(Subscriber sb);
    void unsubscribe(Subscriber sb);
    void notifySubscribers(Stimul triger);
}

interface Subscriber{
    void update(Stimul triger);
}

