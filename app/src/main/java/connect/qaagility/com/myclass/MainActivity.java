package connect.qaagility.com.myclass;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.login.widget.LoginButton;
import com.github.florent37.materialtextfield.MaterialTextField;


import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import connect.qaagility.com.myclass.Data.URLSingelton;


public class MainActivity extends AppCompatActivity {

    private EditText userNameEditText,passwordEditText;
    private View userNameView,PasswordView;
    private Button loginButton;
    private ImageView fbImageView,gPlusImageView;
    private int backPressed = 0;
    private Runnable task;

    private URLSingelton mySingelton = URLSingelton.getMy_SingeltonData_Reference();


    private static final ScheduledExecutorService worker =
            Executors.newSingleThreadScheduledExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());

        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_main);

        userNameEditText = (EditText)findViewById(R.id.userNameEditText);
        passwordEditText = (EditText)findViewById(R.id.passwordEditText);
        userNameView = findViewById(R.id.userNameLinearLayout);
        PasswordView = findViewById(R.id.linearLayout);
//        MaterialTextField x = (MaterialTextField)findViewById(R.id.linearLayout);
//        x.getCard().setMinimumHeight(1);

        loginButton = (Button)findViewById(R.id.loginButton);


        PrintHashKey();
//        fbImageView = (ImageView)findViewById(R.id.fbImageView);
//        gPlusImageView = (ImageView)findViewById(R.id.gPlusImageView);


    }

    public void PrintHashKey(){
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "connect.qaagility.com.myclass",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
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

        final String usNAME = userNameEditText.getText().toString();
        final String paWord = passwordEditText.getText().toString();

        if (usNAME.length() == 0) {
            Toast.makeText(this, "Please Enter User Name", Toast.LENGTH_SHORT).show();
            userNameView.setBackgroundColor(Color.RED);
            return;
        }
        else{
            userNameView.setBackgroundColor(Color.TRANSPARENT);
        }
        if (paWord.length() == 0) {
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            PasswordView.setBackgroundColor(Color.RED);
            return;
        }
        else{
            PasswordView.setBackgroundColor(Color.TRANSPARENT);
        }

        JSONObject params = new JSONObject();
        try {
            params.put("username", usNAME);
            params.put("password", paWord);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest loginRequest = new JsonObjectRequest(Request.Method.POST, mySingelton.loginUrl, params,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        stopLoading();
                        try {
                            String status = response.getString("status");
                            String message = response.getString("errorMessage");
                            mySingelton.Token = response.getString("token");
                            if (status.equalsIgnoreCase("ERROR") || message.length()==0){
                                NavigateToHomePage();
                            }else{
                                showError(message);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            showError("There Was Some Problem Please Try Again After Some Time");
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        showError("There Was Some Problem Please Try Again After Some Time");
                    }
                }

        );

        VolleySingelton.getMy_Volley_Singelton_Reference().getRequestQueue().add(loginRequest);


    }

    private void NavigateToHomePage(){
        startActivity(new Intent(this,homePage.class));
    }

    private void showError(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    public void goToSignUpPage(View view) {

        startActivity(new Intent(this,SignUp.class));

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
