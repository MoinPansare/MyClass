package connect.qaagility.com.myclass;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by macpro on 8/18/15.
 */
public class GalleryGridAdapter extends RecyclerView.Adapter<GalleryGridAdapter.GalleryGridViewHolder> {

    private int pos = 0;
    private Context myContext;
    private LayoutInflater inflator;
    private GridImageSelected my_GridImageSelected;

    public GalleryGridAdapter(Context context) {
        inflator = LayoutInflater.from(context);
        myContext = context;
    }

    public void setMy_GridImageSelected(GridImageSelected my_GridImageSelected){
        this.my_GridImageSelected = my_GridImageSelected;
    }


    @Override
    public GalleryGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflator.inflate(R.layout.grid_cell, parent, false);
        GalleryGridViewHolder holder = new GalleryGridViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(GalleryGridViewHolder holder, int position) {
//        int id = data.get(position);
        holder.primaryImageView.setImageResource(R.drawable.bg3);
        switch(position%3){
            case 0 : holder.primaryImageView.setLayoutParams(new LinearLayout.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT,300));break;
            case 1 : holder.primaryImageView.setLayoutParams(new LinearLayout.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT,450));break;
        }
        setAnimation(holder.layout_view, position);
    }

    private void setAnimation(View view, int position) {
        if (pos > position) {
            Animation animation = AnimationUtils.loadAnimation(myContext, R.anim.push_top);
            view.startAnimation(animation);
        }
        else
        {
            Animation animation = AnimationUtils.loadAnimation(myContext, R.anim.push_bottom);
            view.startAnimation(animation);
        }
        pos = position;
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class GalleryGridViewHolder extends RecyclerView.ViewHolder{

        public ImageView primaryImageView;
        View layout_view;

        public GalleryGridViewHolder(View itemView) {
            super(itemView);
            primaryImageView = (ImageView)itemView.findViewById(R.id.grid_cell_image);
            layout_view = (View)itemView.findViewById(R.id.grid_cell_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    my_GridImageSelected.selectedGridImage(primaryImageView,getAdapterPosition());
                }
            });

        }

    }

    public interface GridImageSelected{
        public void selectedGridImage(ImageView v,int position);
    }

}
