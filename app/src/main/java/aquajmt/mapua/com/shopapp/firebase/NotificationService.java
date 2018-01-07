package aquajmt.mapua.com.shopapp.firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import aquajmt.mapua.com.shopapp.R;
import aquajmt.mapua.com.shopapp.activities.DashboardActivity;
import aquajmt.mapua.com.shopapp.utils.Constants;
import aquajmt.mapua.com.shopapp.utils.SharedPref;

/**
 * Created by Bryan on 7/16/2017.
 */

public class NotificationService extends FirebaseMessagingService {
    public static final String TAG = "NotificationService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        String notificationTitle = null, notificationBody = null;
        String dataTitle = null, dataBody = null, dataShopId = null, dataCustId = null;

        if (remoteMessage.getData().size() > 0 ) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData().get("title"));
            dataTitle = remoteMessage.getData().get("title");
            dataBody = remoteMessage.getData().get("details");
            dataCustId = remoteMessage.getData().get("custId");
            dataShopId = remoteMessage.getData().get("shopId");
        }

        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            notificationTitle = remoteMessage.getNotification().getTitle();
            notificationBody = remoteMessage.getNotification().getBody();
        }

//        if (dataShopId.equalsIgnoreCase(SharedPref.getStringValue(SharedPref.USER,SharedPref.SHOP_ID,getApplicationContext())))
            sendNotification(dataTitle, dataBody,dataCustId);
    }

    private void sendNotification(String dataTitle, String dataMessage, String dataCustId) {
        Intent intent = new Intent(this, DashboardActivity.class);
        intent.putExtra(Constants.TITLE, dataTitle);
        intent.putExtra(Constants.BODY, dataMessage);
        intent.putExtra(Constants.ID, dataCustId);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        android.support.v7.app.NotificationCompat.Builder notificationBuilder = (android.support.v7.app.NotificationCompat.Builder) new android.support.v7.app.NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(Constants.DEL_REQUEST)
                .setContentText(Constants.CUST_ID + dataCustId)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}
