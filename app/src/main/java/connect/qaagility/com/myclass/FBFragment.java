package connect.qaagility.com.myclass;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class FBFragment extends android.support.v4.app.Fragment {

    private LoginButton FBLoginButton;
    private CallbackManager mCallbackManager;


    public FacebookCallback<LoginResult> mFacebookCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();
            Toast.makeText(MyApplication.getAppContext(), profile.getFirstName()+"", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException e) {
            Log.d("Error Some",e.toString());
        }
    };

    public FBFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCallbackManager = CallbackManager.Factory.create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fb, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpLoginButton(view);

    }

    private void setUpLoginButton(View view){
        FBLoginButton = (LoginButton)view.findViewById(R.id.FBLoginButton);
        FBLoginButton.setFragment(this);
        FBLoginButton.setReadPermissions("user_friends");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        FBLoginButton.registerCallback(mCallbackManager,mFacebookCallback);
    }
}
