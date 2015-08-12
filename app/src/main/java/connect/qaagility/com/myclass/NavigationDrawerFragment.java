package connect.qaagility.com.myclass;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {

    public static String file_Name = "File_Name";
    private static String Key_User_Learned_Drawer = "userLearnedDrawer";

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    private boolean mUserLearnedDrawer;
    private boolean fromSavedState;

    private View containerView;


    private RecyclerView recyclerView;
    private NavigationDrawerRecyclerViewAdapter myAdapter;

    private List<NavigationDrawerData> AllData = Collections.emptyList();

    public NavigationDrawerFragment() {
        // Required empty public constructor
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

        View view = (View)inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        AllData = getData();
        myAdapter = new NavigationDrawerRecyclerViewAdapter(getActivity(),AllData);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(myAdapter);
        Log.d("CountMoin",AllData.isEmpty()+"");
        return view;
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {


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
            mDrawerLayout.openDrawer(containerView);
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

    public static void setSharedPreferences(Context context,String Key,String Value){
        SharedPreferences sp = context.getSharedPreferences(file_Name,context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(Key, Value);
        editor.apply();
    }

    public static String getSharedPreferences(Context context,String Key,String Value){
        SharedPreferences sp = context.getSharedPreferences(file_Name,context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        return (sp.getString(Key,Value)).toString();
    }

//    private static List<NavigationDrawerData> getData(){
//
//        List<NavigationDrawerData> data = Collections.emptyList();
//        int[] icons = {R.drawable.user_image,R.drawable.user_image,R.drawable.user_image,R.drawable.user_image,R.drawable.user_image};
//        String[] titles = {"News Board","Assignments","Messages","Notifications","Photos"};
//
//        for(int i=0;i<icons.length && i<titles.length;i++)
//        {
//            NavigationDrawerData current = new NavigationDrawerData();
//            try{
//                current.imageId = (int)icons[i];
//                current.title = titles[i].toString();
//                data.add(current);
//            }catch(Exception e){
//                Log.d("Found Some MOINPANSARE","Found Some Error MOINPANSARE");
//            }
//
//        }
//        return data;
//    }

    public static List<NavigationDrawerData> getData() {
        //load only static data inside a drawer
        List<NavigationDrawerData> data = new ArrayList<>();
        int[] icons = {R.drawable.user_image, R.drawable.user_image, R.drawable.user_image, R.drawable.user_image,R.drawable.user_image};
        String[] titles = {"News Board","Assignments","Messages","Notifications","Photos"};
        for (int i = 0; i < 5; i++) {
            NavigationDrawerData current = new NavigationDrawerData();
            current.imageId = icons[i % icons.length];
            current.title = titles[i % titles.length];
            data.add(current);
        }

        return data;
    }

}
