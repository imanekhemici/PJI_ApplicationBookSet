package pji.example.pji.bookset.accueil;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pji.example.pji.bookset.R;
import pji.example.pji.implementation.Collection.Livre;
import pji.example.pji.implementation.CollectionBdd.LivreDaoImpl;
import pji.example.pji.implementation.base.DatabaseManager;

/**
 * Created by raissa on 29/05/15.
 */
public class AfficheLivresParGenreFragment extends Fragment{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_affiche_livre_choix_genre, container, false);
        String genre=getArguments().getString("genreChoisi");


        ListView view = (ListView) rootView.findViewById(R.id.listLivre) ;

        LivreDaoImpl livreDao = DatabaseManager.getInstance().getHelper().getLivreDao();
        if(livreDao != null)
        {
            List<Livre> livres = livreDao.findAllThisGenre(genre);
            List<HashMap<String, String>> liste = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> element;

            //Pour chaque personne dans notre répertoire…
            for (Livre livre : livres) {

                element = new HashMap<String, String>();

                element.put("titre", livre.getTitre());
                element.put("auteur", livre.getAuteur());
                liste.add(element);

            }

            ListAdapter adapter = new SimpleAdapter(this.getActivity(), liste, R.layout.afficher_livre,
                    new String[] {"titre", "auteur"},
                    new int[] {R.id.titreaff, R.id.auteuraff });

            //Pour finir, on donne à la ListView le SimpleAdapter

            view.setAdapter(adapter);
        }


        return rootView;

    }
}