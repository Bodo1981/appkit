package com.christianbahl.appkit.templates.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.christianbahl.appkit.core.fragment.CBBaseFragment;
import com.christianbahl.appkit.core.util.CBFadeHelper;
import com.christianbahl.appkit.mvp.presenter.CBBasePresenter;
import com.christianbahl.appkit.mvp.view.CBBaseView;
import com.christianbahl.appkit.templates.R;

/**
 * Created by cbahl on 18.01.15.
 */
public abstract class CBBaseFragmentMvp<CV extends View, D, V extends CBBaseView<D>, P extends CBBasePresenter<V, D>>
    extends CBBaseFragment implements CBBaseView<D> {

  protected CV contentView;
  protected TextView errorView;
  protected View loadingView;
  protected P presenter;

  @SuppressWarnings("unchecked") @Override protected void onCreateView(View view,
      ViewGroup container, Bundle savedInstanceState) {
    presenter = createPresenter();

    if (presenter != null) {
      presenter.setView((V) this);

      onPresenterCreated(view, container, savedInstanceState);

      loadData(false);
    }
  }

  @SuppressWarnings("unchecked")
  @Override protected void onViewInflated(View view) {
    super.onViewInflated(view);

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
  @Override public void onDestroyView() {
    super.onDestroyView();

    if (presenter != null) {
      presenter.onDestroy(false);
    }
  }

  /**
   * Creates the {@link P}. Called in {@link #onCreateView(View, ViewGroup, Bundle)}
   *
   * @return {@link P}
   */
  protected abstract P createPresenter();

  /**
   * Called after the presenter was initialized in {@link #onCreateView(View, ViewGroup, Bundle)}.
   *
   * <p>
   * Is <b>only</b> called if <code>{@link P} != null</code>
   * </p>
   *
   * @param view {@link View}
   * @param container {@link ViewGroup}
   * @param savedInstanceState {@link Bundle}
   */
  protected abstract void onPresenterCreated(View view, ViewGroup container,
      Bundle savedInstanceState);

  /**
   * Loads the data. Called in {@link #onCreateView(View, ViewGroup, Bundle)} after {@link
   * #onPresenterCreated(View, ViewGroup, Bundle)}
   *
   * @param isContentVisible is content visible
   */
  protected abstract void loadData(boolean isContentVisible);

  /**
   * Called if the user clicks on the error view
   */
  protected void onErrorViewClicked() {
    loadData(false);
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
