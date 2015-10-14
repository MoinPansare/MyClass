package connect.qaagility.com.myclass;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import connect.qaagility.com.myclass.Data.URLSingelton;

public class ActivityGallery extends AppCompatActivity implements GalleryMainRowAdapter.galleryItemSelected{

    private View parentView;

    private Toolbar toolbar;
    private RecyclerView gallery_recyclerView;
    private List<GalleryData> AllData = new ArrayList<>();
    private GalleryMainRowAdapter myAdapter;
    private SwipeRefreshLayout swipeContainer;

    private NavigationDrawerFragmentGallery navFragment;

    private URLSingelton mySingelton = URLSingelton.getMy_SingeltonData_Reference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        requestWindowFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_gallery);

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainerGallery);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                getData();
            }
        });


        parentView = findViewById(R.id.parent_gallery);
        toolbar = (Toolbar)findViewById(R.id.app_bar_gallery);
        Animation animation = AnimationUtils.loadAnimation(ActivityGallery.this,
                R.anim.app_bar_show);
                toolbar.startAnimation(animation);

        setSupportActionBar(toolbar);
        try{
            getSupportActionBar().setTitle("Gallery");
        }catch(Exception e){

        }

        navFragment = (NavigationDrawerFragmentGallery)getSupportFragmentManager().findFragmentById(R.id.navigationDrawer_fragment_gallery);
        navFragment.setUp(R.id.navigationDrawer_fragment_gallery, (DrawerLayout) findViewById(R.id.drawerLayout_gallery), toolbar, "Gallery");

        gallery_recyclerView = (RecyclerView)findViewById(R.id.gallery_recyclerView);

//        AllData = getData();


        myAdapter = new GalleryMainRowAdapter(this,AllData);
        myAdapter.setMy_gallertItemSelected(this);
        gallery_recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (AllData.size() == 0){
            swipeContainer.setRefreshing(true);
            getData();
        }

//        gallery_recyclerView.setLayoutManager(new GridLayoutManager(this,2));
//        gallery_recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,1));
//        myAdapter.setsomeListData(this);



        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                parentView.setBackgroundResource(R.drawable.login_background);
                gallery_recyclerView.setAdapter(myAdapter);
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
    public boolean onMenuOpened(int featureId, Menu menu) {
        navFragment.hideTheDrawer();
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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
        //load only static data inside a drawer


        JSONObject params = new JSONObject();
//        try {
//            params.put("","");
//        } catch (JSONException e) {
//            e.printStackTrace();
//            swipeContainer.setRefreshing(false);
//        }

        JsonObjectRequest galleryRequest = new JsonObjectRequest(Request.Method.POST, mySingelton.GalleryUrl, params,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        stopLoading();
//                        NavigateToHomePage(response);
                        try{
                            parseResponseIntoStructure(response);
                        }catch (Exception e){
                            swipeContainer.setRefreshing(false);
                        }



                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        stopLoading();
                        showError("There Was Some Problem Please Try Again After Some Time");
                        swipeContainer.setRefreshing(false);
                    }
                }

        );



        VolleySingelton.getMy_Volley_Singelton_Reference().getRequestQueue().add(galleryRequest);
        return;



    }

    private void parseResponseIntoStructure(JSONObject myObj) throws JSONException {

        String status = myObj.getString("status");
        String message = myObj.getString("errorMessage");

        if (status.equalsIgnoreCase("ERROR") || message.length() != 0){
            showError("There Was Some Problem Please Try Again After Some Time");
            swipeContainer.setRefreshing(false);
            return;
        }

        JSONArray jsonArr= myObj.getJSONArray("response");


        for(int i=0;i<jsonArr.length();i++){
            try{
                GalleryData current = new GalleryData();
                JSONObject indexObject = jsonArr.getJSONObject(i);
                current.eventTitle = indexObject.get("eventname").toString();
                current.eventId = Integer.parseInt(indexObject.getString("eventid"));
                JSONArray linkArr = indexObject.getJSONArray("eventarr");
                if (linkArr.length()-3 > 0){
                    current.totalImages = linkArr.length() - 3;
                }
                else {
                    current.totalImages = 0;
                }
                for (int j=0;j<linkArr.length();j++){
                    switch (j){
                        case 0: current.primaryUrl = linkArr.get(0).toString();break;
                        case 1: current.secondaryUrl = linkArr.get(1).toString();break;
                        case 2: current.terniaryUrl = linkArr.getString(2);break;
                        default: current.arr.add(j,linkArr.getString(j));
                    }
                }
                AllData.add(current);
            }catch (Exception e){
                Log.d("ed", "ed");
            }
        }
        removeDuplicates();
        myAdapter.notifyDataSetChanged();
        swipeContainer.setRefreshing(false);
    }

    private void removeDuplicates(){
        List<GalleryData> newData = new ArrayList<>();
        for (int i=0;i<AllData.size();i++){
            int change = 0;
            for (int j=0;j<newData.size();j++){
                GalleryData temp1 = newData.get(j);
                GalleryData temp2 = AllData.get(i);
                if (temp1.eventId == temp2.eventId){
                    change = 1;
                }
            }
            if (change == 0){
                newData.add(AllData.get(i));
            }
        }
        AllData.clear();
        AllData.addAll(newData);
    }

    private void showError(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnClickGalleryItem(View view, String title,GalleryData selectedData) {

        Intent intent = new Intent(this,GalleryGrid.class);
        intent.putExtra("GalleryItemSelected",title);
        intent.putExtra("AllDataForImages", selectedData);
        startActivity(intent);
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
            gallery_recyclerView.setAlpha(0);
            parentView.setBackgroundResource(R.color.trans);
            parentView.setAlpha((float)0.3);
        }

        return super.onKeyDown(keyCode, event);

    }
}
