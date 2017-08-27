package aquajmt.mapua.com.shopapp.api.models;

import aquajmt.mapua.com.shopapp.models.ShopLogin;

/**
 * Created by Jabito on 27/08/2017.
 */

public class LoginResponse {
    ShopInfo shopInfo;
    ShopLogin shopLogin;

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
}
