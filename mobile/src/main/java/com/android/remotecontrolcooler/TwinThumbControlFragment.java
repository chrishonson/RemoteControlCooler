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
    private static final String PRELUDE = "7C";
    private static final String LEFT = "00";
    private static final String RIGHT = "01";
    private static final String FORWARD = "00";
    private static final String REVERSE = "01";

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
                int zero = getActivity().getResources().getInteger(R.integer.zero_accelerator_position);
                MagnitudeAndDirection magnitudeAndDirection = new MagnitudeAndDirection(progress, zero).invoke();
                String direction = magnitudeAndDirection.getDirection();
                int magnitude = magnitudeAndDirection.getMagnitude();
                //this is stupid but 0 well
                if (magnitude == 0) {
                    ((CoolerActivity) getActivity()).sendMessage(PRELUDE +
                            LEFT + direction + "00");
                } else {
                    ((CoolerActivity) getActivity()).sendMessage(PRELUDE +
                            LEFT + direction + Integer.toString(magnitude));
                }
//                                ((CoolerActivity)getActivity()).sendMessage(
//                        "Left " + Integer.toString(progress));
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
                int zero = getActivity().getResources().getInteger(R.integer.zero_accelerator_position);
                MagnitudeAndDirection magnitudeAndDirection = new MagnitudeAndDirection(progress, zero).invoke();
                String direction = magnitudeAndDirection.getDirection();
                int magnitude = magnitudeAndDirection.getMagnitude();
                //this is stupid but 0 well
                if (magnitude == 0) {
                    ((CoolerActivity) getActivity()).sendMessage(PRELUDE +
                            RIGHT + direction + "00");
                } else {
                    ((CoolerActivity) getActivity()).sendMessage(PRELUDE +
                            RIGHT + direction + Integer.toString(magnitude));
                }
//                ((CoolerActivity)getActivity()).sendMessage(
//                        "Right " + Integer.toString(progress));
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
        private int zero;
        private int magnitude;
        private String direction;

        public MagnitudeAndDirection(int progress, int zero) {
            this.progress = progress;
            this.zero = zero;
        }

        public int getMagnitude() {
            return magnitude;
        }

        public String getDirection() {
            return direction;
        }

        public MagnitudeAndDirection invoke() {
            magnitude = zero;
            direction = FORWARD;
            if (progress < zero - 1) {
                short b = (short) progress;
                b -= zero;
                magnitude = Math.abs(b);
                direction = REVERSE;
            } else if (progress > zero) {
                magnitude = progress - zero;
            } else {
                magnitude = 0;
            }
            return this;
        }
    }
}
