package aquajmt.mapua.com.shopapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import aquajmt.mapua.com.shopapp.R;
import butterknife.ButterKnife;

/**
 * Created by Bryan on 7/28/2017.
 */

public class DashboardShopInfoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard_shop_info, container, false);
        ButterKnife.bind(this, view);

        return view;
    }
}
