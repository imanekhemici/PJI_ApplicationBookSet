package pji.example.pji.bookset.accueil;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import pji.example.pji.bookset.R;


/**
 * Created by raissa on 10/05/15.
 */
public class ConfigurerFragment extends Fragment {

    public ConfigurerFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_configurer, container, false);

        Spinner spinner = (Spinner) rootView.findViewById(R.id.configurer_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.configurer_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter1);


        return rootView;

    }
}
