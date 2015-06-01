package pji.example.pji.bookset.recherche;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pji.example.pji.bookset.R;
import pji.example.pji.bookset.accueil.AfficherLivre;
import pji.example.pji.implementation.Collection.Livre;
import pji.example.pji.implementation.extra.Methodes;


public class ResulatActivity extends Methodes {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resulat);

        ListView view = (ListView) findViewById(R.id.listResultat) ;
        List<HashMap<String, Object>> liste = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> element;
        List<Livre> livres = new ArrayList<Livre>();
        livres = (List)getIntent().getSerializableExtra("livreresult");
        TextView resulat = (TextView) findViewById(R.id.resultat);

    if(livres.isEmpty()){
        resulat.setText("Aucun livre trouvé");
    }
    else {

        //Pour chaque personne dans notre répertoire…
        for (Livre livre : livres) {

            String livreTitre =  livre.getTitre().substring(0,1).toUpperCase() + livre.getTitre().substring(1).toLowerCase();
            String livreAuteur = livre.getAuteur().substring(0,1).toUpperCase() + livre.getAuteur().substring(1).toLowerCase();
            element = new HashMap<String, Object>();

            element.put("titre", livre.getTitre());
            element.put("auteur", livre.getAuteur());
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 14;
            Bitmap bm = BitmapFactory.decodeFile(livre.getImage(),options);

            element.put("image",bm);
            liste.add(element);

        }

        SimpleAdapter adapter = new SimpleAdapter(this, liste, R.layout.afficher_livre,
                new String[]{"titre", "auteur","image"},
                new int[]{R.id.titreaff, R.id.auteuraff,R.id.couverture});
        adapter.setViewBinder(new AfficherLivre());
        //Pour finir, on donne à la ListView le SimpleAdapter

        view.setAdapter(adapter);
    }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_resulat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
