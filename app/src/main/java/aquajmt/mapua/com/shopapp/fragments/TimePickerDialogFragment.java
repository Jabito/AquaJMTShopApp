package aquajmt.mapua.com.shopapp.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Bryan on 7/23/2017.
 */

public class TimePickerDialogFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    public static final String TIME_DF_RESULT_HOUR_OF_DAY = "hourOfDay";
    public static final String TIME_DF_RESULT_MINUTE = "minute";

    private static final String REQUEST_CODE_ARG = "requestCode";
    private static final String HOUR_ARG = "hour";
    private static final String MIN_ARG = "min";

    private int requestCode, hour, min;

    public static TimePickerDialogFragment newInstance(int resultFor) {
        TimePickerDialogFragment tdf = new TimePickerDialogFragment();

        Bundle args = new Bundle();
        args.putInt(REQUEST_CODE_ARG, resultFor);
        tdf.setArguments(args);

        return tdf;
    }

    public static TimePickerDialogFragment newInstance(int resultFor, int hour, int min) {
        TimePickerDialogFragment tdf = new TimePickerDialogFragment();

        Bundle args = new Bundle();
        args.putInt(REQUEST_CODE_ARG, resultFor);
        args.putInt(HOUR_ARG, hour);
        args.putInt(MIN_ARG, min);
        tdf.setArguments(args);

        return tdf;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args == null || args.isEmpty() || !args.containsKey(REQUEST_CODE_ARG)) {
            throw new IllegalArgumentException("TimePickerDialogFragment: No arguments found! " +
                    "Looking for the ff. arguments: {" + REQUEST_CODE_ARG + ", " +
                    HOUR_ARG + "*, " + MIN_ARG + "* }");
        } else {
            if (getTargetFragment() != null)
                requestCode = getTargetRequestCode();
            else
                requestCode = args.getInt(REQUEST_CODE_ARG);

            if (args.containsKey(HOUR_ARG) && args.containsKey(MIN_ARG)) {
                hour = args.getInt(HOUR_ARG);
                min = args.getInt(MIN_ARG);
            } else {
                Calendar calendar = Calendar.getInstance();
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                min = calendar.get(Calendar.MINUTE);
            }
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new TimePickerDialog(getActivity(), this, hour, min,
                DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Intent intent = new Intent();
        intent.putExtra(TIME_DF_RESULT_HOUR_OF_DAY, hourOfDay);
        intent.putExtra(TIME_DF_RESULT_MINUTE, minute);

        if (getTargetFragment() != null)
            getTargetFragment().onActivityResult(requestCode, Activity.RESULT_OK, intent);
    }
}
