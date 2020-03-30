package com.asadchattha.mediaplayer.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.asadchattha.mediaplayer.Fragments.FoldersFragment;
import com.asadchattha.mediaplayer.Fragments.VideosFragment;


public class AdapterForPager extends FragmentStatePagerAdapter {

    public AdapterForPager(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                VideosFragment videosFragment = new VideosFragment();
                return videosFragment;
            case 1:
                FoldersFragment foldersFragment = new FoldersFragment();
                return foldersFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Videos";
        } else if (position == 1) {
            return "Folders";
        } else {
            return null;
        }
    }
}
