package aquajmt.mapua.com.shopapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import aquajmt.mapua.com.shopapp.R;
import aquajmt.mapua.com.shopapp.api.Api;
import aquajmt.mapua.com.shopapp.api.models.ShopInfo;
import aquajmt.mapua.com.shopapp.api.retrofit.RetrofitApiImpl;
import aquajmt.mapua.com.shopapp.fragments.CreateBasicShopDetailsFragment;
import aquajmt.mapua.com.shopapp.fragments.CreateShopLocationFragment;
import aquajmt.mapua.com.shopapp.fragments.CreateShopSalesInfoFragment;
import aquajmt.mapua.com.shopapp.fragments.CreateShopScheduleFragment;
import aquajmt.mapua.com.shopapp.models.CreateShopResponse;
import aquajmt.mapua.com.shopapp.utils.SharedPref;
import aquajmt.mapua.com.shopapp.utils.StringUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopCreationActivity extends AppCompatActivity implements CreateBasicShopDetailsFragment.Listener,
        CreateShopLocationFragment.Listener, FragmentManager.OnBackStackChangedListener, CreateShopScheduleFragment.Listener,
        CreateShopSalesInfoFragment.Listener {

    private static final String TAG = "ShopCreationActivity";
    private static final String BASIC_SHOP_DETAILS_FRAG = "basicShopDetailsFrag";
    private static final String SHOP_LOCATION_FRAG = "shopLocationFrag";
    private static final String SHOP_SCHEDULE_FRAG = "shopScheduleFrag";
    private static final String SHOP_SALES_INFO_FRAG = "shopSalesInfoFrag";

    private SimpleDateFormat timeFormatter;

    ShopInfo createShopBody;

    private boolean isBasicShopDetailsCompleted, isShopLocationCompleted,
            isShopScheduleCompleted, isShopSalesInfoCompleted;

    private CreateBasicShopDetailsFragment basicShopDetailsFragment;
    private CreateShopLocationFragment createShopLocationFragment;
    private CreateShopScheduleFragment createShopScheduleFragment;
    private CreateShopSalesInfoFragment createShopSalesInfoFragment;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_creation);

        ButterKnife.bind(this);
        createShopBody = new ShopInfo();
        timeFormatter = new SimpleDateFormat("HH:mm");

        if (savedInstanceState == null) {
            basicShopDetailsFragment = new CreateBasicShopDetailsFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_fragment, basicShopDetailsFragment, BASIC_SHOP_DETAILS_FRAG)
                    .commit();
        } else {
            basicShopDetailsFragment = (CreateBasicShopDetailsFragment)
                    getSupportFragmentManager().findFragmentByTag(BASIC_SHOP_DETAILS_FRAG);
            if (basicShopDetailsFragment == null)
                basicShopDetailsFragment = new CreateBasicShopDetailsFragment();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_fragment, basicShopDetailsFragment, BASIC_SHOP_DETAILS_FRAG)
                    .commit();
        }
    }


    @Override
    public void completedBasicInfo(String uniqueId, String name, String address, String cellphoneNo, String alternateNo) {
        createShopBody.setId(uniqueId);
        createShopBody.setBusinessName(name);
        createShopBody.setAddress(address);
        createShopBody.setCellphoneNo(cellphoneNo);
        createShopBody.setAlternateNo(alternateNo);
        isBasicShopDetailsCompleted = true;

        if (createShopLocationFragment == null)
            createShopLocationFragment = new CreateShopLocationFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_fragment, createShopLocationFragment)
                .addToBackStack(SHOP_LOCATION_FRAG)
                .commit();
    }

    @Override
    public void retrieveBasicInfo(CreateBasicShopDetailsFragment.Receiver receiver) {
        if (isBasicShopDetailsCompleted) {
            receiver.receive(createShopBody.getId(), createShopBody.getBusinessName(),
                    createShopBody.getAddress(), createShopBody.getCellphoneNo(),
                    createShopBody.getAlternateNo());
        } else {
            receiver.notInitialized();
        }
    }

    @Override
    public void errorOccurred() {
        final Snackbar snackbar = Snackbar.make(coordinatorLayout, "An error occurred. " +
                "Please try again.", Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Dismiss", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }

    @Override
    public void completedLocation(float latitude, float longitude) {
        createShopBody.setLatitude(latitude);
        createShopBody.setLongitude(longitude);
        isShopLocationCompleted = true;

        if (createShopScheduleFragment == null)
            createShopScheduleFragment = new CreateShopScheduleFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_fragment, createShopScheduleFragment)
                .addToBackStack(SHOP_SCHEDULE_FRAG)
                .commit();

    }

    @Override
    public void retrieveLocation(CreateShopLocationFragment.Receiver receiver) {
        if (isShopLocationCompleted) {
            receiver.receive(createShopBody.getLatitude(), createShopBody.getLongitude());
        } else {
            receiver.notInitialized();
        }
    }

    @Override
    public void onBackStackChanged() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry backStackEntry = fragmentManager
                    .getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1);
            switch (backStackEntry.getName()) {
                case SHOP_LOCATION_FRAG:
                    break;
                case SHOP_SALES_INFO_FRAG:
                    break;
                case SHOP_SCHEDULE_FRAG:
                    break;
            }
        } else {
            // create basic shop details fragment is currently displayed
        }
    }

    @Override
    public void completedSchedule(Date openingTime, Date closingTime, boolean openOnSundays, boolean openOnMondays, boolean openOnTuesdays, boolean openOnWednesdays, boolean openOnThursdays, boolean openOnFridays, boolean openOnSaturdays, boolean openOnHolidays) {
        String daysAvailable = StringUtils.getStringOfDaysAvailable(openOnSundays, openOnMondays,
                openOnTuesdays, openOnWednesdays, openOnThursdays,
                openOnFridays, openOnSaturdays);

        createShopBody.setTimeOpen(timeFormatter.format(openingTime));
        createShopBody.setTimeClose(timeFormatter.format(closingTime));
        createShopBody.setDaysAvailable(daysAvailable);
        createShopBody.setOpenOnHolidays(openOnHolidays);
        isShopScheduleCompleted = true;

        if (createShopSalesInfoFragment == null)
            createShopSalesInfoFragment = new CreateShopSalesInfoFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_fragment, createShopSalesInfoFragment)
                .addToBackStack(SHOP_SALES_INFO_FRAG)
                .commit();
    }

    @Override
    public void retrieveSchedule(CreateShopScheduleFragment.Receiver receiver) {
        if (isShopScheduleCompleted) {
            boolean[] daysAvailable = StringUtils.getDaysAvailableFromString(
                    createShopBody.getDaysAvailable());
            try {
                receiver.receive(
                        timeFormatter.parse(createShopBody.getTimeOpen()),
                        timeFormatter.parse(createShopBody.getTimeClose()),
                        daysAvailable[0], daysAvailable[1], daysAvailable[2],
                        daysAvailable[3], daysAvailable[4], daysAvailable[5],
                        daysAvailable[6], createShopBody.getOpenOnHolidays());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            receiver.notInitialized();
        }
    }

    @Override
    public void completedSalesInfo(boolean slimOffered, boolean roundOffered, boolean allowSwap, boolean alkalineOffered, boolean distilledOffered, boolean mineralOffered, boolean purifiedOffered, double alkalinePrice, double distilledPrice, double mineralPrice, double purifiedPrice) {
        createShopBody.setRoundOffered(roundOffered);
        createShopBody.setSlimOffered(slimOffered);
        createShopBody.setAllowSwap(allowSwap);
        createShopBody.setAlkaline(alkalineOffered);
        createShopBody.setDistilled(distilledOffered);
        createShopBody.setMineral(mineralOffered);
        createShopBody.setPurified(purifiedOffered);
        createShopBody.setAlkalinePrice(alkalinePrice);
        createShopBody.setDistilledPrice(distilledPrice);
        createShopBody.setMineralPrice(mineralPrice);
        createShopBody.setPurifiedPrice(purifiedPrice);
        isShopSalesInfoCompleted = true;

        createShop();
    }

    private void createShop() {
        RetrofitApiImpl retrofit = new RetrofitApiImpl(Api.API_ENDPOINT);
        retrofit.createShop(createShopBody, new Api.CreateShopListener() {

            @Override
            public void successfullyCreatedShop(CreateShopResponse shopResponse) {
                Intent intent = new Intent(ShopCreationActivity.this, LoginActivity.class);
                SharedPref.setStringValue(SharedPref.USER, SharedPref.SHOP_ID, shopResponse.getShop().getId(), getBaseContext());
                intent.putExtra(SharedPref.shopInfo.getId(), shopResponse.getShop().getId());

                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void retrieveSalesInfo(CreateShopSalesInfoFragment.Receiver receiver) {
        if (isShopSalesInfoCompleted) {
            receiver.receive(
                    createShopBody.getSlimOffered(), createShopBody.getRoundOffered(),
                    createShopBody.getAllowSwap(), createShopBody.getAlkaline(),
                    createShopBody.getDistilled(), createShopBody.getMineral(),
                    createShopBody.getPurified(), createShopBody.getAlkalinePrice(),
                    createShopBody.getDistilledPrice(), createShopBody.getMineralPrice(),
                    createShopBody.getPurifiedPrice());
        } else {
            receiver.notInitialized();
        }
    }
}
