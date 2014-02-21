package by.stanislav.helloworld;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements AnimationListener, OnClickListener {

	private TextView helloWorld;
	private Button startAnim;
	private Typeface fontType;
	private Animation animation;
	public static final String TAG = "Animation state: "; 
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		fontType = Typeface.createFromAsset(
				getApplicationContext().getAssets(), Constants.FONT);
		animation = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.together);
		helloWorld = (TextView) findViewById(R.id.helloWorld);
		startAnim = (Button)findViewById(R.id.startAnim);
		animation.setAnimationListener(this);
		startAnim.setOnClickListener(this);
		helloWorld.setTypeface(fontType);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onAnimationStart(Animation animation) {
		if (animation == this.animation) {
			Log.d(TAG, "Animation started");
		}
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		if (animation == this.animation) {
			Log.d(TAG, "Animation ended");
		}
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		if (animation == this.animation) {
			Log.d(TAG, "Animation repeated");
		}
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.startAnim:
			helloWorld.startAnimation(animation);
			break;
		}
	}
}
