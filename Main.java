public class Main{
    public static void main(String[] args){
        Child myChild = new Child("Petya", State.CALM, false, 10);
        Adult myAdult = new Adult("Danil", State.CALM, false, 10);
        myChild.subscribe(myAdult);
        myAdult.subscribe(myChild);

        myChild.cry();

    }
}