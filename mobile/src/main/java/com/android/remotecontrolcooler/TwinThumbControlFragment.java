package com.android.remotecontrolcooler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.android.remotecontrolcooler.common.logger.Log;
import com.android.remotecontrolcooler.widget.VerticalSeekBar;

public class TwinThumbControlFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String PRELUDE = "7C0000";
    private static final String LEFT = "00";
    private static final String RIGHT = "01";
    private static final String FORWARD = "00";
    private static final String REVERSE = "01";
    private static final String NUMBYTES = "0006";
    private static final String CARRAGERETURN = "0A";
    private static final String LINEFEED = "0A";
    String rightDirection = FORWARD;
    String leftDirection = FORWARD;
    int rightMagnitude = 0;
    int leftMagnitude = 0;
    private Handler handler = new Handler();
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

    private String getHexString(int magnitude) {
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toHexString(magnitude));
        if (sb.length() < 2)

        {
            sb.insert(0, '0'); // pad with leading zero if needed
        }

        String hex = sb.toString();
        return hex;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_drive, container, false);
        VerticalSeekBar seekbar = (VerticalSeekBar) rootView.findViewById(R.id.seekBar1);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int zero = getActivity().getResources().getInteger(R.integer.zero_accelerator_position);
                MagnitudeAndDirection magnitudeAndDirection = new MagnitudeAndDirection(progress, zero).invoke();
                leftDirection = magnitudeAndDirection.getDirection();
                leftMagnitude = magnitudeAndDirection.getMagnitude();
                combineAndSend();


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
                rightDirection = magnitudeAndDirection.getDirection();
                rightMagnitude = magnitudeAndDirection.getMagnitude();
                combineAndSend();

                //this is stupid but 0 well
//                if (rightMagnitude == 0) {
//                    ((CoolerActivity) getActivity()).sendMessage(PRELUDE + NUMBYTES +
//                            RIGHT + rightDirection + "00");
//                } else {
//                    ((CoolerActivity) getActivity()).sendMessage(PRELUDE + NUMBYTES +
//                            RIGHT + rightDirection + getHexString(rightMagnitude));
//
//                }
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

    private void combineAndSend() {
        //this is stupid but 0 well
//        if (leftMagnitude == 0) {
//            ((CoolerActivity) getActivity()).sendMessage(PRELUDE + NUMBYTES +
//                    leftDirection + rightDirection + "00");
//        } else {
//        Log.d(TAG, "")
            ((CoolerActivity) getActivity()).sendMessage(PRELUDE + NUMBYTES +
                    leftDirection + rightDirection + getHexString(leftMagnitude) +
                    getHexString(rightMagnitude) + "/r/n");
//        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((CoolerActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
        handler.postDelayed(runnable, 100);

    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 100);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (getActivity() != null) {
                combineAndSend();
            }
            handler.postDelayed(this, 100);
        }
    };
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
            if (progress < zero) {
                short b = (short) progress;
                b -= (zero - 1);
                magnitude = Math.abs(b);
                direction = REVERSE;
            } else if (progress > zero) {
                magnitude = progress - (zero + 1);
            } else {
                magnitude = 0;
            }
            Log.d("debug", Integer.toString(magnitude));
            return this;
        }
    }
}
