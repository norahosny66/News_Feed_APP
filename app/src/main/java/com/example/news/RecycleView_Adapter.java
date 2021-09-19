package com.example.news;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Console;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RecycleView_Adapter extends RecyclerView.Adapter<RecycleView_Adapter.ViewHolder> {
    int[] programImages ={R.drawable.news_logo,R.drawable.sports_logo,R.drawable.world_logo,
            R.drawable.globla_logo,R.drawable.politics_logo,R.drawable.opinion_logo};
    private LayoutInflater mInflater;

    Cursor dataCursor;

    String url;
    List<News> items= new ArrayList<News>();
   // ListItemClik
    public RecycleView_Adapter( Context c,List<News> items) {

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
         url = news.getUrl();
         if(news.getPillarName().equals("News")&&!news.getSectionID().equals("global-development"))
            holder.imv.setImageResource(programImages[0]);
        else if(news.getPillarName().equals("Sport"))
            holder.imv.setImageResource(programImages[1]);
         else if(news.getSectionID().equals("world"))
             holder.imv.setImageResource(programImages[2]);
         else if(news.getName().equals("Global development"))
             holder.imv.setImageResource(programImages[3]);
         else if(news.getSectionID().equals("politics"))
             holder.imv.setImageResource(programImages[4]);
         else if(news.getSectionID().equals("commentisfree"))
             holder.imv.setImageResource(programImages[5]);
        holder.date.setText(news.getDate());
  // if(!news.getmImage().isEmpty()){
    //Picasso.get().load(news.getmImage()).into(holder.imv);
     // }
    }


    @Override
    public int getItemCount() {
        return items.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

       public TextView title,author,date;
       public ImageView imv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.item_title);
            date=itemView.findViewById(R.id.date_item);
            imv=itemView.findViewById(R.id.imageView);
          //  author=itemView.findViewById(R.id.author);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
               //     if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
                    Intent i = new Intent(Intent.ACTION_VIEW);

                    i.setData(Uri.parse(url));
                    v.getContext().startActivity(i);
                }
            });
        }
    }
}
