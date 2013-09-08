package com.chendong.music;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity 
				implements ActionBar.TabListener{
    
	private LocalFragment localFragment = new LocalFragment();
	private RemoteFragment remoteFragment = new RemoteFragment();
	
	private static final int TAB_INDEX_COUNT = 2;
	
	private static final int TAB_INDEX_ONE = 0;
	private static final int TAB_INDEX_TWO = 1;
	
	private ViewPager mViewPager;
	private ViewPagerAdapter mViewPagerAdapter;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        setUpActionBar();
        setUpViewPager();
        setUpTabs();
    }
    
    private void setUpActionBar() {
    	final ActionBar actionBar = getActionBar();
    	actionBar.setHomeButtonEnabled(false);
    	actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//    	actionBar.setDisplayShowTitleEnabled(false);
//    	actionBar.setDisplayShowHomeEnabled(false);
    	
    	
    }
    
    private void setUpViewPager() {
    	mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
    	
    	mViewPager = (ViewPager)findViewById(R.id.pager);
    	mViewPager.setAdapter(mViewPagerAdapter);
    	mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
    		@Override
    		public void onPageSelected(int position) {
    			final ActionBar actionBar = getActionBar();
    			actionBar.setSelectedNavigationItem(position);
    		}
    		
    		@Override
    		public void onPageScrollStateChanged(int state) {
    			switch(state) {
    				case ViewPager.SCROLL_STATE_IDLE:
    					//TODO
    					break;
    				case ViewPager.SCROLL_STATE_DRAGGING:
    					//TODO
    					break;
    				case ViewPager.SCROLL_STATE_SETTLING:
    					//TODO
    					break;
    				default:
    					//TODO
    					break;
    			}
    		}
    	});
    }
    
    private void setUpTabs() {
    	final ActionBar actionBar = getActionBar();
    	for (int i = 0; i < mViewPagerAdapter.getCount(); ++i) {
    		actionBar.addTab(actionBar.newTab()
    				.setText(mViewPagerAdapter.getPageTitle(i))
    				.setTabListener(this));
    	}
    	
    	
//    	int actionBarTitleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
//    	if (actionBarTitleId > 0) {
//    	    TextView title = (TextView) findViewById(actionBarTitleId);
//    	    if (title != null) {
//    	        title.setTextColor(Color.RED);
//    	    }
//    	}
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    }
    
    public class ViewPagerAdapter extends FragmentPagerAdapter {

		public ViewPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// TODO Auto-generated method stub
			switch (position) {
				case TAB_INDEX_ONE:
					return localFragment;
				case TAB_INDEX_TWO:
					return remoteFragment; 
			}
			throw new IllegalStateException("No fragment at position " + position);
		}

		@Override
		public int getCount() {
			return TAB_INDEX_COUNT;
		}
    	
		@Override
		public CharSequence getPageTitle(int position) {
			String tabLabel = null;
			switch (position) {
				case TAB_INDEX_ONE:
					tabLabel = getString(R.string.tab_local);
					break;
				case TAB_INDEX_TWO:
					tabLabel = getString(R.string.tab_remote);
					break; 
			}
			return tabLabel;
		}
    }

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
}