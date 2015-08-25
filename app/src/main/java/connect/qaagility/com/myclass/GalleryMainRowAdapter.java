package connect.qaagility.com.myclass;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collections;
import java.util.List;

/**
 * Created by macpro on 8/17/15.
 */
public class GalleryMainRowAdapter extends RecyclerView.Adapter<GalleryMainRowAdapter.GalleryDataViewHolder> {

    private int pos = 0;
    private List<GalleryData> data = Collections.emptyList();
    private Context myContext;
    private LayoutInflater inflator;
    public galleryItemSelected my_gallertItemSelected;

    private VolleySingelton mVolleySingleton;
    private ImageLoader mImageLoader;


    public void setMy_gallertItemSelected(galleryItemSelected var) {
        this.my_gallertItemSelected = var;
    }

    public GalleryMainRowAdapter(Context context, List<GalleryData> data) {
        inflator = LayoutInflater.from(context);
        myContext = context;
        this.data = data;

        mVolleySingleton = VolleySingelton.getMy_Volley_Singelton_Reference();
        mImageLoader = mVolleySingleton.getImageLoader();

    }

    @Override
    public GalleryDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflator.inflate(R.layout.gallery_row, parent, false);
        GalleryDataViewHolder holder = new GalleryDataViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final GalleryDataViewHolder holder, int position) {

        GalleryData myData = data.get(position);
        GalleryData someData = data.get(0);

        //set data here from arr


        holder.eventTitleTextView.setText(myData.eventTitle);
        if (myData.totalImages - 2 > 0) {
            holder.numberOfImagesTextView.setVisibility(View.VISIBLE);
            holder.blurryImage.setVisibility(View.VISIBLE);
            holder.downArrowImageView.setVisibility(View.VISIBLE);
            holder.numberOfImagesTextView.setText((myData.totalImages - 2) + "");
        } else {
            holder.numberOfImagesTextView.setVisibility(View.INVISIBLE);
            holder.numberOfImagesTextView.setText("");
            holder.blurryImage.setVisibility(View.INVISIBLE);
            holder.downArrowImageView.setVisibility(View.INVISIBLE);
        }

//        holder.primaryImageView.setBackgroundResource(R.drawable.user_background02);
//        holder.SecondaryImageView.setBackgroundResource(R.drawable.user_background02);
//        holder.terniaryImageView.setBackgroundResource(R.drawable.user_background02);

//        loadImage(holder.primaryImageView, myData.primaryUrl, myData.eventTitle + "Primary.jpg");
//        loadImage(holder.SecondaryImageView, myData.secondaryUrl, myData.eventTitle + "Secondry.jpg");
//        loadImageForView(holder.terniaryImageView, myData.terniaryUrl, myData.eventTitle + "Terniary.jpg");

        loadBitmap(myData.eventTitle +"Primary.jpg", holder.primaryImageView);
        if(holder.primaryImageView.getDrawable() == null){
            VolleySingelton.LoadImageFromUrlAndCache(mImageLoader, holder.primaryImageView, myData.primaryUrl, myData.eventTitle + "Primary.jpg");
        }else{

        }

        loadBitmap(myData.eventTitle +"Secondary.jpg", holder.SecondaryImageView);
        if(holder.primaryImageView.getDrawable() == null){
            VolleySingelton.LoadImageFromUrlAndCache(mImageLoader, holder.primaryImageView, myData.primaryUrl, myData.eventTitle + "Primary.jpg");
        }else{

        }

//        loadBitmap(myData.eventTitle +"Primary.jpg", holder.primaryImageView);
//        if(holder.primaryImageView.getDrawable() == null){
//            VolleySingelton.LoadImageFromUrlAndCache(mImageLoader, holder.primaryImageView, myData.primaryUrl, myData.eventTitle + "Primary.jpg");
//        }else{
//
//        }

//        VolleySingelton.LoadImageFromUrlAndCache(mImageLoader, holder.SecondaryImageView, myData.secondaryUrl, myData.eventTitle + "Secondary.jpg");
        VolleySingelton.LoadImageFromUrlAndCacheForView(mImageLoader, holder.terniaryImageView, myData.terniaryUrl, myData.eventTitle + "Terniary.jpg");
//        holder.primaryImageView.setImageResource(R.drawable.user_background02);
//        holder.SecondaryImageView.setImageResource(R.drawable.bg3);
//        holder.terniaryImageView.(R.drawable.bg3);
//        holder.terniaryImageView.setBackground(new BitmapDrawable());


//        holder.terniaryImageView.setBackgroundResource(R.drawable.bg3);
        setAnimation(holder.card_parentView, position);
    }

    public void loadBitmap(String file_name, ImageView img1) {
        BitmapReader myReader = new BitmapReader(img1);
        myReader.execute(file_name);
        return;
    }

    private class BitmapReader extends AsyncTask<String, Void, Bitmap> {

        Bitmap bitmap_to_return;
        ImageView myImage;

        public BitmapReader(ImageView img) {
            myImage = img;
        }

        @Override
        protected Bitmap doInBackground(String... params) {


            File sdCard = Environment.getExternalStorageDirectory();

            File directory = new File(sdCard.getAbsolutePath() + "/saved_images");

            final File file = new File(directory, params[0]); //or any other format supported

            try {
                FileInputStream streamIn = new FileInputStream(file);
                bitmap_to_return = BitmapFactory.decodeStream(streamIn); //This gets the image
                streamIn.close();
                return bitmap_to_return;
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            myImage.setImageBitmap(bitmap);
        }
    }

    public void loadImage(ImageView imageView_args, String url, String file_name) {

//        Bitmap myImageBitmap = PhotoGalleryHandeler.getImageFrom(file_name);
//        if(myImageBitmap == null){
        VolleySingelton.LoadImageFromUrlAndCache(mImageLoader, imageView_args, url, file_name);
//        }
//        else
//        {
//            imageView_args.setImageBitmap(PhotoGalleryHandeler.getImageFrom(file_name));
//            Toast.makeText(MyApplication.getAppContext(),"From Gallery",Toast.LENGTH_SHORT).show();
//        }

//        MyBitmapReader reader = new MyBitmapReader(imageView_args,mImageLoader);
//        reader.execute(file_name,url);

    }

    public void loadImageForView(RelativeLayout view, String url, String file_name) {
//        Bitmap myImageBitmap = PhotoGalleryHandeler.getImageFrom(file_name);
//        if(myImageBitmap == null){
//            VolleySingelton.LoadImageFromUrlAndCacheForView(mImageLoader, view, url, file_name);
//        }
//        else
//        {
//            Drawable dr = new BitmapDrawable(myContext.getResources(),PhotoGalleryHandeler.getImageFrom(file_name));
//            view.setBackground(dr);
//        }
    }

    private void setAnimation(View view, int position) {
        if (pos > position) {
            Animation animation = AnimationUtils.loadAnimation(myContext, R.anim.push_from_top);
            view.startAnimation(animation);
        } else {
            Animation animation = AnimationUtils.loadAnimation(myContext, R.anim.push_from_bottom);
            view.startAnimation(animation);
        }
        pos = position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface galleryItemSelected {
        public void OnClickGalleryItem(View view, String title);
    }

    public class GalleryDataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // all declareations here

        public TextView eventTitleTextView, numberOfImagesTextView;
        public ImageView primaryImageView, SecondaryImageView, downArrowImageView, blurryImage;
        View card_parentView;
        RelativeLayout terniaryImageView;


        public GalleryDataViewHolder(View itemView) {
            super(itemView);
            eventTitleTextView = (TextView) itemView.findViewById(R.id.event_title_textView);
            numberOfImagesTextView = (TextView) itemView.findViewById(R.id.number_textView);
            primaryImageView = (ImageView) itemView.findViewById(R.id.parent_imageView);
            SecondaryImageView = (ImageView) itemView.findViewById(R.id.secondary_imageView);
            terniaryImageView = (RelativeLayout) itemView.findViewById(R.id.terniaryImage);
            blurryImage = (ImageView) itemView.findViewById(R.id.blurry_imageView);
            downArrowImageView = (ImageView) itemView.findViewById(R.id.downArrowImageView);
            card_parentView = itemView;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            my_gallertItemSelected.OnClickGalleryItem(v, eventTitleTextView.getText().toString());
        }
    }


}
