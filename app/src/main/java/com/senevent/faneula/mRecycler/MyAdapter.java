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


    private String mNavTitles[]; // String Array to store the passed titles Value from MainActivity.java
    private int mIcons[];       // Int Array to store the passed icons resource value from MainActivity.java

    private String name;        //String Resource for header View Name
    private int profile;        //int Resource for header view profile picture
    private String email;

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


    public MyAdapter(String Titles[], int Icons[], String Name, String Email, int Profile){ // MyAdapter Constructor with titles and icons parameter
        // titles, icons, name, email, profile pic are passed from the main activity as we
        mNavTitles = Titles;                //have seen earlier
        mIcons = Icons;
        name = Name;
        email = Email;
        profile = Profile;                     //here we assign those passed values to the values we declared here
        //in adapter



    }
}
