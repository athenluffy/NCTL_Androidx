package com.example.athenmangang.nctl;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class landing extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "Landing";
    private DrawerLayout drawerLayout;
    private FirebaseAuth mAuth;
    Toolbar toolbar;
    TextView username, mail;
    String guser, gmail;
    ImageView profile_pic;
    Uri user_photo;
    @Override
    protected void onStart() {


        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        //load toolbar
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        guser = firebaseUser.getDisplayName();
        gmail = firebaseUser.getEmail();
        user_photo=firebaseUser.getPhotoUrl();
//set toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Navigation view
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        //setting current username and email on nav header
        username = headerView.findViewById(R.id.txtGUsername);
        username.setText(guser+"\n");
        mail = headerView.findViewById(R.id.txtGMail);
        mail.setText(gmail);
        profile_pic=headerView.findViewById(R.id.imgUser);
        Glide.with(headerView).load(user_photo).into(profile_pic);

        Log.d(TAG, "onCreate: Loading Profile url");

        //add toggle to drawer layout
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.cnd, R.string.ond);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        if (savedInstanceState == null) {
            //open a fragment at loading
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_edit_item()).commit();
            navigationView.setCheckedItem(R.id.nav_3);//set selected
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.right_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.share:
                Toast.makeText(this, "share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.help:
                Toast.makeText(this, "help", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_1:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_rent_item()).commit();
                break;
            case R.id.nav_2:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_add_item()).commit();
                break;
            case R.id.nav_3:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_view_history()).commit();
                break;
            case R.id.navEditItem:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment_edit_item()).commit();

                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}