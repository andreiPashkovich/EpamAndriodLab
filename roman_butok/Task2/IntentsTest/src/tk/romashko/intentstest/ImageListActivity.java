package tk.romashko.intentstest;

import java.util.ArrayList;
import java.util.Arrays;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class ImageListActivity extends Activity {
	
	private GridView gridView;	
	//Array with images ids
	private ArrayList<Integer> imageList = new ArrayList<Integer>(
			Arrays.asList(R.drawable.image1, R.drawable.image2,
					R.drawable.image3, R.drawable.image4, R.drawable.image5,
					R.drawable.image6, R.drawable.image7, R.drawable.image8,
					R.drawable.image9, R.drawable.image10, R.drawable.image11,
					R.drawable.image12));

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_list);
		
		gridView = (GridView) findViewById(R.id.grid_view);
		
		gridView.setAdapter(new ImageAdapter(this, imageList));
		
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				
			/*	Bitmap bitmap = BitmapFactory.decodeResource(getResources(), safeLongToInt(id));
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
				byte[] b = baos.toByteArray(); */
				
				int imageURI = safeLongToInt(id);
				
				Intent intent = new Intent(); 
				
				intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("android.resource://"+
						getPackageName()+"/"+imageURI));
				setResult(RESULT_OK, intent);
				finish();
			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image_list, menu);
		return true;
	}
	
	public static int safeLongToInt(long l) {
	    if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
	        throw new IllegalArgumentException
	            (l + " cannot be cast to int without changing its value.");
	    }
	    return (int) l;
	}

}
