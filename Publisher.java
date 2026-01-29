interface Publisher{
    void subscribe(Subscriber subscriber);
    void unsubscribe(Subscriber subscribe);
    void notifySubscribers(Stimul stimul);

}