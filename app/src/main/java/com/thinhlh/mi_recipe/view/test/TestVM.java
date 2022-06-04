package com.thinhlh.mi_recipe.view.test;

import com.thinhlh.domain.repository.common.CommonRepo;
import com.thinhlh.mi_recipe.base.viewmodel.BaseRepoViewModel;

public class TestVM extends BaseRepoViewModel<CommonRepo, TestUV> {
    public void onTextClicked() {
        uiCallback.updateData("Success");
//        getRepo().ping(new BaseRepoCallback<BaseResponse<String>>() {
//
//            @Override
//            public void apiRequesting(Boolean show) {
//                showLoading(show);
//            }
//
//            @Override
//            public void apiResponse(BaseResponse<String> data) {
//                uiCallback.updateData(data.getData());
//            }
//        });
    }

    @Override
    protected CommonRepo createRepo() {
        return new CommonRepo();
    }
}
