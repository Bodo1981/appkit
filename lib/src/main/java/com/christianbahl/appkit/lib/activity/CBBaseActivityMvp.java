package com.christianbahl.appkit.lib.activity;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.christianbahl.appkit.lib.R;
import com.christianbahl.appkit.lib.presenter.CBBasePresenter;
import com.christianbahl.appkit.lib.util.CBFadeHelper;
import com.christianbahl.appkit.lib.view.CBBaseMvpView;

/**
 * Created by cbahl on 17.01.15.
 */
public abstract class CBBaseActivityMvp<CV extends View, D, V extends CBBaseMvpView<D>, P extends CBBasePresenter<V>>
    extends CBBaseActivityPresenter<P> implements CBBaseMvpView<D> {

  protected CV contentView;
  protected TextView errorView;
  protected View loadingView;

  @SuppressWarnings("unchecked") @Override public void onSupportContentChanged() {
    super.onSupportContentChanged();

    contentView = (CV) findViewById(R.id.content_view);
    loadingView = findViewById(R.id.loading_view);
    errorView = (TextView) findViewById(R.id.error_view);

    if (contentView == null) {
      throw new IllegalStateException("The content view is not specified. "
          + "You have to provide a View with R.id.content_view in your inflated xml layout");
    }

    if (loadingView == null) {
      throw new IllegalStateException("The loading view is not specified. "
          + "You have to provide a View with R.id.loading_view in your inflated xml layout");
    }

    if (errorView == null) {
      throw new IllegalStateException("The error view is not specified. "
          + "You have to provide a View with R.id.error_view in your inflated xml layout");
    }

    errorView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        onErrorViewClicked();
      }
    });
  }

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
   * Called if the user clicks on the error view (R.id.error_view)
   */
  protected abstract void onErrorViewClicked();

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
    Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
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

  public CV getContentView() {
    return contentView;
  }

  public TextView getErrorView() {
    return errorView;
  }

  public View getLoadingView() {
    return loadingView;
  }
}
