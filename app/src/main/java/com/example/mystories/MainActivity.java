package com.example.mystories;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.example.mystories.clases.Utilidades;
import com.google.android.gms.ads.AdRequest;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,unoFragment.OnFragmentInteractionListener,
        dosFragment.OnFragmentInteractionListener,BuscarFragment.OnFragmentInteractionListener,ContenedorFragment.OnFragmentInteractionListener {

    private InterstitialAd interstitialAd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        //inicializamos  cambia por el ID de Admob
        //MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

        // setAdUnitID se cambia por el ID del bloque de anuncio
        //ca-app-pub-3940256099942544/1033173712  ID de Admob que si funciona
       // interstitialAd = new InterstitialAd(this);
        //interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
       // AdRequest adRequestIntAd = new AdRequest.Builder().build();
        //interstitialAd.loadAd(adRequestIntAd);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (Utilidades.validaPantalla==true){
            Fragment fragment=new BuscarFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main,fragment).commit();
            Utilidades.validaPantalla=false;
        }


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment miFragment=null;
        boolean fragmentSeleccionado=false;

        if (id == R.id.nav_camera) {
            miFragment=new BuscarFragment();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_gallery) {
            miFragment=new unoFragment();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_slideshow) {
            miFragment=new dosFragment();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_manage) {
            miFragment=new ContenedorFragment();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        if (fragmentSeleccionado==true){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main,miFragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void btnbuscar(View view) {
        Intent next = new Intent(MainActivity.this,DosActivity.class);
        startActivity(next);

        //cargamos el anucio
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        }
        //finish();
    }
}
