package pji.example.pji.bookset.modification;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.File;
import java.util.List;

import pji.example.pji.bookset.R;
import pji.example.pji.implementation.Collection.Livre;
import pji.example.pji.implementation.extra.Methodes;

public class Modification2Activity extends Methodes {
    private Bitmap bitmap;
    private boolean taken;
    private boolean imgCapFlag;
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modification2);

        image = new ImageView(getApplicationContext());
        Spinner spinner = (Spinner) findViewById(R.id.GenrespinnerModif);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.genre_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        Spinner spinner1 = (Spinner) findViewById(R.id.GenrespinnerModif);
        final Livre livre = (Livre) getIntent().getSerializableExtra("livreDe");
        List list = spinner1.getTouchables();
        spinner1.setSelection(list.indexOf(livre.getGenre()));

        EditText emprunte = (EditText) findViewById(R.id.emprunteModif);
        emprunte.setText(livre.getEmprunte());

        EditText prete = (EditText) findViewById(R.id.preteModif);
        prete.setText(livre.getPrete());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_modification2, menu);
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
    public void FromCamera(View view) {

        Log.i("camera", "startCameraActivity()");
        File file = new File(getPackageName());
        Uri outputFileUri = Uri.fromFile(file);
        Intent intent = new Intent(
                android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(intent, 1);

    }

    public void FromCard(View view) {
        Intent i = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, 2);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == RESULT_OK
                && null != data) {

            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            bitmap = BitmapFactory.decodeFile(picturePath);
            image.setImageBitmap(bitmap);

            if (bitmap != null) {
                ImageView rotate = (ImageView) findViewById(R.id.rotate);

            }

        } else {

            Log.i("SonaSys", "resultCode: " + resultCode);
            switch (resultCode) {
                case 0:
                    Log.i("SonaSys", "User cancelled");
                    break;
                case -1:
                    onPhotoTaken();
                    break;

            }

        }

    }

    protected void onPhotoTaken() {
        // Log message
        Log.i("SonaSys", "onPhotoTaken");
        taken = true;
        imgCapFlag = true;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        bitmap = BitmapFactory.decodeFile(getPackageName(), options);
        image.setImageBitmap(bitmap);


    }


    public void suite2Modif(View view){
        Livre livre = (Livre) getIntent().getSerializableExtra("livreDe");

        Spinner genre_s = (Spinner) findViewById(R.id.GenrespinnerModif);
        String genre = genre_s.getSelectedItem().toString();
        livre.setGenre(genre);

        EditText emprunte = (EditText)findViewById(R.id.emprunteModif);
        String emprunte_s = emprunte.getText().toString();

        livre.setEmprunte(emprunte_s);

        EditText prete = (EditText)findViewById(R.id.preteModif);
        String prete_s = prete.getText().toString();

        livre.setPrete(prete_s);


        Intent intent = new Intent(this,Modification3Activity.class);
        intent.putExtra("livreDe",livre);

        startActivity(intent);
    }
}
