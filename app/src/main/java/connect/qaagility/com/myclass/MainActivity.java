package connect.qaagility.com.myclass;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import connect.qaagility.com.myclass.LiveFeedsAdapter.someListData;

public class MainActivity extends AppCompatActivity implements someListData,NavigationDrawerFragment.topViewClick {

    private static String dataArgs = "connect.qaagility.com.myclass.MyClass_Data_Args1";

    private Toolbar toolbar;
    private RecyclerView mainRecyclerView;

    private List<NewsFeedsData> AllData = Collections.emptyList();
    private LiveFeedsAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        requestWindowFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        try{
            getSupportActionBar().setTitle(R.string.main_page_title);
        }catch(Exception e){

        }

        NavigationDrawerFragment navFragment = (NavigationDrawerFragment)getSupportFragmentManager().findFragmentById(R.id.navigationDrawer_fragment);
        navFragment.setUp(R.id.navigationDrawer_fragment,(DrawerLayout) findViewById(R.id.drawerLayout), toolbar ,"Live Feed");

        navFragment.settopViewClick(this);

        mainRecyclerView = (RecyclerView)findViewById(R.id.rv);


        AllData = getData();

        myAdapter = new LiveFeedsAdapter(this,AllData);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter.setsomeListData(this);
        mainRecyclerView.setAdapter(myAdapter);


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

    public static List<NewsFeedsData> getData() {
        //load only static data inside a drawer
        List<NewsFeedsData> data = new ArrayList<>();
        String[] titles = {"News Board","Assignments","Messages","Notifications","Photos"};
        String[] dates = {"1/8/2015","2/8/2015","3/8/2015","4/8/2015","5/8/2015"};
        String[] mainBody = {"This is Some Dummy Data Tp Prove Than At Times The Text Might Get Clipped Off And We Might Not Be Able To See The Rest Of The Content, but at times we can see actually what is intended for viewing, but at times we can see actually what is intended for viewing","Assignments","Messages","Notifications","Photos"};
        String[] additional = {"News Board","Assignments","Messages","Notifications","Photos"};
        String[] backGroundPriority = {"urgent","normal","normal","urgent","normal"};
        for (int i = 0; i < 5; i++) {
            NewsFeedsData current = new NewsFeedsData();
            current.eventTitle = titles[i % titles.length];
            current.AddedDate = dates[i % dates.length];
            current.mainBody = mainBody[i % mainBody.length];
            current.moreInfo = additional[i % additional.length];
            current.backgroundColor = backGroundPriority[i % backGroundPriority.length];
            data.add(current);
        }
        for (int i = 0; i < 5; i++) {
            NewsFeedsData current = new NewsFeedsData();
            current.eventTitle = titles[i % titles.length];
            current.AddedDate = dates[i % dates.length];
            current.mainBody = mainBody[i % mainBody.length];
            current.moreInfo = additional[i % additional.length];
            current.backgroundColor = backGroundPriority[i % backGroundPriority.length];
            data.add(current);
        }
        for (int i = 0; i < 5; i++) {
            NewsFeedsData current = new NewsFeedsData();
            current.eventTitle = titles[i % titles.length];
            current.AddedDate = dates[i % dates.length];
            current.mainBody = mainBody[i % mainBody.length];
            current.moreInfo = additional[i % additional.length];
            current.backgroundColor = backGroundPriority[i % backGroundPriority.length];
            data.add(current);
        }

        return data;
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
}
