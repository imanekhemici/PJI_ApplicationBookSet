package pji.example.pji.bookset.recherche;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pji.example.pji.bookset.R;
import pji.example.pji.bookset.accueil.AfficherLivre;
import pji.example.pji.implementation.Collection.Livre;
import pji.example.pji.implementation.CollectionBdd.LivreDao;
import pji.example.pji.implementation.base.DatabaseManager;
import pji.example.pji.implementation.extra.Methodes;

public class ResultatScannerActivity extends Methodes {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat_scanner);
        Intent intent = getIntent();

        ListView view = (ListView) findViewById(R.id.listResultatScanner) ;

        List<Livre> livres = (List<Livre>) intent.getSerializableExtra("livres");
        List<HashMap<String, Object>> liste = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> element;

        for (Livre livre : livres) {

            element = new HashMap<String, Object>();
            String livreTitre =  livre.getTitre().substring(0,1).toUpperCase() + livre.getTitre().substring(1).toLowerCase();
            String livreAuteur = livre.getAuteur().substring(0,1).toUpperCase() + livre.getAuteur().substring(1).toLowerCase();
            element.put("titre", livreTitre);
            element.put("auteur", livreAuteur);


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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_resultat_scanner, menu);
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

    public void rechercheResultat(View view) throws SQLException {

        LivreDao livreBdd= DatabaseManager.getInstance().getHelper().getLivreDao();
        EditText titreedit = (EditText) findViewById(R.id.recherche_valeur);
        String titre = titreedit.getText().toString();

        Spinner typeRecherche = (Spinner) findViewById(R.id.recherche_spiner);
        String rechercheType = typeRecherche.getSelectedItem().toString();

        List livres = new ArrayList<Livre>();
        switch(rechercheType){
            case "Isbn":
                livres = livreBdd.findByIsbn(titre);
                break;

            case "Titre":
                livres = livreBdd.findByTitle(titre);
                break;
            case "Auteur":
                livres = livreBdd.findByAuteur(titre);
                break;
            case "Genre":
                livres = livreBdd.findByGenre(titre);
                break;
            case "Langue":
                livres = livreBdd.findByLangue(titre);
                break;
        }

        Intent intent = new Intent(this, ResulatActivity.class);
        intent.putExtra("livreresult", (java.io.Serializable) livres);
        startActivity(intent);
    }

}
