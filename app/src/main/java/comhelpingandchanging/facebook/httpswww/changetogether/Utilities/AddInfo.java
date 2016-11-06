package comhelpingandchanging.facebook.httpswww.changetogether.Utilities;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;

import comhelpingandchanging.facebook.httpswww.changetogether.Activities.OwnProfileFragment;

/**
 * Created by Yannick on 05.11.2016.
 */

public class AddInfo extends AsyncTask<Void, Void, String > {

    Account account;
    Activity callingActivity;
    String email;
    String key;
    String value;
    RequestHandler rh = new RequestHandler();

    public AddInfo(Activity callingActivity, String email, String key, String value){

        account = (Account) callingActivity.getApplication();
        this.callingActivity = callingActivity;
        this.email = email;
        this.key = key;
        this.value = value;
    }
    @Override
    protected String doInBackground(Void... params) {
        HashMap<String,String> data = new HashMap<>();

        data.put("email", email);
        data.put(key, value);
        String link = "";
        switch (key){
            case("password"):
                link = Constants.DBEDITPASSWORD;
                break;
            case("location"):
                link = Constants.DBEDITLOCATION;
                break;
            case("language"):
                link = Constants.DBEDITLANGUAGE;
                break;
        }
        String result = rh.sendPostRequest(link,data);

        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        if(result.equals("connection error")) Snackbar.make(callingActivity.findViewById(android.R.id.content), "Connection error", Snackbar.LENGTH_INDEFINITE)
                .setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rh.retry();
                    }
                })
                .setActionTextColor(Color.RED)
                .show();
        else
            callingActivity.finish();
    }
}
