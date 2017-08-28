package aquajmt.mapua.com.shopapp.api.models;

import java.util.List;

/**
 * Created by Jabito on 27/08/2017.
 */

public class GetOrderResponse {

    private List<OrderInfo> orders;
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
