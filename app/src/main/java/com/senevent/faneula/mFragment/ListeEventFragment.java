package com.senevent.faneula.mFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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

        rv= (RecyclerView) view.findViewById(R.id.mRecylcerID);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        fireBaseClient=new FireBaseClient(getActivity(),DB_URL,rv);

        fireBaseClient.refreshData();

        return view;
    }

}
