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
    
	private LocalFragment localFragment ;
	private RemoteFragment remoteFragment ;
	
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
        localFragment = new LocalFragment();
        remoteFragment = new RemoteFragment();
        setUpActionBar();
        setUpViewPager();
        setUpTabs();
    }
    
    @Override
    public void onResume(){
    	super.onResume();
//    	 localFragment = new LocalFragment();
//         remoteFragment = new RemoteFragment();
//         setUpActionBar();
//         setUpViewPager();
//         setUpTabs();
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
     					break;
    				case ViewPager.SCROLL_STATE_DRAGGING:
     					break;
    				case ViewPager.SCROLL_STATE_SETTLING:
     					break;
    				default:
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