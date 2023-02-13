package com.my_web_app;

public class Location {
    private String name;
    private int id,parentId;

    public Location() {
    }

    public Location(String name, int id, int parentId) {
        this.name = name;
        this.id = id;
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }



    public int getParentId() {
        return parentId;
    }

   public int getId(){
        return id;
   }

    @Override
    public String toString(){

        return getName()+" -> "+getParentId()+"\n";
    }
}
