package pji.example.pji.bookset.accueil;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

/**
 * Created by imane khemici on 28/05/15.
 */
public class AfficherLivre implements SimpleAdapter.ViewBinder {
    @Override
    public boolean setViewValue(View view, Object data,String textRepresentation) {
        if( (view instanceof ImageView) & (data instanceof Bitmap) ) {
            ImageView iv = (ImageView) view;
            Bitmap bm = (Bitmap) data;
            iv.setImageBitmap(bm);
            return true;
        }

        return false;
    }
}