package com.dasn.javawebapp;

public class Note {
    private int id=0;
    private String title="";
    private String text="";
    private Boolean hidden=false;
    
    public Note() {
        
    }
    public Note(int _id, String _title, String _text, Boolean _hidden) {
        id=_id;
        title=_title;
        text=_text;
        hidden=_hidden;        
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the hidden
     */
    public Boolean getHidden() {
        return hidden;
    }

    /**
     * @param hidden the hidden to set
     */
    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
}
