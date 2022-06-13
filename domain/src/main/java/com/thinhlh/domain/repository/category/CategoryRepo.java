package com.thinhlh.domain.repository.category;

import com.thinhlh.domain.api.base.BaseResponse;
import com.thinhlh.domain.repository.base.BaseRepo;
import com.thinhlh.domain.repository.base.BaseRepoCallback;

import java.util.List;

public class CategoryRepo extends BaseRepo {

    public void getCategories(BaseRepoCallback<BaseResponse<List<Category>>> callback) {
        categoryService.getCategories().enqueue(getApiCallback(callback));
    }

}
