package id.compunerd.silab.adapter;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import id.compunerd.silab.R;

public class ItemAdapter extends BaseAdapter {

    TextView text1, text2;
    ImageView imgView;
    String[][] data;
    Activity activity;


    public ItemAdapter(Activity activity, String[][] data) {
        this.data = data;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        
        if (v == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(activity);
            v = vi.inflate(R.layout.list_item, null);
        }

        Object p = getItem(position);

        if (p != null){
            imgView = (ImageView) v.findViewById(R.id.imgView);
            text1 = (TextView) v.findViewById(R.id.text1);
            text2 = (TextView) v.findViewById(R.id.text2);

            int id = activity.getResources().getIdentifier(data[position][2], "drawable", activity.getPackageName());
            Drawable drawable = activity.getResources().getDrawable(id);

            imgView.setImageDrawable(drawable);
            text1.setText(data[position][0]);
            text2.setText(data[position][1]);

        }


        return v;
    }
}
