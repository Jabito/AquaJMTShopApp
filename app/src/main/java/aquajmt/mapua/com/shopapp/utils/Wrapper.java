package aquajmt.mapua.com.shopapp.utils;

/**
 * Created by Bryan on 7/19/2017.
 */
public class Wrapper<T> {
    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
