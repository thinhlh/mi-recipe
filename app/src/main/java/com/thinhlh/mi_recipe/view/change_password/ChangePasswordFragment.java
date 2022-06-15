package com.thinhlh.mi_recipe.view.change_password;

import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.fragment.BaseFragment;
import com.thinhlh.mi_recipe.databinding.FragmentChangePasswordBinding;
import com.thinhlh.mi_recipe.view.landing.LandingFragment;
import com.thinhlh.utils.helper.AppPreferenceKeys;
import com.thinhlh.utils.helper.AppPreferences;
import com.thinhlh.utils.helper.SystemHelper;

public class ChangePasswordFragment extends BaseFragment<FragmentChangePasswordBinding, ChangePasswordVM> implements ChangePasswordUV {
    @Override
    protected Integer layoutRes() {
        return R.layout.fragment_change_password;
    }

    @Override
    protected Class<ChangePasswordVM> viewModelClass() {
        return ChangePasswordVM.class;
    }

    @Override
    protected void initViewModel(ChangePasswordVM viewModel) {
        viewModel.init(this);
        binding.setVm(viewModel);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initAction() {

    }

    @Override
    public String getCurrentPassword() {
        return binding.currentPassword.getText().toString();
    }

    @Override
    public String getNewPassword() {
        return binding.newPassword.getText().toString();
    }

    @Override
    public String getReEnterPassword() {
        return binding.reEnterPassword.getText().toString();
    }

    @Override
    public void signOut() {
        AppPreferences.get().removeObject(AppPreferenceKeys.accessToken);
        getNavigator().goTo(new LandingFragment());
    }

    @Override
    public void onFragmentBackPressed() {
        SystemHelper.toggleSoftInput(fragmentContext, false);
        super.onFragmentBackPressed();
    }
}
