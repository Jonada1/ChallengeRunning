package com.example.jonada.challengerunning;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

public class SenseNavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    private int currentChosenNavMenu = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sense);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

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
        getMenuInflater().inflate(R.menu.sense, menu);
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
        if (id != this.currentChosenNavMenu) {
            closeTheDrawer();
        }
        if (id == R.id.nav_start_challenge) {
            Intent newChallengeIntent = new Intent(SenseNavigationActivity.this, NewChallengeActivity.class);
            startActivity(newChallengeIntent);
            toolbar.setTitle("Challenge Run");
        } else if (id == R.id.nav_friends) {
            Intent friendsIntent = new Intent(SenseNavigationActivity.this, FriendsActivity.class);
            startActivity(friendsIntent);
        } else if (id == R.id.nav_add_friend) {
            Intent newFriendIntent = new Intent(SenseNavigationActivity.this, AddFriendActivity.class);
            startActivity(newFriendIntent);
        } else if (id == R.id.nav_leaderboard) {
            Intent leaderBoardIntent = new Intent(SenseNavigationActivity.this, LeaderbordActivity.class);
            startActivity(leaderBoardIntent);
            changeMainView(R.layout.activity_leaderbord);
        } else if (id == R.id.nav_challenges) {
            Intent myChallengesIntent = new Intent(SenseNavigationActivity.this, CompletedChallengesActivity.class);
            startActivity(myChallengesIntent);
            changeMainView(R.layout.activity_completed_challenges);
        }

        closeTheDrawer();

        return true;
    }
    private void closeTheDrawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
    public void changeMainView(int layout){
        final LinearLayout viewGroup = (LinearLayout)findViewById(R.id.content_view);
        LayoutInflater li = LayoutInflater.from(this);
        viewGroup.removeAllViews();
        View view = li.inflate(layout, null, false);
        viewGroup.addView(view);
    }

}
