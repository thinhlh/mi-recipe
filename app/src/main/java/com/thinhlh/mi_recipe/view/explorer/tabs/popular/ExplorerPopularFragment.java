package com.thinhlh.mi_recipe.view.explorer.tabs.popular;

import android.content.res.ColorStateList;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.ColorDrawable;
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
import com.thinhlh.mi_recipe.R;
import com.thinhlh.mi_recipe.base.fragment.BaseFragment;
import com.thinhlh.mi_recipe.databinding.FragmentExplorerPopularBinding;

import java.io.IOException;

public class ExplorerPopularFragment extends BaseFragment<FragmentExplorerPopularBinding, ExplorerPopularVM> implements ExplorerPopularUV {

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
        setUpVideoView();
        var imageWidth = binding.image.getLayoutParams().width;
        binding.image.setLayoutParams(new LinearLayout.LayoutParams(imageWidth, imageWidth));
    }

    private void setUpVideoView() {
        var videoView = binding.videoView;

        videoView.requestLayout();
        videoView.setClipToOutline(true);
        videoView.setZOrderOnTop(true);
        videoView.setVideoURI(Uri.parse("https://assets.mixkit.co/videos/preview/mixkit-placing-grilled-chicken-on-salad-26607-large.mp4"));
        videoView.setOnPreparedListener(mediaPlayer -> {
            this.mediaPlayer = mediaPlayer;
            binding.progressCircular.setVisibility(View.GONE);
            mediaPlayer.setOnVideoSizeChangedListener((mediaPlayer1, i, i1) -> {
                var mediaController = new MediaController(fragmentContext);
                videoView.setMediaController(mediaController);
                videoView.requestLayout();

            });
            mediaPlayer.setVideoScalingMode(MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initAction() {

    }

    @Override
    public void onFragmentBackPressed() {
        this.mediaPlayer.stop();
        this.mediaPlayer.release();
        this.mediaPlayer = null;
        binding.videoView.stopPlayback();
        super.onFragmentBackPressed();
    }
}
