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

        songArray = new ArrayList();
        Song song = new Song(R.drawable.milky_chance, "Becoming", "Milky Chance", "unknown album");
        songArray.add(song);
        song = new Song(R.drawable.milky_chance, "Down by the river", "Milky Chance", "unknown album");
        songArray.add(song);
        song = new Song(R.drawable.milky_chance, "Fairytale", "Milky Chance", "unknown album");
        songArray.add(song);
        song = new Song(R.drawable.milky_chance, "Feathery", "Milky Chance", "unknown album");
        songArray.add(song);
        song = new Song(R.drawable.milky_chance, "Flashed Junk Mind", "Milky Chance", "unknown album");
        songArray.add(song);
        song = new Song(R.drawable.milky_chance, "Indigo", "Milky Chance", "unknown album");
        songArray.add(song);
        song = new Song(R.drawable.milky_chance, "Loveland", "Milky Chance", "unknown album");
//        songArray.add(song);
//        song = new Song(R.drawable.milky_chance, "Running", "Milky Chance", "unknown album");
//        songArray.add(song);
//        song = new Song(R.drawable.milky_chance, "Sadnecessary", "Milky Chance", "unknown album");
//        songArray.add(song);
//        song = new Song(R.drawable.milky_chance, "Stolen dance", "Milky Chance", "unknown album");
//        songArray.add(song);
//        song = new Song(R.drawable.milky_chance, "Stunner", "Milky Chance", "unknown album");
//        songArray.add(song);
//        song = new Song(R.drawable.milky_chance, "Sweet sun", "Milky Chance", "unknown album");
        songArray.add(song);
        song = new Song(R.mipmap.icon, "Blackbird", "Other", "unknown album");
        songArray.add(song);
        song = new Song(R.drawable.queen, "A Kind Of Magic", "Queen", "Greatest Hits");
        songArray.add(song);
        song = new Song(R.drawable.queen, "Another One Bites The Dust", "Queen", "Greatest Hits");
        songArray.add(song);
        song = new Song(R.drawable.queen, "Bicycle Race", "Queen", "Greatest Hits");
        songArray.add(song);
        song = new Song(R.drawable.queen, "Bohemian Rhapsody", "Queen", "Greatest Hits");
        songArray.add(song);
        song = new Song(R.drawable.queen, "Breakthru", "Queen", "Greatest Hits");
        songArray.add(song);
        song = new Song(R.drawable.queen, "Crazy Little Thing Called Love", "Queen", "Greatest Hits");
        songArray.add(song);
        song = new Song(R.drawable.queen, "Don't Stop Me Now", "Queen", "Greatest Hits");
        songArray.add(song);
        song = new Song(R.drawable.queen, "Fat Bottomed Girls", "Queen", "Greatest Hits");
        songArray.add(song);
        song = new Song(R.drawable.queen, "Flash", "Queen", "Greatest Hits");
        songArray.add(song);
        song = new Song(R.drawable.queen, "Friends Will Be Friends", "Queen", "Greatest Hits");
        songArray.add(song);
        song = new Song(R.drawable.queen, "Good Old Fashioned Lover Boy", "Queen", "Greatest Hits");
        songArray.add(song);
        song = new Song(R.drawable.queen, "Hammer To Fall", "Queen", "Greatest Hits");
        songArray.add(song);
        song = new Song(R.drawable.queen, "Headlong", "Queen", "Greatest Hits");
        songArray.add(song);
        song = new Song(R.drawable.queen, "I Want It All", "Queen", "Greatest Hits");
        songArray.add(song);
        song = new Song(R.drawable.queen, "I Want To Break Free", "Queen", "Greatest Hits");
        songArray.add(song);
        song = new Song(R.drawable.queen, "I'm Going Slightly Mad", "Queen", "Greatest Hits");
        songArray.add(song);
        song = new Song(R.drawable.queen, "Innuendo", "Queen", "Greatest Hits");
        songArray.add(song);
        song = new Song(R.drawable.queen, "It's A Hard Life", "Queen", "Greatest Hits");
        songArray.add(song);
        song = new Song(R.drawable.queen, "Killer Queen", "Queen", "Greatest Hits");
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
                ((CoolerActivity) getActivity()).sendMessage(selectedSong.getTitle());
//                ((CoolerActivity)getActivity()).sendMessage("B" + "/r/n");
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
