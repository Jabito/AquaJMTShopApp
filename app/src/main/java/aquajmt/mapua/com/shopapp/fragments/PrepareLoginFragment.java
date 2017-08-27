package aquajmt.mapua.com.shopapp.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import aquajmt.mapua.com.shopapp.R;
import aquajmt.mapua.com.shopapp.api.Api;
import aquajmt.mapua.com.shopapp.api.models.ShopInfo;
import aquajmt.mapua.com.shopapp.api.retrofit.RetrofitApiImpl;
import aquajmt.mapua.com.shopapp.utils.SharedPref;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class PrepareLoginFragment extends Fragment {

    private PrepareLoginFragmentListener listener;
    private boolean isUniqueIdValid;

    SharedPref sharedPref;

    @BindView(R.id.txt_unique_id)
    TextView etUniqueId;

    @BindView(R.id.btn_next)
    Button btnNext;

    @BindView(R.id.prg_loading)
    ProgressBar prgLoading;

    @BindView(R.id.txt_register)
    TextView tvRegister;

    public PrepareLoginFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prepare_login, container, false);
        ButterKnife.bind(this, view);

        btnNext.setEnabled(true);
        prgLoading.setVisibility(View.GONE);

        return view;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof PrepareLoginFragmentListener)
            listener = (PrepareLoginFragmentListener) context;
        else
            throw new RuntimeException(context.toString() + " must implement " + PrepareLoginFragmentListener.class.getSimpleName());
    }

    @Override
    public void onDetach(){
        super.onDetach();
        listener = null;
    }

    @OnClick(R.id.btn_next)
    void btnNextOnClick() {
        this.toggleUiState();
        RetrofitApiImpl retrofit = new RetrofitApiImpl(Api.API_ENDPOINT);
        retrofit.getShopInfo(etUniqueId.getText().toString(), new Api.PrepareLoginFragmentListener() {

            @Override
            public void success(ShopInfo shopInfo) {
                listener.loginPrepared(shopInfo.getBusinessName());
                prgLoading.setVisibility(View.GONE);
            }

            @Override
            public void successWithNoAdmin(ShopInfo shopInfo) {
                listener.goToRegisterFragment();
            }

            @Override
            public void error() {
                Toast.makeText(getContext(), "Shop ID Not valid.", Toast.LENGTH_SHORT).show();
                toggleUiState();
            }
        });
    }

    @OnClick(R.id.txt_register)
    void tvRegisterOnClick(){
        listener.registerShop();
    }

    private void toggleUiState() {
        btnNext.setEnabled(!btnNext.isEnabled());
        prgLoading.setVisibility(prgLoading.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
    }

    @OnTextChanged(R.id.txt_unique_id)
    void txtUniqueIdOnTextChanged(){ if(isUniqueIdValid) this.normalizeUniqueIdUiState();}

    private void normalizeUniqueIdUiState() {
        isUniqueIdValid = false;
        etUniqueId.setTextColor(Color.BLACK);
    }

    public interface PrepareLoginFragmentListener{
        void loginPrepared(String shopName);
        void goToRegisterFragment();
        void registerShop();
    }
}
