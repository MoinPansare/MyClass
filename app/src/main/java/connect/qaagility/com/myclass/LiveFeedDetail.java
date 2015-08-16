package connect.qaagility.com.myclass;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class LiveFeedDetail extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView titleTextView;


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

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String str = intent.getStringExtra("connect.qaagility.com.myclass.MyClass_Data_Args1");

        titleTextView = (TextView)findViewById(R.id.titleTextView);
        titleTextView.setText(str.toString());
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
                if (android.os.Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) {

                    Toast.makeText(this,id+"",Toast.LENGTH_SHORT).show();
                    finishAfterTransition();
                }
                else{
                    Toast.makeText(this,"MOIN",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default: Toast.makeText(this,"Fuck You",Toast.LENGTH_SHORT).show();break;
        }

        return super.onOptionsItemSelected(item);
    }
}
