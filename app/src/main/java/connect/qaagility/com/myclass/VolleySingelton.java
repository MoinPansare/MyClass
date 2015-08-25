package connect.qaagility.com.myclass;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.LruCache;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by macpro on 8/20/15.
 */
public class VolleySingelton {

    public static VolleySingelton my_Volley_Singelton_Reference;
    private RequestQueue my_requestQueue;
    private ImageLoader my_imageLoader;

    private VolleySingelton(){
        my_requestQueue=Volley.newRequestQueue(MyApplication.getAppContext());
        my_imageLoader=new ImageLoader(my_requestQueue,new ImageLoader.ImageCache() {

            private LruCache<String, Bitmap> cache=new LruCache<>((int)(Runtime.getRuntime().maxMemory()/1024)/8);
            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
    }

    public static VolleySingelton getMy_Volley_Singelton_Reference(){
        if(my_Volley_Singelton_Reference == null){
            my_Volley_Singelton_Reference = new VolleySingelton();
        }
        return my_Volley_Singelton_Reference;
    }

    public RequestQueue getRequestQueue(){
        return my_requestQueue;
    }

    public ImageLoader getImageLoader(){
        return my_imageLoader;
    }

    public static void LoadImageFromUrlAndCache(ImageLoader myImageLoader_args, final ImageView desired_ImageView, final String url, final String file_name){
        myImageLoader_args.get(url, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                desired_ImageView.setImageBitmap(response.getBitmap());
                PhotoGalleryHandeler.saveFileToExternalStorage(response.getBitmap(),file_name);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                desired_ImageView.setImageResource(R.drawable.parent_image);
            }
        },400,400);
    }

    public static void LoadImageFromUrlAndCacheForView(ImageLoader myImageLoader_args, final RelativeLayout desired_ImageView, final String url, final String file_name){
        myImageLoader_args.get(url, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {

                Drawable dr = new BitmapDrawable(MyApplication.getAppContext().getResources(), response.getBitmap());
                desired_ImageView.setBackground(dr);
                PhotoGalleryHandeler.saveFileToExternalStorage(response.getBitmap(),file_name);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                desired_ImageView.setBackgroundResource(R.drawable.parent_image);
            }
        },400,400);
    }



    public static Boolean checkIfImageIsCached(ImageLoader myImageLoader_args,String url){
        return myImageLoader_args.isCached(url,400,400);
    }


}
