package com.tomsbee.sortrecyclerviewsample.Bean;

/**
 * Created by 2015 on 2016/7/28.
 */
public class UserInfo {
    private int id;
    private String name;
    private String  sortLetters; //拼音的首字母


    public UserInfo(int id,String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }
}
