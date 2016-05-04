package com.senevent.faneula;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.senevent.faneula.mFireBase.FireBaseClient;

public class MainActivity extends AppCompatActivity {

    public final static String ID_Test = "com.senevent.faneula.MainActivity._ID";
    final static String DB_URL="https://senevent.firebaseio.com/";
    EditText nameEditText,urlEditText,detailEditText;
    Button saveBtn;
    RecyclerView rv;

    FireBaseClient fireBaseClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        rv= (RecyclerView) findViewById(R.id.mRecylcerID);
        rv.setLayoutManager(new LinearLayoutManager(this));

        fireBaseClient=new FireBaseClient(this,DB_URL,rv);

        fireBaseClient.refreshData();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayDialog();
            }
        });

       /* if (savedInstanceState == null) {
            Fragment eventListFragment = new MainActivityFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.toolbar, eventListFragment).commit();
        }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                //String product = (String) listView.getItemAtPosition(position);

                System.out.println("Display text" + product);
                Intent i = new Intent(MainActivity.this, DetailFragment.class);
                // sending data to new activity
                i.putExtra(ID_Test, product);
                startActivity(i);
            }
        });

        RelativeLayout relative1 = (RelativeLayout) findViewById(R.id.mRecylcerID);
        relative1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, DetailFragment.class));
            }
        }); */
    }

    //SHOW INPUT DIALOG
    private void displayDialog()
    {
        Dialog d=new Dialog(this);
        d.setTitle("Save Online");
        d.setContentView(R.layout.dialoglayout);

        nameEditText= (EditText) d.findViewById(R.id.nameEditText);
        urlEditText= (EditText) d.findViewById(R.id.urlEditText);
        detailEditText= (EditText) d.findViewById(R.id.detailEditText);

        saveBtn= (Button) d.findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fireBaseClient.saveOnline(nameEditText.getText().toString(),urlEditText.getText().toString(), detailEditText.getText().toString());

                nameEditText.setText("");
                urlEditText.setText("");
                detailEditText.setText("");
            }
        });

        //SHOW
        d.show();
    }



}
