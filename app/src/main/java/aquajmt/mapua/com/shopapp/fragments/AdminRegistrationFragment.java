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
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jabito on 27/08/2017.
 */

public class AdminRegistrationFragment extends Fragment {

    private AdminRegistrationFragmentListener listener;

    @BindView(R.id.txt_username)
    EditText etUsername;

    @BindView(R.id.txt_email)
    EditText etEmail;

    @BindView(R.id.txt_password)
    EditText etPassword;

    @BindView(R.id.txt_confirm_password)
    EditText etConfirmPassword;

    @BindView(R.id.btn_next)
    Button btnNext;

    public static AdminRegistrationFragment newInstance() {
        AdminRegistrationFragment fragment = new AdminRegistrationFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AdminRegistrationFragmentListener) {
            listener = (AdminRegistrationFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement " +
                    AdminRegistrationFragmentListener.class.getSimpleName());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_register_admin_credentials, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @OnClick(R.id.btn_next)
    void btnNextOnClick() {
        //Check if Username exists.
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String email = etEmail.getText().toString();
        String confirmPass = etConfirmPassword.getText().toString();
        if (username.equals("") || password.equals("") || email.equals("") || confirmPass.equals(""))
            Toast.makeText(getContext(), "Please complete the fields.", Toast.LENGTH_SHORT).show();
        else if (!password.equals(confirmPass))
            Toast.makeText(getContext(), "Password did not match with confirmed password.", Toast.LENGTH_SHORT).show();
        else {
            final ShopLogin shop = new ShopLogin();
            shop.setUsername(username);
            shop.setPassword(password);
            shop.setEmail(email);

            RetrofitApiImpl retrofit = new RetrofitApiImpl(Api.API_ENDPOINT);
            retrofit.getUsernameExists(username, email, new Api.AdminRegistrationFragmentListener() {

                @Override
                public void valid() {
                    listener.registerPartTwo(shop);
                }

                @Override
                public void usernameUsed() {
                    Toast.makeText(getContext(), "Username already used.", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void emailUsed() {
                    Toast.makeText(getContext(), "Email already used.", Toast.LENGTH_SHORT).show();
                }
            });

            listener.registerPartTwo(shop);
        }
    }

    public interface AdminRegistrationFragmentListener {
        void registerPartTwo(ShopLogin shopLogin);
    }
}
