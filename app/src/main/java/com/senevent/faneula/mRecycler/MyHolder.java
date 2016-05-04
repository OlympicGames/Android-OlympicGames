package com.senevent.faneula.mRecycler;


import android.content.Intent;
import android.support.v7.widget.RecyclerView;



import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.senevent.faneula.DetailFragment;
import com.senevent.faneula.ItemDetailFragment;
import com.senevent.faneula.R;



/**
 * Naby Fofana
 */
public class MyHolder extends RecyclerView.ViewHolder {
   // protected long orderId;

    TextView nameTxt;
    TextView detailTxt;
    ImageView img;
    public MyAdapter.DummyItem mItem;


    public MyHolder(final View itemView) {
        super(itemView);

        nameTxt= (TextView) itemView.findViewById(R.id.nameTxt);
        detailTxt = (TextView) itemView.findViewById(R.id.detailTxt);
        img= (ImageView) itemView.findViewById(R.id.movieImage);


      /* itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Intent detailIntent = new Intent(v.getContext(), type.class);
                //detailIntent.putExtra("MyClass", myclass);
                //mContext.startActivity(detailIntent);
               // String product = (String) listView.getItemAtPosition(position);
              //  String product = (String) itemView.getVerticalScrollbarPosition();
                Intent intent = new Intent(view.getContext(), DetailFragment.class);
               //intent.putExtra("MyClass", MyHolder.getOrderId());
            //    intent.putExtra(DetailFragment.ARG_DETAIL_ID, String.valueOf(getLayoutPosition()));
                intent.putExtra(DetailFragment.ARG_DETAIL_ID, );
                view.getContext().startActivity(intent);
            }
        }); */

    }
}
