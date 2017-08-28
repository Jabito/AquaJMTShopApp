package aquajmt.mapua.com.shopapp.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import aquajmt.mapua.com.shopapp.models.ShopLogin;

/**
 * Created by Jabito on 27/08/2017.
 */

public class LoginResponse {

    @SerializedName("shopInfo")
    @Expose
    private ShopInfo shopInfo;
    @SerializedName("shopLogin")
    @Expose
    private ShopLogin shopLogin;
    @SerializedName("responseCode")
    @Expose
    private String responseCode;
    @SerializedName("responseDesc")
    @Expose
    private String responseDesc;

    public ShopInfo getShopInfo() {
        return shopInfo;
    }

    public void setShopInfo(ShopInfo shopInfo) {
        this.shopInfo = shopInfo;
    }

    public ShopLogin getShopLogin() {
        return shopLogin;
    }

    public void setShopLogin(ShopLogin shopLogin) {
        this.shopLogin = shopLogin;
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

    @Override
    public String toString() {
        return "ClassPojo [shopInfo = " + shopInfo + ", shopLogin = " + shopLogin + ", responseCode = " + responseCode + ", responseDesc = " + responseDesc + "]";
    }
}
