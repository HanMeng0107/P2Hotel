package com.hejunlin.liveplayback.inter;

import android.view.View;
import android.widget.MediaController;

/**
 * @author HM DateTime 2017��3��13�� ����9:24:00
 * @version 1.0
 */
public interface IMediaController {
	void hide();

	boolean isShowing();

	void setAnchorView(View view);

	void setEnabled(boolean enabled);

	void setMediaPlayer(MediaController.MediaPlayerControl player);

	void show(int timeout);

	void show();

	// ----------
	// Extends
	// ----------
	void showOnce(View view);
}
