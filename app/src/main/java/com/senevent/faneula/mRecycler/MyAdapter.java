package com.senevent.faneula.mRecycler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.parceler.Parcels;
import com.senevent.faneula.DetailFragment;
import com.senevent.faneula.R;
import com.senevent.faneula.mData.Movie;
import com.senevent.faneula.mPicasso.PicassoClient;

import java.util.ArrayList;

/**
 * Created by Oclemmy on 4/12/2016 for ProgrammingWizards Channel.
 */
public class MyAdapter extends RecyclerView.Adapter<MyHolder>{

    Context c;
    ArrayList<Movie> movies;


    public MyAdapter(Context c, ArrayList<Movie> movies) {
        this.c = c;
        this.movies = movies;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,parent,false);
        MyHolder holder=new MyHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        holder.nameTxt.setText(movies.get(position).getName());
        holder.detailTxt.setText(movies.get(position).getDetail());
        PicassoClient.downloadImage(c, movies.get(position).getUrl(), holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailFragment.class);
                //intent.putExtra("position", itemPosition + "");
                intent.putExtra("movies", Parcels.wrap(movies.get(position)));
                view.getContext().startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

}
