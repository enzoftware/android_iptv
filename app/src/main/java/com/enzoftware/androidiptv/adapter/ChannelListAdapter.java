package com.enzoftware.androidiptv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.enzoftware.androidiptv.R;
import com.enzoftware.androidiptv.m3u.ChannelItem;
import com.enzoftware.androidiptv.m3u.ChannelList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ChannelListAdapter extends BaseAdapter implements Filterable {
    private Picasso picasso;
    private LayoutInflater mInflater;
    private ArrayList<ChannelItem> originalChannelList, filteredChannelList;
    private Context mContext;
    private ChannelFilter filter = new ChannelFilter();

    public ChannelListAdapter(@NonNull Context context, ChannelList cl) {
        mContext = context;
        originalChannelList = cl.items;
        filteredChannelList = cl.items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        picasso = Picasso.get();
    }

    @Override
    public int getCount() {
        return filteredChannelList.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredChannelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = mInflater.inflate(R.layout.channel_list_row, parent, false);
        ChannelItem c = (ChannelItem) getItem(position);

        TextView name = (TextView) rowView.findViewById(R.id.channelName);
        ImageView logo = (ImageView) rowView.findViewById(R.id.channelLogo);

        name.setText(c.name);
        picasso.load(c.metadata.get("tvg-logo")).resize(0, 130).into(logo);

        return rowView;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    class ChannelFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            filteredChannelList = new ArrayList<ChannelItem>();
            String searchString = constraint.toString().toLowerCase();

            for (ChannelItem ci : originalChannelList) {
                if (ci.name.toLowerCase().contains(searchString)) {
                    filteredChannelList.add(ci);
                }
            }
            results.count = filteredChannelList.size();
            results.values = filteredChannelList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            notifyDataSetChanged();
        }
    }
}
