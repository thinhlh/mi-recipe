package com.thinhlh.mi_recipe.view.create_recipe;

import android.net.Uri;

import androidx.lifecycle.MutableLiveData;

import com.thinhlh.domain.api.base.BaseResponse;
import com.thinhlh.domain.repository.base.BaseRepo;
import com.thinhlh.domain.repository.base.BaseRepoCallback;
import com.thinhlh.domain.repository.ingredient.IngredientRepo;
import com.thinhlh.domain.repository.recipe.CreateRecipeRequest;
import com.thinhlh.domain.repository.recipe.Ingredient;
import com.thinhlh.mi_recipe.base.viewmodel.BaseRepoViewModel;
import com.thinhlh.mi_recipe.base.viewmodel.BaseUiViewModel;
import com.thinhlh.mi_recipe.view.create_recipe.adapter.CreateIngredient;

import java.util.ArrayList;
import java.util.List;

public class CreateRecipeVM extends BaseRepoViewModel<IngredientRepo, CreateRecipeUV> {

    public MutableLiveData<Boolean> formCorrect = new MutableLiveData<>(false);

    public final MutableLiveData<List<CreateIngredient>> createIngredientList = new MutableLiveData<>(new ArrayList<>() {{
        add(new CreateIngredient(null, "", 0));
    }});

    public final MutableLiveData<List<String>> createDirectionList = new MutableLiveData<>(new ArrayList<>() {{
        add("");
    }});

    public void createRecipe() {

        CreateRecipeRequest request = new CreateRecipeRequest
                .Builder()
                .directions(uiCallback.getDirections())
                .build();
    }

    public void getIngredients() {
        getRepo().getIngredients(new BaseRepoCallback<>() {
            @Override
            public void apiRequesting(Boolean show) {
                showLoading(show);
            }

            @Override
            public void apiResponse(BaseResponse<List<Ingredient>> data) {
                uiCallback.updateIngredients(data.getData());
            }
        });
    }


    public final MutableLiveData<Uri> selectedImageUri = new MutableLiveData<>();

    public void openImagePicker() {
        uiCallback.openImagePicker();
    }

    public void clearImage() {
        selectedImageUri.postValue(null);
    }

    public void onBackPressedClick() {
        uiCallback.onFragmentBackPressed();
    }

    @Override
    protected IngredientRepo createRepo() {
        return new IngredientRepo();
    }
}
