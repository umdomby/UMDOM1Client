package by.umdom.serviceclientviewpagerumdom1.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.umdom.serviceclientviewpagerumdom1.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentBedroom extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_bedroom, container, false);
    }

}
