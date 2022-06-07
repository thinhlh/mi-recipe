package com.thinhlh.mi_recipe.view.others.adapters;

public class Setting {
    private String title;
    private boolean isSwitch;
    private Runnable onClick;

    public Setting(String title,boolean isSwitch,Runnable onClick){
        this.title = title;
        this.isSwitch = isSwitch;
        this.onClick = onClick;
    }

    public String getTitle() {
        return title;
    }

    public boolean getIsSwitch() {
        return isSwitch;
    }


    public Runnable getOnClick() {
        return onClick;
    }
}
