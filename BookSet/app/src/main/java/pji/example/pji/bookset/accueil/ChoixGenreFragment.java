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
 * Created by raissa on 28/05/15.
 */
public class ChoixGenreFragment extends Fragment {

    private View rootView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_choix_genre, container, false);

        Spinner spinner = (Spinner) rootView.findViewById(R.id.Genrespinner);

        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this.getActivity(), R.array.genre_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        return rootView;

    }



}

