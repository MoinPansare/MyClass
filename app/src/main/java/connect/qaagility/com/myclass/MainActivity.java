package connect.qaagility.com.myclass;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import connect.qaagility.com.myclass.LiveFeedsAdapter.someListData;

public class MainActivity extends AppCompatActivity implements someListData {

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

        mainRecyclerView = (RecyclerView)findViewById(R.id.rv);


        AllData = getData();

        myAdapter = new LiveFeedsAdapter(this,AllData);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter.setsomeListData(this);
        mainRecyclerView.setAdapter(myAdapter);

        mainRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            int y = 0;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//                Log.d("new state",newState+"");
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                y=y+dy;
//                Log.d("current Y", y + "");
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
    public void InitiateActivityTransition(TextView textView,int position) {

        final Intent newIntent = new Intent(this,LiveFeedDetail.class);
        newIntent.putExtra(dataArgs,textView.getText().toString());


        //for multiple

//        Intent intent = new Intent(context, DetailsActivity.class);
//        intent.putExtra(DetailsActivity.EXTRA_CONTACT, contact);
//        Pair<View, String> p1 = Pair.create((View)ivProfile, "profile");
//        Pair<View, String> p2 = Pair.create(vPalette, "palette");
//        Pair<View, String> p3 = Pair.create((View)tvName, "text");
//        ActivityOptionsCompat options = ActivityOptionsCompat.
//                makeSceneTransitionAnimation(this, p1, p2, p3);
//        startActivity(intent, options.toBundle());


        ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(this, textView , "detail_title");

        ActivityCompat.startActivity(this, newIntent, options.toBundle());
    }
}
