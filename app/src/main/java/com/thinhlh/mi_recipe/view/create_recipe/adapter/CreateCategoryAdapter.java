package com.thinhlh.mi_recipe.view.create_recipe.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.thinhlh.domain.repository.category.Category;
import com.thinhlh.domain.repository.recipe.Ingredient;
import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.adapter.BaseBindingListAdapter;
import com.thinhlh.mi_recipe.base.adapter.BaseBindingViewHolder;
import com.thinhlh.mi_recipe.base.adapter.BaseItemClickListener;
import com.thinhlh.mi_recipe.databinding.ItemCreateRecipeCategoryBinding;
import com.thinhlh.mi_recipe.databinding.ItemCreateRecipeIngredientBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class CreateCategoryAdapter extends BaseBindingListAdapter<Category> {

    private List<Category> categories = new ArrayList<>();
    private Consumer<Integer> onAddItemClick;
    private Consumer<Integer> onRemoveItemClick;

    private static class DiffUtilCallback extends DiffUtil.ItemCallback<Category> {

        @Override
        public boolean areItemsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Category oldItem, @NonNull Category newItem) {
            return Objects.equals(oldItem.getId(), newItem.getId());
        }
    }

    public void addCategories(List<Category> categories) {
        this.categories.addAll(categories);
        notifyDataSetChanged();
    }

    public CreateCategoryAdapter(@Nullable BaseItemClickListener<Category> itemClickListener, Consumer<Integer> onAddItemClick, Consumer<Integer> onRemoveItemClick) {
        super(new DiffUtilCallback(), itemClickListener);
        this.onAddItemClick = onAddItemClick;
        this.onRemoveItemClick = onRemoveItemClick;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseBindingViewHolder<Category> holder, int position) {
        var binding = (ItemCreateRecipeCategoryBinding) holder.getBinding();

        setUpSpinner(binding, position);

        if (position == getItemCount() - 1) {
            // Last item, then the end button is add
            binding.endIcon.setImageResource(R.drawable.ic_baseline_add_24);
            binding.endIcon.setOnClickListener(view -> {
                onAddItemClick.accept(position);
                notifyDataSetChanged();
            });

        } else {
            // Not the last item, so the end button is remove
            binding.endIcon.setImageResource(R.drawable.ic_baseline_remove_24);
            binding.endIcon.setOnClickListener(view -> {
                onRemoveItemClick.accept(position);
                notifyDataSetChanged();
            });
        }
    }

    private void setUpSpinner(ItemCreateRecipeCategoryBinding binding, Integer position) {
        var adapter = new ArrayAdapter<Category>(binding.actionBarSpinner.getContext(), R.layout.item_spinner) {
            @NonNull
            @Override
            public View getView(int i, @Nullable View convertView, @NonNull ViewGroup parent) {

                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_spinner, parent, false);
                }
                TextView tv = convertView.findViewById(R.id.title);

                tv.setText(getItem(i).getTitle());

                return convertView;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                return getView(position, convertView, parent);
            }
        };

        binding.actionBarSpinner.setAdapter(adapter);
        binding.actionBarSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                var selectedItem = categories.get(i);

                getCurrentList().get(position).setId(selectedItem.getId());
                getCurrentList().get(position).setTitle(selectedItem.getTitle());
                getCurrentList().get(position).setThumbnail(selectedItem.getThumbnail());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        adapter.addAll(categories);
    }

    @Override
    protected Integer getViewType(int position) {
        return R.layout.item_create_recipe_category;
    }
}
