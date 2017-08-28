package aquajmt.mapua.com.shopapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import aquajmt.mapua.com.shopapp.R;
import aquajmt.mapua.com.shopapp.utils.SharedPref;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bryan on 7/28/2017.
 */

public class DashboardShopInfoFragment extends Fragment {

    @BindView(R.id.tv_shop_name)
    TextView tvShopName;

    @BindView(R.id.tv_shop_address)
    TextView tvShopAddress;

    @BindView(R.id.tv_contact_no)
    TextView tvContactNo;

    @BindView(R.id.tv_alternate_no)
    TextView tvAlternateNo;

    @BindView(R.id.rl_alternate_no)
    RelativeLayout rlAlternateNo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard_shop_info, container, false);
        ButterKnife.bind(this, view);

        tvShopName.setText(SharedPref.shopInfo.getBusinessName());
        tvShopAddress.setText(SharedPref.shopInfo.getAddress());
        tvContactNo.setText(SharedPref.shopInfo.getCellphoneNo());
        if(null != SharedPref.shopInfo.getAlternateNo())
            tvAlternateNo.setText(SharedPref.shopInfo.getAlternateNo());
        else
            rlAlternateNo.setVisibility(View.GONE);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
