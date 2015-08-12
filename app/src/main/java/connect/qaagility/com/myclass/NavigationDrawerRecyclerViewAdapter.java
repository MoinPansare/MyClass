package connect.qaagility.com.myclass;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by macpro on 8/12/15.
 */
public class NavigationDrawerRecyclerViewAdapter extends RecyclerView.Adapter<NavigationDrawerRecyclerViewAdapter.MyViewHolder> {

    List<NavigationDrawerData> data = Collections.emptyList();
    private LayoutInflater inflator;

    public NavigationDrawerRecyclerViewAdapter(Context context, List<NavigationDrawerData> data) {
        inflator = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflator.inflate(R.layout.nav_drawer_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NavigationDrawerData myData = data.get(position);
        holder.textView.setText(myData.title);
        holder.imgView.setImageResource(myData.imageId);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imgView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.nav_view_row_textView);
            imgView = (ImageView) itemView.findViewById(R.id.nav_view_row_ImageView);
        }
    }
}
