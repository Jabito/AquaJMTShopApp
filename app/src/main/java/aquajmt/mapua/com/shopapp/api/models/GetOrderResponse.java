package aquajmt.mapua.com.shopapp.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Jabito on 27/08/2017.
 */

public class GetOrderResponse {

    @SerializedName("orders")
    @Expose
    private List<OrderInfo> orders;
    @SerializedName("responseDesc")
    @Expose
    private String responseDesc;

    public List<OrderInfo> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderInfo> orders) {
        this.orders = orders;
    }

    public String getResponseDesc() {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }

    @Override
    public String toString() {
        return "ClassPojo [orders = " + orders + ", responseDesc = " + responseDesc + "]";
    }
}
