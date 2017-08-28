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
 * Created by Jabito on 27/08/2017.
 */

public class AdminRegistrationTwoFragment extends Fragment{

    private AdminRegistrationTwoFragmentListener listener;

    @BindView(R.id.txt_first_name)
    EditText etFirstName;

    @BindView(R.id.txt_middle_name)
    EditText etMiddleName;

    @BindView(R.id.txt_last_name)
    EditText etLastName;

    @BindView(R.id.btn_finish)
    Button btnFinish;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AdminRegistrationTwoFragmentListener) {
            listener = (AdminRegistrationTwoFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement " +
                    AdminRegistrationTwoFragmentListener.class.getSimpleName());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_register_admin_details, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public static AdminRegistrationTwoFragment newInstance(ShopLogin shopUser) {
        AdminRegistrationTwoFragment fragment = new AdminRegistrationTwoFragment();
        SharedPref.shopLogin.setUsername(shopUser.getUsername());
        SharedPref.shopLogin.setPassword(shopUser.getPassword());
        SharedPref.shopLogin.setEmail(shopUser.getEmail());

        return fragment;
    }

    @OnClick(R.id.btn_finish)
    void btnNextOnClick(){
        System.out.println("Button Clicked.");
        String firstName = etFirstName.getText().toString();
        String middleName = etMiddleName.getText().toString();
        String lastName = etLastName.getText().toString();

        if(firstName.equals("") || lastName.equals(""))
            Toast.makeText(getContext(), "Please complete Firstname and Lastname fields.", Toast.LENGTH_SHORT).show();
        else {
            SharedPref.shopLogin.setFirstName(firstName);
            SharedPref.shopLogin.setMiddleName(middleName);
            SharedPref.shopLogin.setLastName(lastName);

            RetrofitApiImpl retrofit = new RetrofitApiImpl(Api.API_ENDPOINT);
            retrofit.createShopUser(SharedPref.shopLogin, new Api.CreateShopUserListener() {

                @Override
                public void success() {
                    listener.loginPrepared("Welcome new User.");
                }
            });
        }
    }

    public interface AdminRegistrationTwoFragmentListener{
        void loginPrepared(String shopName);
    }
}
