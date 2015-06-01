package pji.example.pji.bookset.modification;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;

import java.sql.SQLException;

import pji.example.pji.bookset.R;
import pji.example.pji.bookset.accueil.AccueilActivity;
import pji.example.pji.implementation.Collection.Livre;
import pji.example.pji.implementation.base.DatabaseManager;

public class Modification3Activity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modification3);
        Livre livre = (Livre) getIntent().getSerializableExtra("livreDe");

        EditText publie = (EditText) findViewById(R.id.publie);
        publie.setText(livre.getPublie_le());

        RatingBar note_tex = (RatingBar) findViewById(R.id.noteModif);
        note_tex.setRating(livre.getNote());

        RatingBar note_perso_tex = (RatingBar) findViewById(R.id.notepersoModif);
        note_perso_tex.setRating(livre.getNotePerso());

        CheckBox favoris = (CheckBox) findViewById(R.id.favorisModif);
        favoris.setChecked(livre.isFavori());

        CheckBox lu_c = (CheckBox) findViewById(R.id.luModif);
        lu_c.setChecked(livre.isLu());

        CheckBox mappartient_c = (CheckBox) findViewById(R.id.mappartientModif);
        mappartient_c.setChecked(livre.isMappartient());


        CheckBox panier_c = (CheckBox) findViewById(R.id.panierModif);
        panier_c.setChecked(livre.isPanier());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_modification3, menu);
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

    public void confirmerModif(View view) throws SQLException {
        Livre livre = (Livre) getIntent().getSerializableExtra("livreDe");

        EditText publie = (EditText) findViewById(R.id.publie);
        String publie_s = publie.getText().toString();
        livre.setPublie_le(publie_s);

        RatingBar note_tex = (RatingBar) findViewById(R.id.noteModif);
        float note = note_tex.getRating();

        livre.setNote(note);

        RatingBar note_perso_tex = (RatingBar) findViewById(R.id.notepersoModif);
        float noteperso = note_perso_tex.getRating();

        livre.setNotePerso(noteperso);

        CheckBox favoris = (CheckBox) findViewById(R.id.favorisModif);
        boolean favori = favoris.isChecked();

        livre.setFavori(favori);

        CheckBox lu_c = (CheckBox) findViewById(R.id.luModif);
        boolean lu = lu_c.isChecked();

        livre.setLu(lu);

        CheckBox mappartient_c = (CheckBox) findViewById(R.id.mappartientModif);
        boolean mappartient = mappartient_c.isChecked();

        livre.setMappartient(mappartient);

        CheckBox panier_c = (CheckBox) findViewById(R.id.panierModif);
        boolean panier = panier_c.isChecked();
        livre.setPanier(panier);

        DatabaseManager.getInstance().getHelper().getLivreDao().update(livre);
        Intent intent = new Intent(this, AccueilActivity.class);
        startActivity(intent);
    }
    }

