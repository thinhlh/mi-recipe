package com.thinhlh.mi_recipe.view.create_recipe.adapter;

import android.text.Editable;
import android.text.TextWatcher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.thinhlh.domain.repository.recipe.Direction;
import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.adapter.BaseBindingListAdapter;
import com.thinhlh.mi_recipe.base.adapter.BaseBindingViewHolder;
import com.thinhlh.mi_recipe.base.adapter.BaseItemClickListener;
import com.thinhlh.mi_recipe.databinding.ItemCreateRecipeDirectionBinding;
import com.thinhlh.mi_recipe.databinding.ItemCreateRecipeIngredientBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class CreateDirectionAdapter extends BaseBindingListAdapter<String> {

    private Consumer<Integer> onAddItemClick;
    private Consumer<Integer> onRemoveItemClick;

    private static class DiffUtilCallback extends DiffUtil.ItemCallback<String> {

        @Override
        public boolean areItemsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return oldItem.equals(newItem);
        }
    }

    public CreateDirectionAdapter(@Nullable BaseItemClickListener<String> itemClickListener, Consumer<Integer> onAddItemClick, Consumer<Integer> onRemoveItemClick) {
        super(new DiffUtilCallback(), itemClickListener);

        this.onAddItemClick = onAddItemClick;
        this.onRemoveItemClick = onRemoveItemClick;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseBindingViewHolder<String> holder, int position) {
        super.onBindViewHolder(holder, position);

        var binding = (ItemCreateRecipeDirectionBinding) holder.getBinding();
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
                binding.direction.clearComposingText();
                binding.direction.clearFocus();
                onRemoveItemClick.accept(position);
                notifyDataSetChanged();
            });
        }

        binding.direction.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                getCurrentList().set(position, editable.toString());
            }
        });
    }

    public List<Direction> getDirections() {
        var result = new ArrayList<String>();
        for (int i = 0; i < getItemCount(); i++) {
            result.add(getItem(i));
        }

        return result
                .stream()
                .map((content) -> new Direction(content, null))
                .collect(Collectors.toList());
    }

    @Override
    protected Integer getViewType(int position) {
        return R.layout.item_create_recipe_direction;
    }
}
