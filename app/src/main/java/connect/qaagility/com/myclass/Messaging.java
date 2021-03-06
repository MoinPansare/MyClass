package connect.qaagility.com.myclass;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Messaging extends AppCompatActivity {

    EditText enteringTextView;
    ImageView sendButtonImageView;

    Toolbar my_toolbar;
    RecyclerView my_recyclreView;
    private List<MessageData> AllData = Collections.emptyList();
    private MessageAdapter myAdapter;

    View parentView,dataEnteringView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);
        my_toolbar = (Toolbar) findViewById(R.id.meaage_app_bar);
        parentView = findViewById(R.id.parentView);
        dataEnteringView = findViewById(R.id.dataEnteringLayout);

        Animation anim = AnimationUtils.loadAnimation(Messaging.this,
                R.anim.app_bar_show);
        my_toolbar.startAnimation(anim);

        setSupportActionBar(my_toolbar);
        getSupportActionBar().setTitle("Messages");

        my_recyclreView = (RecyclerView) findViewById(R.id.message_recyclreView);
//        my_recyclreView.
        AllData = getData();

        myAdapter = new MessageAdapter(this, AllData);
        my_recyclreView.setLayoutManager(new LinearLayoutManager(this, 1, true));



//        my_recyclreView.

        enteringTextView = (EditText) findViewById(R.id.meaage_edit_text);
        sendButtonImageView = (ImageView) findViewById(R.id.send_imageView);
        sendButtonImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!enteringTextView.getText().toString().equalsIgnoreCase(" ")) {
                    MessageData attachingData = new MessageData();
                    attachingData.sender = 0;
                    attachingData.messageDate = "2/8/2015";
                    attachingData.messageBody = enteringTextView.getText().toString();
                    AllData.add(0, attachingData);
                    myAdapter.InformNewValue();
                    myAdapter.notifyDataSetChanged();
                    enteringTextView.setText("");
//                    try{
//                        Thread.sleep(500);
//                    }catch(Exception e){
//                        Log.d("in exception", "some exception");
//                    }
//                    my_recyclreView.smoothScrollToPosition(myAdapter.getItemCount() - 1);
                }
            }
        });
//        my_recyclreView.scrollToPosition(AllData.size());

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                parentView.setBackgroundResource(R.drawable.login_background);
                my_recyclreView.setAdapter(myAdapter);
                dataEnteringView.setAlpha(1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    public List<MessageData> getData() {
        //load only static data inside a drawer
        List<MessageData> data = new ArrayList<>();
        String[] titles = {"News Board", "Assignments", "Messages", "Notifications", "Photos"};
        String[] dates = {"1/8/2015", "2/8/2015", "3/8/2015", "4/8/2015", "5/8/2015"};
        String[] mainBody = {"This is Some Dummy Data Tp Prove Than At Times The Text Might Get Clipped", "Assignments", "Messages", "Notifications", "Photos"};
        String[] additional = {"News Board", "Assignments", "Messages", "Notifications", "Photos"};
        String[] backGroundPriority = {"urgent", "normal", "normal", "urgent", "normal"};
        for (int i = 0; i < 5; i++) {
            MessageData current = new MessageData();
            current.messageBody = mainBody[0];
            current.messageDate = dates[i % dates.length];
            current.sender = i % 2;
            data.add(current);
        }
        for (int i = 0; i < 5; i++) {
            MessageData current = new MessageData();
            current.messageBody = mainBody[0];
            current.messageDate = dates[i % dates.length];
            current.sender = i % 2;
            data.add(current);
        }
        for (int i = 0; i < 5; i++) {
            MessageData current = new MessageData();
            current.messageBody = mainBody[0];
            current.messageDate = dates[i % dates.length];
            current.sender = i % 2;
            data.add(current);
        }
        return data;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_messaging, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                Toast.makeText(this, "You Selected Settings", Toast.LENGTH_LONG).show();
                break;
            case R.id.action_about_us:
                startActivity(new Intent(this, about_us.class));
                break;
            case R.id.action_logout:
                Toast.makeText(this, "You Selected Logout", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "Fuck You", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder> {

        private int pos = 0,newValue=-1;
        private Context myContext;
        private LayoutInflater inflator;
        private List<MessageData> data = Collections.emptyList();

        public void InformNewValue() {
            this.newValue = 0;
        }

        public MessageAdapter(Context context, List<MessageData> data) {
            inflator = LayoutInflater.from(context);
            myContext = context;
            this.data = data;
            pos = data.size();
        }

        @Override
        public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflator.inflate(R.layout.message_cell, parent, false);
            MessageViewHolder holder = new MessageViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MessageViewHolder holder, int position) {

            MessageData myData = data.get(position);
            MessageData someData = data.get(position);
            //set text here

            holder.center_layout_view.setBackgroundResource(R.color.dividerColor);

            if (someData.sender == 0) {
                holder.leftImage.setImageResource(0);
                holder.rightImage.setImageResource(R.drawable.user_image);
                holder.left_layout_view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT, 11));

                holder.right_layout_view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT, 10));

                holder.center_layout_view.setBackgroundResource(R.color.iAmSending);
            } else {
                holder.rightImage.setImageResource(0);
                holder.leftImage.setImageResource(R.drawable.parent_image);
                holder.left_layout_view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT, 10));

                holder.right_layout_view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT, 11));

                holder.center_layout_view.setBackgroundResource(R.color.iAmRecieveing);
            }
            holder.bodyTextView.setText(someData.messageBody);
            holder.dateTextView.setText(someData.messageDate);
            setAnimation(holder.parent_layout_view, someData.sender);

        }

        private void setAnimation(View view, int sender) {
            if (sender == 1) {
                Animation animation = AnimationUtils.loadAnimation(myContext, R.anim.slide_in_left);
                view.startAnimation(animation);
            } else {
                Animation animation = AnimationUtils.loadAnimation(myContext, R.anim.slide_in_right);
                view.startAnimation(animation);
            }
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {

        View center_layout_view, left_layout_view, right_layout_view, parent_layout_view;
        TextView dateTextView, bodyTextView;
        ImageView leftImage,rightImage;


        public MessageViewHolder(View itemView) {
            super(itemView);
            center_layout_view = (View) itemView.findViewById(R.id.message_cell_message_background);
            left_layout_view = (View) itemView.findViewById(R.id._message_cell_left_blank);
            right_layout_view = (View) itemView.findViewById(R.id._message_cell_right_blank);
            parent_layout_view = (View) itemView.findViewById(R.id.message_cell_parent);
            dateTextView = (TextView) itemView.findViewById(R.id.message_cell_date);
            bodyTextView = (TextView) itemView.findViewById(R.id.message_cell_main_message);
            leftImage = (ImageView)itemView.findViewById(R.id.left_ImageView);
            rightImage = (ImageView)itemView.findViewById(R.id.right_ImageView);
        }

    }

    public class MessageData {
        String messageBody;
        String messageDate;
        int sender;//0 for sender 1 for recipient
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if ((keyCode == KeyEvent.KEYCODE_BACK)) {

            my_toolbar.post(new Runnable() {
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
            parentView.setBackgroundResource(R.color.trans);
            parentView.setAlpha((float) 0.3);
            my_recyclreView.setAlpha(0);
            dataEnteringView.setAlpha(0);
        }

        return super.onKeyDown(keyCode, event);

    }

}
