package com.thinhlh.mi_recipe.view.explorer.tabs.categories;

import com.thinhlh.domain.repository.category.Category;
import com.thinhlh.mi_recipe.base.userview.BaseUserView;

import java.util.List;

public interface ExplorerCategoriesUV extends BaseUserView {
    void updateCategories(List<Category> categories);
}
