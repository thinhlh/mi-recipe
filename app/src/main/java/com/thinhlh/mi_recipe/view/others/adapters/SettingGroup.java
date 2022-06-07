package com.thinhlh.mi_recipe.view.others.adapters;

import java.util.List;

public class SettingGroup {
    private String title;
    private List<Setting> settings;

    public SettingGroup(String title, List<Setting> settings) {
        this.title = title;
        this.settings = settings;
    }

    public String getTitle() {
        return title;
    }

    public List<Setting> getSettings() {
        return settings;
    }
}
