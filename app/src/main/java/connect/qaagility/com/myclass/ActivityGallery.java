package connect.qaagility.com.myclass;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActivityGallery extends AppCompatActivity implements GalleryMainRowAdapter.galleryItemSelected{

    private Toolbar toolbar;
    private RecyclerView gallery_recyclerView;
    private List<GalleryData> AllData = Collections.emptyList();
    private GalleryMainRowAdapter myAdapter;

    private NavigationDrawerFragmentGallery navFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        requestWindowFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_gallery);

        toolbar = (Toolbar)findViewById(R.id.app_bar_gallery);
        toolbar.startAnimation(AnimationUtils.loadAnimation(ActivityGallery.this,
                R.anim.app_bar_show));

        setSupportActionBar(toolbar);
        try{
            getSupportActionBar().setTitle("Gallery");
        }catch(Exception e){

        }

        navFragment = (NavigationDrawerFragmentGallery)getSupportFragmentManager().findFragmentById(R.id.navigationDrawer_fragment_gallery);
        navFragment.setUp(R.id.navigationDrawer_fragment_gallery, (DrawerLayout) findViewById(R.id.drawerLayout_gallery), toolbar, "Gallery");

        gallery_recyclerView = (RecyclerView)findViewById(R.id.gallery_recyclerView);

        AllData = getData();

        myAdapter = new GalleryMainRowAdapter(this,AllData);
        myAdapter.setMy_gallertItemSelected(this);
        gallery_recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        gallery_recyclerView.setLayoutManager(new GridLayoutManager(this,2));
//        gallery_recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,1));
//        myAdapter.setsomeListData(this);

        gallery_recyclerView.setAdapter(myAdapter);
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

    public static List<GalleryData> getData() {
        //load only static data inside a drawer
        List<GalleryData> data = new ArrayList<>();
        String[] titles = {"New Year","Chrismas","Diwali","Holi","Ramzan","Ganpati Vacations"};
        String primaryUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/38/Queen-Elizabeth-School.jpg/320px-Queen-Elizabeth-School.jpg";
        String secondryUrl = "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-ash1/t5/277110_261934653038_1251853370_n.jpg";
        String terniaryUrl = "http://media-cache-ak0.pinimg.com/736x/c5/65/9b/c5659b2b6cd5a88ec785b09e49efcfec.jpg";

        for (int i = 0; i < 5; i++) {
            GalleryData current = new GalleryData();
            current.eventTitle = titles[i % titles.length];
            current.totalImages = 3;
            current.primaryUrl = primaryUrl;
            current.secondaryUrl = secondryUrl;
            current.terniaryUrl = terniaryUrl;
            for(int j=0;j<17;j++){
//                current.arr.add("http://media-cache-ak0.pinimg.com/736x/c5/65/9b/c5659b2b6cd5a88ec785b09e49efcfec.jpg");
                current.arr.add(j,secondryUrl.toString());
            }
            data.add(current);
        }
        for (int i = 0; i < 5; i++) {
            GalleryData current = new GalleryData();
            current.eventTitle = titles[i % titles.length];
            current.totalImages = 3;
            current.primaryUrl = primaryUrl;
            current.secondaryUrl = secondryUrl;
            current.terniaryUrl = terniaryUrl;
            for(int j=0;j<17;j++){
//                current.arr.add("http://media-cache-ak0.pinimg.com/736x/c5/65/9b/c5659b2b6cd5a88ec785b09e49efcfec.jpg");
                current.arr.add(j,secondryUrl);
            }
            data.add(current);
        }
        for (int i = 0; i < 5; i++) {
            GalleryData current = new GalleryData();
            current.eventTitle = titles[i % titles.length];
            current.totalImages = 3;
            current.primaryUrl = primaryUrl;
            current.secondaryUrl = secondryUrl;
            current.terniaryUrl = terniaryUrl;
            for(int j=0;j<17;j++){
//                current.arr.add("http://media-cache-ak0.pinimg.com/736x/c5/65/9b/c5659b2b6cd5a88ec785b09e49efcfec.jpg");
                current.arr.add(j,secondryUrl);
            }
            data.add(current);
        }

        return data;
    }

    @Override
    public void OnClickGalleryItem(View view, String title,GalleryData selectedData) {

        Intent intent = new Intent(this,GalleryGrid.class);
        intent.putExtra("GalleryItemSelected",title);
        intent.putExtra("AllData", selectedData);
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if ((keyCode == KeyEvent.KEYCODE_BACK)) {

            toolbar.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(300);
                        getSupportActionBar().hide();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            gallery_recyclerView.setAlpha(0);
        }

        return super.onKeyDown(keyCode, event);

    }
}
