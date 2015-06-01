package pji.example.pji.bookset.recherche;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.List;

import pji.example.pji.bookset.R;
import pji.example.pji.implementation.CollectionBdd.LivreDao;
import pji.example.pji.implementation.base.DatabaseManager;
import pji.example.pji.implementation.extra.Methodes;


public class RechercheChoixActivity extends Methodes {
    private Handler handler = new Handler();
    private TextView txtScanResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_choix);
    }

    public void scan(View view){
        IntentIntegrator integration = new IntentIntegrator(this);
        integration.initiateScan();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case IntentIntegrator.REQUEST_CODE:
                IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode,
                        resultCode, data);
                if (scanResult == null) {
                    return;
                }
                final String result = scanResult.getContents();
                if (result != null) {
                    LivreDao livreBdd= DatabaseManager.getInstance().getHelper().getLivreDao();
                    List livres = livreBdd.findByIsbn(result);

                    Intent intent = new Intent(this, ResultatScannerActivity.class);
                    intent.putExtra("livres",(java.io.Serializable)livres);
                    startActivity(intent);
                   /** handler.post(new Runnable() {
                        @Override
                        public void run() {
                                txtScanResult.setText(result);

                        }
                    });*/
                }
                break;
            default:
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recherche_choix, menu);
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
