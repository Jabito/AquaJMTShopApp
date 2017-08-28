package aquajmt.mapua.com.shopapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import aquajmt.mapua.com.shopapp.R;
import aquajmt.mapua.com.shopapp.api.Api;
import aquajmt.mapua.com.shopapp.api.models.ShopInfo;
import aquajmt.mapua.com.shopapp.api.retrofit.RetrofitApiImpl;
import aquajmt.mapua.com.shopapp.models.LoginJson;
import aquajmt.mapua.com.shopapp.models.ShopLogin;
import aquajmt.mapua.com.shopapp.utils.SharedPref;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jabito on 27/08/2017.
 */

public class ShopLoginFragment extends Fragment {

    private static final String SHOP_NAME = "shopName";
    private SharedPref sharedPref;

    private ShopLoginFragmentListener listener;
    private String shopName;

    @BindView(R.id.lbl_shop_name)
    TextView tvShopName;

    @BindView(R.id.txt_username)
    EditText etUsername;

    @BindView(R.id.txt_password)
    EditText etPassword;

    @BindView(R.id.prg_loading)
    ProgressBar prgLoading;

    @BindView(R.id.btn_back)
    Button btnBack;

    @BindView(R.id.btn_login)
    Button btnLogin;

    @BindView(R.id.tv_register)
    TextView tvRegister;

    public static ShopLoginFragment newInstance(String shopName) {
        ShopLoginFragment fragment = new ShopLoginFragment();
        Bundle args = new Bundle();
        args.putString(SHOP_NAME, shopName);
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_shop_login, container, false);
        ButterKnife.bind(this, view);
        if (getArguments() != null) {
            shopName = getArguments().getString(SHOP_NAME);
        } else {
            throw new IllegalArgumentException("Use ShopLoginFragment#newInstance method " +
                    "instead of manually creating the fragment.");
        }
        tvShopName.setText(shopName);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ShopLoginFragmentListener) {
            listener = (ShopLoginFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement " +
                    ShopLoginFragmentListener.class.getSimpleName());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @OnClick(R.id.btn_back)
    void btnOnBack() {
        listener.goBack();
    }

    @OnClick(R.id.btn_login)
    void btnLoginOnClick() {
        RetrofitApiImpl retrofit = new RetrofitApiImpl(Api.API_ENDPOINT);
        final LoginJson loginJson = new LoginJson();
        loginJson.setUsername(etUsername.getText().toString());
        loginJson.setPassword(etPassword.getText().toString());
        retrofit.loginShopUser(loginJson,
                new Api.ShopLoginFragmentListener() {
                    @Override
                    public void success(ShopLogin shopLogin, ShopInfo shopInfo) {
                        sharedPref.shopLogin = shopLogin;
                        sharedPref.shopInfo = shopInfo;
                        sharedPref.setStringValue(sharedPref.USER, sharedPref.SHOP_ID, shopInfo.getId(), getContext());
                        sharedPref.setStringValue(sharedPref.USER, sharedPref.masterUSERNAME, loginJson.getUsername(), getContext());
                        sharedPref.setStringValue(sharedPref.USER, sharedPref.masterPASSWORD, loginJson.getPassword(), getContext());
                        listener.successfullyLoggedIn();
                    }

                    @Override
                    public void usernameNotFound() {
                        final Snackbar snackbar = Snackbar.make(getView(), "Username does not Exist.", Snackbar.LENGTH_INDEFINITE);
                        snackbar.setAction("Dismiss", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                snackbar.dismiss();
                            }
                        });
                        snackbar.show();
                    }

                    @Override
                    public void invalidCredentials() {
                        final Snackbar snackbar = Snackbar.make(getView(), "Username and Password did not match.", Snackbar.LENGTH_INDEFINITE);
                        snackbar.setAction("Dismiss", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                snackbar.dismiss();
                            }
                        });
                        snackbar.show();
                    }
                });
    }

    @OnClick(R.id.tv_register)
    void tvRegisterOnClick(){
        listener.registerShopUser();
    }

    public interface ShopLoginFragmentListener {
        void successfullyLoggedIn();
        void goBack();
        void registerShopUser();
    }
}
