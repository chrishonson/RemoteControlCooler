package com.android.remotecontrolcooler.common;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.android.remotecontrolcooler.CoolerActivity;


/**
 * Created by nickc on 7/18/15.
 */

public class SectionNumberFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static SectionNumberFragment newInstance(int sectionNumber) {
        SectionNumberFragment fragment = new SectionNumberFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((CoolerActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
