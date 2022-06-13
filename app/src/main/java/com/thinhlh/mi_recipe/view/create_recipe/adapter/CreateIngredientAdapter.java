package com.thinhlh.mi_recipe.view.create_recipe.adapter;

import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;

import com.thinhlh.domain.repository.recipe.Ingredient;
import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.adapter.BaseBindingListAdapter;
import com.thinhlh.mi_recipe.base.adapter.BaseBindingViewHolder;
import com.thinhlh.mi_recipe.base.adapter.BaseItemClickListener;
import com.thinhlh.mi_recipe.databinding.ItemCreateRecipeIngredientBinding;
import com.thinhlh.mi_recipe.databinding.ItemSpinnerBinding;
import com.thinhlh.mi_recipe.generated.callback.OnClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class CreateIngredientAdapter extends BaseBindingListAdapter<CreateIngredient> {

    private List<Ingredient> ingredients = new ArrayList<>();
    private Consumer<Integer> onAddItemClick;
    private Consumer<Integer> onRemoveItemClick;

    private static class DiffCallback extends DiffUtil.ItemCallback<CreateIngredient> {

        @Override
        public boolean areItemsTheSame(@NonNull CreateIngredient oldItem, @NonNull CreateIngredient newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull CreateIngredient oldItem, @NonNull CreateIngredient newItem) {
            return
                    Objects.equals(oldItem.getId(), newItem.getId())
                            && Objects.equals(oldItem.getTitle(), newItem.getTitle());
        }
    }

    public CreateIngredientAdapter(@Nullable BaseItemClickListener<CreateIngredient> itemClickListener, Consumer<Integer> onAddItemClick, Consumer<Integer> onRemoveItemClick) {
        super(new DiffCallback(), itemClickListener);

        this.onAddItemClick = onAddItemClick;
        this.onRemoveItemClick = onRemoveItemClick;
    }

    public void addIngredients(List<Ingredient> ingredients) {
        this.ingredients.addAll(ingredients);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull BaseBindingViewHolder<CreateIngredient> holder, int position) {
        super.onBindViewHolder(holder, position);

        var binding = (ItemCreateRecipeIngredientBinding) holder.getBinding();

        setUpSpinner(binding);

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
                binding.ingredientQuantity.clearComposingText();
                binding.ingredientQuantity.clearFocus();
                onRemoveItemClick.accept(position);
                notifyDataSetChanged();
            });
        }
    }

    private void setUpSpinner(ItemCreateRecipeIngredientBinding binding) {

        var adapter = new ArrayAdapter<Ingredient>(binding.actionBarSpinner.getContext(), R.layout.item_spinner) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_spinner, parent, false);
                }
                TextView tv = convertView.findViewById(R.id.title);

                tv.setText(getItem(position).getTitle());

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
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                var selectedItem = ingredients.get(position);

                binding.unit.setText(selectedItem.getUnit());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        adapter.addAll(ingredients);
    }

    @Override
    protected Integer getViewType(int position) {
        return R.layout.item_create_recipe_ingredient;
    }

    @Override
    public CreateIngredient getItem(int position) {
        return super.getItem(position);
    }
}
