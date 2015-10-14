package connect.qaagility.com.myclass.signupFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

import connect.qaagility.com.myclass.MyApplication;
import connect.qaagility.com.myclass.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChildInfo extends Fragment {

    private signUpConfirmationInterface my_signUpConfirmationInterface;

    private Spinner myClassSpinner,myBatchSpinner;

    private Button signUpButton;

    private ArrayList<String> classSpinnerData;
    private ArrayList<String> batchSpinnerData;


    public void setMy_signUpConfirmationInterface(signUpConfirmationInterface my_signUpConfirmationInterface) {
        this.my_signUpConfirmationInterface = my_signUpConfirmationInterface;
    }

    public ChildInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_child_info, container, false);

        signUpButton = (Button)view.findViewById(R.id.signUp_Confirmation);

        myClassSpinner = (Spinner)view.findViewById(R.id.class_spinner_info);
        myBatchSpinner = (Spinner)view.findViewById(R.id.Batch_Spinner_info);

        classSpinnerData = new ArrayList<String>();
        classSpinnerData.add("1st");
        classSpinnerData.add("2nd");
        classSpinnerData.add("3rd");
        classSpinnerData.add("4th");
        classSpinnerData.add("5th");
        classSpinnerData.add("6th");
        classSpinnerData.add("7th");
        classSpinnerData.add("8th");
        classSpinnerData.add("9th");
        classSpinnerData.add("10th");

        batchSpinnerData = new ArrayList<String>();
        batchSpinnerData.add("A");
        batchSpinnerData.add("B");
        batchSpinnerData.add("C");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MyApplication.getAppContext(),R.layout.spinner_item, classSpinnerData);

        adapter.setDropDownViewResource(R.layout.spinner_item);

        myClassSpinner.setAdapter(adapter);

        ArrayAdapter<String> batchAdapter = new ArrayAdapter<String>(MyApplication.getAppContext(),R.layout.spinner_item, batchSpinnerData);

        batchAdapter.setDropDownViewResource(R.layout.spinner_item);

        myBatchSpinner.setAdapter(batchAdapter);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                my_signUpConfirmationInterface.signUpConfirmed();
            }
        });

        return view;
    }


    public static final ChildInfo newInstance()
    {
        ChildInfo f = new ChildInfo();
        return f;
    }

    public interface signUpConfirmationInterface{
        public void signUpConfirmed();
    }


}
