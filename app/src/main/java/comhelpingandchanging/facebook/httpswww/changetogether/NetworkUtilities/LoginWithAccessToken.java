package comhelpingandchanging.facebook.httpswww.changetogether.NetworkUtilities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.util.Base64;
import android.view.View;

import java.util.HashMap;
import java.util.regex.Pattern;

import comhelpingandchanging.facebook.httpswww.changetogether.Activities.MainAppActivity;
import comhelpingandchanging.facebook.httpswww.changetogether.Utilities.Account;
import comhelpingandchanging.facebook.httpswww.changetogether.Utilities.Constants;

/**
 * Created by Yannick on 29.10.2016.
 */

public class LoginWithAccessToken extends AsyncTask<Void, Void, String> {

    ProgressDialog loading;
    private Account account;
    private Activity callingActivity;
    private String email;
    private String accessToken;
    RequestHandler rh = new RequestHandler();

    public LoginWithAccessToken(Activity callingActivity, String email, String accessToken) {

        account = (Account) callingActivity.getApplication();
        this.callingActivity = callingActivity;
        this.email = email;
        this.accessToken = accessToken;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        loading = ProgressDialog.show(callingActivity, "Uploading...", null,true,true);
    }

    @Override
    protected String doInBackground(Void... params) {
        HashMap<String,String> data = new HashMap<>();

        data.put("email", email);
        data.put("token", accessToken);
        String result = rh.sendPostRequest(Constants.DBLOGINWITHACCESSTOKEN,data);

        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        loading.dismiss();
        if(result.equals("connection error"))
            Snackbar.make(callingActivity.findViewById(android.R.id.content), "Connection error", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Retry", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            new LoginWithAccessToken(callingActivity, email, accessToken).execute();
                        }
                    })
                    .setActionTextColor(Color.RED)
                    .show();
        else {
            if(result.equals("error") || result.equals("log in first"))
                ;
            else {
                String[] results = result.split(Pattern.quote("|"));
                String location = results[0];
                String language = results[1];

                byte[] decodedString = Base64.decode(results[2], Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                setSelfInfo(results[3], email, location, language, decodedByte);
                Intent search = new Intent(callingActivity, MainAppActivity.class);
                callingActivity.startActivity(search);
                callingActivity.finishAffinity();
            }
        }
    }

    private void setSelfInfo(String sessionId, String email, String location, String language, Bitmap profilePic){

        account.setSessionId(sessionId);
        account.setEmail(email);
        account.setLocation(location);
        account.setLanguage(language);
        account.setProfilePic(profilePic);
    }
}