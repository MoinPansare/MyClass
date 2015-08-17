package connect.qaagility.com.myclass;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

public class LiveFeedDetail extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView titleTextView,dateTextView,mainBodyTextView;
    private View layout_view_background;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        requestWindowFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_live_feed_detail);

//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
//        try{
//            getWindow().setEnterTransition(new Explode());
//            getWindow().setExitTransition(new Explode());
//        }catch(Exception e){
//
//        }



        toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Detail");

//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.show_gradually);
//        toolbar.startAnimation(animation);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String title_str = intent.getStringExtra("connect.qaagility.com.myclass.MyClass_Data_Args1title");
        String date_str = intent.getStringExtra("connect.qaagility.com.myclass.MyClass_Data_Args1date");
        String mainBody_str = intent.getStringExtra("connect.qaagility.com.myclass.MyClass_Data_Args1mainBody");
        int backgroundColor_int = intent.getIntExtra("connect.qaagility.com.myclass.MyClass_Data_Args1color", R.color.row_elevation_color);
//        ColorDrawable viewColor = intent.getStringExtra("")


//        Toast.makeText(this,backgroundColor_int+"",Toast.LENGTH_SHORT).show();
        layout_view_background = (View)findViewById(R.id.layout_background);
        titleTextView = (TextView)findViewById(R.id.titleTextView);
        dateTextView = (TextView)findViewById(R.id.dateTextView);
        mainBodyTextView = (TextView)findViewById(R.id.detailTextView);

        layout_view_background.setBackgroundColor(backgroundColor_int);
        titleTextView.setText(title_str);
        dateTextView.setText(date_str);
        mainBodyTextView.setText(mainBody_str);
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
            case R.id.action_about_us : startActivity(new Intent(this, about_us.class));break;
            case R.id.action_logout : Toast.makeText(this,"You Selected Logout",Toast.LENGTH_SHORT).show();break;
            case 16908332 :
                if (android.os.Build.VERSION.SDK_INT >= 21) {

//                    Toast.makeText(this,id+"",Toast.LENGTH_SHORT).show();
                    finishAfterTransition();
                }
                else{
//                    Toast.makeText(this,android.os.Build.VERSION.SDK_INT+"MOIN",Toast.LENGTH_SHORT).show();
                    finish();
                }
//                Animation animation = AnimationUtils.loadAnimation(this, R.anim.hide_gradually);
//                toolbar.startAnimation(animation);
                break;
            default: Toast.makeText(this,"Fuck You",Toast.LENGTH_SHORT).show();break;
        }

        return super.onOptionsItemSelected(item);
    }
}
