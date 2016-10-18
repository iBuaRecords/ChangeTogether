package comhelpingandchanging.facebook.httpswww.changetogether;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by len13 on 17.10.2016.
 */

public class ProfileActivity extends Activity {
    Button menu;
    Button feedbackButton;
    ListView feedbackList;
    EditText feedback;
    ArrayList<String> listItems=new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        menu = (Button) findViewById(R.id.menuButton);
        menu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent menuActivity = new Intent(ProfileActivity.this, MenuActivity.class);
                startActivity(menuActivity);
            }
        });

        feedbackList = (ListView) findViewById(R.id.list);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        feedbackList.setAdapter(adapter);

        feedback = (EditText) findViewById(R.id.editText);



        feedbackButton = (Button) findViewById(R.id.sendButton);
        feedbackButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Editable feedbackText = feedback.getText();
                listItems.add(0,feedbackText.toString());
                adapter.notifyDataSetChanged();
            }
        });




    }
}