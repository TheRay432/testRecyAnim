package com.example.testrecyanim.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testrecyanim.R;
import com.example.testrecyanim.model.NewsItem;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> implements Filterable {

    private Context mContext;
    private List<NewsItem> mData;
    private List<NewsItem> mDataFilter;
    private boolean isDark=false;

    public NewsAdapter() {
    }

    public NewsAdapter(Context mContext, List<NewsItem> mData, boolean isDark) {
        this.mContext = mContext;
        this.mData = mData;
        this.isDark = isDark;
        this.mDataFilter=mData;
    }

    public NewsAdapter(Context mContext, List<NewsItem> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.mDataFilter=mData;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(mContext).inflate(R.layout.item_news,parent,false);
       return new NewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.img_user.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_transition_animation));
        holder.container.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_scale_animation));

        holder.tv_title.setText(mDataFilter.get(position).getTitle());
        holder.tv_date.setText(mDataFilter.get(position).getDate());
        holder._tv_description.setText(mDataFilter.get(position).getContent());
        holder.img_user.setImageResource(mDataFilter.get(position).getUserPhoto());
    }

    @Override
    public int getItemCount() {
        return mDataFilter.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String key=constraint.toString();
                if(key.isEmpty()){
                    mDataFilter=mData;
                }
                else {
                    List<NewsItem> lstFilter=new ArrayList<>();
                    for(NewsItem row : mData){
                        if(row.getTitle().toLowerCase().contains(key.toLowerCase())){
                            lstFilter.add(row);
                        }
                    }
                    mDataFilter=lstFilter;
                }
                FilterResults filterResults=new FilterResults();
                filterResults.values=mDataFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mDataFilter=(List<NewsItem>)results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
    private ImageView img_user;
    private TextView tv_title,tv_date,_tv_description;
    private ConstraintLayout container;
    public NewsViewHolder(@NonNull View itemView) {
        super(itemView);
        img_user=(ImageView)itemView.findViewById(R.id.img_user);
        tv_title=(TextView)itemView.findViewById(R.id.tv_title);
        tv_date=(TextView)itemView.findViewById(R.id.tv_date);
        _tv_description=(TextView)itemView.findViewById(R.id.tv_description);
       container=(ConstraintLayout)itemView.findViewById(R.id.action_container);

       if(isDark){
           setDarkTheme();
       }
    }
    private void setDarkTheme(){
        container.setBackgroundResource(R.drawable.card_bg_dark);
    }
}
}
