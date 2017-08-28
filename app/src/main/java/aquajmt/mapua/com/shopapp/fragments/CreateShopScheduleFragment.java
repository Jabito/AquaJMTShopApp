package aquajmt.mapua.com.shopapp.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import aquajmt.mapua.com.shopapp.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * Created by Bryan on 7/18/2017.
 */

public class CreateShopScheduleFragment extends Fragment {

    public static final int OPENING_TIME_DF_REQUEST = 1;
    public static final int CLOSING_TIME_DF_REQUEST = 2;

    private SimpleDateFormat timeFormatter;
    private Calendar openingTimeCalendar, closingTimeCalendar;
    private Listener listener;

    @BindView(R.id.txt_opening_time)
    TextView txtOpeningTime;

    @BindView(R.id.txt_closing_time)
    TextView txtClosingTime;

    @BindView(R.id.txt_days_open_validation)
    TextView txtDaysOpenValidation;

    @BindView(R.id.chk_sunday)
    CheckBox chkSunday;

    @BindView(R.id.chk_monday)
    CheckBox chkMonday;

    @BindView(R.id.chk_tuesday)
    CheckBox chkTuesday;

    @BindView(R.id.chk_wednesday)
    CheckBox chkWednesday;

    @BindView(R.id.chk_thursday)
    CheckBox chkThursday;

    @BindView(R.id.chk_friday)
    CheckBox chkFriday;

    @BindView(R.id.chk_saturday)
    CheckBox chkSaturday;

    @BindView(R.id.chk_holidays)
    CheckBox chkHolidays;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        timeFormatter = new SimpleDateFormat("HH:mm");
        openingTimeCalendar = Calendar.getInstance();
        openingTimeCalendar.set(Calendar.HOUR_OF_DAY, 8);
        openingTimeCalendar.set(Calendar.MINUTE, 0);

        closingTimeCalendar = Calendar.getInstance();
        closingTimeCalendar.set(Calendar.HOUR_OF_DAY, 21);
        closingTimeCalendar.set(Calendar.MINUTE, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_shop_schedule, container, false);
        ButterKnife.bind(this, view);

        listener.retrieveSchedule(new Receiver() {
            @Override
            public void receive(Date openingTime, Date closingTime,
                                boolean openOnSundays, boolean openOnMondays,
                                boolean openOnTuesdays, boolean openOnWednesdays,
                                boolean openOnThursdays, boolean openOnFridays,
                                boolean openOnSaturdays, boolean openOnHolidays) {
                openingTimeCalendar.setTime(openingTime);
                closingTimeCalendar.setTime(closingTime);

                chkSunday.setChecked(openOnSundays);
                chkMonday.setChecked(openOnMondays);
                chkTuesday.setChecked(openOnTuesdays);
                chkWednesday.setChecked(openOnWednesdays);
                chkThursday.setChecked(openOnThursdays);
                chkFriday.setChecked(openOnFridays);
                chkSaturday.setChecked(openOnSaturdays);
                chkHolidays.setChecked(openOnHolidays);

                continueInit();
                onDaysOpenCheckChanged();
            }

            @Override
            public void notInitialized() {
                continueInit();
            }

            private void continueInit() {
                txtOpeningTime.setText(timeFormatter.format(openingTimeCalendar.getTime()));
                txtClosingTime.setText(timeFormatter.format(closingTimeCalendar.getTime()));
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CreateShopSalesInfoFragment.Listener) {
            listener = (CreateShopScheduleFragment.Listener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement "
                    + CreateShopScheduleFragment.class.getSimpleName() + "."
                    + Listener.class.getSimpleName());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == OPENING_TIME_DF_REQUEST && resultCode == Activity.RESULT_OK) {
            openingTimeCalendar.set(Calendar.HOUR_OF_DAY,
                    data.getIntExtra(TimePickerDialogFragment.TIME_DF_RESULT_HOUR_OF_DAY, 0));
            openingTimeCalendar.set(Calendar.MINUTE,
                    data.getIntExtra(TimePickerDialogFragment.TIME_DF_RESULT_MINUTE, 0));

            if (openingTimeCalendar.after(closingTimeCalendar)) {
                openingTimeCalendar.setTime(closingTimeCalendar.getTime());
            }

            txtOpeningTime.setText(timeFormatter.format(openingTimeCalendar.getTime()));

        } else if (requestCode == CLOSING_TIME_DF_REQUEST && resultCode == Activity.RESULT_OK) {
            closingTimeCalendar.set(Calendar.HOUR_OF_DAY,
                    data.getIntExtra(TimePickerDialogFragment.TIME_DF_RESULT_HOUR_OF_DAY, 0));
            closingTimeCalendar.set(Calendar.MINUTE,
                    data.getIntExtra(TimePickerDialogFragment.TIME_DF_RESULT_MINUTE, 0));

            if (closingTimeCalendar.before(openingTimeCalendar)) {
                closingTimeCalendar.setTime(openingTimeCalendar.getTime());
            }

            txtClosingTime.setText(timeFormatter.format(closingTimeCalendar.getTime()));
        }
    }

    @OnClick(R.id.txt_opening_time)
    void txtOpeningTimeOnClick() {
        TimePickerDialogFragment tdf = TimePickerDialogFragment
                .newInstance(OPENING_TIME_DF_REQUEST,
                        openingTimeCalendar.get(Calendar.HOUR_OF_DAY),
                        openingTimeCalendar.get(Calendar.MINUTE));
        tdf.setTargetFragment(this, OPENING_TIME_DF_REQUEST);
        tdf.show(getFragmentManager(), "opening_tdf");
    }

    @OnClick(R.id.txt_closing_time)
    void txtClosingTimeOnClick() {
        TimePickerDialogFragment tdf = TimePickerDialogFragment
                .newInstance(CLOSING_TIME_DF_REQUEST,
                        closingTimeCalendar.get(Calendar.HOUR_OF_DAY),
                        closingTimeCalendar.get(Calendar.MINUTE));
        tdf.setTargetFragment(this, CLOSING_TIME_DF_REQUEST);
        tdf.show(getFragmentManager(), "closing_tdf");
    }

    @OnCheckedChanged({ R.id.chk_sunday, R.id.chk_monday, R.id.chk_tuesday,
            R.id.chk_wednesday, R.id.chk_thursday, R.id.chk_friday,
            R.id.chk_saturday, R.id.chk_holidays })
    void onDaysOpenCheckChanged() {
        txtDaysOpenValidation.setVisibility(isDaysOpenValid() ? View.GONE : View.VISIBLE);
    }

    @OnClick(R.id.btn_next)
    void btnNextOnClick() {
        boolean hasValidationErrors = !isDaysOpenValid()
                || !isOpeningTimeValid() || !isClosingTimeValid();

        if (!hasValidationErrors) {
            listener.completedSchedule(
                    openingTimeCalendar.getTime(),
                    closingTimeCalendar.getTime(),
                    chkSunday.isChecked(), chkMonday.isChecked(),
                    chkTuesday.isChecked(), chkWednesday.isChecked(),
                    chkThursday.isChecked(), chkFriday.isChecked(),
                    chkSaturday.isChecked(), chkHolidays.isChecked());
        }
    }

    private boolean isOpeningTimeValid() {
        return openingTimeCalendar.before(closingTimeCalendar);
    }

    private boolean isClosingTimeValid() {
        return closingTimeCalendar.after(openingTimeCalendar);
    }

    private boolean isDaysOpenValid() {
        return chkSunday.isChecked() || chkMonday.isChecked() || chkTuesday.isChecked()
                || chkWednesday.isChecked() || chkThursday.isChecked() || chkFriday.isChecked()
                || chkSaturday.isChecked() || chkHolidays.isChecked();
    }

    public interface Listener {
        void completedSchedule(Date openingTime, Date closingTime,
                               boolean openOnSundays, boolean openOnMondays,
                               boolean openOnTuesdays, boolean openOnWednesdays,
                               boolean openOnThursdays, boolean openOnFridays,
                               boolean openOnSaturdays, boolean openOnHolidays);
        void retrieveSchedule(Receiver receiver);
    }

    public interface Receiver {
        void receive(Date openingTime, Date closingTime,
                     boolean openOnSundays, boolean openOnMondays,
                     boolean openOnTuesdays, boolean openOnWednesdays,
                     boolean openOnThursdays, boolean openOnFridays,
                     boolean openOnSaturdays, boolean openOnHolidays);
        void notInitialized();
    }
}
