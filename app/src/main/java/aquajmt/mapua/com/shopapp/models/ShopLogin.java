package aquajmt.mapua.com.shopapp.models;

import org.json.JSONObject;

/**
 * Created by Jabito on 27/08/2017.
 */

public class ShopLogin {

    private String id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String middleName;
    private String staffOf;
    private String createdOn;
    private String updatedOn;

    public ShopLogin(){}

    public ShopLogin(JSONObject slj) {
        try {
            setId(null == slj.getString("id") ? "" : slj.getString("id"));
            setUsername(null == slj.getString("username") ? "" : slj.getString("username"));
            setEmail(null == slj.getString("email") ? "" : slj.getString("email"));
            setFirstName(null == slj.getString("firstName") ? "" : slj.getString("firstName"));
            setLastName(null == slj.getString("lastName") ? "" : slj.getString("lastName"));
            setMiddleName(null == slj.getString("middleName") ? "" : slj.getString("middleName"));
            setStaffOf(null == slj.getString("staffOf") ? "" : slj.getString("staffOf"));
            setCreatedOn(null == slj.getString("createdOn") ? "" : slj.getString("createdOn"));
            setUpdatedOn(null == slj.getString("updatedOn") ? "" : slj.getString("updatedOn"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getStaffOf() {
        return staffOf;
    }

    public void setStaffOf(String staffOf) {
        this.staffOf = staffOf;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }
}
