package com.gnirt.devotional;
/*
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;*/

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

public class MainActivity extends Activity implements OnClickListener {

	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;
	private ViewFlipper mViewFlipper;	
	private AnimationListener mAnimationListener;
	private Context mContext;
	private MediaPlayer player;
    private ImageButton	btplay;
    
    // Media Button initialization - IA
    
    private Button playbutton;
    private Button pausebutton;
    private Button play1button;
    private Button pause1button;
    boolean rotate=true;
    // Flowers Animation variable initialization - VT
    
    private ImageView flowerImage1;
	private ImageView flowerImage2;
	private ImageView flowerImage3;
	private ImageView flowerImage4;
	private Animation flowerAnimation;
	private Animation flowerAnimation1;
	private Button flowerButton;
	
	@SuppressWarnings("deprecation")
	private final GestureDetector detector = new GestureDetector(new SwipeGestureDetector());

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Audio Code	- IA
		
		// MediaPlayer player;
		AssetFileDescriptor afd;
		try {
		// Read the music file from the asset folder
		afd = getAssets().openFd("hanumanchalisa.mp3");
		// Creation of new media player;
		player = new MediaPlayer();
		// Set the player music source.
		player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(),afd.getLength());
		
		// Set the looping and play the music.
		player.setLooping(true);
		player.prepare();
		
		//By default it is going to playing on opening application.
		//player.start();
		// player.stop();
		} catch (IOException e) {
		e.printStackTrace();
		}
	
		//Music player action of buttons - IA
		
		
		play1button = (Button)findViewById(R.id.play1);
		// play1button.setVisibility(Button.GONE);
		pause1button = (Button)findViewById(R.id.pause1);
		pause1button.setVisibility(Button.GONE);
		 findViewById(R.id.pause1).setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					player.pause();
					 pause1button.setVisibility(Button.GONE);
					 play1button.setVisibility(Button.VISIBLE);
				}	
				});
		 
		
	            findViewById(R.id.play1).setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					player.start();
					play1button.setVisibility(Button.GONE);
					pause1button.setVisibility(Button.VISIBLE);
					System.out.println("paly");
				}
				
				});
	           

		
	//  ViewFliper code	Via touch base handling stop and start - IA
		mContext = this;
		Activity mActivity = this;
		mViewFlipper = (ViewFlipper) this.findViewById(R.id.view_flipper);
		mViewFlipper.setOnTouchListener(new OnTouchListener()
		{
			
			
			@Override
			public boolean onTouch(final View view, final MotionEvent event) {
				detector.onTouchEvent(event);

				if (rotate){
					mViewFlipper.stopFlipping();
					rotate=!rotate;
				}
				else {
					mViewFlipper.setAutoStart(true);
					mViewFlipper.setFlipInterval(2000);
					mViewFlipper.startFlipping();
					rotate=!rotate;
				}
				if(event.getAction()==0){
				rotate=!rotate;
				}
				return true;
				
			}
			
		}
		
		  );
		
		// Old code for handling images start and stop via button - IA
		
	  mViewFlipper.setAutoStart(true);
    // playbutton = (Button)  findViewById(R.id.play);
     // playbutton.setVisibility(Button.GONE);
    
	/*		 playbutton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				//sets auto flipping
				mViewFlipper.setAutoStart(true);
				mViewFlipper.setFlipInterval(2000);
				mViewFlipper.startFlipping();
				playbutton.setVisibility(Button.GONE);
				pausebutton.setVisibility(Button.VISIBLE);
			}
		});*/

	//pausebutton= (Button)findViewById(R.id.stop);
	//pausebutton.setVisibility(Button.GONE);
	/*pausebutton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				//stop auto flipping 
				mViewFlipper.stopFlipping();
				pausebutton.setVisibility(Button.GONE);
				playbutton.setVisibility(Button.VISIBLE);
			}
		});
		*/
	
		//animation listener
		mAnimationListener = new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
				//animation started event
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				//TODO animation stopped event
			}
		};
		
		
		// Initialize rose flower petal or leaf image - VT
				flowerButton = (Button) findViewById(R.id.flwButton);
				flowerImage1 = (ImageView) findViewById(R.id.flower1);
				flowerImage3 = (ImageView) findViewById(R.id.flower3);
				flowerAnimation = AnimationUtils.loadAnimation(this, R.anim.flower);
				
				flowerImage2 = (ImageView) findViewById(R.id.flower2);
				flowerImage4 = (ImageView) findViewById(R.id.flower4);
				flowerAnimation1 = AnimationUtils.loadAnimation(this, R.anim.flower1);
				
				// Initial call make image invisble - VT
				flowerImage1.setVisibility(View.INVISIBLE);
				flowerImage2.setVisibility(View.INVISIBLE);
				flowerImage3.setVisibility(View.INVISIBLE);
				flowerImage4.setVisibility(View.INVISIBLE);
				
				flowerButton.setOnClickListener(this);		
			
		// Google ad View java code - VT
				
				/* AdView mAdView = (AdView) findViewById(R.id.adView);
		        //AdRequest adRequest = new AdRequest.Builder().build();
		        AdRequest request = new AdRequest()
		                .addTestDevice(AdRequest.TEST_EMULATOR	);        // All emulators
		                //.addTestDevice("AC98C820A50B4AD8A2106EDE96FB87D4");  // My Galaxy Nexus test phone
		                //.build();
		        mAdView.loadAd(request);		
		        */
				//LinearLayout layout = (LinearLayout) findViewById(R.id.adView);
				AdView layout = (AdView) findViewById(R.id.adView);
				AdView adView = new AdView(this, AdSize.BANNER, "a14ff402be4457c");
			    layout.addView(adView);
		 
			    AdRequest adRequest = new AdRequest();
			    adRequest.addTestDevice(AdRequest.TEST_EMULATOR);
				adView.loadAd(adRequest);
				
		
	}

// Image swipe and handle next and previous via swipegeturedetector - IA
	class SwipeGestureDetector extends SimpleOnGestureListener {
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			try {
				// right to left swipe
				if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_in));
					mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_out));
					// controlling animation
					mViewFlipper.getInAnimation().setAnimationListener(mAnimationListener);
					mViewFlipper.showNext();
					System.out.println("Next screen");
					return true;
				} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.right_in));
					mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext,R.anim.right_out));
					// controlling animation
					mViewFlipper.getInAnimation().setAnimationListener(mAnimationListener);
					mViewFlipper.showPrevious();
					System.out.println("Previous screen");
					return true;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			return false;
		}
		
	}
	
	
	public void fAnimation(ImageView FI){
		FI.setVisibility(View.VISIBLE);
		FI.startAnimation(flowerAnimation);
		FI.setVisibility(View.INVISIBLE);
	}
	
	public void fAnimation1(ImageView FI){
		FI.setVisibility(View.VISIBLE);
		FI.startAnimation(flowerAnimation1);
		FI.setVisibility(View.INVISIBLE);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if (v == flowerButton ){
		/*	fAnimation(flowerImage4);
			fAnimation(flowerImage3);
			fAnimation1(flowerImage1);
			fAnimation1(flowerImage2);
		*/
			
			fAnimation(flowerImage1);
			fAnimation(flowerImage3);
			fAnimation1(flowerImage2);
			fAnimation1(flowerImage4);
			
		}
		
	}
	

}
	
