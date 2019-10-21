package com.enzoftware.androidiptv.m3u;

import java.util.ArrayList;

public class ChannelList {
    public String name;
    public ArrayList<ChannelItem> items;
    public ArrayList<String> groups;

    public ChannelList() {
        items = new ArrayList<ChannelItem>();
    }

    public void add(ChannelItem item) {
        items.add(item);
    }
}
