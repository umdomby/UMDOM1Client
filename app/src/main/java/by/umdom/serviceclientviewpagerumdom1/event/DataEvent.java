package by.umdom.serviceclientviewpagerumdom1.event;

public class DataEvent<T> {

    private T data;

    public DataEvent(T data) {

        this.data = data;
    }

    public T getData() {

        return data;
    }
}
