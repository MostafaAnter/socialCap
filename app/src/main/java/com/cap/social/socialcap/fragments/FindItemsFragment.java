package com.cap.social.socialcap.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cap.social.socialcap.R;

/**
 * Created by mostafa on 13/02/16.
 */
public class FindItemsFragment extends Fragment {

    public FindItemsFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.find_items_fragment, container, false);
        return view;
    }
}
