package com.enzoftware.androidiptv.m3u;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChannelList implements Serializable {
    public String name;
    public List<ChannelItem> items;
    public List<String> groups;

    public ChannelList() {
        items = new ArrayList<ChannelItem>();
    }

    public void add(ChannelItem item) {
        items.add(item);
    }
}
