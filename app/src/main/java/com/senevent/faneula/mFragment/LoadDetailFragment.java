package com.senevent.faneula.mFragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.senevent.faneula.R;
import com.senevent.faneula.mData.Movie;

import org.parceler.Parcels;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoadDetailFragment.OnFragmentInteractionListener} interface
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


    public LoadDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_load_detail, container, false);
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

        SwitchCompat switchCompat = (SwitchCompat) view.findViewById(R.id.switch_compat);
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Toast.makeText(DetailFragment.this,"Android SwitchCompat Example Value "+isChecked, Toast.LENGTH_LONG).show();

                // Add code to print out the key hash
  /*              try {
                    PackageInfo info = getPackageManager().getPackageInfo(
                            "com.facebook.samples.hellofacebook",
                            PackageManager.GET_SIGNATURES);
                    for (Signature signature : info.signatures) {
                        MessageDigest md = MessageDigest.getInstance("SHA");
                        md.update(signature.toByteArray());
                      //  Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                    }
                } catch (PackageManager.NameNotFoundException e) {

                } catch (NoSuchAlgorithmException e) {

                }
*/

            }
        });

        return view;
    }
/*
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
/*    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
*/
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
