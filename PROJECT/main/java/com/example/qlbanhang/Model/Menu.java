package com.example.qlbanhang.Model;

public class Menu {
    private String s_Name;
    private int i_Resource;

    public Menu(String s_Name, int i_Resource) {
        this.s_Name = s_Name;
        this.i_Resource = i_Resource;
    }

    public String getS_Name() {
        return s_Name;
    }

    public void setS_Name(String s_Name) {
        this.s_Name = s_Name;
    }

    public int getI_Resource() {
        return i_Resource;
    }

    public void setI_Resource(int i_Resource) {
        this.i_Resource = i_Resource;
    }
}
