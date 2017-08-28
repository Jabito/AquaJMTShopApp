package aquajmt.mapua.com.shopapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import aquajmt.mapua.com.shopapp.R;
import aquajmt.mapua.com.shopapp.fragments.AdminRegistrationFragment;
import aquajmt.mapua.com.shopapp.fragments.AdminRegistrationLocationFragment;
import aquajmt.mapua.com.shopapp.fragments.AdminRegistrationTwoFragment;
import aquajmt.mapua.com.shopapp.fragments.PrepareLoginFragment;
import aquajmt.mapua.com.shopapp.fragments.ShopLoginFragment;
import aquajmt.mapua.com.shopapp.models.ShopLogin;
import aquajmt.mapua.com.shopapp.utils.SharedPref;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements PrepareLoginFragment.PrepareLoginFragmentListener,
        ShopLoginFragment.ShopLoginFragmentListener, AdminRegistrationFragment.AdminRegistrationFragmentListener {

    private static final String PREPARE_LOGIN_FRAG_TAG = "prepare_login_frag_tag";
    private static final String LOGIN_FRAG_TAG = "login_frag_tag";
    private FragmentTransaction fragmentTransaction;
    private PrepareLoginFragment prepareLoginFragment;
    private ShopLoginFragment shopLoginFragment;
    private AdminRegistrationFragment adminRegistrationFragment;
    private AdminRegistrationTwoFragment adminRegistrationTwoFragment;
    private AdminRegistrationLocationFragment adminRegistrationLocationFragment;
    private ShopLogin shopLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        shopLogin = new ShopLogin();

        if (null != SharedPref.shopInfo.getBusinessName())
            loginPrepared(SharedPref.shopInfo.getBusinessName());
        else if (savedInstanceState == null) {
            prepareLoginFragment = new PrepareLoginFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_fragment, prepareLoginFragment, PREPARE_LOGIN_FRAG_TAG)
                    .commit();
        } else {
            prepareLoginFragment = (PrepareLoginFragment)
                    getSupportFragmentManager().findFragmentByTag(PREPARE_LOGIN_FRAG_TAG);
            if (prepareLoginFragment == null)
                prepareLoginFragment = new PrepareLoginFragment();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_fragment, prepareLoginFragment, PREPARE_LOGIN_FRAG_TAG)
                    .commit();
        }
    }

    @Override
    public void successfullyLoggedIn() {
        startActivity(new Intent(this, DashboardActivity.class));
        finish();
    }

    @Override
    public void goBack() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0)
            getSupportFragmentManager().popBackStack();
    }

    @Override
    public void registerShopUser() {
        //todo shopUserRegistration Fragment + Layout
    }

    @Override
    public void registerPartTwo(ShopLogin shop) {
        shopLogin.setUsername(shop.getUsername());
        shopLogin.setEmail(shop.getEmail());
        shopLogin.setPassword(shop.getPassword());
        adminRegistrationTwoFragment = AdminRegistrationTwoFragment.newInstance();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_fragment, adminRegistrationTwoFragment)
                .addToBackStack(LOGIN_FRAG_TAG)
                .commit();
    }

    @Override
    public void loginPrepared(String shopName) {
        shopLoginFragment = ShopLoginFragment.newInstance(shopName);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_fragment, shopLoginFragment)
                .addToBackStack(LOGIN_FRAG_TAG)
                .commit();
    }

    @Override
    public void goToRegisterFragment() {
        adminRegistrationFragment = AdminRegistrationFragment.newInstance();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_fragment, adminRegistrationFragment)
                .addToBackStack(LOGIN_FRAG_TAG)
                .commit();
    }

    @Override
    public void registerShop() {
        startActivity(new Intent(this, ShopCreationActivity.class));
        finish();
    }
}