package connect.qaagility.com.myclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

public class UserPage extends AppCompatActivity {

    private ImageView user_image,backImage;
    private ScrollView mainScrollView;
    private View topView,header_coloredView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        user_image = (ImageView)findViewById(R.id.profile_image);
        mainScrollView = (ScrollView)findViewById(R.id.scrollView);
        topView = (View)findViewById(R.id.UserheaderView);
        header_coloredView = (View)findViewById(R.id.colored_view_user);
        backImage = (ImageView)findViewById(R.id.user_back_Image);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (android.os.Build.VERSION.SDK_INT >= 21) {
                    finishAfterTransition();
                    return;
                }
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//        switch(id){
//            case R.id.action_settings : Toast.makeText(this, "You Selected Settings", Toast.LENGTH_LONG).show();break;
//            case R.id.action_about_us : startActivity(new Intent(this,about_us.class));break;
//            case R.id.action_logout : Toast.makeText(this,"You Selected Logout",Toast.LENGTH_SHORT).show();break;
//            case R.id.home:finish();break;
//            default: Toast.makeText(this,"Fuck You",Toast.LENGTH_SHORT).show();break;
//        }

        return super.onOptionsItemSelected(item);
    }





}
