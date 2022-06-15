package com.thinhlh.mi_recipe.view.explorer.tabs.popular;

import android.content.res.ColorStateList;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.thinhlh.domain.repository.recipe.Recipe;
import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.adapter.BaseItemClickListener;
import com.thinhlh.mi_recipe.base.fragment.BaseFragment;
import com.thinhlh.mi_recipe.databinding.FragmentExplorerPopularBinding;
import com.thinhlh.mi_recipe.view.dashboard.adapter.RecipeAdapter;
import com.thinhlh.mi_recipe.view.recipe_detail.RecipeDetailFragment;

import java.io.IOException;
import java.util.List;

public class ExplorerPopularFragment extends BaseFragment<FragmentExplorerPopularBinding, ExplorerPopularVM> implements ExplorerPopularUV {

    private RecipeAdapter recipeAdapter;

    private MediaPlayer mediaPlayer;

    @Override
    protected Integer layoutRes() {
        return R.layout.fragment_explorer_popular;
    }

    @Override
    protected Class<ExplorerPopularVM> viewModelClass() {
        return ExplorerPopularVM.class;
    }

    @Override
    protected void initViewModel(ExplorerPopularVM viewModel) {
        viewModel.init(this);
        binding.setVm(viewModel);
    }

    @Override
    protected void initView() {
        initAdapter();
        setUpVideoView();
        var imageWidth = binding.image.getLayoutParams().width;
        binding.image.setLayoutParams(new LinearLayout.LayoutParams(imageWidth, imageWidth));
    }

    private void setUpVideoView() {
        mediaPlayer = new MediaPlayer();
//        var videoView = binding.videoView;
        var textureView = binding.videoView;

        textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surfaceTexture, int i, int i1) {
                try {
                    mediaPlayer.setDataSource("https://assets.mixkit.co/videos/preview/mixkit-placing-grilled-chicken-on-salad-26607-large.mp4");
                    mediaPlayer.setSurface(new Surface(surfaceTexture));
                    mediaPlayer.prepareAsync();

                    mediaPlayer.setOnVideoSizeChangedListener((mediaPlayer1, _i, _i1) -> {
//                        var mediaController = new MediaController(fragmentContext);
//                        videoView.setMediaController(mediaController);
//                        videoView.requestLayout();

                    });

                    mediaPlayer.setOnPreparedListener(mp -> {
                        binding.progressCircular.setVisibility(View.GONE);
                        mediaPlayer.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT);
                        mediaPlayer.setLooping(true);
                        mediaPlayer.start();
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surfaceTexture, int i, int i1) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surfaceTexture) {
                return true;
            }

            @Override
            public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surfaceTexture) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.getAllPopularRecipes();
    }

    @Override
    protected void initData() {
        viewModel.getAllPopularRecipes();
    }

    @Override
    protected void initAction() {

    }

    private void initAdapter() {
        recipeAdapter = new RecipeAdapter((item, adapterPosition) -> {
            getNavigator().goTo(RecipeDetailFragment.getInstance(item));
        }, R.layout.item_recipe_explorer_popular);

        binding.recyclerView.setAdapter(recipeAdapter);
    }

    @Override
    public void updatePopularRecipes(List<Recipe> recipes) {
        recipeAdapter.submitList(recipes);
    }
}
