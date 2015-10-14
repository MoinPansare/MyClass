package connect.qaagility.com.myclass;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by macpro on 8/26/15.
 */
public class AssignmentsAdapter extends RecyclerView.Adapter<AssignmentsAdapter.AssignmentsViewHolder> {

    private int pos = 0,newValue=-1;
    private Context myContext;
    private LayoutInflater inflator;
    private List<AssignmentsData> data = Collections.emptyList();
    public assignmentEvent my_assignmentEvent;

    public void InformNewValue() {
        this.newValue = 0;
    }

    public void setMy_assignmentEvent(assignmentEvent my_assignmentEvent) {
        this.my_assignmentEvent = my_assignmentEvent;
    }

    public AssignmentsAdapter(Context context, List<AssignmentsData> data) {
        inflator = LayoutInflater.from(context);
        myContext = context;
        this.data = data;
    }

    @Override
    public AssignmentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflator.inflate(R.layout.assignments_row, parent, false);
        AssignmentsViewHolder holder = new AssignmentsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AssignmentsViewHolder holder, int position) {

        AssignmentsData myData = data.get(position);
        //set text here

        holder.titleTextView.setText(myData.subject);
        holder.submittedDateTextView.setText(myData.postedDate);
        holder.dueDateTextView.setText("Due On :"+myData.lastDate);
        holder.summaryTextView.setText(myData.summary);
        int myPos = myData.position_in_view%6;
        switch (myPos){
            case 0:holder.parent_layout.setBackgroundResource(R.color.blue1);break;
            case 1:holder.parent_layout.setBackgroundResource(R.color.blue2);break;
            case 2:holder.parent_layout.setBackgroundResource(R.color.blue3);break;
            case 3:holder.parent_layout.setBackgroundResource(R.color.blue4);break;
            case 4:holder.parent_layout.setBackgroundResource(R.color.blue3);break;
            case 5:holder.parent_layout.setBackgroundResource(R.color.blue2);break;
        }
        setAnimation(holder.parent, position);
    }

    private void setAnimation(View view, int position) {

        if (pos > position) {
            Animation animation = AnimationUtils.loadAnimation(myContext, R.anim.push_from_top);
            view.startAnimation(animation);
        }
        else
        {
            Animation animation = AnimationUtils.loadAnimation(myContext, R.anim.push_from_bottom);
            view.startAnimation(animation);
        }
        pos = position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class AssignmentsViewHolder extends RecyclerView.ViewHolder {

        View parent_layout,parent;
        TextView titleTextView, submittedDateTextView,dueDateTextView,summaryTextView;

        public AssignmentsViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.subject_textView);
            submittedDateTextView = (TextView) itemView.findViewById(R.id.dateTextView);
            dueDateTextView = (TextView) itemView.findViewById(R.id.dueDate);
            summaryTextView = (TextView) itemView.findViewById(R.id.summary);
            parent_layout = (View) itemView.findViewById(R.id.parent_assignment);
            parent = (View) itemView.findViewById(R.id.parent_assignment_view);
        }

    }

    public static class AssignmentsData {
        String postedDate;
        String lastDate;
        String subject;
        String mainBody;
        String summary;
        int position_in_view;
    }

    public interface assignmentEvent{
        public void assignmentClicked(View v,int position);
    }

}
