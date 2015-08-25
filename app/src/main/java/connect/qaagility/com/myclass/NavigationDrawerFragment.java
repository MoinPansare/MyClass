package connect.qaagility.com.myclass;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment implements NavigationDrawerRecyclerViewAdapter.MyClickListener {


    private View top_view;
    private ImageView childImageView;
    private TextView childNameTextView;
    private topViewClick my_topViewClick;

    private String myTitle;

    public String file_Name = "file_name";
    public String Key_User_Learned_Drawer = "userLearnedDrawer";

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    private boolean mUserLearnedDrawer;
    private boolean fromSavedState;

    private View containerView;


    private RecyclerView recyclerView;
    private NavigationDrawerRecyclerViewAdapter myAdapter;

    private List<NavigationDrawerData> AllData = Collections.emptyList();

    public NavigationDrawerFragment() {

    }

    public void settopViewClick(topViewClick some_topViewClick){
        this.my_topViewClick = some_topViewClick;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = Boolean.valueOf(getSharedPreferences(getActivity(), Key_User_Learned_Drawer, "false"));
        if (savedInstanceState != null) {
            fromSavedState = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


//        return inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        View view = (View) inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        AllData = getData();
        myAdapter = new NavigationDrawerRecyclerViewAdapter(getActivity(), AllData);
        myAdapter.setMyClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(myAdapter);

        top_view = (View)view.findViewById(R.id.top_View);
        childImageView = (ImageView)view.findViewById(R.id.child_image);
        childNameTextView = (TextView)view.findViewById(R.id.child_name);

        top_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                my_topViewClick.userClickedTopView(v);

            }
        });

        return view;
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar, String myTitle) {

        this.myTitle = myTitle;
        containerView = (View) getActivity().findViewById(fragmentId);

        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    setSharedPreferences(getActivity(), Key_User_Learned_Drawer, "true");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
//                if(slideOffset<0.4){
//                    toolbar.setAlpha(1-slideOffset);
//                }
            }
        };

        if (!mUserLearnedDrawer && !fromSavedState) {
//            mDrawerLayout.openDrawer(containerView);
        } else {
            mDrawerLayout.closeDrawer(containerView);
        }

        mDrawerLayout.post(new Runnable() {

            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    public void setSharedPreferences(Context context, String Key, String Value) {
        SharedPreferences sp = context.getSharedPreferences(file_Name, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(Key, Value);
        editor.apply();
    }

    public String getSharedPreferences(Context context, String Key, String Value) {
        SharedPreferences sp = context.getSharedPreferences(file_Name, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        return (sp.getString(Key, Value)).toString();
    }


    public static List<NavigationDrawerData> getData() {
        //load only static data inside a drawer
        List<NavigationDrawerData> data = new ArrayList<>();
        int[] icons = {R.drawable.user_image, R.drawable.user_image, R.drawable.user_image, R.drawable.user_image, R.drawable.user_image};
        String[] titles = {"News Board", "Assignments", "Messages", "Notifications", "Photos"};
        for (int i = 0; i < 5; i++) {
            NavigationDrawerData current = new NavigationDrawerData();
            current.imageId = icons[i % icons.length];
            current.title = titles[i % titles.length];
            data.add(current);
        }

        return data;
    }

    @Override
    public void ItemClicked(View view, int position) {

        mDrawerLayout.closeDrawer(Gravity.LEFT);

        switch (position) {
            case 0:
                if (!this.myTitle.toString().equalsIgnoreCase("Live Feed")) {
                    startActivity(new Intent(getActivity(), MainActivity.class));
                    getActivity().finish();
                }
                break;
            case 1:
                if (!this.myTitle.toString().equalsIgnoreCase("Assignment")) {
                    startActivity(new Intent(getActivity(), assignments.class));
                    getActivity().finish();
                }
                break;
            case 2:
                if (!this.myTitle.toString().equalsIgnoreCase("Messages")) {
                    startActivity(new Intent(getActivity(), Messaging.class));
                    getActivity().finish();
                }
                break;
            case 3:
//                if (!this.myTitle.toString().equalsIgnoreCase("Notifications")) {
//                    startActivity(new Intent(getActivity(), ImagePinch.class));
//                    getActivity().finish();
//                }
                break;
            case 4:
                if (!this.myTitle.toString().equalsIgnoreCase("Gallery")) {
                    startActivity(new Intent(getActivity(), ActivityGallery.class));
                    getActivity().finish();
                }
                break;

            default:
                Toast.makeText(getActivity(), "Not Created Yet", Toast.LENGTH_SHORT).show();
        }
    }

    public void hideDrawer(){
        mDrawerLayout.closeDrawer(Gravity.LEFT);
    }

    public interface topViewClick{
        public void userClickedTopView(View v);
    }
}
