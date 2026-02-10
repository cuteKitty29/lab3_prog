public class Script{
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