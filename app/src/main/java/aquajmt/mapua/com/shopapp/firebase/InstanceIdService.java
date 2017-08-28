package aquajmt.mapua.com.shopapp.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Bryan on 7/16/2017.
 */

public class InstanceIdService extends FirebaseInstanceIdService {
    public static final String TAG = "InstanceIdService";

    @Override
    public void onTokenRefresh() {
        String newToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "New token: " + newToken);
    }
}
