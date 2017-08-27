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

import aquajmt.mapua.com.shopapp.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jabito on 27/08/2017.
 */

public class AdminRegistrationTwoFragment extends Fragment{

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
    }

    public static AdminRegistrationTwoFragment newInstance() {
        AdminRegistrationTwoFragment fragment = new AdminRegistrationTwoFragment();
        return fragment;
    }


}
