package connect.qaagility.com.myclass;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ActivityGallery extends AppCompatActivity {

    private Toolbar toolbar;

    private NavigationDrawerFragmentGallery navFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_gallery);

        toolbar = (Toolbar)findViewById(R.id.app_bar_gallery);
        setSupportActionBar(toolbar);
        try{
            getSupportActionBar().setTitle("Gallery");
        }catch(Exception e){

        }

        navFragment = (NavigationDrawerFragmentGallery)getSupportFragmentManager().findFragmentById(R.id.navigationDrawer_fragment_gallery);
        navFragment.setUp(R.id.navigationDrawer_fragment_gallery, (DrawerLayout) findViewById(R.id.drawerLayout_gallery), toolbar, "Gallery");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        navFragment.hideTheDrawer();
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        switch(id){
            case R.id.action_settings : Toast.makeText(this, "You Selected Settings", Toast.LENGTH_LONG).show();break;
            case R.id.action_about_us : startActivity(new Intent(this,about_us.class));break;
            case R.id.action_logout : Toast.makeText(this,"You Selected Logout",Toast.LENGTH_SHORT).show();break;
            default: Toast.makeText(this,"Fuck You",Toast.LENGTH_SHORT).show();break;
        }

        return super.onOptionsItemSelected(item);
    }
}
