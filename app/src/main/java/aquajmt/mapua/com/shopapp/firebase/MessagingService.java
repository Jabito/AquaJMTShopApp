package aquajmt.mapua.com.shopapp.firebase;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Bryan on 7/16/2017.
 */

public class MessagingService extends FirebaseMessagingService {
    public static final String TAG = "MessagingService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "Message received from: " + remoteMessage.getFrom());
    }
}
