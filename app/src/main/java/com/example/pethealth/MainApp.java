package com.example.pethealth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainApp extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth mAuth;
    private SectionPagerAdapter mSectionPagerAdaper;
    private ViewPager mViewPager;
    private TextView username;
    private DatabaseHelper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);
        setFragment(new HomeFragment());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myDb=new DatabaseHelper(this);
        mAuth = FirebaseAuth.getInstance();
       // FirebaseUser currentUser=FirebaseAuth.getInstance().getCurrentUser();
       // Toast.makeText(this,""+currentUser.getUid(),Toast.LENGTH_LONG).show();

        ProgressBar progressBar=(ProgressBar)findViewById(R.id.progressBar2);
      mSectionPagerAdaper=new SectionPagerAdapter(getSupportFragmentManager());
      mViewPager= (ViewPager)findViewById(R.id.container);
     // setupViewPager(mViewPager);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

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
        getMenuInflater().inflate(R.menu.main_app, menu);
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
    private void logOut()
    { Log.e("mainapp","suntem in functia de logout");
        mAuth.signOut();
        backLogin();
    }
    private void backLogin()
    {
        startActivity(new Intent(MainApp.this,LoginActivity.class));
        finish();
    }
    public void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.progress, fragment)
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
Log.e("Mainapp","Suntem in on NaivigationItemSelected");

        if (id == R.id.nav_account) {
         getSupportFragmentManager().beginTransaction().replace(R.id.progress,
               new MyAccountFragment()).commit();//modify progressbar visibility to gone or make it a fragment on its own
           // Toast.makeText(MainApp.this,
                   // "Merge butonul ", Toast.LENGTH_LONG).show();
        Log.e("MainApp","ajunge si aici");
       // setProgressBarVisibility(false);

        }
        else if(id==R.id.nav_home)
        {
            startActivity(new Intent(MainApp.this,MainApp.class));
        }
        else if (id == R.id.nav_feedback) {
            getSupportFragmentManager().beginTransaction().replace(R.id.progress,
                    new FeedbackFragment()).commit();
        }
        else if(id==R.id.nav_logout)
        {
            Log.e("MainApp","Suntem la logout.");
            mAuth.signOut();
            startActivity(new Intent(MainApp.this,LoginActivity.class));
        }

        else if(id==R.id.nav_stats)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.progress,
                    new StatsFragment()).commit();
        }
        else if(id==R.id.nav_calories)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.progress,
                    new CaloriesFragment()).commit();
        }
        else if(id==R.id.nav_medical)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.progress,
                    new MedicalFragment()).commit();
        }
        else if(id==R.id.nav_goals) {
            getSupportFragmentManager().beginTransaction().replace(R.id.progress,
                    new GoalsFragment()).commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

   /* private void setupViewPager(ViewPager viewPager)
    {
        SectionPagerAdapter adapter=new SectionPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MyAccountFragment(),"MyAccountFrag");
        viewPager.setAdapter(adapter);


    }
    public void setViewPager(int fragNumb)
    {
        mViewPager.setCurrentItem(fragNumb);
    }*/
}
