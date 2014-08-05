package com.cloay.ctagviewdemo.widget;

import com.cloay.ctagviewdemo.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * 
 * @ClassName: CTagView 
 * @Description: A costum view for tag list 
 * @author cloay Email:shangrody@gmail.com 
 * @date 2014-8-5 ÉÏÎç10:43:22 
 *
 */
public class CTagView extends LinearLayout {
	private final int DEFAULT_TAG_HEIGHT = 35;
	private final int DEFAULT_BUTTON_MARGIN = 12;
	private final int DEFAULT_BUTTON_PADDING = 12;
	private final int DEFAULT_BUTTON_PADDING_TOP = 6;
	private final int DEFAULT_LAYOUT_MARGIN_TOP = 12;
	
	private Context mContext;
	private OnTagClickListener onTagClickerListener;
	
	public CTagView(Context context) {
		super(context);
		mContext = context;
		setOrientation(VERTICAL);		//set orientation vertical
	}
	
	public CTagView(Context context, AttributeSet attrs){
		super(context, attrs);
		mContext = context;
	}
	
	@SuppressLint("NewApi")
	public CTagView(Context context, AttributeSet attrs, int defStyle){
		super(context, attrs, defStyle);
		mContext = context;
	}

	/**
	 * 
	 * @Name: setTags 
	 * @Description:  
	 * @param tags
	 * @return void  
	 *
	 */
	@SuppressWarnings("deprecation")
	public void setTags(String []tags){
		removeAllViews();	//remove all old views, at first
		if(tags.length > 0){
			LinearLayout tempLayout  = new LinearLayout(mContext);
			tempLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			addView(tempLayout);
			int tempWidth = 0;
			for(int i = 0; i < tags.length; i++){
				final String tagStr = tags[i];
				Button button = new Button(mContext);
				button.setText(tagStr);
				button.setTextColor(Color.parseColor("#666666"));
				button.setTextSize(15);
				button.setPadding(dip2px(DEFAULT_BUTTON_PADDING), dip2px(DEFAULT_BUTTON_PADDING_TOP), 
						dip2px(DEFAULT_BUTTON_PADDING), dip2px(DEFAULT_BUTTON_PADDING_TOP));
				button.setBackgroundDrawable(getResources().getDrawable(R.drawable.tag_bg));
				button.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						if(onTagClickerListener != null)
							onTagClickerListener.onTagClick(tagStr);
					}
				});
				
				int btnWidth = (int) (2*dip2px(DEFAULT_BUTTON_PADDING) + button.getPaint().measureText(button.getText().toString()));
				LayoutParams layoutParams = new LayoutParams(btnWidth, dip2px(DEFAULT_TAG_HEIGHT));
				layoutParams.leftMargin = dip2px(DEFAULT_BUTTON_MARGIN);
				tempWidth += dip2px(DEFAULT_BUTTON_MARGIN) + btnWidth; //add tag width
				Log.v("CTagView", "width:" + tempWidth);
				//the last tag margin right DEFAULT_BUTTON_MARGIN, don't forget
				if(tempWidth + dip2px(DEFAULT_BUTTON_MARGIN) > getDeviceWidth()){  //if out of screen, add a new layout 
					tempLayout  = new LinearLayout(mContext);
					LayoutParams lParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
					lParams.topMargin = dip2px(DEFAULT_LAYOUT_MARGIN_TOP);
					tempLayout.setLayoutParams(lParams);
					addView(tempLayout);
					tempWidth = (int) (2*dip2px(DEFAULT_BUTTON_PADDING) + button.getPaint().measureText(button.getText().toString()));
				}
				tempLayout.addView(button, layoutParams);
				Log.v("CTagView", "button:" + button.getWidth());
			}
		}else{
			Button button = new Button(mContext);
			button.setText("No tags");
			button.setTextColor(Color.parseColor("#666666"));
			button.setTextSize(15);
			button.setPadding(dip2px(DEFAULT_BUTTON_PADDING), dip2px(DEFAULT_BUTTON_PADDING_TOP), 
					dip2px(DEFAULT_BUTTON_PADDING), dip2px(DEFAULT_BUTTON_PADDING_TOP));
			button.setBackgroundDrawable(getResources().getDrawable(R.drawable.tag_bg));
			LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, dip2px(DEFAULT_TAG_HEIGHT));
			layoutParams.leftMargin = dip2px(DEFAULT_BUTTON_MARGIN);
			addView(button, layoutParams);
		}
	}
	
	/**
	 * 
	 * @Name: setOnTagClickListener 
	 * @Description:  
	 * @param listener
	 *
	 */
	public void setOnTagClickListener(OnTagClickListener listener){
		onTagClickerListener = listener;
	}
	
	/**
	 * 
	 * @ClassName: TagClickerListener 
	 * @Description: this is a interface 
	 * @author cloay Email:shangrody@gmail.com 
	 * @date 2014-8-5 ÉÏÎç10:40:07 
	 *
	 */
	public interface OnTagClickListener{ 
		/**
		 * 
		 * @Name: onTagClick
		 * @Description: Callback this after tag was clicked 
		 * @param tag
		 *
		 */
		public void onTagClick(String tag);
		
	}
	
	/**
	 * 
	 * @Name: getDeviceWidth 
	 * @Description: get device width
	 * @return int    
	 *
	 */
	private int getDeviceWidth(){
		DisplayMetrics metrics = new DisplayMetrics();
		((Activity)mContext).getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return metrics.widthPixels;
	}
	
	/**
	 * 
	 * @Name: dip2px 
	 * @Description: dip to px
	 * @param dipValue
	 * @return int  
	 *
	 */
	private int dip2px(float dipValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
