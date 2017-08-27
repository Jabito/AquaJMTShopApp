package aquajmt.mapua.com.shopapp.models;

import aquajmt.mapua.com.shopapp.api.models.ShopInfo;

/**
 * Created by Jabito on 27/08/2017.
 */

public class CreateShopResponse {

    public ShopInfo shop;
    public String responseCode;
    public String responseDesc;

    public ShopInfo getShop() {
        return shop;
    }

    public void setShop(ShopInfo shop) {
        this.shop = shop;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDesc() {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }
}
