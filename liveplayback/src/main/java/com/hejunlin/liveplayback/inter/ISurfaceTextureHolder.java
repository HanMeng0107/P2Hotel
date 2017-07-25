package com.hejunlin.liveplayback.inter;

import android.graphics.SurfaceTexture;

/**
 * @author HM DateTime 2017��3��13�� ����10:00:38
 * @version 1.0
 */
public interface ISurfaceTextureHolder {
	void setSurfaceTexture(SurfaceTexture surfaceTexture);

	SurfaceTexture getSurfaceTexture();

	void setSurfaceTextureHost(ISurfaceTextureHost surfaceTextureHost);
}
