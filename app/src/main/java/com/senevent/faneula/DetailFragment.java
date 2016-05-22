package com.senevent.faneula;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.senevent.faneula.mData.Movie;
import com.senevent.faneula.mFragment.ListeEventFragment;
import com.senevent.faneula.mFragment.LoadDetailFragment;

import org.parceler.Parcels;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;

//import com.facebook.FacebookSdk;



public class DetailFragment extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_detail);

        if (savedInstanceState == null) {
            Fragment loadDetailFragment = new LoadDetailFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.detailId, loadDetailFragment).commit();
        }

    }




}
