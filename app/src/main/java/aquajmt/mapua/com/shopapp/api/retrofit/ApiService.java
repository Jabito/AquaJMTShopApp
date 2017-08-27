package aquajmt.mapua.com.shopapp.api.retrofit;

import aquajmt.mapua.com.shopapp.api.Api;
import aquajmt.mapua.com.shopapp.api.models.LoginResponse;
import aquajmt.mapua.com.shopapp.api.models.ShopInfo;
import aquajmt.mapua.com.shopapp.models.CreateShopResponse;
import aquajmt.mapua.com.shopapp.models.LoginJson;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Jabito on 26/08/2017.
 */

public interface ApiService {

    @GET(Api.GET_SHOP_ENDPOINT)
    Call<ResponseBody> getShopInfo(@Query("id") String id);

    @POST(Api.LOGIN_ENDPOINT)
    Call<LoginResponse> login(@Body LoginJson loginJson);

    @GET(Api.VALIDATE_REG_EMAIL)
    Call<ResponseBody> validateUserAndEmail(@Query("username") String username, @Query("email") String email);

    @GET(Api.CHECK_SHOP_ID_ENDPOINT)
    Call<ResponseBody> checkShopIdValid(@Query("shopId") String shopId);

    @POST(Api.CREATE_SHOP_ENDPOINT)
    Call<CreateShopResponse> createShop(@Body ShopInfo shopInfo);
}
