package com.android.remotecontrolcooler;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by nickc on 7/18/15.
 */

public class AccessoriesFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static AccessoriesFragment newInstance(int sectionNumber) {
        AccessoriesFragment fragment = new AccessoriesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_control_music, container, false);
        Button rewindButtonk = (Button) rootView.findViewById(R.id.rewind_button);
        Button playButton = (Button) rootView.findViewById(R.id.play_button);
        Button skipButton = (Button) rootView.findViewById(R.id.skip_button);
        rewindButtonk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CoolerActivity) getActivity()).sendMessage("Rewind");
            }
        });
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CoolerActivity) getActivity()).sendMessage("Play");
            }
        });
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CoolerActivity) getActivity()).sendMessage("Skip");
            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((CoolerActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
