package com.senevent.faneula.mFragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.senevent.faneula.R;
import com.senevent.faneula.mFireBase.FireBaseClient;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListeEventFragment extends Fragment {

    final static String DB_URL="https://senevent.firebaseio.com/";
    EditText nameEditText,urlEditText,detailEditText;
    Button saveBtn;
    RecyclerView rv;

    FireBaseClient fireBaseClient;
    public ListeEventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_liste_event, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        rv= (RecyclerView) view.findViewById(R.id.mRecylcerID);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        fireBaseClient=new FireBaseClient(getActivity(),DB_URL,rv);

        fireBaseClient.refreshData();


/*
        fab.setOnClickListener(new View.OnClickListener() {
        @Override
     public void onClick(View view) {
         displayDialog();
     }
 });

if (savedInstanceState == null) {
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






*/



        return view;
    }

}
