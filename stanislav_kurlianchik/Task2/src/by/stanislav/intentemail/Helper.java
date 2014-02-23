package by.stanislav.intentemail;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class Helper {

	public static void openTwitter(Activity activity, String logIn) {
		if (NetworkUtils.isNetworkAvailable(activity)) {
			Uri uri;
			Intent intent;
			try {
				uri = Uri.parse("twitter://user?screen_name=" + logIn);
				intent = new Intent(Intent.ACTION_VIEW, uri);
				activity.startActivity(intent);
			} catch (Exception e) {
				uri = Uri.parse("https://twitter.com/#!/" + logIn);
				intent = new Intent(Intent.ACTION_VIEW, uri);
				activity.startActivity(intent);
			}
		} else {
			showToast(activity, Constants.CONNECTION_ERROR);
		}
	}

	public static void openInstagram(Activity activity, String logIn) {
		if (NetworkUtils.isNetworkAvailable(activity)) {
			Uri uri;
			Intent intent;
			try {
				uri = Uri.parse("instagram://user?screen_name=" + logIn);
				intent = new Intent(Intent.ACTION_VIEW, uri);
				activity.startActivity(intent);
			} catch (Exception e) {
				uri = Uri.parse("http://instagram.com/" + logIn);
				intent = new Intent(Intent.ACTION_VIEW, uri);
				activity.startActivity(intent);
			}
		} else {
			showToast(activity, Constants.CONNECTION_ERROR);
		}
	}

	public static void openSkype(Activity activity, String logIn) {
		if (NetworkUtils.isNetworkAvailable(activity)) {
			Uri uri;
			Intent intent;
			try {
				uri = Uri.parse("skype:" + logIn);
				intent = new Intent(Intent.ACTION_VIEW, uri);
				activity.startActivity(intent);
			} catch (Exception e) {
				showToast(activity, "Skype not installed");
			}
		} else {
			showToast(activity, Constants.CONNECTION_ERROR);
		}

	}

	public static void makeCall(Activity activity, String telNumber) {
		activity.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
				+ telNumber)));
	}

	public static void sendSMS(Activity activity, String message) {
		Intent smsIntent = new Intent(Intent.ACTION_VIEW);
		smsIntent.setType("vnd.android-dir/mms-sms");
		smsIntent.putExtra("address", Constants.TELNUMBER);
		smsIntent.putExtra("sms_body", message);
		activity.startActivity(smsIntent);
	}


	public static void showToast(Activity activity, String message) {
		Toast.makeText(activity.getApplicationContext(), message,
				Toast.LENGTH_SHORT).show();
	}


}
