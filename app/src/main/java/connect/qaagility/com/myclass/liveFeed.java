package connect.qaagility.com.myclass;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import connect.qaagility.com.myclass.Data.URLSingelton;

public class liveFeed extends AppCompatActivity implements LiveFeedsAdapter.someListData,NavigationDrawerFragment.topViewClick {

    private View mainParent;

    private static String dataArgs = "connect.qaagility.com.myclass.MyClass_Data_Args1";

    private Toolbar toolbar;
    private RecyclerView mainRecyclerView;
    private SwipeRefreshLayout swipeContainer;

    private List<NewsFeedsData> AllData = new ArrayList<>();
    private LiveFeedsAdapter myAdapter;

    URLSingelton mySingelton = URLSingelton.getMy_SingeltonData_Reference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        requestWindowFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_feed);

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainerLiveFeeds);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                refreshTheData();
            }
        });



        mainParent = (View)findViewById(R.id.main_Parent);
        toolbar = (Toolbar)findViewById(R.id.app_bar);
//        toolbar.setAlpha(0);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.app_bar_show);
        toolbar.startAnimation(animation);
//        toolbar.startAnimation(AnimationUtils.loadAnimation(liveFeed.this,
//                R.anim.app_bar_show));
        setSupportActionBar(toolbar);
        try{
            getSupportActionBar().setTitle(R.string.main_page_title);
        }catch(Exception e){

        }


        NavigationDrawerFragment navFragment = (NavigationDrawerFragment)getSupportFragmentManager().findFragmentById(R.id.navigationDrawer_fragment);
        navFragment.setUp(R.id.navigationDrawer_fragment, (DrawerLayout) findViewById(R.id.drawerLayout), toolbar, "Live Feed");

        navFragment.settopViewClick(this);

        mainRecyclerView = (RecyclerView)findViewById(R.id.rv);
//        AllData = getData();
        myAdapter = new LiveFeedsAdapter(this,AllData);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter.setsomeListData(this);

        if (AllData.size()==0){
            swipeContainer.setRefreshing(true);
            getData();
        }





        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                mainParent.setBackgroundResource(R.drawable.login_background);
                mainRecyclerView.setAdapter(myAdapter);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id){
            case R.id.action_settings : Toast.makeText(this, "You Selected Settings", Toast.LENGTH_LONG).show();break;
            case R.id.action_about_us : startActivity(new Intent(this,about_us.class));break;
            case R.id.action_logout : Toast.makeText(this,"You Selected Logout",Toast.LENGTH_SHORT).show();break;
            default: Toast.makeText(this,"Fuck You",Toast.LENGTH_SHORT).show();break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getData() {



        JSONObject params = new JSONObject();
        try {
            params.put("index",1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest loginRequest = new JsonObjectRequest(Request.Method.POST, mySingelton.liveFeedsUrl, params,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        stopLoading();
//                        NavigateToHomePage(response);
                        try{
                            parseResponseIntoStructure(response);
                        }catch (Exception e){

                        }



                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        stopLoading();
                        showError("There Was Some Problem Please Try Again After Some Time");
                    }
                }

        );

//        startLoading();
        VolleySingelton.getMy_Volley_Singelton_Reference().getRequestQueue().add(loginRequest);
        return;
    }

    private void parseResponseIntoStructure(JSONObject myObj) throws JSONException {

        String status = myObj.getString("status");
        String message = myObj.getString("errorMessage");

        if (status.equalsIgnoreCase("ERROR") || message.length() != 0){
            showError("There Was Some Problem Please Try Again After Some Time");
            return;
        }

        JSONArray jsonArr= myObj.getJSONArray("response");
//        String[] responseArray = new String[jsonArr.length()];
//        for(int i=0;i<jsonArr.length();i++){
//            responseArray[i]=(String)jsonArr.get(i);
//        }

        for(int i=0;i<jsonArr.length();i++){
            try{
                NewsFeedsData current = new NewsFeedsData();
                JSONObject indexObject = jsonArr.getJSONObject(i);
                current.eventTitle = indexObject.get("heading").toString();
                current.AddedDate = indexObject.get("date").toString();
                current.AddedDate = indexObject.get("date").toString();
                current.mainBody = indexObject.get("body").toString();
                current.moreInfo = indexObject.get("body").toString();
                current.backgroundColor = "urgent";
                AllData.add(current);
            }catch (Exception e){
                Log.d("ed","ed");
            }

        }

        myAdapter.notifyDataSetChanged();
        swipeContainer.setRefreshing(false);



    }

    private void refreshTheData() {
        Log.d("refreshign","asdasd");
        getData();


    }

    private void showError(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }



    @Override
    public void InitiateActivityTransition(TextView textView,View viewSelected,TextView dateTextView,TextView detailTextView,int position) {

        final Intent newIntent = new Intent(this,LiveFeedDetail.class);
        newIntent.putExtra(dataArgs+"title",textView.getText().toString());
        newIntent.putExtra(dataArgs+"date",dateTextView.getText().toString());
        newIntent.putExtra(dataArgs+"mainBody",detailTextView.getText().toString());

        ColorDrawable viewColor = (ColorDrawable) viewSelected.getBackground();
//        Toast.makeText(this,viewColor+"",Toast.LENGTH_SHORT).show();
        int colorId = viewColor.getColor();

        newIntent.putExtra(dataArgs+"color",colorId);
//        newIntent.putExtra(dataArgs+"color",viewColor.toString());


        //for multiple

//        Intent intent = new Intent(context, DetailsActivity.class);
//        intent.putExtra(DetailsActivity.EXTRA_CONTACT, contact);
        Pair<View, String> p1 = Pair.create(viewSelected, "detail_view");
//        Pair<View, String> p2 = Pair.create((View)textView, "detail_title");
//        Pair<View, String> p3 = Pair.create((View)dateTextView, "detail_date");
//        Pair<View, String> p4 = Pair.create((View)detailTextView, "detail_body");

//        Pair<View, String> p3 = Pair.create((View)tvName, "text");
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this,p1);
//        startActivity(intent, options.toBundle());


//        ActivityOptionsCompat options = ActivityOptionsCompat.
//                            makeSceneTransitionAnimation(this, textView , "detail_title");

        ActivityCompat.startActivity(this, newIntent, options.toBundle());
    }

    @Override
    public void userClickedTopView(View v) {
        Intent myIntent = new Intent(this,UserPage.class);
        Pair<View, String> p1 = Pair.create((View)v, "user_page");
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, p1);
        ActivityCompat.startActivity(this,myIntent, options.toBundle());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if ((keyCode == KeyEvent.KEYCODE_BACK)) {

            toolbar.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(300);
                        getSupportActionBar().hide();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            mainParent.setBackgroundResource(R.color.trans);
            mainParent.setAlpha((float)0.3);
            mainRecyclerView.setAlpha(0);
        }

        return super.onKeyDown(keyCode, event);

    }

}
