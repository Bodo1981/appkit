package com.christianbahl.appkit.lib.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.christianbahl.appkit.lib.R;
import com.christianbahl.appkit.lib.presenter.CBBasePresenter;
import com.christianbahl.appkit.lib.util.CBFadeHelper;
import com.christianbahl.appkit.lib.view.CBBaseMvpView;

/**
 * Created by cbahl on 18.01.15.
 */
public abstract class CBBaseFragmentMvp<CV extends View, D, V extends CBBaseMvpView<D>, P extends CBBasePresenter<V>>
    extends CBBaseFragmentPresenter implements CBBaseMvpView<D> {

  protected CV contentView;
  protected TextView errorView;
  protected View loadingView;
  protected P presenter;

  @Override protected Integer getLayoutResId() {
    return R.layout.cb_fragment;
  }

  @SuppressWarnings("unchecked") @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    contentView = (CV) view.findViewById(R.id.content_view);
    loadingView = view.findViewById(R.id.loading_view);
    errorView = (TextView) view.findViewById(R.id.error_view);

    if (contentView == null) {
      throw new IllegalStateException("The content view is not specified. "
          + "You have to provide a View with R.id.contentView in your inflated xml layout");
    }

    if (loadingView == null) {
      throw new IllegalStateException("The loading view is not specified. "
          + "You have to provide a View with R.id.loadingView in your inflated xml layout");
    }

    if (errorView == null) {
      throw new IllegalStateException("The error view is not specified. "
          + "You have to provide a View with R.id.errorView in your inflated xml layout");
    }

    errorView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        onErrorViewClicked();
      }
    });
  }

  /**
   * Called if the user clicks on the error view
   */
  protected abstract void onErrorViewClicked();

  @Override public void showLoading(boolean isContentVisible) {
    animateLoadingViewIn(isContentVisible);
  }

  @Override public void showContent() {
    animateContentViewIn();
  }

  @Override public void showError(Exception e, boolean isContentVisible) {
    String errorMsg = getErrorMessage(e, isContentVisible);

    if (isContentVisible) {
      showLightError(errorMsg);
    } else {
      animateErrorViewIn(errorMsg);
    }
  }

  /**
   * Get the error message for a certain Exception that will be shown on {@link
   * #showError(Exception, boolean)}
   *
   * @param e {@link Exception}
   * @param isContentVisible is content visible
   */
  protected String getErrorMessage(Exception e, boolean isContentVisible) {
    return e.getLocalizedMessage();
  }

  /**
   * Show light error
   *
   * @param errorMsg error message
   */
  protected void showLightError(String errorMsg) {
    Toast.makeText(getActivity(), errorMsg, Toast.LENGTH_SHORT).show();
  }

  /**
   * Animate the loading view
   *
   * @param isContentVisible is content visible
   */
  protected void animateLoadingViewIn(boolean isContentVisible) {
    if (!isContentVisible) {
      CBFadeHelper.showLoading(loadingView, contentView, errorView);
    }
  }

  /**
   * Animate the content view in
   */
  protected void animateContentViewIn() {
    CBFadeHelper.showContent(loadingView, contentView, errorView);
  }

  /**
   * Animate the error view in
   *
   * @param errorMsg error message
   */
  protected void animateErrorViewIn(String errorMsg) {
    CBFadeHelper.showError(errorMsg, loadingView, contentView, errorView);
  }
}
