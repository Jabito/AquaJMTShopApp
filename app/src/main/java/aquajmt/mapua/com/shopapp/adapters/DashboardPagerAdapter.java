package aquajmt.mapua.com.shopapp.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.graphics.drawable.DrawableCompat;

import aquajmt.mapua.com.shopapp.R;
import aquajmt.mapua.com.shopapp.fragments.DashboardNotificationsFragment;
import aquajmt.mapua.com.shopapp.fragments.DashboardOrdersFragment;
import aquajmt.mapua.com.shopapp.fragments.DashboardShopInfoFragment;


/**
 * Created by Bryan on 7/28/2017.
 */

public class DashboardPagerAdapter extends FragmentPagerAdapter {

    private final Context context;
    private final Fragment[] fragments;

    public DashboardPagerAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.context = context;

        DashboardOrdersFragment ordersFragment = new DashboardOrdersFragment();
        DashboardNotificationsFragment notificationsFragment = new DashboardNotificationsFragment();
        DashboardShopInfoFragment shopInfoFragment = new DashboardShopInfoFragment();

        fragments = new Fragment[] {ordersFragment, notificationsFragment, shopInfoFragment};
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }


    @Override
    public int getCount() {
        return fragments.length;
    }


    public void setupTabLayout(TabLayout tabLayout) {
        ColorStateList colorStateList;
        if (Build.VERSION.SDK_INT >= 23)
            colorStateList = context.getResources().getColorStateList(R.color.tab_icon, context.getTheme());
        else
            colorStateList = context.getResources().getColorStateList(R.color.tab_icon);

        for (int i = 0; i < fragments.length; i++) {
            Fragment fragment = getItem(i);

            int drawableResource; // TODO: assign the appropriate drawable resource ID
            if (fragment instanceof DashboardOrdersFragment) {
                drawableResource = R.drawable.ic_close_black_24dp;
            } else if (fragment instanceof DashboardNotificationsFragment) {
                drawableResource = R.drawable.ic_close_black_24dp;
            } else if (fragment instanceof DashboardShopInfoFragment) {
                drawableResource = R.drawable.ic_close_black_24dp;
            } else {
                throw new AssertionError("There should be no other Fragments in DashboardPagerAdapter.");
            }

            TabLayout.Tab tab = tabLayout.newTab();
            Drawable drawable = context.getDrawable(drawableResource);
            if (drawable != null) {
                drawable = DrawableCompat.wrap(drawable);
                DrawableCompat.setTintList(drawable, colorStateList);

                tab.setIcon(drawable);
            }

            tabLayout.addTab(tab);
        }
    }
}
