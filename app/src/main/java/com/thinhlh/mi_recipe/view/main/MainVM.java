package com.thinhlh.mi_recipe.view.main;

import com.thinhlh.domain.api.RetrofitService;
import com.thinhlh.mi_recipe.base.viewmodel.BaseUiViewModel;
import com.thinhlh.utils.helper.AppPreferenceKeys;
import com.thinhlh.utils.helper.AppPreferences;

public class MainVM extends BaseUiViewModel<MainUV> {
    void initRetrofitService() {
        var accessToken = AppPreferences.get().getString(AppPreferenceKeys.accessToken);
        if (accessToken != null && !accessToken.isEmpty()) {
            RetrofitService.get().init(accessToken, () -> uiCallback.apiTimeOut());
        }
    }
}
