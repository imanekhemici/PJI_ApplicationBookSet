package pji.example.pji.bookset.modification;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import pji.example.pji.bookset.R;
import pji.example.pji.implementation.Collection.Livre;
import pji.example.pji.implementation.extra.Methodes;

public class Modification1Activity extends Methodes {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modification1);

        final Livre livre = (Livre) getIntent().getSerializableExtra("livre1");

        EditText text = (EditText) findViewById(R.id.titreModif);
        String titre = livre.getTitre();
        text.setText(titre);
        text = (EditText) findViewById(R.id.auteurModif);
        text.setText(livre.getAuteur());

        text = (EditText) findViewById(R.id.isbnModif);
        text.setText(livre.getIsbn());

        EditText langue_t = (EditText) findViewById(R.id.langueModif);
        langue_t.setText(livre.getLangue());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_modification1, menu);
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
    public void suivantModif(View view){
        final Livre livre = (Livre) getIntent().getSerializableExtra("livre1");

        EditText text = (EditText) findViewById(R.id.titreModif);
        String titre = text.getText().toString();
        text = (EditText) findViewById(R.id.auteurModif);
        String auteur = text.getText().toString();
        text = (EditText) findViewById(R.id.isbnModif);
        String isbn = text.getText().toString();

        EditText langue_t = (EditText) findViewById(R.id.langueModif);
        String langue = langue_t.getText().toString();

        if (titre.equals("") | auteur.equals("") | isbn.equals("")) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            // 2. Chain together various setter methods to set the dialog characteristics
            builder.setMessage("Veuillez remplir les champs vides")
                    .setTitle("Champs vide");

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                }
            });
            // 3. Get the AlertDialog from create()
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {

            if ((isbn.length() !=10) && (isbn.length() != 13)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                // 2. Chain together various setter methods to set the dialog characteristics
                builder.setMessage("Isbn-10 or Isbn-13")
                        .setTitle("Isbn");

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                // 3. Get the AlertDialog from create()
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                livre.setTitre(titre);
                livre.setAuteur(auteur);
                livre.setIsbn(isbn);
                livre.setLangue(langue);
                Intent intent = new Intent(this, Modification2Activity.class);
                intent.putExtra("livreDe",livre);
                startActivity(intent);
            }
        }

    }
}
