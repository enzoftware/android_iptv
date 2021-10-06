package com.enzoftware.androidiptv.adapter

import android.content.Context
import com.enzoftware.androidiptv.m3u.ChannelList
import com.squareup.picasso.Picasso
import android.view.LayoutInflater
import android.view.View
import com.enzoftware.androidiptv.m3u.ChannelItem
import android.view.ViewGroup
import android.widget.*
import com.enzoftware.androidiptv.R
import java.util.ArrayList

class ChannelListAdapter(private val mContext: Context, cl: ChannelList) : BaseAdapter(),
    Filterable {
    private val picasso: Picasso
    private val mInflater: LayoutInflater
    private val originalChannelList: List<ChannelItem>
    private var filteredChannelList: MutableList<ChannelItem>
    private val filter = ChannelFilter()
    override fun getCount(): Int {
        return filteredChannelList.size
    }

    override fun getItem(position: Int): Any {
        return filteredChannelList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
        val rowView = mInflater.inflate(R.layout.channel_list_row, parent, false)
        val c = getItem(position) as ChannelItem
        val name = rowView.findViewById<View>(R.id.channelName) as TextView
        val logo = rowView.findViewById<View>(R.id.channelLogo) as ImageView
        name.text = c.name
        return rowView
    }

    override fun getFilter(): Filter {
        return filter
    }

    internal inner class ChannelFilter : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val results = FilterResults()
            filteredChannelList = ArrayList()
            val searchString = constraint.toString().toLowerCase()
            for (ci in originalChannelList) {
                if (ci.name.toLowerCase().contains(searchString)) {
                    filteredChannelList.add(ci)
                }
            }
            results.count = filteredChannelList.size
            results.values = filteredChannelList
            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            notifyDataSetChanged()
        }
    }

    init {
        originalChannelList = cl.items
        filteredChannelList = cl.items
        mInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        picasso = Picasso.get()
    }
}