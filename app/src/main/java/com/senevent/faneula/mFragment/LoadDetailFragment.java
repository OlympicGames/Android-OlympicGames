package com.senevent.faneula.mFragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.senevent.faneula.R;
import com.senevent.faneula.mData.Movie;
import com.senevent.faneula.mFacebook.LoginActivity;
import com.senevent.faneula.mFacebook.PrefUtils;

import org.parceler.Parcels;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.
 */
public class LoadDetailFragment extends Fragment {

  //  private OnFragmentInteractionListener mListener;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String ARG_DETAIL_ID = "item_id";
    Movie mMovies;



    //Naby
    String passedVar=null;
    String passedVar2=null;
    private TextView passView = null;
    private TextView passView2 = null;
    private ImageView iv;
    private Bitmap bitmap;

    private TextView info;
    private LoginButton loginButton;
    private CallbackManager callbackManager;


    public LoadDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_load_detail, container, false);
              //  FacebookSdk.sdkInitialize(getApplicationContext());
        mMovies = Parcels.unwrap(getActivity().getIntent().getParcelableExtra("movies"));


        passedVar = mMovies.getName();
        passedVar2 = mMovies.getDetail();

        //passedVar = getIntent().getStringExtra(ARG_DETAIL_ID);
        passView = (TextView)view.findViewById(R.id.passed);
        passView.setText(passedVar);

        passView2 = (TextView)view.findViewById(R.id.passed2);
        passView2.setText(passedVar2);

        iv = (ImageView)view.findViewById(R.id.imageView1);
        bitmap = getBipmapFromURL(mMovies.getUrl());
        iv.setImageBitmap(bitmap);


        final SwitchCompat switchCompat = (SwitchCompat) view.findViewById(R.id.switch_compat);
        switchCompat.
                setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                   // loginButton.performClick();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    getActivity().startActivity(intent);

                    if(PrefUtils.getCurrentUser(getActivity()) != null){
                switchCompat.setChecked(true);
                    }else {
                        switchCompat.setChecked(false);
                    }

                }
            }
        });

        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    //Methode qui permet de voir l'image à partir d'un URL
    public Bitmap getBipmapFromURL(String src){
        try{
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        }catch (Exception e){
            e.printStackTrace();
            return null;

        }
    }
}
