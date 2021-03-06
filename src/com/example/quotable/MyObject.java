package com.example.quotable;

public class MyObject {
    private String KEY_SETID;
    private String KEY_SETNAME;
    private String AUTHOR;
    public MyObject(String id, String name)
    {
    	KEY_SETID = id;
    	KEY_SETNAME = name;
    }
    public MyObject(String id, String name, String author)
    {
    	KEY_SETID = id;
    	KEY_SETNAME = name;
    	AUTHOR = author;
    }
    public String getKEY_SETID() {
        return KEY_SETID;
    }
    
    public String getAuthor() {
        return AUTHOR;
    }

    public void setKEY_SETID(String kEY_SETID) {
        KEY_SETID = kEY_SETID;
    }

    public String getKEY_SETNAME() {
        return KEY_SETNAME;
    }

    public void setKEY_SETNAME(String kEY_SETNAME) {
        KEY_SETNAME = kEY_SETNAME;
    }

    @Override
    public String toString() {
        return this.KEY_SETNAME; // Value to be displayed in the Spinner
    }
}
