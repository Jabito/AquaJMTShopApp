package aquajmt.mapua.com.shopapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import aquajmt.mapua.com.shopapp.R;
import aquajmt.mapua.com.shopapp.api.Api;
import aquajmt.mapua.com.shopapp.api.retrofit.RetrofitApiImpl;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * Created by Bryan on 7/18/2017.
 */

public class CreateBasicShopDetailsFragment extends Fragment {

    private Listener listener;
    private boolean isUniqueIdAvailable;

    @BindView(R.id.txt_unique_id)
    TextView txtUniqueId;

    @BindView(R.id.txt_name)
    TextView txtName;

    @BindView(R.id.txt_address)
    TextView txtAddress;

    @BindView(R.id.txt_cellphone_no)
    TextView txtCellphoneNo;

    @BindView(R.id.txt_alt_contact_no)
    TextView txtAltContactNo;

    @BindView(R.id.img_invalid_uid)
    ImageView imgInvalidUid;

    @BindView(R.id.img_valid_uid)
    ImageView imgValidUid;

    @BindView(R.id.prg_loading_uid)
    ProgressBar prgLoadingUid;

    @BindView(R.id.txt_uid_validation_used)
    TextView txtUidValidationUsed;

    @BindView(R.id.txt_uid_validation_empty)
    TextView txtUidValidationEmpty;

    @BindView(R.id.txt_uid_validation_start)
    TextView txtUidValidationStart;

    @BindView(R.id.txt_uid_validation_chars)
    TextView txtUidValidationChars;

    @BindView(R.id.txt_name_validation)
    TextView txtNameValidation;

    @BindView(R.id.txt_address_validation)
    TextView txtAddressValidation;

    @BindView(R.id.txt_cellphone_no_validation)
    TextView txtCellphoneNoValidation;

    @BindView(R.id.txt_alt_contact_no_validation)
    TextView txtAltContactNoValidation;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Listener) {
            listener = (Listener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement "
                    + CreateBasicShopDetailsFragment.class.getSimpleName() + "."
                    + Listener.class.getSimpleName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_basic_shop_details, container, false);
        ButterKnife.bind(this, view);

        listener.retrieveBasicInfo(new Receiver() {
            @Override
            public void receive(String uniqueId, String name, String address, String cellphoneNo, String alternateNo) {
                txtUniqueId.setText(uniqueId);
                txtName.setText(name);
                txtAddress.setText(address);
                txtCellphoneNo.setText(cellphoneNo);
                txtAltContactNo.setText(alternateNo);

                txtUniqueIdOnTextChanged();
                txtNameOnTextChanged();
                txtAddressOnTextChanged();
                txtCellphoneNoOnTextChanged();
                txtAltContactNoOnTextChanged();
            }

            @Override
            public void notInitialized() {
            }
        });

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @OnClick(R.id.btn_next)
    void btnNextOnClick() {
        boolean hasValidationErrors =
                !validateAndUpdateUniqueIdUiState() | !validateAndUpdateNameUiState()
                        | !validateAndUpdateAddressUiState() | !validateAndUpdateCellphoneNoUiState()
                        | !validateAndUpdateAltContactNoUiState();

        if (!hasValidationErrors) {
            listener.completedBasicInfo(
                    txtUniqueId.getText().toString(),
                    txtName.getText().toString(),
                    txtAddress.getText().toString(),
                    txtCellphoneNo.getText().toString(),
                    txtAltContactNo.getText().toString()
            );
        }
    }

    @OnTextChanged(R.id.txt_unique_id)
    void txtUniqueIdOnTextChanged() {
        txtUidValidationUsed.setVisibility(View.GONE);
        imgValidUid.setVisibility(View.GONE);
        imgInvalidUid.setVisibility(View.GONE);

        int flags = validateUniqueId(txtUniqueId.getText().toString());
        if (flags != UNIQUE_ID_VALID) {
            prgLoadingUid.setVisibility(View.GONE);
            updateUniqueIdUiState(flags);
        } else {
            txtUidValidationEmpty.setVisibility(View.GONE);
            txtUidValidationStart.setVisibility(View.GONE);
            txtUidValidationChars.setVisibility(View.GONE);
            prgLoadingUid.setVisibility(View.VISIBLE);

            String uniqueId = txtUniqueId.getText().toString();

            RetrofitApiImpl retrofitApi = new RetrofitApiImpl(Api.API_ENDPOINT);
            retrofitApi.checkUniqueIdValid(uniqueId, new Api.CheckUniqueIdAvailabilityListener() {

                @Override
                public void isUniqueIdAvailable(Boolean isAvailable) {
                    isUniqueIdAvailable = isAvailable;
                    prgLoadingUid.setVisibility(View.GONE);
                    int flags = validateUniqueId(txtUniqueId.getText().toString());
                    if (flags != UNIQUE_ID_VALID) {
                        updateUniqueIdUiState(flags);
                    } else if (isUniqueIdAvailable) {
                        imgValidUid.setVisibility(View.VISIBLE);
                        imgInvalidUid.setVisibility(View.GONE);
                        txtUidValidationUsed.setVisibility(View.GONE);
                    } else {
                        imgValidUid.setVisibility(View.GONE);
                        imgInvalidUid.setVisibility(View.VISIBLE);
                        txtUidValidationUsed.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }

    @OnTextChanged(R.id.txt_name)
    void txtNameOnTextChanged() {
        validateAndUpdateNameUiState();
    }

    @OnTextChanged(R.id.txt_address)
    void txtAddressOnTextChanged() {
        validateAndUpdateAddressUiState();
    }

    @OnTextChanged(R.id.txt_cellphone_no)
    void txtCellphoneNoOnTextChanged() {
        validateAndUpdateCellphoneNoUiState();
    }

    @OnTextChanged(R.id.txt_alt_contact_no)
    void txtAltContactNoOnTextChanged() {
        validateAndUpdateAltContactNoUiState();
    }

    private void updateUniqueIdUiState(int flags) {
        boolean isEmpty = (flags & UNIQUE_ID_EMPTY) == UNIQUE_ID_EMPTY;
        txtUidValidationEmpty.setVisibility(isEmpty ? View.VISIBLE : View.GONE);

        boolean hasInvalidStart = (flags & UNIQUE_ID_INVALID_START) == UNIQUE_ID_INVALID_START;
        txtUidValidationStart.setVisibility(hasInvalidStart ? View.VISIBLE : View.GONE);

        boolean hasInvalidChars = (flags & UNIQUE_ID_INVALID_CHARS) == UNIQUE_ID_INVALID_CHARS;
        txtUidValidationChars.setVisibility(hasInvalidChars ? View.VISIBLE : View.GONE);
    }

    private boolean validateAndUpdateUniqueIdUiState() {
        int flags = validateUniqueId(txtUniqueId.getText().toString());

        boolean isValid = flags == UNIQUE_ID_VALID;
        if (!isValid) {
            imgValidUid.setVisibility(View.GONE);
            imgInvalidUid.setVisibility(View.GONE);
            prgLoadingUid.setVisibility(View.GONE);

            updateUniqueIdUiState(flags);
        }

        return isValid && isUniqueIdAvailable;
    }

    private boolean validateAndUpdateNameUiState() {
        boolean isValid = isNameValid();
        txtNameValidation.setVisibility(isValid ? View.GONE : View.VISIBLE);

        return isValid;
    }

    private boolean validateAndUpdateAddressUiState() {
        boolean isValid = isAddressValid();
        txtAddressValidation.setVisibility(isValid ? View.GONE : View.VISIBLE);

        return isValid;
    }

    private boolean validateAndUpdateCellphoneNoUiState() {
        boolean isValid = isCellNoValid();
        txtCellphoneNoValidation.setVisibility(isValid ? View.GONE : View.VISIBLE);

        return isValid;
    }

    private boolean validateAndUpdateAltContactNoUiState() {
        boolean isValid = isAltContactNoValid();
        txtAltContactNoValidation.setVisibility(isValid ? View.GONE : View.VISIBLE);

        return isValid;
    }

    private boolean isNameValid() {
        return !txtName.getText().toString().isEmpty();
    }

    private boolean isAddressValid() {
        return !txtAddress.getText().toString().isEmpty();
    }

    private boolean isCellNoValid() {
        return !txtCellphoneNo.getText().toString().isEmpty();
    }

    private boolean isAltContactNoValid() {
        return !txtAltContactNo.getText().toString().isEmpty();
    }

    private static final int UNIQUE_ID_VALID = 0;
    private static final int UNIQUE_ID_EMPTY = 1;
    private static final int UNIQUE_ID_INVALID_START = 2;
    private static final int UNIQUE_ID_INVALID_CHARS = 4;

    private int validateUniqueId(String uid) {
        int flags = UNIQUE_ID_VALID;

        if (uid.isEmpty()) {
            flags |= UNIQUE_ID_EMPTY;
        } else {
            char[] uidChars = uid.toCharArray();
            if (!Character.isLetter(uidChars[0]))
                flags |= UNIQUE_ID_INVALID_START;

            for (char c : uidChars) {
                if (!Character.isLetterOrDigit(c) && c != '_' && c != '-')
                    flags |= UNIQUE_ID_INVALID_CHARS;
            }
        }

        return flags;
    }

    public interface Listener {
        void completedBasicInfo(String uniqueId, String name, String address,
                                String cellphoneNo, String alternateNo);

        void retrieveBasicInfo(Receiver receiver);

        void errorOccurred();
    }

    public interface Receiver {
        void receive(String uniqueId, String name, String address,
                     String cellphoneNo, String alternateNo);

        void notInitialized();
    }
}
