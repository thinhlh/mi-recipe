package com.thinhlh.mi_recipe.view.dashboard.adapter;

import android.net.Uri;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.thinhlh.domain.repository.category.Category;
import com.thinhlh.domain.repository.recipe.Recipe;
import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.adapter.BaseBindingListAdapter;
import com.thinhlh.mi_recipe.base.adapter.BaseBindingViewHolder;
import com.thinhlh.mi_recipe.base.adapter.BaseItemClickListener;
import com.thinhlh.mi_recipe.databinding.ItemCategoryBinding;
import com.thinhlh.mi_recipe.databinding.ItemCategoryRecipeBinding;
import com.thinhlh.mi_recipe.databinding.ItemDashboardRecipeBinding;
import com.thinhlh.mi_recipe.databinding.ItemExplorerChiefRecipeBinding;
import com.thinhlh.mi_recipe.databinding.ItemGroupSettingBinding;
import com.thinhlh.mi_recipe.databinding.ItemRecipeExplorerPopularBinding;
import com.thinhlh.mi_recipe.databinding.ItemRecipeTopChartBinding;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RecipeAdapter extends BaseBindingListAdapter<Recipe> {
    private final @LayoutRes
    Integer recipeLayout;

    private static class DiffCallback extends DiffUtil.ItemCallback<Recipe> {

        @Override
        public boolean areItemsTheSame(@NonNull Recipe oldItem, @NonNull Recipe newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Recipe oldItem, @NonNull Recipe newItem) {
            return Objects.equals(oldItem.getId(), newItem.getId());
        }
    }

    public RecipeAdapter(@Nullable BaseItemClickListener<Recipe> itemClickListener, Integer recipeLayout) {
        super(new DiffCallback(), itemClickListener);
        this.recipeLayout = recipeLayout;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseBindingViewHolder<Recipe> holder, int position) {
        super.onBindViewHolder(holder, position);
        var categories = getCategoriesString(getItem(position).getCategories());

        if (holder.getBinding() instanceof ItemRecipeExplorerPopularBinding) {
            ((ItemRecipeExplorerPopularBinding) holder.getBinding()).categoriesTxt.setText(categories);
        } else if (holder.getBinding() instanceof ItemRecipeTopChartBinding) {
            ((ItemRecipeTopChartBinding) holder.getBinding()).setIndex(position + 1);
        } else if (holder.getBinding() instanceof ItemExplorerChiefRecipeBinding) {
            ((ItemExplorerChiefRecipeBinding) holder.getBinding()).categoriesTxt.setText(categories);
        } else if (holder.getBinding() instanceof ItemCategoryRecipeBinding) {
            ((ItemCategoryRecipeBinding) holder.getBinding()).categoriesTxt.setText(categories);
        }
    }

    private String getCategoriesString(List<Category> categories) {
        return categories
                .stream()
                .map(Category::getTitle)
                .collect(Collectors.joining(" • "));
    }

    @Override
    protected Integer getViewType(int position) {
        return recipeLayout;
    }
}
