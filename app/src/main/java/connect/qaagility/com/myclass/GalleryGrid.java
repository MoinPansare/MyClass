package connect.qaagility.com.myclass;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;


public class GalleryGrid extends AppCompatActivity implements GalleryGridAdapter.GridImageSelected {

    private Toolbar myToolbar;
    private RecyclerView my_recyclewView;
    private GalleryGridAdapter my_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        requestWindowFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_grid);
        myToolbar = (Toolbar)findViewById(R.id.app_bar_gallery_grid);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("GalleryItemSelected"));
        my_recyclewView = (RecyclerView)findViewById(R.id.grid_recyclerView);

        my_adapter = new GalleryGridAdapter(this);
        my_adapter.setMy_GridImageSelected(this);
        my_recyclewView.setLayoutManager(new StaggeredGridLayoutManager(2,1));
        my_recyclewView.setAdapter(my_adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id){
            case R.id.action_settings : Toast.makeText(this, "You Selected Settings", Toast.LENGTH_LONG).show();break;
            case R.id.action_logout : Toast.makeText(this,"You Selected Logout",Toast.LENGTH_SHORT).show();break;
            case android.R.id.home : finish();
//                NavUtils.navigateUpFromSameTask(this);
                break;
            default: Toast.makeText(this,"Fuck You", Toast.LENGTH_SHORT).show();break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void selectedGridImage(ImageView v, int position) {
//        Toast.makeText(this,position+"",Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(this,PhotoViewer.class);
        myIntent.putExtra("imagePassedToPhotoPage",R.drawable.bg3);
        Pair<View, String> p1 = Pair.create((View)v, "photo_detail");
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, p1);
//        Log.d("optionsToBundle", options.toBundle() + "");
        ActivityCompat.startActivity(this,myIntent, options.toBundle());
    }
}
