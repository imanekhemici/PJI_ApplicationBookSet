package pji.example.pji.bookset.ajout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.sql.SQLException;

import pji.example.pji.bookset.R;
import pji.example.pji.implementation.Collection.Livre;
import pji.example.pji.implementation.base.DatabaseManager;
import pji.example.pji.implementation.extra.Methodes;


public class ConfirmationAjout extends Methodes {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_ajout);

        TextView details = (TextView) findViewById(R.id.detailsLivre);
        details.setText(afficher());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_confirmation_ajout, menu);
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

    public void ajouter(View view) throws SQLException {
        Livre livre =(Livre) getIntent().getSerializableExtra("livre1");
        Intent intent = new Intent(this, Confirmation.class);

        boolean existe = DatabaseManager.getInstance().getHelper().getLivreDao().existe(livre.getTitre());

        if(existe){
            intent.putExtra("message","Ce livre existe deja dans votre base");
            startActivity(intent);

        }
        else{

            DatabaseManager.getInstance().getHelper().getLivreDao().addData(livre);
            intent.putExtra("message", "Ce livre a été bien ajouté");
            startActivity(intent);
        }

    }

    public String afficher(){

        Livre livre =(Livre) getIntent().getSerializableExtra("livre1");
        String details = "";

        details = "Titre: "+livre.getTitre()+"\n"
                + "Auteur: "+livre.getAuteur()+"\n"
                + "Isbn: "+livre.getIsbn()+"\n"
                + "Genre: "+livre.getGenre()+"\n"
                + "Langue: "+livre.getLangue()+"\n"
                + "Publié le: "+livre.getPublie_le()+"\n"
                + "Note: "+livre.getNote()+"\n"
                + "Note personelle"+livre.getNotePerso()+"\n"
                + "Favoris: "+livre.isFavori()+"\n"
                + "Lu: "+livre.isLu()+"\n"
                + "M'appartient: "+livre.isMappartient()+"\n"
                + "Emprunté: "+livre.getEmprunte()+"\n"
                + "Prété à: "+livre.getPrete()+"\n"
                ;
        return details;

    }
}
