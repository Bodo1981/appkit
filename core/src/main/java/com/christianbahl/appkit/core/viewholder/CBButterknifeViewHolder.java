package com.christianbahl.appkit.core.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.ButterKnife;

/**
 * <p>
 * ViewHolder which uses Butterknife.
 * </p>
 *
 * @author Christian Bahl
 */
public class CBButterknifeViewHolder extends RecyclerView.ViewHolder {

  public CBButterknifeViewHolder(View v) {
    super(v);

    ButterKnife.inject(this, v);
  }
}
