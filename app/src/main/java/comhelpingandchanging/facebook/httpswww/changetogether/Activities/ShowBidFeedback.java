package comhelpingandchanging.facebook.httpswww.changetogether.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import comhelpingandchanging.facebook.httpswww.changetogether.R;
import comhelpingandchanging.facebook.httpswww.changetogether.Utilities.Account;

/**
 * Created by Ludwig on 05.11.2016.
 */

public class ShowBidFeedback extends Activity {

    TextView bid;
    Button rateBtn;
    ListView feedbackList;
    public CustomAdapter adapter;
    public ArrayList<String[]> feedbacks = new ArrayList<>();
    Account account;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bid_feedback);

        account = (Account) getApplication();

        bid = (TextView) findViewById(R.id.bidType);
        rateBtn = (Button) findViewById(R.id.rateBtn);
        feedbackList = (ListView) findViewById(R.id.listFeedback);

        bid.setText(getIntent().getStringExtra("tag"));
        adapter = new CustomAdapter(this, feedbacks);
        feedbackList.setAdapter(adapter);

        rateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeedbackDialog add = new FeedbackDialog();
                add.setArguments(new Bundle());
                add.getArguments().putString("tag", getIntent().getStringExtra("tag"));
                add.show(getFragmentManager(), "Biete Dialog");
            }
        });

        account.searchFeedback(this, getIntent().getStringExtra("tag"));
    }
}
