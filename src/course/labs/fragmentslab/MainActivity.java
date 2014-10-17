package course.labs.fragmentslab;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity implements
		FriendsFragment.SelectionListener {

	private static final String TAG = "Lab-Fragments";

	private FriendsFragment mFriendsFragment;
	private FeedFragment mFeedFragment;

    private static final String FEED_KEY = "feed";
    private int mLastSelectedPosition = 0;


    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		Log.i(TAG, "Entered MainActivity onCreate()");
		
		if (savedInstanceState == null) {
			// If the layout is single-pane, create the FriendsFragment 
			// and add it to the Activity
	
			if (!isInTwoPaneMode()) {
				
				mFriendsFragment = new FriendsFragment();
	
				//TODO 1 - add the FriendsFragment to the fragment_container
				getFragmentManager().beginTransaction().add(R.id.fragment_container, mFriendsFragment).commit();
				
	
			} else {
	
				// Otherwise, save a reference to the FeedFragment for later use
	
				mFeedFragment = (FeedFragment) getFragmentManager()
						.findFragmentById(R.id.feed_frag);
			}
		}
        else {
            // Restore last selected feed from saved instance
            Log.i(TAG, "Start restoring previous selected Feed");
            mLastSelectedPosition = savedInstanceState.getInt(FEED_KEY);
            Log.i(TAG, "Previous selected Feed index was " + mLastSelectedPosition);
/*            FragmentManager fragmentManager = getFragmentManager();
            mFeedFragment = (FeedFragment) fragmentManager.findFragmentById(R.id.feed_frag);
            if (mLastSelectedPosition != 0)
                mFeedFragment.updateFeedDisplay(mLastSelectedPosition); */
        }

	}

	// If there is no fragment_container ID, then the application is in
	// two-pane mode

	private boolean isInTwoPaneMode() {

		return findViewById(R.id.fragment_container) == null;
	
	}

	// Display selected Twitter feed

	public void onItemSelected(int position) {

		Log.i(TAG, "Entered onItemSelected(" + position + ")");

		// If there is no FeedFragment instance, then create one

		if (mFeedFragment == null)
			mFeedFragment = new FeedFragment();

		// If in single-pane mode, replace single visible Fragment

		if (!isInTwoPaneMode()) {

			//TODO 2 - replace the fragment_container with the FeedFragment
			getFragmentManager().beginTransaction().replace(R.id.fragment_container, mFeedFragment).addToBackStack(null).commit();
						

			// execute transaction now
			getFragmentManager().executePendingTransactions();

		}

		// Update Twitter feed display on FriendFragment
		mFeedFragment.updateFeedDisplay(position);
        mLastSelectedPosition = position;

	}

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        Log.i(TAG, "Entered onSaveInstanceState");

        // save the current foreground feed
        savedInstanceState.putInt(FEED_KEY, mLastSelectedPosition);
        Log.i(TAG, "Saved feed index = " + mLastSelectedPosition);
        // as recommended by android basics training, always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);

    }

}
