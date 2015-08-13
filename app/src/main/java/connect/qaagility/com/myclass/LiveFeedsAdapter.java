package connect.qaagility.com.myclass;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by macpro on 8/13/15.
 */
public class LiveFeedsAdapter extends RecyclerView.Adapter<LiveFeedsAdapter.LiveDataViewHolder> {

    private List<NewsFeedsData> data = Collections.emptyList();
    private Context myContext;
    private LayoutInflater inflator;

    public LiveFeedsAdapter(Context context, List<NewsFeedsData> data) {
        inflator = LayoutInflater.from(context);
        myContext = context;
        this.data = data;
    }


    @Override
    public LiveFeedsAdapter.LiveDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflator.inflate(R.layout.news_cards, parent, false);
        LiveDataViewHolder holder = new LiveDataViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(LiveDataViewHolder holder, int position) {

        NewsFeedsData myData = data.get(position);
        NewsFeedsData someData = data.get(0);
        holder.card_title_text_view.setText(myData.eventTitle);
        holder.card_date_text_view.setText(myData.AddedDate);
        holder.card_mainBody_textView.setText(someData.mainBody);
        holder.card_additionalBody_textView.setText(myData.moreInfo);
        setColorFor(holder.card_parentView, myData.backgroundColor);
    }

    private void setColorFor(View v, String str) {
        switch (str) {
            case "urgent":
                v.setBackgroundColor(Color.parseColor("#FF7583"));
                break;
            default:
                v.setBackgroundColor(Color.parseColor("#DEDEDE"));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class LiveDataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView card_title_text_view;
        TextView card_date_text_view;
        TextView card_mainBody_textView;
        TextView card_additionalBody_textView;
        Button card_moreInfo_button;
        View card_parentView;


        public LiveDataViewHolder(View itemView) {
            super(itemView);
            card_title_text_view = (TextView) itemView.findViewById(R.id.card_title_text_view);
            card_date_text_view = (TextView) itemView.findViewById(R.id.card_date_text_view);
            card_mainBody_textView = (TextView) itemView.findViewById(R.id.card_mainBody_textView);
            card_additionalBody_textView = (TextView) itemView.findViewById(R.id.card_additionalBody_textView);
            card_moreInfo_button = (Button) itemView.findViewById(R.id.card_moreInfo_button);
            card_parentView = (View)itemView.findViewById(R.id.card_view);
            card_moreInfo_button.setTag(0);
            card_moreInfo_button.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (card_moreInfo_button.getTag() == 0) {
                card_moreInfo_button.setTag(1);
                card_moreInfo_button.setText("Less Info");
            } else {
                card_moreInfo_button.setTag(0);
                card_moreInfo_button.setText("More Info");
            }
        }
    }
}