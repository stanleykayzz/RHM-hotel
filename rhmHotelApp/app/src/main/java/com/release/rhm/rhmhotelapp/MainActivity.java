package com.release.rhm.rhmhotelapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import rhm.com.fragment.account;
import rhm.com.fragment.bookingHistory;
import rhm.com.fragment.bookingType;
import rhm.com.fragment.gallery;
import rhm.com.fragment.main;
import rhm.com.fragment.bookingRoom;
import rhm.com.fragment.room;
import rhm.com.fragment.location;
import rhm.com.fragment.contact;
import rhm.com.fragment.hotel;
import rhm.com.fragment.login;
import rhm.com.fragment.logout;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.release.rhm.rhmhotelapp.R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(com.release.rhm.rhmhotelapp.R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(com.release.rhm.rhmhotelapp.R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, com.release.rhm.rhmhotelapp.R.string.navigation_drawer_open, com.release.rhm.rhmhotelapp.R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(com.release.rhm.rhmhotelapp.R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setMenuForConnectedUser(navigationView);

        //On rajoute du code pour gérer nos fragments
        FragmentManager fragMan = getSupportFragmentManager();
        fragMan.beginTransaction().replace(com.release.rhm.rhmhotelapp.R.id.content_frame, new main()).commit();
    }

    private void setMenuForConnectedUser(NavigationView navigationView){
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(com.release.rhm.rhmhotelapp.R.id.nav_login).setVisible(false);

        SharedPreferences sharedPreferences = this.getSharedPreferences("myUser",0);
        Boolean connected = sharedPreferences.getBoolean("UserConnected",false);

        if (connected == true)
        {
            nav_Menu.findItem(com.release.rhm.rhmhotelapp.R.id.nav_login).setVisible(false);
            nav_Menu.findItem(com.release.rhm.rhmhotelapp.R.id.nav_logout).setVisible(true);
            nav_Menu.findItem(com.release.rhm.rhmhotelapp.R.id.nav_account).setVisible(true);
            nav_Menu.findItem(com.release.rhm.rhmhotelapp.R.id.nav_history).setVisible(true);
            Toast.makeText(this,"Vous êtes connecté",Toast.LENGTH_LONG).show();
        }
        else
        {
            nav_Menu.findItem(com.release.rhm.rhmhotelapp.R.id.nav_login).setVisible(true);
            nav_Menu.findItem(com.release.rhm.rhmhotelapp.R.id.nav_logout).setVisible(false);
            nav_Menu.findItem(com.release.rhm.rhmhotelapp.R.id.nav_account).setVisible(false);
            nav_Menu.findItem(com.release.rhm.rhmhotelapp.R.id.nav_history).setVisible(false);
            Toast.makeText(this,"Vous n'êtes pas connecté",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(com.release.rhm.rhmhotelapp.R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == com.release.rhm.rhmhotelapp.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentManager fragMan = getSupportFragmentManager();
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == com.release.rhm.rhmhotelapp.R.id.content_frame) {
            fragMan.beginTransaction().replace(com.release.rhm.rhmhotelapp.R.id.content_frame, new bookingRoom()).commit();
        } else if (id == com.release.rhm.rhmhotelapp.R.id.nav_gallery) {
            fragMan.beginTransaction().replace(com.release.rhm.rhmhotelapp.R.id.content_frame, new gallery()).commit();
        } else if (id == com.release.rhm.rhmhotelapp.R.id.nav_book) {
            fragMan.beginTransaction().replace(com.release.rhm.rhmhotelapp.R.id.content_frame, new bookingType()).commit();
        } else if (id == com.release.rhm.rhmhotelapp.R.id.nav_room) {
            fragMan.beginTransaction().replace(com.release.rhm.rhmhotelapp.R.id.content_frame, new room()).commit();
        }else if (id == com.release.rhm.rhmhotelapp.R.id.nav_hotel) {
            fragMan.beginTransaction().replace(com.release.rhm.rhmhotelapp.R.id.content_frame, new hotel()).commit();
        } else if (id == com.release.rhm.rhmhotelapp.R.id.nav_login) {
            fragMan.beginTransaction().replace(com.release.rhm.rhmhotelapp.R.id.content_frame, new login()).commit();
        } else if (id == com.release.rhm.rhmhotelapp.R.id.nav_logout) {
            fragMan.beginTransaction().replace(com.release.rhm.rhmhotelapp.R.id.content_frame, new logout()).commit();
        } else if (id == com.release.rhm.rhmhotelapp.R.id.nav_contact) {
            fragMan.beginTransaction().replace(com.release.rhm.rhmhotelapp.R.id.content_frame, new contact()).commit();
        } else if (id == com.release.rhm.rhmhotelapp.R.id.nav_history) {
            fragMan.beginTransaction().replace(com.release.rhm.rhmhotelapp.R.id.content_frame, new bookingHistory()).commit();
        } else if (id == com.release.rhm.rhmhotelapp.R.id.nav_account) {
            fragMan.beginTransaction().replace(com.release.rhm.rhmhotelapp.R.id.content_frame, new account()).commit();
        } else if (id == com.release.rhm.rhmhotelapp.R.id.nav_location) {
            location locationFragment = new location();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(com.release.rhm.rhmhotelapp.R.id.content_frame, locationFragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(com.release.rhm.rhmhotelapp.R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}