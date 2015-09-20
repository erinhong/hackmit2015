package com.tripster.tripster.ui.finder;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tripster.tripster.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class TripFinderActivityFragment extends Fragment {

    public TripFinderActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trip_finder, container, false);
    }
}
