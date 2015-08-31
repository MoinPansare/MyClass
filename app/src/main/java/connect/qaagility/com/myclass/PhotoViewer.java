package connect.qaagility.com.myclass;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class PhotoViewer extends AppCompatActivity {

    private ViewPager my_view_pager;
    private ImageView back_overlay_imageView;
    private GalleryData myData;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        requestWindowFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_viewer);
//        back_overlay_imageView = (ImageView)findViewById(R.id.back_overlay_imageView);
//        back_overlay_imageView.setImageResource(getIntent().getIntExtra("imagePassedToPhotoPage",R.drawable.bg3));
//
//        Context context = PhotoViewer.this;
//
//        int padding = this.getResources().getDimensionPixelSize(
//                R.dimen.padding_medium);
//        back_overlay_imageView.setPadding(padding, padding, padding, padding);
//        back_overlay_imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        myData = getIntent().getParcelableExtra("dataForPager");
        position = getIntent().getIntExtra("my_current_selection_position",0);



        my_view_pager = (ViewPager) findViewById(R.id.view_pager);
        ImagePagerAdapter adapter = new ImagePagerAdapter(myData);
        my_view_pager.setAdapter(adapter);
        my_view_pager.setCurrentItem(position);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
//
        switch(id){
            case R.id.action_settings : Toast.makeText(this, "You Selected Settings", Toast.LENGTH_LONG).show();break;
            case R.id.action_about_us : startActivity(new Intent(this, about_us.class));break;
            case R.id.action_logout : Toast.makeText(this,"You Selected Logout",Toast.LENGTH_SHORT).show();break;
            case 16908332 :
                if (android.os.Build.VERSION.SDK_INT >= 21) {

//                    Toast.makeText(this,id+"",Toast.LENGTH_SHORT).show();
                    finishAfterTransition();
                    finish();
                }
                else{
//                    Toast.makeText(this,android.os.Build.VERSION.SDK_INT+"MOIN",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default: Toast.makeText(this,"Fuck You",Toast.LENGTH_SHORT).show();break;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ImagePagerAdapter extends PagerAdapter {

        private VolleySingelton mVolleySingleton;
        private ImageLoader mImageLoader;

        private List<String> myCollection = new ArrayList<String>();

        private int[] mImages = new int[]{
                R.drawable.bg3,
                R.drawable.parent_image,
                R.drawable.user_background02,
                R.drawable.user_image
        };

        public ImagePagerAdapter(GalleryData myData){
            mVolleySingleton = VolleySingelton.getMy_Volley_Singelton_Reference();
            mImageLoader = mVolleySingleton.getImageLoader();
            myCollection.add(myData.primaryUrl);
            myCollection.add(myData.secondaryUrl);
            myCollection.add(myData.terniaryUrl);
            for (String url:myData.arr) {
                myCollection.add(url);
            }
        }

        @Override
        public int getCount() {
            return myCollection.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((ImageView) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Context context = PhotoViewer.this;
//            ImageView imageView = new ImageView(context);
            TouchImageView imageView = new TouchImageView(container.getContext());
            if(android.os.Build.VERSION.SDK_INT>=21){
//                imageView.setTransitionName("photo_detail");
                imageView.setElevation(R.dimen.padding_large);
            }
            int padding = context.getResources().getDimensionPixelSize(
                    R.dimen.padding_medium);
//            imageView.setLayoutParams(new LinearLayout.LayoutParams(400,400));
            imageView.setPadding(padding, padding, padding, padding);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
//            imageView.setImageResource(mImages[position]);
            VolleySingelton.LoadImageFromUrl(mImageLoader, imageView, myCollection.get(position));

//            ((ViewPager) container).addView(imageView, 0);
            ((ViewPager)container).addView(imageView,400,400);

            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((ImageView) object);
        }


    }
}
