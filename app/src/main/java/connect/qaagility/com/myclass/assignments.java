package connect.qaagility.com.myclass;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class assignments extends AppCompatActivity implements AssignmentsAdapter.assignmentEvent {

    private Toolbar toolbar;
    private NavigationDrawerAssignments navFragment;
    private RecyclerView myRecyclerView;
    private List<AssignmentsAdapter.AssignmentsData> AllData = Collections.emptyList();
    private AssignmentsAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignments);
        toolbar = (Toolbar)findViewById(R.id.app_bar);
        toolbar.startAnimation(AnimationUtils.loadAnimation(this,
                R.anim.app_bar_show));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Assignments");

        navFragment = (NavigationDrawerAssignments)getSupportFragmentManager().findFragmentById(R.id.fragmentDrawer_assignments);
        navFragment.setUp(R.id.fragmentDrawer_assignments, (DrawerLayout) findViewById(R.id.drawerLayoutAssignment), toolbar, "Assignments");

        myRecyclerView = (RecyclerView)findViewById(R.id.rv_assignments);

        AllData = getData();

        myAdapter = new AssignmentsAdapter(this,AllData);
        myAdapter.setMy_assignmentEvent(this);

//        myAdapter.setMy_gallertItemSelected(this);

        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myRecyclerView.setAdapter(myAdapter);


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
            case R.id.action_about_us : startActivity(new Intent(this,about_us.class));break;
            case R.id.action_logout : Toast.makeText(this,"You Selected Logout",Toast.LENGTH_SHORT).show();break;
            default: Toast.makeText(this,"Fuck You",Toast.LENGTH_SHORT).show();break;
        }

        return super.onOptionsItemSelected(item);
    }

    public static List<AssignmentsAdapter.AssignmentsData> getData() {

        String postedDate;
        String lastDate;
        String subject;
        String mainBody;
        String summary;
        int position_in_view;

        //load only static data inside a drawer
        List<AssignmentsAdapter.AssignmentsData> data = new ArrayList<>();
        String[] subjectsArr = {"Hindi","English","History","Physics","Maths","Biology"};

        for (int i = 0; i < 5; i++) {
            AssignmentsAdapter.AssignmentsData current = new AssignmentsAdapter.AssignmentsData();
            current.subject = subjectsArr[i];
            current.postedDate = "1/9/2015";
            current.lastDate = "10/9/2015";
            current.summary = "Complete The assignment told in class";
            current.position_in_view = i;
            current.mainBody = "Main Body Of assignment";
            data.add(current);
        }
        for (int i = 0; i < 5; i++) {
            AssignmentsAdapter.AssignmentsData current = new AssignmentsAdapter.AssignmentsData();
            current.subject = subjectsArr[i];
            current.postedDate = "1/9/2015";
            current.lastDate = "10/9/2015";
            current.summary = "Complete The assignment told in class";
            current.position_in_view = i+5;
            current.mainBody = "Main Body Of assignment";
            data.add(current);
        }
        for (int i = 0; i < 5; i++) {
            AssignmentsAdapter.AssignmentsData current = new AssignmentsAdapter.AssignmentsData();
            current.subject = subjectsArr[i];
            current.postedDate = "1/9/2015";
            current.lastDate = "10/9/2015";
            current.summary = "Complete The assignment told in class";
            current.position_in_view = i+10;
            current.mainBody = "Main Body Of assignment";
            data.add(current);
        }

        return data;
    }

    @Override
    public void assignmentClicked(View v, int position) {
        Toast.makeText(this,"Clicked "+position,Toast.LENGTH_SHORT).show();
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
            myRecyclerView.setAlpha(0);
        }

        return super.onKeyDown(keyCode, event);

    }


}
