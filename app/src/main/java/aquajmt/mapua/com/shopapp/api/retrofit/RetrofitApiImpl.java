package aquajmt.mapua.com.shopapp.api.retrofit;

import org.json.JSONObject;

import aquajmt.mapua.com.shopapp.api.Api;
import aquajmt.mapua.com.shopapp.api.models.GetOrderResponse;
import aquajmt.mapua.com.shopapp.api.models.LoginResponse;
import aquajmt.mapua.com.shopapp.api.models.ShopInfo;
import aquajmt.mapua.com.shopapp.models.CreateShopResponse;
import aquajmt.mapua.com.shopapp.models.LoginJson;
import aquajmt.mapua.com.shopapp.models.ShopLogin;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jabito on 26/08/2017.
 */

public class RetrofitApiImpl extends Api {

    private ApiService apiService;

    public RetrofitApiImpl(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    @Override
    public void getShopInfo(String shopId, final Api.PrepareLoginFragmentListener prepareLoginListener) {

        apiService.getShopInfo(shopId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code() == 200 && null != response.body()) {
                        JSONObject responseJson = new JSONObject(response.body().string());
                        JSONObject shopInfoJson = responseJson.getJSONObject("shop");
                        ShopInfo shop = new ShopInfo();

                        shop.setId(shopInfoJson.getString("id"));
                        shop.setBusinessName(shopInfoJson.getString("businessName"));

                        if (responseJson.getBoolean("hasAdmin"))
                            prepareLoginListener.success(shop);
                        else
                            prepareLoginListener.successWithNoAdmin(shop);
                    } else if (response.code() == 404) {
                        prepareLoginListener.error();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    public void loginShopUser(final LoginJson login, final Api.ShopLoginFragmentListener shopLoginFragmentListener) {
        apiService.login(login).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                System.out.print(response);
                try {
                    if (response.body().getResponseCode().equals("200") && null != response.body()) {
                        ShopLogin shopLogin = response.body().getShopLogin();
                        ShopInfo shop = response.body().getShopInfo();

                        shopLoginFragmentListener.success(shopLogin, shop);
                    } else if (response.code() == 401) {
                        shopLoginFragmentListener.invalidCredentials();
                    } else if (response.code() == 404) {
                        shopLoginFragmentListener.usernameNotFound();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void getUsernameExists(String username, String email, final Api.AdminRegistrationFragmentListener adminRegistrationFragmentListener) {
        apiService.validateUserAndEmail(username, email).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    adminRegistrationFragmentListener.valid();
                } else if (response.code() == 400) {
                    adminRegistrationFragmentListener.usernameUsed();
                } else if (response.code() == 410) {
                    adminRegistrationFragmentListener.emailUsed();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    public void checkUniqueIdValid(String uniqueId, final Api.CheckUniqueIdAvailabilityListener checkUniqueListener) {
        apiService.checkShopIdValid(uniqueId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject responseJson = new JSONObject(response.body().string());
                    checkUniqueListener.isUniqueIdAvailable(!responseJson.getBoolean("shopIdExist"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    public void createShop(ShopInfo createShopBody, final Api.CreateShopListener createShopListener) {
        apiService.createShop(createShopBody).enqueue(new Callback<CreateShopResponse>() {
            @Override
            public void onResponse(Call<CreateShopResponse> call, Response<CreateShopResponse> response) {
                if(response.code() == 200)
                    createShopListener.successfullyCreatedShop(response.body());
            }

            @Override
            public void onFailure(Call<CreateShopResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void getOrders(String shopId, int waterType, String status, int page, int pageSize, final Api.GetShopOrdersListener getOrdersListener) {
        apiService.getOrders(shopId, waterType, status, page, pageSize).enqueue(new Callback<GetOrderResponse>() {
            @Override
            public void onResponse(Call<GetOrderResponse> call, Response<GetOrderResponse> response) {
                if(response.code() == 200)
                    getOrdersListener.retrievedShopOrders(response.body().getOrders());
                else if(response.code() == 404)
                    getOrdersListener.invalidRequest();
                else if(response.code() == 500)
                    getOrdersListener.onError();
            }

            @Override
            public void onFailure(Call<GetOrderResponse> call, Throwable t) {

            }
        });
    }
}
