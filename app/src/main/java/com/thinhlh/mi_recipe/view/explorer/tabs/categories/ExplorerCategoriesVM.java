package com.thinhlh.mi_recipe.view.explorer.tabs.categories;

import com.thinhlh.domain.api.base.BaseResponse;
import com.thinhlh.domain.repository.base.BaseRepoCallback;
import com.thinhlh.domain.repository.category.Category;
import com.thinhlh.domain.repository.category.CategoryRepo;
import com.thinhlh.mi_recipe.base.viewmodel.BaseRepoViewModel;
import com.thinhlh.mi_recipe.base.viewmodel.BaseUiViewModel;

import java.util.List;

public class ExplorerCategoriesVM extends BaseRepoViewModel<CategoryRepo, ExplorerCategoriesUV> {
    public void getCategories() {
        getRepo().getCategories(new BaseRepoCallback<>() {
            @Override
            public void apiRequesting(Boolean show) {
                showLoading(show);
            }

            @Override
            public void apiResponse(BaseResponse<List<Category>> data) {
                uiCallback.updateCategories(data.getData());

            }
        });
    }

    @Override
    protected CategoryRepo createRepo() {
        return new CategoryRepo();
    }
}
