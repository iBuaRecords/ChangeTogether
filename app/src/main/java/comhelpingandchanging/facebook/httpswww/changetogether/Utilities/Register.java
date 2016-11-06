package comhelpingandchanging.facebook.httpswww.changetogether.Utilities;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by Yannick on 26.10.2016.
 */

public class Register extends AsyncTask<Void, Void, String>{

    Account account;
    private Activity callingActivity;
    private String email;
    private String password;
    RequestHandler rh = new RequestHandler();

    public Register(Activity callingActivity, String email, String password) {

        account = (Account) callingActivity.getApplication();
        this.callingActivity = callingActivity;
        this.email = email;
        this.password = password;
    }


    @Override
    protected String doInBackground(Void... params) {
        HashMap<String,String> data = new HashMap<>();

        data.put("email", email);
        data.put("password", password);
        String result = rh.sendPostRequest(Constants.DBREGISTER,data);

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
        else {
            if (result.equals("User added, logging in...") || result.equals("User already exists, logging in instead...")) {
                account.login(callingActivity, email, password);
            }
            Toast.makeText(callingActivity, result, Toast.LENGTH_SHORT).show();
        }
    }
}
