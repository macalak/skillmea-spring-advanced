package ite.librarymaster.event;

public class GenericEvent <T> {
    private T payload;

    public GenericEvent(T payload) {
        this.payload = payload;
    }

    public T getPayload() {
        return payload;
    }
}
