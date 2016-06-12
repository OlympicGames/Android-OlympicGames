package com.senevent.faneula;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.senevent.faneula.mData.Movie;
import com.senevent.faneula.mFacebook.LoginActivity;
import com.senevent.faneula.mFacebook.LogoutActivity;
import com.senevent.faneula.mFacebook.PrefUtils;
import com.senevent.faneula.mFacebook.User;


import org.json.JSONObject;
import org.parceler.Parcels;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class DetailActivity extends AppCompatActivity {
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private ProgressDialog progressDialog;
    private SwitchCompat switchCompat ;
    User user;

    public static final String ARG_DETAIL_ID = "item_id";
    Movie mMovies;



    //Naby
    String passedVar=null;
    String passedVar2=null;
    private TextView passView = null;
    private TextView passView2 = null;
    private ImageView iv;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        mMovies = Parcels.unwrap(getIntent().getParcelableExtra("movies"));


        passedVar = mMovies.getName();
        passedVar2 = mMovies.getDetail();

        passedVar = getIntent().getStringExtra(ARG_DETAIL_ID);
        passView = (TextView)findViewById(R.id.passed);
        passView.setText(passedVar);

        passView2 = (TextView)findViewById(R.id.passed2);
        passView2.setText(passedVar2);

        iv = (ImageView)findViewById(R.id.imageView1);
        bitmap = getBipmapFromURL(mMovies.getUrl());
        iv.setImageBitmap(bitmap);

        if(PrefUtils.getCurrentUser(DetailActivity.this) != null){

            Intent homeIntent = new Intent(DetailActivity.this, LogoutActivity.class);

            startActivity(homeIntent);

            finish();
        }

    }
    @Override
    protected void onResume() {
        super.onResume();


        callbackManager= CallbackManager.Factory.create();

        loginButton= (LoginButton)findViewById(R.id.login_button1);

        loginButton.setReadPermissions("public_profile", "email","user_friends");


        switchCompat  = (SwitchCompat) findViewById(R.id.switch_compat);
        switchCompat.
                setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DetailActivity.this);

                            // Setting Dialog Title
                            alertDialog.setTitle("Login avec Facebook.");

                            // Setting Dialog Message
                            alertDialog.setMessage("Connexion avec ?");

                            // Setting Icon to Dialog
                            // alertDialog.setIcon(R.drawable.delete);

                            // Setting Positive "Yes" Button
                            alertDialog.setPositiveButton("OUI", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,int which) {

                                    // Write your code here to invoke YES event
                                    progressDialog = new ProgressDialog(DetailActivity.this);
                                    progressDialog.setMessage("Loading...");
                                    progressDialog.show();

                                    loginButton.performClick();

                                    loginButton.setPressed(true);

                                    loginButton.invalidate();

                                    loginButton.registerCallback(callbackManager, mCallBack);

                                    loginButton.setPressed(false);

                                    loginButton.invalidate();
                                }
                            });

                            // Setting Negative "NO" Button
                            alertDialog.setNegativeButton("NON", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // Write your code here to invoke NO event
                                    //  Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    switchCompat.setChecked(false);
                                }
                            });

                            // Showing Alert Message
                            alertDialog.show();

                        }
                    }
                });








    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    private FacebookCallback<LoginResult> mCallBack = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {

            progressDialog.dismiss();

            // App code
            GraphRequest request = GraphRequest.newMeRequest(
                    loginResult.getAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(
                                JSONObject object,
                                GraphResponse response) {

                            Log.e("response: ", response + "");
                            try {
                                user = new User();
                                user.facebookID = object.getString("id").toString();
                                user.email = object.getString("email").toString();
                                user.name = object.getString("name").toString();
                                user.gender = object.getString("gender").toString();
                                PrefUtils.setCurrentUser(user, DetailActivity.this);

                                switchCompat.setChecked(true);
                            } catch (Exception e) {
                                e.printStackTrace();
                                switchCompat.setChecked(false);
                            }
                            Toast.makeText(DetailActivity.this, "welcome " + user.name, Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(DetailActivity.this, LogoutActivity.class);
                            startActivity(intent);
                            finish();

                        }

                    });

            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,email,gender, birthday");
            request.setParameters(parameters);
            request.executeAsync();
        }

        @Override
        public void onCancel() {
            progressDialog.dismiss();
            switchCompat.setChecked(false);
        }

        @Override
        public void onError(FacebookException e) {
            progressDialog.dismiss();
            switchCompat.setChecked(false);
        }
    };

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
