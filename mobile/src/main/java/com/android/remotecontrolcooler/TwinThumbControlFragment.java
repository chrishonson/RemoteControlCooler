package com.android.remotecontrolcooler;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.android.remotecontrolcooler.widget.VerticalSeekBar;

public class TwinThumbControlFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TwinThumbControlFragment newInstance(int sectionNumber) {
        TwinThumbControlFragment fragment = new TwinThumbControlFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public TwinThumbControlFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_drive, container, false);
        VerticalSeekBar seekbar = (VerticalSeekBar)rootView.findViewById(R.id.seekBar1);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                MagnitudeAndDirection magnitudeAndDirection = new MagnitudeAndDirection(progress).invoke();
                String direction = magnitudeAndDirection.getDirection();
                int magnitude = magnitudeAndDirection.getMagnitude();
                ((CoolerActivity)getActivity()).sendMessage(
                        "Left " + direction + Integer.toString(magnitude));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        VerticalSeekBar seekbar2 = (VerticalSeekBar) rootView.findViewById(R.id.seekBar2);
        seekbar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                MagnitudeAndDirection magnitudeAndDirection = new MagnitudeAndDirection(progress).invoke();
                String direction = magnitudeAndDirection.getDirection();
                int magnitude = magnitudeAndDirection.getMagnitude();
                ((CoolerActivity) getActivity()).sendMessage(
                        "Right " + direction + Integer.toString(magnitude));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
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

    private class MagnitudeAndDirection {
        private int progress;
        private int magnitude;
        private String direction;

        public MagnitudeAndDirection(int progress) {
            this.progress = progress;
        }

        public int getMagnitude() {
            return magnitude;
        }

        public String getDirection() {
            return direction;
        }

        private final static int ZERO_ACCELERATOR = 256;

        public MagnitudeAndDirection invoke() {
            magnitude = ZERO_ACCELERATOR;
            direction = "forward ";
            if (progress < ZERO_ACCELERATOR - 1) {
                short b = (short) progress;
                b -= ZERO_ACCELERATOR;
                magnitude = Math.abs(b);
                direction = "reverse ";
            } else if (progress > ZERO_ACCELERATOR) {
                magnitude = progress - ZERO_ACCELERATOR;
            } else {
                magnitude = 0;
            }
            return this;
        }
    }
}
