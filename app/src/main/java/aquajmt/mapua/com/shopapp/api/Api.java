package aquajmt.mapua.com.shopapp.api;

import java.util.List;

import aquajmt.mapua.com.shopapp.api.models.OrderInfo;
import aquajmt.mapua.com.shopapp.api.models.ShopInfo;
import aquajmt.mapua.com.shopapp.models.CreateShopResponse;
import aquajmt.mapua.com.shopapp.models.LoginJson;
import aquajmt.mapua.com.shopapp.models.ShopLogin;

/**
 * Created by Jabito on 26/08/2017.
 */

public abstract class Api {

//    public static final String API_ENDPOINT = "http://10.0.2.2:8081/";
    public static final String API_ENDPOINT = "http://www.aquajmt.com:8081/";
    public static final String GET_SHOP_ENDPOINT = "api/shop/info";
    public static final String LOGIN_ENDPOINT = "api/loginShopUser";
    public static final String VALIDATE_REG_EMAIL = "api/shop/userExists";
    public static final String CHECK_SHOP_ID_ENDPOINT = "api/shop/checkIfValid";
    public static final String CREATE_SHOP_ENDPOINT = "api/addShopInfo";
    public static final String GET_ORDERS_ENDPOINT = "api/getOrders";
    public static final String CREATE_SHOP_USER_ENDPOINT = "api/addShopLogin";
    public static final String ACCEPT_ORDER_ENDPOINT = "api/acceptOrder";
    public static final String DECLINE_ORDER_ENDPOINT = "api/declineOrder";

    public abstract void getShopInfo(String shopId, PrepareLoginFragmentListener prepareLoginFragmentListener);
    public abstract void loginShopUser(LoginJson login, ShopLoginFragmentListener shopLoginFragmentListener);
    public abstract void getUsernameExists(String username, String email, AdminRegistrationFragmentListener adminRegistrationFragmentListener);
    public abstract void checkUniqueIdValid(String uniqueId, CheckUniqueIdAvailabilityListener checkUniqueIdListener);
    public abstract void createShop(ShopInfo shop, CreateShopListener createShopListener);
    public abstract void getOrders(String shopId, int type, String status, int page, int pageSize, GetShopOrdersListener getOrdersListener);
    public abstract void createShopUser(ShopLogin shopUser, CreateShopUserListener createShopUserListener);
    public abstract void acceptOrder(String shopId, AcceptOrderListener acceptOrderListener);
    public abstract void declineOrder(String shopId, DeclineOrderListener declineOrderListener);
    
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

    public interface GetShopOrdersListener {
        void retrievedShopOrders(List<OrderInfo> orders);
        void invalidRequest();
        void onError();
    }

    public interface CreateShopUserListener {
        void success();
    }

    public interface AcceptOrderListener{
        void success();
    }

    public interface DeclineOrderListener{
        void success();
    }
}
