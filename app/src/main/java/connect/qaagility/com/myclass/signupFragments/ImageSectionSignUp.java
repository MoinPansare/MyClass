package connect.qaagility.com.myclass.signupFragments;


import android.app.DownloadManager;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import connect.qaagility.com.myclass.MyApplication;
import connect.qaagility.com.myclass.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageSectionSignUp extends Fragment {

    private int imageUploaded=0;
    private int skipping = 0;

    private imageSelectionInterface my_imageSelectionInterface;

    private ImageView profileImageView;

    private Button selectImage,continutButton;

    public void setMy_imageSelectionInterface(imageSelectionInterface my_imageSelectionInterface) {
        this.my_imageSelectionInterface = my_imageSelectionInterface;
    }

    private static int RESULT_LOAD_IMG = 1;
    String imgDecodableString;

    public ImageSectionSignUp() {
        // Required empty public constructor
    }

    public static final ImageSectionSignUp newInstance()
    {
        ImageSectionSignUp f = new ImageSectionSignUp();
        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image_section_sign_up, container, false);
        profileImageView = (ImageView)view.findViewById(R.id.chile_Image_Uploading);
        selectImage = (Button)view.findViewById(R.id.selectImage_Button);
        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                my_imageSelectionInterface.getImageForView();
            }
        });

        continutButton = (Button)view.findViewById(R.id.skip_continue_Button);
        continutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                my_imageSelectionInterface.everyThingUploadedImageSelection(2);
            }
        });

        return view;
    }

    public void setUserImage(Bitmap someBitmap){
        imageUploaded = 1;
        continutButton.setText("Continue");
        profileImageView.setImageBitmap(someBitmap);
    }

    public interface imageSelectionInterface{
        public void getImageForView();
        public void everyThingUploadedImageSelection(int position);
    }


}
