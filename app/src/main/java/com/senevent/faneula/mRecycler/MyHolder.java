package com.senevent.faneula.mRecycler;


import android.support.v7.widget.RecyclerView;



import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.senevent.faneula.R;



/**
 * Naby Fofana
 */
public class MyHolder extends RecyclerView.ViewHolder {
   // protected long orderId;

    TextView nameTxt;
    TextView detailTxt;
    ImageView img;



    public MyHolder(final View itemView) {
        super(itemView);

        nameTxt= (TextView) itemView.findViewById(R.id.nameTxt);
        detailTxt = (TextView) itemView.findViewById(R.id.detailTxt);
        img= (ImageView) itemView.findViewById(R.id.movieImage);




    }
}
