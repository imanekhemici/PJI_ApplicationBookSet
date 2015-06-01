package pji.example.pji.bookset.recherche;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pji.example.pji.bookset.R;
import pji.example.pji.implementation.Collection.Livre;
import pji.example.pji.implementation.CollectionBdd.LivreDao;
import pji.example.pji.implementation.base.DatabaseManager;
import pji.example.pji.implementation.extra.Methodes;


public class RechercheActivity extends Methodes {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);
        Spinner spinner = (Spinner) findViewById(R.id.recherche_spiner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.recherche_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);        EditText valeur = (EditText) findViewById(R.id.recherche_valeur);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recherche, menu);
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
