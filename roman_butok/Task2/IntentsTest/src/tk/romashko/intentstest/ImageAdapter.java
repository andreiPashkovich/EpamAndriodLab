package tk.romashko.intentstest;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	private Context imContext;
	private List<Integer> imageList;
	
	public ImageAdapter(Context c, List<Integer> list) {
		imContext = c;
		this.imageList = list;
	}

	@Override
	public int getCount() {
		return imageList.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return imageList.get(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView = (ImageView) convertView;

		if (imageView == null) {
			imageView = new ImageView(imContext);
			imageView.setLayoutParams(new GridView.LayoutParams(250, 250));
			imageView.setPadding(5, 5, 5, 5);
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		}

		imageView.setImageResource(imageList.get(position));
		return imageView;
	}

}
