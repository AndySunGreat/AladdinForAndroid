package com.aladdin.questionbank.home;

import com.aladdin.questionbank.R;
import com.aladdin.questionbank.common.ContentFragment;
import com.aladdin.questionbank.common.FriendFragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
/***
 * Home Screen Activity
 * 
 * @author AndySun
 *
 */

public class HomeActivity extends ActionBarActivity implements OnClickListener {
	 
	 private ImageButton mTabWeixin;
	 private ImageButton mTabFriend;
	 private ImageButton mTabDiscover;
	 private ImageButton mTabMe;
/*	 private Button mTabWeixin;
	 private Button mTabFriend;
	 private Button mTabDiscover;
	 private Button mTabMe;*/
	 private ContentFragment mWeiXinFragment;
	 private FriendFragment mFriendFragment;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.home);
		
		initView();
	}
	public void initView(){
		mTabWeixin = (ImageButton)findViewById(R.id.weixinButton);
		mTabFriend =  (ImageButton)findViewById(R.id.friendButton);
		mTabDiscover =  (ImageButton)findViewById(R.id.discoverButton);
		mTabMe =  (ImageButton)findViewById(R.id.meButton);
		// 设置默认的fragement
		setDefaultFragment();
	}
	
	public void setDefaultFragment(){
		FragmentManager fm = getFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		mWeiXinFragment = new ContentFragment();
		transaction.replace(R.id.id_content, mWeiXinFragment);
		transaction.commit();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		FragmentManager fm = getFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		switch(v.getId())
		{
			case R.id.weixinButton:
				if(mWeiXinFragment==null){
					mWeiXinFragment = new ContentFragment();
				}
				// 使用当前Fragment的布局替代id_content的控件 
				transaction.replace(R.id.id_content, mWeiXinFragment);
				break;
			case R.id.friendButton:
				if(mFriendFragment==null){
					mFriendFragment = new FriendFragment();
				}
				// 使用当前Fragment的布局替代id_content的控件 
				transaction.replace(R.id.id_content, mFriendFragment);
				break;
		}
		 // transaction.addToBackStack();  
        // 事务提交  
        transaction.commit();  
	}
	
	
	
}
