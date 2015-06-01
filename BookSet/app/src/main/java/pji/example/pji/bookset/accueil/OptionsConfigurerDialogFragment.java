package pji.example.pji.bookset.accueil;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import pji.example.pji.bookset.R;

/**
 * Created by raissa on 11/05/15.
 */
public class OptionsConfigurerDialogFragment extends DialogFragment {
    public static int config;

    public static OptionsConfigurerDialogFragment newInstance(int title) {
        OptionsConfigurerDialogFragment frag = new OptionsConfigurerDialogFragment();
        Bundle args = new Bundle();
        args.putInt("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.pick_option_configurer)
                .setItems(R.array.configurer_array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                       config = which;
                        Intent intent = new Intent(getActivity(),AccueilActivity.class);
                        intent.putExtra("config",config);
                        startActivity(intent);

                    }
                });
        return builder.create();
    }

    void showDialog() {
        DialogFragment newFragment = OptionsSynchroniserDialogFragment.newInstance(
                R.string.pick_option_configurer);
        newFragment.show(getFragmentManager(), "dialog");
    }
}

