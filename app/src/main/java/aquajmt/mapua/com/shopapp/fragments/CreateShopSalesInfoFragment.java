package aquajmt.mapua.com.shopapp.fragments;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import aquajmt.mapua.com.shopapp.R;
import aquajmt.mapua.com.shopapp.utils.Wrapper;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * Created by Bryan on 7/19/2017.
 */

public class CreateShopSalesInfoFragment extends Fragment {

    private Listener listener;

    @BindView(R.id.chk_slim)
    CheckBox chkSlim;

    @BindView(R.id.chk_round)
    CheckBox chkRound;

    @BindView(R.id.chk_distilled)
    CheckBox chkDistilled;

    @BindView(R.id.chk_mineral)
    CheckBox chkMineral;

    @BindView(R.id.chk_alkaline)
    CheckBox chkAlkaline;

    @BindView(R.id.chk_purified)
    CheckBox chkPurified;

    @BindView(R.id.chk_allow_swap)
    CheckBox chkAllowSwap;

    @BindView(R.id.txt_distilled_price)
    TextView txtDistilledPrice;

    @BindView(R.id.txt_mineral_price)
    TextView txtMineralPrice;

    @BindView(R.id.txt_alkaline_price)
    TextView txtAlkalinePrice;

    @BindView(R.id.txt_purified_price)
    TextView txtPurifiedPrice;

    @BindView(R.id.txt_container_types_validation)
    TextView txtContainerTypesValidation;

    @BindView(R.id.txt_water_types_validation)
    TextView txtWaterTypesValidation;

    @BindView(R.id.txt_purified_price_validation)
    TextView txtPurifiedPriceValidation;

    @BindView(R.id.txt_mineral_price_validation)
    TextView txtMineralPriceValidation;

    @BindView(R.id.txt_alkaline_price_validation)
    TextView txtAlkalinePriceValidation;

    @BindView(R.id.txt_distilled_price_validation)
    TextView txtDistilledPriceValidation;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_shop_sales_info, container, false);
        ButterKnife.bind(this, view);

        listener.retrieveSalesInfo(new Receiver() {
            @Override
            public void receive(boolean slimOffered, boolean roundOffered, boolean allowSwap,
                                boolean alkalineOffered, boolean distilledOffered,
                                boolean mineralOffered, boolean purifiedOffered,
                                double alkalinePrice, double distilledPrice,
                                double mineralPrice, double purifiedPrice) {
                chkSlim.setChecked(slimOffered);
                chkRound.setChecked(roundOffered);
                chkAllowSwap.setChecked(allowSwap);
                chkAlkaline.setChecked(alkalineOffered);
                chkDistilled.setChecked(distilledOffered);
                chkMineral.setChecked(mineralOffered);
                chkPurified.setChecked(purifiedOffered);

                if (alkalineOffered)
                    txtAlkalinePrice.setText(String.valueOf(alkalinePrice));
                if (distilledOffered)
                    txtDistilledPrice.setText(String.valueOf(distilledPrice));
                if (mineralOffered)
                    txtMineralPrice.setText(String.valueOf(mineralPrice));
                if (purifiedOffered)
                    txtPurifiedPrice.setText(String.valueOf(purifiedPrice));

                txtPurifiedPriceOnTextChanged();
                txtDistilledPriceOnTextChanged();
                txtMineralPriceOnTextChanged();
                txtAlkalinePriceOnTextChanged();
                chkContainersOnCheckChanged();
                chkWaterTypesOnCheckChanged();
            }

            @Override
            public void notInitialized() { }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Listener) {
            listener = (Listener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement "
                    + CreateShopSalesInfoFragment.class.getSimpleName() + "."
                    + Listener.class.getSimpleName());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @OnTextChanged(R.id.txt_purified_price)
    void txtPurifiedPriceOnTextChanged() {
        if (chkPurified.isChecked())
            changePriceUiStateOnValidate(txtPurifiedPrice, txtPurifiedPriceValidation);
    }

    @OnTextChanged(R.id.txt_distilled_price)
    void txtDistilledPriceOnTextChanged() {
        if (chkDistilled.isChecked())
            changePriceUiStateOnValidate(txtDistilledPrice, txtDistilledPriceValidation);
    }

    @OnTextChanged(R.id.txt_mineral_price)
    void txtMineralPriceOnTextChanged() {
        if (chkMineral.isChecked())
            changePriceUiStateOnValidate(txtMineralPrice, txtMineralPriceValidation);
    }

    @OnTextChanged(R.id.txt_alkaline_price)
    void txtAlkalinePriceOnTextChanged() {
        if (chkAlkaline.isChecked())
            changePriceUiStateOnValidate(txtAlkalinePrice, txtAlkalinePriceValidation);
    }

    @OnCheckedChanged(R.id.chk_purified)
    void chkPurifiedOnCheckedChanged() {
        txtPurifiedPrice.setEnabled(chkPurified.isChecked());
    }

    @OnCheckedChanged(R.id.chk_distilled)
    void chkDistilledOnCheckedChanged() {
        txtDistilledPrice.setEnabled(chkDistilled.isChecked());
    }

    @OnCheckedChanged(R.id.chk_mineral)
    void chkMineralOnCheckedChanged() {
        txtMineralPrice.setEnabled(chkMineral.isChecked());
    }

    @OnCheckedChanged(R.id.chk_alkaline)
    void chkAlkalineOnCheckedChanged() {
        txtAlkalinePrice.setEnabled(chkAlkaline.isChecked());
    }

    @OnCheckedChanged({R.id.chk_slim, R.id.chk_round})
    void chkContainersOnCheckChanged() {
        txtContainerTypesValidation.setVisibility(
                !isContainerTypesSelectionValid() ? View.VISIBLE : View.GONE);
    }

    @OnCheckedChanged({R.id.chk_alkaline, R.id.chk_mineral, R.id.chk_purified, R.id.chk_distilled})
    void chkWaterTypesOnCheckChanged() {
        txtWaterTypesValidation.setVisibility(
                !isWaterTypesSelectionValid() ? View.VISIBLE : View.GONE);
    }

    @OnClick(R.id.btn_finish)
    void btnFinishOnClick() {
        double alkalinePrice = 0.0, distilledPrice = 0.0,
                mineralPrice = 0.0, purifiedPrice = 0.0;

        boolean hasValidationErrors = !isContainerTypesSelectionValid();
        boolean isWaterTypesSelectionValid = isWaterTypesSelectionValid();
        hasValidationErrors = hasValidationErrors || !isWaterTypesSelectionValid;

        if (isWaterTypesSelectionValid) {
            Wrapper<Double> price = new Wrapper<>();
            boolean isValid;

            if (chkDistilled.isChecked()) {
                isValid = isPriceValid(txtDistilledPrice, price);
                hasValidationErrors = hasValidationErrors || !isValid;
                if (isValid)
                    distilledPrice = price.getValue();
            }
            if (chkMineral.isChecked()) {
                isValid = isPriceValid(txtMineralPrice, price);
                hasValidationErrors = hasValidationErrors || !isValid;
                if (isValid)
                    mineralPrice = price.getValue();
            }
            if (chkPurified.isChecked()) {
                isValid = isPriceValid(txtPurifiedPrice, price);
                hasValidationErrors = hasValidationErrors || !isValid;
                if (isValid)
                    purifiedPrice = price.getValue();
            }
            if (chkAlkaline.isChecked()) {
                isValid = isPriceValid(txtAlkalinePrice, price);
                hasValidationErrors = hasValidationErrors || !isValid;
                if (isValid)
                    alkalinePrice = price.getValue();
            }
        }

        if (!hasValidationErrors) {
            listener.completedSalesInfo(
                    chkSlim.isChecked(), chkRound.isChecked(), chkAllowSwap.isChecked(),
                    chkAlkaline.isChecked(), chkDistilled.isChecked(),
                    chkMineral.isChecked(), chkPurified.isChecked(),
                    alkalinePrice, distilledPrice, mineralPrice, purifiedPrice);
        }
    }

    private void changePriceUiStateOnValidate(TextView textView, TextView validationTextView) {
        Drawable wrap = DrawableCompat.wrap(textView.getBackground());
        if (isPriceValid(textView)) {
            validationTextView.setVisibility(View.GONE);
            wrap.clearColorFilter();
        } else {
            validationTextView.setVisibility(View.VISIBLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                wrap.setColorFilter(getContext().getColor(android.R.color.holo_red_dark), PorterDuff.Mode.MULTIPLY);
            } else {
                wrap.setColorFilter(getResources().getColor(android.R.color.holo_red_dark), PorterDuff.Mode.MULTIPLY);
            }
        }
    }

    private boolean isContainerTypesSelectionValid() {
        return chkSlim.isChecked() || chkRound.isChecked();
    }

    private boolean isWaterTypesSelectionValid() {
        return chkDistilled.isChecked() || chkMineral.isChecked()
                || chkPurified.isChecked() || chkAlkaline.isChecked();
    }

    private boolean isPriceValid(TextView textView) {
        return isPriceValid(textView, null);
    }

    private boolean isPriceValid(TextView textView, Wrapper<Double> wrapper) {
        if (textView.getText().toString().isEmpty())
            return false;

        try {
            if (wrapper != null) {
                wrapper.setValue(Double.valueOf(textView.getText().toString()));
                return wrapper.getValue() >= 0;
            } else {
                double val = Double.valueOf(textView.getText().toString());
                return val >= 0;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public interface Listener {
        void completedSalesInfo(boolean slimOffered, boolean roundOffered, boolean allowSwap,
                                boolean alkalineOffered, boolean distilledOffered,
                                boolean mineralOffered, boolean purifiedOffered,
                                double alkalinePrice, double distilledPrice,
                                double mineralPrice, double purifiedPrice);
        void retrieveSalesInfo(Receiver receiver);
    }

    public interface Receiver {
        void receive(boolean slimOffered, boolean roundOffered, boolean allowSwap,
                     boolean alkalineOffered, boolean distilledOffered,
                     boolean mineralOffered, boolean purifiedOffered,
                     double alkalinePrice, double distilledPrice,
                     double mineralPrice, double purifiedPrice);
        void notInitialized();
    }
}
