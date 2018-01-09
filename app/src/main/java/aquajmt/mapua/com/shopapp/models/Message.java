package aquajmt.mapua.com.shopapp.models;

/**
 * Created by IPC on 1/9/2018.
 */

public class Message {
    private String title, details;

    public Message(){}

    public Message(String title, String details) {
        this.title = title;
        this.details = details;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return details;
    }
}
