package aquajmt.mapua.com.shopapp.api;

import aquajmt.mapua.com.shopapp.api.models.ShopInfo;
import aquajmt.mapua.com.shopapp.models.CreateShopResponse;
import aquajmt.mapua.com.shopapp.models.LoginJson;
import aquajmt.mapua.com.shopapp.models.ShopLogin;

/**
 * Created by Jabito on 26/08/2017.
 */

public abstract class Api {

    public static final String API_ENDPOINT = "http://10.0.2.2:8081/";
    public static final String GET_SHOP_ENDPOINT = "api/shop/info";
    public static final String LOGIN_ENDPOINT = "api/loginShopUser";
    public static final String VALIDATE_REG_EMAIL = "api/shop/userExists";
    public static final String CHECK_SHOP_ID_ENDPOINT = "api/shop/checkIfValid";
    public static final String CREATE_SHOP_ENDPOINT = "api/addShopInfo";

    public abstract void getShopInfo(String shopId, PrepareLoginFragmentListener prepareLoginFragmentListener);
    public abstract void loginShopUser(LoginJson login, ShopLoginFragmentListener shopLoginFragmentListener);
    public abstract void getUsernameExists(String username, String email, AdminRegistrationFragmentListener adminRegistrationFragmentListener);
    public abstract void checkUniqueIdValid(String uniqueId, CheckUniqueIdAvailabilityListener checkUniqueIdListener);
    public abstract void createShop(ShopInfo shop, CreateShopListener createShopListener);


    public interface PrepareLoginFragmentListener{
        void success(ShopInfo shopInfo);
        void successWithNoAdmin(ShopInfo shopInfo);
        void error();
    }

    public interface ShopLoginFragmentListener{
        void success(ShopLogin shopLogin, ShopInfo shopInfo);
        void usernameNotFound();
        void invalidCredentials();
    }

    public interface AdminRegistrationFragmentListener {
        void valid();
        void usernameUsed();
        void emailUsed();
    }

    public interface CheckUniqueIdAvailabilityListener {
        void isUniqueIdAvailable(Boolean isAvailable);
    }

    public interface CreateShopListener{
        void successfullyCreatedShop(CreateShopResponse shopResponse);
    }
}
