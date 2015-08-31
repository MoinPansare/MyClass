package connect.qaagility.com.myclass;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import connect.qaagility.com.myclass.LiveFeedsAdapter.someListData;

public class MainActivity extends AppCompatActivity {

    private EditText userNameEditText,passwordEditText;
    private Button loginButton;
    private ImageView fbImageView,gPlusImageView;
    private int backPressed = 0;
    private Runnable task;

    private static final ScheduledExecutorService worker =
            Executors.newSingleThreadScheduledExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_main);

        userNameEditText = (EditText)findViewById(R.id.userNameEditText);
        passwordEditText = (EditText)findViewById(R.id.passwordEditText);

        loginButton = (Button)findViewById(R.id.loginButton);

        fbImageView = (ImageView)findViewById(R.id.fbImageView);
        gPlusImageView = (ImageView)findViewById(R.id.gPlusImageView);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }


    public void goToLiveFeedPage(View view) {
        int  userNameValid = 1;
        int passwordVlaid = 1;

        if(userNameValid == 1 && passwordVlaid == 1){
            startActivity(new Intent(this,homePage.class));
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if(backPressed == 0){
                backPressed = 1;
                Toast.makeText(this,"Press Back Again To Exit",Toast.LENGTH_SHORT).show();
                task = new Runnable() {
                    public void run() {
                        backPressed = 0;
                    }
                };
                worker.schedule(task, 2, TimeUnit.SECONDS);
                return false;
            }
        }

        return super.onKeyDown(keyCode, event);

    }
}
