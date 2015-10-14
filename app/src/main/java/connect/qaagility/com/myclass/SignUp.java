package connect.qaagility.com.myclass;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import connect.qaagility.com.myclass.signupFragments.ChildInfo;
import connect.qaagility.com.myclass.signupFragments.CustomPager;
import connect.qaagility.com.myclass.signupFragments.EmailRegistrationFragment;
import connect.qaagility.com.myclass.signupFragments.ImageSectionSignUp;


public class SignUp extends AppCompatActivity implements EmailRegistrationFragment.emailPartCorrect,ImageSectionSignUp.imageSelectionInterface,ChildInfo.signUpConfirmationInterface {

    private CustomPager mPager;
    private int PICK_IMAGE_REQUEST = 1;

    ImageSectionSignUp frg2;
    private ChildInfo frg3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        frg2 = ImageSectionSignUp.newInstance();
        frg2.setMy_imageSelectionInterface(this);

        frg3 = ChildInfo.newInstance();
        frg3.setMy_signUpConfirmationInterface(this);

        mPager = (CustomPager)findViewById(R.id.viewPager_signup);
        mPager.setPagingEnabled(false);
        mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mPager.setScrollDurationFactor(3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void AllEmailParametersCorrect(int index) {
        navigateToPage(1);
    }

    @Override
    public void getImageForView() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                frg2.setUserImage(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void everyThingUploadedImageSelection(int position) {
        navigateToPage(2);
    }

    private void navigateToPage(int index){
        mPager.setPagingEnabled(true);
        mPager.setCurrentItem(index,true);
        mPager.setPagingEnabled(false);
    }

    @Override
    public void signUpConfirmed() {
        Toast.makeText(SignUp.this,"Your Request Has Been Sent Successfully",Toast.LENGTH_LONG).show();
        finish();
    }

    private class MyPagerAdapter extends FragmentPagerAdapter{

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {

                case 0: EmailRegistrationFragment frg1 = EmailRegistrationFragment.newInstance();
                    frg1.setMy_emailPartCorrect(SignUp.this);
                    return frg1;
                case 1:return frg2;
                case 2: return frg3;
//                case 3: return ThirdFragment.newInstance("ThirdFragment, Instance 2");
//                case 4: return ThirdFragment.newInstance("ThirdFragment, Instance 3");
                default: return EmailRegistrationFragment.newInstance();
            }

        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
