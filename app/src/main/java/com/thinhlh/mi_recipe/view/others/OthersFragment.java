package com.thinhlh.mi_recipe.view.others;

import com.google.android.material.divider.MaterialDividerItemDecoration;
import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.adapter.BaseItemClickListener;
import com.thinhlh.mi_recipe.base.fragment.BaseFragment;
import com.thinhlh.mi_recipe.base.widgets.SpacingItemDecoration;
import com.thinhlh.mi_recipe.databinding.FragmentOthersBinding;
import com.thinhlh.mi_recipe.view.landing.LandingFragment;
import com.thinhlh.mi_recipe.view.others.adapters.Setting;
import com.thinhlh.mi_recipe.view.others.adapters.SettingAdapter;
import com.thinhlh.mi_recipe.view.others.adapters.SettingGroup;
import com.thinhlh.mi_recipe.view.others.adapters.SettingGroupAdapter;

import java.util.ArrayList;

public class OthersFragment extends BaseFragment<FragmentOthersBinding, OthersVM> implements OthersUV {
    private SettingGroupAdapter settingGroupAdapter;

    @Override
    protected Integer layoutRes() {
        return R.layout.fragment_others;
    }

    @Override
    protected Class<OthersVM> viewModelClass() {
        return OthersVM.class;
    }

    @Override
    protected void initViewModel(OthersVM viewModel) {
        viewModel.init(this);
        binding.setVm(viewModel);
    }

    @Override
    protected void initView() {
        settingGroupAdapter = new SettingGroupAdapter((item, adapterPosition) -> {

        });

        binding.recyclerView.addItemDecoration(new SpacingItemDecoration(fragmentContext, MaterialDividerItemDecoration.VERTICAL, R.dimen.pad_L));
        binding.recyclerView.setAdapter(settingGroupAdapter);
    }

    @Override
    protected void initData() {
        settingGroupAdapter.submitList(new ArrayList<>() {{
            add(new SettingGroup("Account", new ArrayList<>() {{
                add(new Setting("Edit profile", false, () -> {

                }));
                add(new Setting("Change password", false, () -> {

                }));
            }}));

            add(new SettingGroup("Preferences", new ArrayList<>() {{
                add(new Setting("Language", false, () -> {

                }));
                add(new Setting("Saved Recipes", false, () -> {

                }));
                add(new Setting("Licenses", false, () -> {

                }));
            }}));
        }});
    }

    @Override
    protected void initAction() {

    }

    @Override
    public void goToLandingPage() {
        getNavigator().setRootFragment(new LandingFragment());
        getNavigator().goToRoot();
        getNavigator().clearHistory();
    }
}
