package comhelpingandchanging.facebook.httpswww.changetogether.DialogFragments;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import comhelpingandchanging.facebook.httpswww.changetogether.Adapter.PlacesAutoCompleteAdapter;
import comhelpingandchanging.facebook.httpswww.changetogether.Adapter.SpinnerAdapter;
import comhelpingandchanging.facebook.httpswww.changetogether.Fragments.BieteFragment;
import comhelpingandchanging.facebook.httpswww.changetogether.R;
import comhelpingandchanging.facebook.httpswww.changetogether.Utilities.Account;

public class BidDialogNew extends DialogFragment {
    AutoCompleteTextView location;
    BieteFragment callingFragment;
    String city;
    String bid;
    Spinner bidTypes;
    TextView description;
    TextView date;
    TextView time;
    Button done;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_select_biete_new, container, false);

        callingFragment = (BieteFragment) getParentFragment();

        bidTypes = (Spinner) rootView.findViewById(R.id.bidSpinner);

        SpinnerAdapter adapter = new SpinnerAdapter(getActivity(), android.R.layout.simple_list_item_1);


        adapter.addAll(getResources().getStringArray(R.array.bid_arrays));
        adapter.add("Was wollen Sie anbieten?");
        bidTypes.setAdapter(adapter);
        bidTypes.setSelection(adapter.getCount());

        bidTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub

                if(bidTypes.getSelectedItem() == "Was wollen Sie anbieten?")
                {

                }
                else{
                    bid = bidTypes.getSelectedItem().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });


        location = (AutoCompleteTextView) rootView.findViewById(R.id.location);
        time = (TextView) rootView.findViewById(R.id.time);
        date = (TextView) rootView.findViewById(R.id.date);

        final AutoCompleteTextView autocompleteView = (AutoCompleteTextView) rootView.findViewById(R.id.location);
        autocompleteView.setAdapter(new PlacesAutoCompleteAdapter(getActivity(), R.layout.autocomplete_list_item)); // vorher getActivity() anstelle von this

        autocompleteView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position) != null)
                city = parent.getItemAtPosition(position).toString();
            }
        });


        description = (TextView) rootView.findViewById(R.id.description);

        done = (Button) rootView.findViewById(R.id.doneBtn);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (city != null && description.getText().toString().length() != 0 && location.getText().toString().length() != 0 &&
                 city.equals(location.getText().toString()) && time.getText().toString().length() != 0 && date.getText().toString().length() != 0) {
                    Double[] latLong = getLocationFromAddress(autocompleteView.getText().toString());

                    ((Account) callingFragment.getActivity().getApplication()).
                            addBid(callingFragment, bid, description.getText().toString(),
                                    autocompleteView.getText().toString(), latLong[0], latLong[1], date.getText().toString(), time.getText().toString(), 0);
                    getDialog().dismiss();
                }
                else
                    location.setError("Location doesn't exist");
            }
        });

        return rootView;
    }

    public Double[] getLocationFromAddress(String strAddress){

        Double[] latLong = new Double[2];
        Geocoder coder = new Geocoder(getActivity());
        List<Address> address;

        try {
            address = coder.getFromLocationName(strAddress,1);
            if (address==null) {
                return null;
            }
            Address location=address.get(0);
            latLong[0] = location.getLatitude();
            latLong[1] = location.getLongitude();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return latLong;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

        Activity activity = getActivity();
        if(callingFragment instanceof MyDialogCloseListener)
            ((MyDialogCloseListener)callingFragment).handleDialogClose(dialog);
        else if(activity instanceof MyDialogCloseListener)
            ((MyDialogCloseListener)activity).handleDialogClose(dialog);
    }
}
