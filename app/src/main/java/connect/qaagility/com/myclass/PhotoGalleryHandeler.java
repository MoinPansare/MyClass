package connect.qaagility.com.myclass;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by macpro on 8/21/15.
 */
public class PhotoGalleryHandeler {

    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }


    public static int saveFileToExternalStorage(Bitmap imageToSave, String file_name) {
        if (!isExternalStorageReadable()) {
            return 0;
        }

        MyBitmapWriter bitmapWriter = new MyBitmapWriter(imageToSave);
        bitmapWriter.execute(file_name);
        return 1;
    }

    public static Bitmap getImageFrom(String file_Name) {

        final Bitmap bitmap;

        File sdCard = Environment.getExternalStorageDirectory();

        File directory = new File(sdCard.getAbsolutePath() + "/saved_images");

        final File file = new File(directory, file_Name); //or any other format supported


        try {
            FileInputStream streamIn = new FileInputStream(file);
            bitmap = BitmapFactory.decodeStream(streamIn); //This gets the image
            streamIn.close();
            return bitmap;
        } catch (Exception e) {
            return null;
        }

//        MyBitmapReader reader = new MyBitmapReader();
//        AsyncTask<String, Void, Bitmap> bitmap = reader.execute(file_Name);
//        return bitmap;
    }

    private static class MyBitmapWriter extends AsyncTask<String,Void,Void>{

        private Bitmap myBitmap;

        public MyBitmapWriter(Bitmap myBitmap){
            this.myBitmap = myBitmap;

        }

        @Override
        protected Void doInBackground(String... params) {
            // 0 filename

            String root = Environment.getExternalStorageDirectory().toString();
            File myDir = new File(root + "/saved_images");
            myDir.mkdirs();
            File file = new File(myDir, params[0]);
            if (file.exists()) file.delete();
            try {
                FileOutputStream out = new FileOutputStream(file);
                myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }


    }
}
