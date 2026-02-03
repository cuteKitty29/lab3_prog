import java.util.ArrayList;

public class Human extends Creature implements Subscriber, Publisher{ 
    public ArrayList<Subscriber> subscribers;  
    private State state;
    private  String name;
    private boolean isSleep;
    private boolean isAlive;
    private int fearLevel;
    private  Nose humanNose;
    private  Eyes humanEyes;
    private  int ID;

    public Human(String name, State state, boolean isSleep, int fearLevel){
        super(name, state, isSleep, fearLevel);
        humanNose = new Nose();
        humanEyes = new Eyes();
        ID = MyObject.maxID++;
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
        private EyeColor[] eyeColor;
        private int clarity = 0;
        private boolean isEyeOpen = false;
        private MyObject target;

        public void openEyes(){
            System.out.println("Eyes is open");
            isEyeOpen = true;
            clarity += 30;
            getClarity();
            System.out.println("The color of eyes is ");
            for (EyeColor color: eyeColor){
                System.out.println(color);
            }
        }

        public MyObject getTarget(){
            return target;
        }

        public void changeTarget(MyObject obj){
            target = obj;
            System.out.println(toString() + "\' eyes\' target was changed on" + obj.toString());
        }

        public EyeColor[] getEyeColor(){
            return eyeColor;
        }


        public String getClarity(){
            if (clarity > 50){
                System.out.println("The eyes of " + toString() + "perceive everything. The clarity is " + clarity);
            }
            else {
                System.out.println("The eyes of " + toString() + "couldn't perceive anything. The clarity is only " + clarity);

            }
        }

    }

    public class Nose{
        private int sensitivity;
        private boolean isSleep;

        public Nose(){
            sensitivity = 0;
            isSleep = true;
        }

        public void wakeUp(){
            isSleep = false;
            sensitivity += 60;
            move();
            wrinkle();
        }

        private void move(){
            if (!isSleep){
                System.out.println("Nose is moving");

            }
            else{
                System.out.println("Nose sleeps!");   //it also may be a mistake
            }
        }

        private void wrinkle(){
            if (!isSleep){   
                System.out.println("Nose id wrinkling");
            }
            else{
                System.out.println("Nose is Sleep!");  //it may be a mistake
            }
        }

        public int getSensitivity(){
            return sensitivity;
        }

        public void swell(){
            sensitivity += 20;
        }
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
            default:
                ignore();
        }
    }
    

    @Override
    public void wakeUp(){
        System.out.println(toString() + " has woken up");
        changeState(State.CALM);
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
            return hashCode() == obj.hashCode(); 
        }
        return false;
    }

    @Override
    public int hashCode(){
        return "Human".length() * name.length() * ID;
    }

    @Override
    public String toString(){
        return "Human " + name;
    }
}

