package com.example.news;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecycleView_Adapter extends RecyclerView.Adapter<RecycleView_Adapter.ViewHolder> {
    String url;
    List<News> items= new ArrayList<News>();

    public RecycleView_Adapter( Context context,List<News> items) {
        this.items=items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View list_item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ViewHolder(list_item);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleView_Adapter.ViewHolder holder, int position) {
        News news = items.get(position);
        holder.title.setText(news.getTitle());
        holder.SectionName.setText(news.getSectionName());
        holder.date.setText(news.getDate());
         url = news.getUrl();
         if(news.getPillarName().equals("News")&&!news.getSectionID().equals("global-development"))
            holder.imv.setImageResource(Constants.programImages[0]);
        else if(news.getPillarName().equals("Sport"))
            holder.imv.setImageResource(Constants.programImages[1]);
         else if(news.getSectionID().equals("world"))
             holder.imv.setImageResource(Constants.programImages[2]);
         else if(news.getSectionName().equals("Global development"))
             holder.imv.setImageResource(Constants.programImages[3]);
         else if(news.getSectionID().equals("politics"))
             holder.imv.setImageResource(Constants.programImages[4]);
         else if(news.getSectionID().equals("commentisfree"))
             holder.imv.setImageResource(Constants.programImages[5]);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
       public TextView title,SectionName,date;
       public ImageView imv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.item_title);
            date=itemView.findViewById(R.id.date_item);
            imv=itemView.findViewById(R.id.imageView);
            SectionName=itemView.findViewById(R.id.SectionName);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent Open_Story_In_Website = new Intent(Intent.ACTION_VIEW);
                    Open_Story_In_Website.setData(Uri.parse(url));
                    v.getContext().startActivity(Open_Story_In_Website);
                }
            });
        }
    }
}
