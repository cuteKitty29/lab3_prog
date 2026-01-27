public class Human extends Creature{

    public Human(String name, State state, boolean isSleep, int fearLevel){
        super(String name, State state, boolean isSleep, int fearLevel);
    }

    public class Eyes{
        private EyeColor[] eyeColor[];
        private int clarity;
        private boolean isEyeOpen;


        public EyeColor[] getEyeColor(){
            return this.eyeColor[];
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

    @Overrite
    public Action react(Stimul stimul){

        switch (stimul){

            case CRY:
                return Action.GET_SCARED;
                break;

            case CALM:
                return Action.IGNORE;
                break;
        }
    }

    @Overrite
    public doSmt(Action action){
        if (isAlive){
            switch (action){
                case IGNORE:
                    System.out.println(name + "ничего не делает");
                    break;

                case GET_SCARED:
                    System.out.println(name + "испугался");
                    this.fearLevel += 10;
                    System.out.println("Уровень страха у " + name + "повысился");
                    break;
            }
        }
        else{
            System.out.println("Creature ia died!!!")
        }


    }
    @Overrite
    private void die(){
        notifySubscribers(Stimul.DEATH);
        for (Object subscriber: subscribers){
            unsubscribe(subscriber);
        }
    }
}