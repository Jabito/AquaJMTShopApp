package aquajmt.mapua.com.shopapp.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

import aquajmt.mapua.com.shopapp.R;
import aquajmt.mapua.com.shopapp.adapters.DashboardPagerAdapter;
import aquajmt.mapua.com.shopapp.api.Api;
import aquajmt.mapua.com.shopapp.api.models.OrderInfo;
import aquajmt.mapua.com.shopapp.api.retrofit.RetrofitApiImpl;
import aquajmt.mapua.com.shopapp.fragments.DashboardNotificationsFragment;
import aquajmt.mapua.com.shopapp.fragments.DashboardOrdersFragment;
import aquajmt.mapua.com.shopapp.utils.SharedPref;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardActivity extends FragmentActivity implements TabLayout.OnTabSelectedListener,
        DashboardOrdersFragment.Listener, DashboardNotificationsFragment.Listener {

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.container_view_pager)
    ViewPager containerViewPager;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ButterKnife.bind(this);

        DashboardPagerAdapter dashboardPagerAdapter =
                new DashboardPagerAdapter(this, getSupportFragmentManager());
        dashboardPagerAdapter.setupTabLayout(tabLayout);

        containerViewPager.setAdapter(dashboardPagerAdapter);
        containerViewPager.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        containerViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }

    @Override
    public void retrieveShopOrders(int type, int page, int pageSize, final DashboardOrdersFragment.Receiver receiver) {
        RetrofitApiImpl retrofit = new RetrofitApiImpl(Api.API_ENDPOINT);
        retrofit.getOrders(SharedPref.getStringValue(SharedPref.USER, SharedPref.SHOP_ID, getBaseContext()),
                type, "", page, pageSize, new Api.GetShopOrdersListener() {

            @Override
            public void retrievedShopOrders(List<OrderInfo> orders) {
                receiver.retrieveShopOrders(orders);
            }

            @Override
            public void invalidRequest() {
                receiver.onError();
                errorOccurred();
            }

            @Override
            public void onError() {
                receiver.onError();;
            }
        });
    }

    private void errorOccurred() {
        final Snackbar snackbar = Snackbar.make(coordinatorLayout, R.string.error_message_network,
                Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(R.string.dismiss, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }

    @Override
    public void retrieveShopOrders(int type, int page, int pageSize, final DashboardNotificationsFragment.Receiver receiver) {
        RetrofitApiImpl retrofit = new RetrofitApiImpl(Api.API_ENDPOINT);
        retrofit.getOrders(SharedPref.getStringValue(SharedPref.USER, SharedPref.SHOP_ID, getBaseContext()),
                type, "", page, pageSize, new Api.GetShopOrdersListener() {

                    @Override
                    public void retrievedShopOrders(List<OrderInfo> orders) {
                        receiver.retrieveShopOrders(orders);
                    }

                    @Override
                    public void invalidRequest() {
                        receiver.onError();
                        errorOccurred();
                    }

                    @Override
                    public void onError() {
                        receiver.onError();;
                    }
                });
    }
}
