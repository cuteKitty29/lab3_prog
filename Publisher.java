interface Publisher{
    void subscribe(Subscriber);
    void unsubsribe(Subscriber);
    void notifySubscribers(Stimul stimul);

}