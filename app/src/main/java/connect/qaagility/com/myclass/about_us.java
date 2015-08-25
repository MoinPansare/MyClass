package connect.qaagility.com.myclass;

import android.graphics.Bitmap;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.LruCache;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

public class about_us extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView my_imageView,copyImageView;
    private TextView myTextView;
    private Button myButton;

    private VolleySingelton mVolleySingleton;
    private ImageLoader mImageLoader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("About Us");

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mVolleySingleton = VolleySingelton.getMy_Volley_Singelton_Reference();
        mImageLoader = mVolleySingleton.getImageLoader();

        my_imageView = (ImageView)findViewById(R.id.about_us_imageView);
        copyImageView = (ImageView)findViewById(R.id.about_us_copy_imageVIew);
        myTextView = (TextView)findViewById(R.id.titleTextView);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about_us, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id){
            case R.id.action_settings : //Toast.makeText(this, "You Selected Settings", Toast.LENGTH_LONG).show();
                loadImage();
             break;
            case R.id.action_logout : Toast.makeText(this,"You Selected Logout",Toast.LENGTH_SHORT).show();break;
            case android.R.id.home : finish();
//                NavUtils.navigateUpFromSameTask(this);
                break;
            default: Toast.makeText(this,"Fuck You", Toast.LENGTH_SHORT).show();break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadImage() {

//        mImageLoader.get("http://www.k12blueprint.com/sites/default/files/images/android-wallpaper5_2560x1600_1.jpg", new ImageLoader.ImageListener() {
//            @Override
//            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
//                my_imageView.setImageBitmap(response.getBitmap());
//                Log.d("in success", response.getBitmap() + "success");
//                myTextView.setText("Success");
//            }
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                my_imageView.setImageResource(R.drawable.parent_image);
//                Log.d("in success", "Error");
//                myTextView.setText("Error");
//            }
//        },300,300);
//
//        Boolean flag = mImageLoader.isCached("http://www.k12blueprint.com/sites/default/files/images/android-wallpaper5_2560x1600_1.jpg", 300, 300);
//        Toast.makeText(this,flag+"",Toast.LENGTH_SHORT).show();

        Bitmap myImageBitmap = PhotoGalleryHandeler.getImageFrom("Moin1.jpg");
        if(myImageBitmap == null){
            VolleySingelton.LoadImageFromUrlAndCache(mImageLoader, my_imageView, "http://www.k12blueprint.com/sites/default/files/images/android-wallpaper5_2560x1600_1.jpg","Moin1.jpg");
        }
        else
        {
            my_imageView.setImageBitmap(PhotoGalleryHandeler.getImageFrom("Moin1.jpg"));
        }

    }


}
