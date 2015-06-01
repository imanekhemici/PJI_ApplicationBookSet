package pji.example.pji.bookset.accueil;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import pji.example.pji.bookset.R;

/**
 * Created by raissa on 11/05/15.
 */
public class OptionsSynchroniserDialogFragment extends DialogFragment {

    public static OptionsSynchroniserDialogFragment newInstance(int title) {
        OptionsSynchroniserDialogFragment frag = new OptionsSynchroniserDialogFragment();
        Bundle args = new Bundle();
        args.putInt("title", title);
        frag.setArguments(args);
        return frag;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.pick_option_synchroniser)
                .setItems(R.array.synchroniser_array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                    }
                });
        return builder.create();
    }

    void showDialog() {
        DialogFragment newFragment = OptionsSynchroniserDialogFragment.newInstance(
                R.string.pick_option_synchroniser);
        newFragment.show(getFragmentManager(), "dialog");
    }
}

