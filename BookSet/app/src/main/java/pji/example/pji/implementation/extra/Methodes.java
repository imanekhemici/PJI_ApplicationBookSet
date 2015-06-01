package pji.example.pji.implementation.extra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import java.sql.SQLException;

import pji.example.pji.bookset.information.InformationActivity;
import pji.example.pji.bookset.R;
import pji.example.pji.bookset.accueil.AccueilActivity;

import pji.example.pji.bookset.ajout.AjouterActivity;
import pji.example.pji.bookset.ajout.AjouterElementActivity;
import pji.example.pji.bookset.modification.Modification1Activity;
import pji.example.pji.bookset.recherche.RechercheActivity;
import pji.example.pji.bookset.recherche.RechercheChoixActivity;
import pji.example.pji.implementation.Collection.Livre;
import pji.example.pji.implementation.base.DatabaseManager;

/**
 * Created by imane khemici on 09/05/15.
 */
public class    Methodes  extends ActionBarActivity {
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    private Uri fileUri;
    public void ajouterElement(View view) {
        Intent intent = new Intent(this, AjouterActivity.class);
        startActivity(intent);

    }

    public void accueil(View view){
        Intent intent = new Intent(this, AccueilActivity.class);
        startActivity(intent);

    }
    public void annuler(View view){
        Intent intent = new Intent(this, AccueilActivity.class);
        startActivity(intent);

    }
    public void recherche(View view){
        Intent intent = new Intent(this,RechercheChoixActivity.class);
        startActivity(intent);
    }



    public void ajouter(View view) throws SQLException {
        Intent intent = new Intent(this, AjouterElementActivity.class);
        startActivity(intent);
    }

    public void information(View view) throws SQLException {
        TextView livreTitre = (TextView) view.findViewById(R.id.titreaff);
        String titre = livreTitre.getText().toString().toLowerCase();
        Livre livre = DatabaseManager.getInstance().getHelper().getLivreDao().findByTitre(titre) ;
        Intent intent = new Intent(this,InformationActivity.class);
        intent.putExtra("livre1",livre);
        startActivity(intent);
    }

    public void supprimer(View view){
        final Livre livre = (Livre) getIntent().getSerializableExtra("livre1");
        final Intent intent = new Intent(this, AccueilActivity.class);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog.Builder builder1 = builder.setMessage("Suppression")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            DatabaseManager.getInstance().getHelper().getLivreDao().delete(livre);
                            startActivity(intent);

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        builder.create().show();
    }


    public void ajouterAnouveau(View view){

        Intent intent = new Intent(this, AjouterElementActivity.class);
        startActivity(intent);
    }
    public void rechercheManuelle(View view){
        Intent intent = new Intent(this, RechercheActivity.class);
        startActivity(intent);
    }


    public void modifier(View view){
        final Livre livre = (Livre) getIntent().getSerializableExtra("livre1");
        Intent intent = new Intent(this, Modification1Activity.class);
        intent.putExtra("livre1",livre);
        startActivity(intent);

    }



}
