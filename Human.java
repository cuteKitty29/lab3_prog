import java.util.ArrayList;

public class Human extends Creature implements Subscriber, Publisher{
    public ArrayList<Object> listObjectsHands;
    public ArrayList<Subscriber> subscribers;  

    public Human(String name, State state, boolean isSleep, int fearLevel){
        super(name, state, isSleep, fearLevel);
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
        react(stimul);
    }

    public class Eyes{
        private EyeColor[] eyeColor[];
        private int clarity;
        private boolean isEyeOpen;


        public EyeColor[][] getEyeColor(){
            return this.eyeColor;
        }

        public int getClarity(){
            return this.clarity;
        }

    }

    public class Nose{
        private int sensitivity;

        public int getSensitivity(){
            return this.sensitivity;
        }

        public void swell(){
            this.sensitivity += 20;
        }
    }

    @Override
    public Action react(Stimul stimul){

        switch (stimul){

            case CRY:
                return Action.GET_SCARED;

            case CALM:
                return Action.IGNORE;
        }
        return Action.IGNORE;
    }

    @Override
    public void doSmt(Action action){
        if (isAlive){
            switch (action){
                case IGNORE:
                    System.out.println(name + " do nothing");

                case GET_SCARED:
                    System.out.println(name + " has got scared");
                    this.fearLevel += 10;
                    System.out.println("The fear level of " + name + " has risen");
            }
        }
        else{
            System.out.println("Creature ia died!!!");
        }


    }
    
    private void die(){
        notifySubscribers(Stimul.DEATH);
        for (Subscriber subscriber: subscribers){
            unsubscribe(subscriber);
        }
    }

    @Override 
    public boolean equals(Object obj){
        if (obj instanceof Human){
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return "Human " + name;
    }
}

