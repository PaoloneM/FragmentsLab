package course.labs.fragmentslab;

import course.labs.fragmentslab.FriendsFragment.SelectionListener;
import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class FeedFragment extends Fragment {

	private static final String TAG = "Lab-Fragments";
    private static final String FEED_KEY = "feed";

	private TextView mTextView;
	private static FeedFragmentData feedFragmentData;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.feed, container, false);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		Log.i(TAG, "Entered FeedFragment onActivityCreated()");
		// Read in all Twitter feeds 
		if (null == feedFragmentData) { 
			
			feedFragmentData = new FeedFragmentData(getActivity());

		}
		
		if (savedInstanceState != null){
			Log.i(TAG, "Entered FeedFragment onActivityCreated() restore function");
			mTextView = (TextView) getView().findViewById(R.id.feed_view);
			mTextView.setText(savedInstanceState.getString(FEED_KEY));
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "Entered FeedFragment onCreate()");
	}

		
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.i(TAG, "Entered FeedFragment onAttach()");
	}

	// Display Twitter feed for selected feed

	void updateFeedDisplay(int position) {

		Log.i(TAG, "Entered updateFeedDisplay()");
				
		mTextView = (TextView) getView().findViewById(R.id.feed_view);
		mTextView.setText(feedFragmentData.getFeed(position));

	}
	
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        Log.i(TAG, "Entered FeedFragment onSaveInstanceState");

        // save the current foreground feed
        savedInstanceState.putString(FEED_KEY, (String) mTextView.getText());
        Log.i(TAG, "Saved feed Text" );
        // as recommended by android basics training, always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);

    }


}
