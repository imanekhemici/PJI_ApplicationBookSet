package pji.example.pji.bookset.accueil;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import pji.example.pji.bookset.R;
        ;
import pji.example.pji.implementation.base.DatabaseManager;
import pji.example.pji.implementation.extra.Methodes;


public class  AccueilActivity extends Methodes{

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private String[] mSettingsTitles;

    private String[] mOptionsTitles;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        //Initialisation de la base de données
        DatabaseManager.init(this);

        mTitle = mDrawerTitle = getTitle().toString();
        mSettingsTitles = getResources().getStringArray(R.array.settings_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mOptionsTitles = getResources().getStringArray(R.array.options_array);

        ObjectDrawerItem[] drawerItem = new ObjectDrawerItem[5];

        drawerItem[0] = new ObjectDrawerItem(R.drawable.ic_drawer, "Accueil");
        drawerItem[1] = new ObjectDrawerItem(R.drawable.ic_drawer, "Favoris");
        drawerItem[2] = new ObjectDrawerItem(R.drawable.ic_drawer, "Panier");
        drawerItem[3] = new ObjectDrawerItem(R.drawable.ic_drawer, "Non lu(s)");
        drawerItem[4] = new ObjectDrawerItem(R.drawable.ic_drawer, "Lu(s)");
       /* drawerItem[5] = new ObjectDrawerItem(R.drawable.ic_drawer, "Afficher par genre");*/


        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        /*mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.listview_item_row, mSettingsTitles));*/
        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.listview_item_row, drawerItem);
        mDrawerList.setAdapter(adapter);

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,  R.string.drawer_open, R.string.drawer_close ) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }


        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }



      /*  Spinner spinner = (Spinner) findViewById(R.id.configurer_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.configurer_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter1);*/
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Fragment fragment1 = null;
        // Handle presses on the action bar items
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        else {
            switch (item.getItemId()) {
                case R.id.synchroniser:
                    OptionsSynchroniserDialogFragment op = new OptionsSynchroniserDialogFragment();
                    //op.showDialog();
                    op.show(getFragmentManager(), "dialog");
                    return true;

                case R.id.configurer:
                    OptionsConfigurerDialogFragment od = new OptionsConfigurerDialogFragment();
                    od.show(getFragmentManager(), "dialog");
                    return true;
            }
        }

        if (fragment1 != null) {
            android.app.FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment1).commit();
        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }

        return super.onOptionsItemSelected(item);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.synchroniser).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    private void selectItem(int position) {
        // update the main content by replacing fragments

        android.app.Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new AccueilFragment();
                break;
            case 1:
                fragment = new FavorisFragment();
                break;
            case 2:
                fragment = new PanierFragment();
                break;
            case 3:
                fragment = new NonLusFragment();
                break;
            case 4:
                fragment = new LusFragment();
                break;
            /*case 5:
                fragment = new ChoixGenreFragment();
                break;*/

            default:
                break;
        }

        if (fragment != null) {
            android.app.FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(mSettingsTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


/**
    public void information(View view) throws SQLException {
            TextView livreTitre = (TextView) findViewById(R.id.titreaff);
            Livre livre = DatabaseManager.getInstance().getHelper().getLivreDao().findByTitle(livreTitre.getText().toString());
            Intent intent = new Intent(this,InformationActivity.class);
            intent.putExtra("livreDetails",livre);
            startActivity(intent);
    }*/

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }



}
