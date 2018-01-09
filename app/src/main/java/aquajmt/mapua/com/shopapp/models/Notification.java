package aquajmt.mapua.com.shopapp.models;

/**
 * Created by IPC on 1/8/2018.
 */

public class Notification {
    private String title, details;

    public Notification(){}

    public Notification(String title, String details) {
        this.title = title;
        this.details = details;

    }

    public String getTitle() {
        return title;
    }

    public String getDetails() {
        return details;
    }

}
