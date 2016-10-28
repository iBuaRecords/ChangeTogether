package comhelpingandchanging.facebook.httpswww.changetogether.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import comhelpingandchanging.facebook.httpswww.changetogether.R;


/**
 * Created by Yannick on 28.10.2016.
 */

public class MainActivity extends Activity {

    Button login;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        login = (Button) findViewById(R.id.loginBtn);
        register = (Button) findViewById(R.id.registerBtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(login);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(register);
            }
        });
    }
}