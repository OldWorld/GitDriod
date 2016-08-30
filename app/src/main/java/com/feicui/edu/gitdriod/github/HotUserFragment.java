package com.feicui.edu.gitdriod.github;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feicui.edu.gitdriod.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HotUserFragment extends Fragment {


    public HotUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hot_user, container, false);
    }

}
