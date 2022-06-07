package com.thinhlh.mi_recipe.view.login;

import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.fragment.BaseFragment;
import com.thinhlh.mi_recipe.databinding.FragmentLoginBinding;
import com.thinhlh.mi_recipe.view.home.HomeFragment;
import com.thinhlh.mi_recipe.view.test.TestFragment;

public class LoginFragment extends BaseFragment<FragmentLoginBinding, LoginVM> implements LoginUV {
    @Override
    protected Integer layoutRes() {
        return R.layout.fragment_login;
    }

    @Override
    protected Class<LoginVM> viewModelClass() {
        return LoginVM.class;
    }

    @Override
    protected void initViewModel(LoginVM viewModel) {
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
    public void goToHome() {
        getNavigator().goTo(new HomeFragment());
    }

    @Override
    public String getEmailField() {
        return binding.email.getText().toString();
    }

    @Override
    public String getPasswordField() {
        return binding.password.getText().toString();
    }

    @Override
    public String getConfirmPasswordField() {
        return binding.passwordConfirm.getText().toString();
    }

    @Override
    public String getNameField() {
        return binding.name.getText().toString();
    }
}
