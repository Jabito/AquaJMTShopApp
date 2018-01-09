const functions = require('firebase-functions');

const admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);

exports.pushNotification = functions.database.ref('/messages/{pushId}').onWrite( event => {

    console.log('Push notification event triggered');

    var valueObject = event.data.val();

    const payload = {
        notification: {
            title: 'Order Request',
            body: "Login to view",
            sound: "default"
        },
        data: {
            title: valueObject.title,
            message: valueObject.message
        }
    };

    const options = {
        priority: "high",
        timeToLive: 60 * 60 * 24 //24 hours
    };

    return admin.messaging().sendToTopic("notifications", payload, options);
});