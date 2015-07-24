package com.android.remotecontrolcooler;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by nickc on 7/18/15.
 */

public class ControlMusicFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private ArrayList songArray;
    ListView listSongs;
    public static ControlMusicFragment newInstance(int sectionNumber) {
        ControlMusicFragment fragment = new ControlMusicFragment();
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

        Song song = new Song(R.mipmap.icon, "crappy song", "Justin Bieber", "some album");
        songArray = new ArrayList();
        songArray.add(song);
        songArray.add(song);
        songArray.add(song);
        songArray.add(song);
        songArray.add(song);
        songArray.add(song);
        songArray.add(song);
        songArray.add(song);
        songArray.add(song);
        songArray.add(song);
        songArray.add(song);
        songArray.add(song);
        songArray.add(song);
        songArray.add(song);
        songArray.add(song);
        songArray.add(song);
        songArray.add(song);
        songArray.add(song);

        // Get the ListView by Id and instantiate the adapter with
        // cars data and then set it the ListView
        listSongs = (ListView) rootView.findViewById(R.id.list_songs);
        MusicListAdapter adapter = new MusicListAdapter(getActivity(), songArray);
        listSongs.setAdapter(adapter);
        // Set the onItemClickListener on the ListView to listen for items clicks
        listSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song selectedSong = (Song) songArray.get(position);
//                ((CoolerActivity)getActivity()).sendMessage(selectedSong.getTitle());
                ((CoolerActivity)getActivity()).sendMessage("B" + "/r/n");
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
