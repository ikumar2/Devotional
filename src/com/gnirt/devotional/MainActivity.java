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
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Process;
import android.os.RemoteException;
import android.support.v7.app.NotificationCompat;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.GestureDetector;
// import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
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
	private MediaPlayer player = null;
    private ImageButton	btplay;
    static private int x=0;
    static private int y=0;
    static private int z=1;
    // Media Button initialization - IA
    
    private Button playbutton;
 //   private Button pausebutton;
    private Button play1button;
    private Button pause1button;
    private Button replay1button;
    private Button replaystop1button;
    boolean rotate=true;
    // Flowers Animation variable initialization - VT
    
    private ImageView flowerImage1;
	private ImageView flowerImage2;
	private ImageView flowerImage3;
	private ImageView flowerImage4;
	private ImageView flowerImage5;
	private ImageView flowerImage6;
	private Animation flowerAnimation;
	private Animation flowerAnimation1;
	private Button flowerButton;
	
	//Setting up NotificationID - VT
	private static int notificationId = 1;
	
	// Phone listner
	
	private PhoneStateListener mPhoneListener;
	
	@SuppressWarnings("deprecation")
	private final GestureDetector detector = new GestureDetector(new SwipeGestureDetector());

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

	
/*	int id = android.os.Process.myPid();
			System.out.println("PID" +android.os.Process.myPid());
		if(id!=0)
		{
			initNotification();
		}*/
	
		// Audio Code	- IA
		
		// MediaPlayer player;
		AssetFileDescriptor afd;
		try {
		// Read the music file from the asset folder
		afd = getAssets().openFd("hanumanchalisa.mp3");
		// Creation of new media player;
		player = new MediaPlayer();
		// Set the player music source.
		if(!player.isPlaying());{
		System.out.println("Player is not playing" +player.isPlaying());
		
		player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(),afd.getLength());
		
		System.out.println("AFD length" +afd.getLength());
		// Set the looping and play the music.
		//player.setLooping(true);
		player.prepare();
		
		//By default it is going to playing on opening application.
		//player.start();
	//	player.pause();
		
		
		}} catch (IOException e) {
		e.printStackTrace();
		
		}
		
		
	
		// Handle phone call and sync audio and rotation of images
		
		TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		mPhoneListener = new PhoneStateListener() {		
			 @Override
		    public void onCallStateChanged(int state, String incomingNumber) {
					System.out.println("Intial phone" +state + " " +incomingNumber);
		        //try {
		            switch (state) {
		            case TelephonyManager.CALL_STATE_RINGING:
		            	System.out.println("Ringing" +state);
		            	if(mViewFlipper.isFlipping()){
		            	mViewFlipper.stopFlipping();
		            	// pausebutton.setVisibility(Button.GONE);
						playbutton.setVisibility(Button.VISIBLE);}
		            	if (player.isPlaying()){
		            	 player.pause();
						 pause1button.setVisibility(Button.GONE);
						 play1button.setVisibility(Button.VISIBLE);
						 System.out.println("Phone pause");
		            	}
		                break;

		            case TelephonyManager.CALL_STATE_OFFHOOK:
		            	System.out.println("CALL_STATE_OFFHOOK" +state);
		                // do something...
		            	if(mViewFlipper.isFlipping()){
		            	mViewFlipper.stopFlipping();
		            	// pausebutton.setVisibility(Button.GONE);
						playbutton.setVisibility(Button.VISIBLE);
		            	}
		            	if (player.isPlaying()){
		            	player.pause();
						 pause1button.setVisibility(Button.GONE);
						 play1button.setVisibility(Button.VISIBLE);
						 System.out.println("Phone1 pause");
		            	}
					/*	 Intent startMain = new Intent(Intent.ACTION_MAIN);
		                    startMain.addCategory(Intent.CATEGORY_HOME);
		                    startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		                    activity.startActivity(startMain);*/
		            	break;

		            case TelephonyManager.CALL_STATE_IDLE:
		            	System.out.println("CALL_STATE_IDLE" +state);
		            	
		            	if (z!=0){
		            	mViewFlipper.startFlipping();}
		            //	pausebutton.setVisibility(Button.VISIBLE);
					//	playbutton.setVisibility(Button.GONE);
		            	
		                if (x!=0 && y!=0) {
		                	player.start();
		            	play1button.setVisibility(Button.GONE); 
						pause1button.setVisibility(Button.VISIBLE);  
						 System.out.println("Phone2 pause");
						 // y=0;
		                }
		            	break;
		            default:
		                System.out.println("Unknown phone state=" + state);
		            }
		        } //catch (RemoteException e) {}
		   // } 
		};
		tm.listen(mPhoneListener, PhoneStateListener.LISTEN_CALL_STATE);
	//	tm.listen(mPhoneListener, PhoneStateListener.LISTEN_CALL_STATE);	
	
		
		//Music player action of buttons - IA
		
		
		play1button = (Button)findViewById(R.id.play1);
	    play1button.setVisibility(Button.VISIBLE);
		pause1button = (Button)findViewById(R.id.pause1);
		pause1button.setVisibility(Button.GONE);
		System.out.println("On first time pause gone");
		 findViewById(R.id.pause1).setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					// Cancel Notification
					 cancelNotification();
					player.pause();
					pause1button.setVisibility(Button.GONE);
					play1button.setVisibility(Button.VISIBLE);
					y=0;
					System.out.println("On second time pause gone" +y);
					
					// player.release();
				}	
				});
		 
		
	            findViewById(R.id.play1).setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					// Initialize Notification
			    	initNotification();
					player.start();
					play1button.setVisibility(Button.GONE);
					pause1button.setVisibility(Button.VISIBLE);
					x=1;
					y=1;
					System.out.println("paly" +x  +" " +y);
				}
				
				});
	           
	          //Music player Replay buttons Action - IA
	            
	            replay1button = (Button)findViewById(R.id.replay1);
	    	//	replay1button.setVisibility(Button.GONE);
	    		replaystop1button = (Button)findViewById(R.id.replaystop1);
	    		replaystop1button.setVisibility(Button.GONE);
	    		// replaystop1button.setVisibility(Button.GONE);
	    		 findViewById(R.id.replaystop1).setOnClickListener(new OnClickListener() {
	    				@Override
	    				public void onClick(View view) {
	    				player.setLooping(false);
	    					 replaystop1button.setVisibility(Button.GONE);
	    					 replay1button.setVisibility(Button.VISIBLE);
	    					// System.out.println("replay false" + false);
	    					
	    				}	
	    				});
	    		 
	    		
	    	            findViewById(R.id.replay1).setOnClickListener(new OnClickListener() {
	    				@Override
	    				public void onClick(View view) {
	    					player.setLooping(true);
	    					replay1button.setVisibility(Button.GONE);
	    					replaystop1button.setVisibility(Button.VISIBLE);
	    				//	 System.out.println("replay true" + true);
	    				}
	    				
	    				});
	    	           // Play button should visible once hanuman chalisa would be completed
	    	            
	    	            player.setOnCompletionListener(new OnCompletionListener() {
	    	            		
	    	            	@Override
	    	            	public void onCompletion(MediaPlayer player) {
	    	            		if (!player.isLooping()){
	    	            			System.out.println("player looping status" + !player.isLooping());
	    	            			 pause1button.setVisibility(Button.GONE);
	    	    					 play1button.setVisibility(Button.VISIBLE);
	    	    					 y=0;
	    	    					 System.out.println("On third time pause gone");
	    	            		}
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
				System.out.println(event +"screen touch for flipping");
				if (mViewFlipper.isFlipping()){
					mViewFlipper.stopFlipping();
				//	pausebutton.setVisibility(Button.GONE);
					playbutton.setVisibility(Button.VISIBLE);
					System.out.println(mViewFlipper.isFlipping() +"stop flipping");
					z=0;
				}
			/*	else {
					//mViewFlipper.setAutoStart(true);
					//mViewFlipper.setFlipInterval(2000);
					mViewFlipper.startFlipping();
					playbutton.setVisibility(Button.GONE);
					pausebutton.setVisibility(Button.VISIBLE);
					System.out.println(mViewFlipper.isFlipping() +"start flipping");
				}*/
			/*	if(event.getAction()==0){
				rotate=!rotate;
				}*/
				return true;
				
			}
			
		}
		
		  );
		
		// Old code for handling images start and stop via button - IA
		
	  mViewFlipper.setAutoStart(true);
	//  initNotification();
    playbutton = (Button)  findViewById(R.id.play);
     playbutton.setVisibility(Button.GONE);
    
			 playbutton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				//sets auto flipping
				if(!mViewFlipper.isFlipping()){
				mViewFlipper.setAutoStart(true);
				mViewFlipper.setFlipInterval(2000);
				mViewFlipper.startFlipping();
				playbutton.setVisibility(Button.GONE);
			//	pausebutton.setVisibility(Button.VISIBLE);
				z=1;
			}}
		});

/*	pausebutton= (Button)findViewById(R.id.pause);*/
//	pausebutton.setVisibility(Button.GONE);
/*    pausebutton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				//stop auto flipping 
				if(mViewFlipper.isFlipping()){
				mViewFlipper.stopFlipping();
				pausebutton.setVisibility(Button.GONE);
				playbutton.setVisibility(Button.VISIBLE);
				z=0;
			}}
		});*/
		
	
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
				flowerImage5 = (ImageView) findViewById(R.id.flower5);
				flowerAnimation = AnimationUtils.loadAnimation(this, R.anim.flower);
				
				flowerImage2 = (ImageView) findViewById(R.id.flower2);
				flowerImage4 = (ImageView) findViewById(R.id.flower4);
				flowerImage6 = (ImageView) findViewById(R.id.flower6);
				flowerAnimation1 = AnimationUtils.loadAnimation(this, R.anim.flower1);
				
				// Initial call make image invisble - VT
				flowerImage1.setVisibility(View.INVISIBLE);
				flowerImage2.setVisibility(View.INVISIBLE);
				flowerImage3.setVisibility(View.INVISIBLE);
				flowerImage4.setVisibility(View.INVISIBLE);
				flowerImage5.setVisibility(View.INVISIBLE);
				flowerImage6.setVisibility(View.INVISIBLE);
				
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
			
				//Commented Add for lunching
				/*	AdView layout = (AdView) findViewById(R.id.adView);
				AdView adView = new AdView(this, AdSize.BANNER, "a14ff402be4457c");
			    layout.addView(adView);
		 
			    AdRequest adRequest = new AdRequest();
			    adRequest.addTestDevice(AdRequest.TEST_EMULATOR);
				adView.loadAd(adRequest);*/
				
		
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
			fAnimation1(flowerImage5);
			fAnimation1(flowerImage2);
			fAnimation1(flowerImage4);
			fAnimation1(flowerImage6);
			
		}
		
	}
	
	// Create Notification -- VT
	private void initNotification() {

	    Intent intent = new Intent(this, MainActivity.class);
	    Context context = getApplicationContext();

	    PendingIntent penInt = PendingIntent.getActivity(context, 0, intent, 0);

	    long when = System.currentTimeMillis();
	    NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
	    builder.setContentTitle("Tring Hanuman Chalisa");
	    builder.setContentText("Active....");
	    builder.setSmallIcon(R.drawable.tring_devotion_hanuman);
	    builder.setTicker("Media  Playing...");
	    builder.setContentIntent(penInt);
	    NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
	    notificationManager.notify(notificationId, builder.build());

	}

	private void cancelNotification() {
	    NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
	    notificationManager.cancel(notificationId);
	   // notificationManager.cancelAll();
	}


/*	
public void onDestroy()
{
	Process.killProcess(android.os.Process.myPid());
	cancelNotification();
	System.out.println("Destroy App");
}*/
}