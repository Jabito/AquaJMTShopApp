package aquajmt.mapua.com.shopapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import aquajmt.mapua.com.shopapp.R;
import aquajmt.mapua.com.shopapp.api.Api;
import aquajmt.mapua.com.shopapp.api.retrofit.RetrofitApiImpl;
import aquajmt.mapua.com.shopapp.models.ShopLogin;
import aquajmt.mapua.com.shopapp.utils.SharedPref;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jabito on 28/08/2017.
 */

public class ShopUserRegistrationFragment extends Fragment {

    private ShopUserRegistrationListener listener;

    @BindView(R.id.et_username)
    EditText etUsername;

    @BindView(R.id.et_password)
    EditText etPassword;

    @BindView(R.id.et_confirm_password)
    EditText etConfirmPassword;

    @BindView(R.id.et_email)
    EditText etEmail;

    @BindView(R.id.et_first_name)
    EditText etFirstName;

    @BindView(R.id.et_last_name)
    EditText etLastName;

    @BindView(R.id.btn_register)
    Button btnRegister;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ShopUserRegistrationListener) {
            listener = (ShopUserRegistrationListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement " +
                    ShopUserRegistrationListener.class.getSimpleName());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_register_shop_user, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @OnClick(R.id.btn_register)
    void btnRegisterOnClick() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();
        String email = etEmail.getText().toString();
        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();

        if(username.equals("") || password.equals("") || confirmPassword.equals("")
                || email.equals("")|| firstName.equals("") || lastName.equals(""))
            Toast.makeText(getContext(), "Complete all fields.", Toast.LENGTH_SHORT).show();
        else if(!password.equals(confirmPassword))
            Toast.makeText(getContext(), "Passwords did not match.", Toast.LENGTH_SHORT).show();
        else{
            ShopLogin shopLogin = new ShopLogin();
            shopLogin.setStaffOf(SharedPref.shopInfo.getId());
            shopLogin.setUsername(username);
            shopLogin.setPassword(password);
            shopLogin.setEmail(email);
            shopLogin.setFirstName(firstName);
            shopLogin.setLastName(lastName);

            RetrofitApiImpl retrofit = new RetrofitApiImpl(Api.API_ENDPOINT);
            retrofit.createShopUser(shopLogin, new Api.CreateShopUserListener(){

                @Override
                public void success() {
                    listener.loginPrepared(SharedPref.shopInfo.getBusinessName());
                }
            });
        }
    }

    public static ShopUserRegistrationFragment newInstance() {
        ShopUserRegistrationFragment fragment = new ShopUserRegistrationFragment();
        return fragment;
    }

    public interface ShopUserRegistrationListener {
        void loginPrepared(String shopName);
    }
}
