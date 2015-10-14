package connect.qaagility.com.myclass.signupFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

import connect.qaagility.com.myclass.MyApplication;
import connect.qaagility.com.myclass.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmailRegistrationFragment extends Fragment {

    private emailPartCorrect my_emailPartCorrect;

    private Boolean passwordCorrect = false;
    private Boolean emailIdCorrect = false;

    private View confirmPasswordLayout,emailIdLayout;
    private EditText passwordEditText,confirmPasswordEditText,userNameEditText,userEmailIdEditText;
    private Button continueButton;

    public void setMy_emailPartCorrect(emailPartCorrect my_emailPartCorrect) {
        this.my_emailPartCorrect = my_emailPartCorrect;
    }

    public EmailRegistrationFragment() {
        // Required empty public constructor
    }

    public static final EmailRegistrationFragment newInstance()
    {
        EmailRegistrationFragment f = new EmailRegistrationFragment();
        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_email_registration, container, false);

        userNameEditText = (EditText)view.findViewById(R.id.userNameEditText_SignUp);
        userEmailIdEditText = (EditText)view.findViewById(R.id.userEmailIdEditText_SignUp);
        passwordEditText = (EditText)view.findViewById(R.id.passwordEditText_SignUp);
        confirmPasswordEditText = (EditText)view.findViewById(R.id.confirmPasswordEditText_SignUp);
        continueButton = (Button)view.findViewById(R.id.continue_Button);
        confirmPasswordLayout = view.findViewById(R.id.confirmPasswordSignupLayout);
        emailIdLayout = view.findViewById(R.id.userEmailIdSignupLayout);

        confirmPasswordEditText.addTextChangedListener(new TextWatcher() {

            String password = "";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                password = passwordEditText.getText().toString();
                if(password.length()==0){
                    Toast.makeText(MyApplication.getAppContext(),"Please Enter Password First",Toast.LENGTH_LONG).show();
                    return;
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(password.contentEquals(confirmPasswordEditText.getText().toString())){
                    confirmPasswordLayout.setBackgroundResource(R.color.trans);
                    passwordCorrect = true;
                }else {
                    confirmPasswordLayout.setBackgroundResource(R.drawable.wrong_input);
                    passwordCorrect = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        userEmailIdEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Pattern pattern = Patterns.EMAIL_ADDRESS;
                emailIdCorrect = pattern.matcher(userEmailIdEditText.getText().toString()).matches();
                if(emailIdCorrect){
                    emailIdLayout.setBackgroundResource(R.color.trans);
                }else{
                    emailIdLayout.setBackgroundResource(R.drawable.wrong_input);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emailIdCorrect && passwordCorrect) {
                    my_emailPartCorrect.AllEmailParametersCorrect(1);
                } else {
                    Toast.makeText(MyApplication.getAppContext(), "Something is Wrong", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

    public interface emailPartCorrect{
        public void AllEmailParametersCorrect(int index);
    }


}
