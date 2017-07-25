/******************************************************************
*
*	CyberHTTP for Java
*
*	Copyright (C) Satoshi Konno 2002
*
*	File: HTTPRequestListener.java
*
*	Revision;
*
*	12/13/02
*		- first revision.
*	
******************************************************************/

package com.hejunlin.liveplayback.playfile;

/** HTTP ������� */
public interface HTTPRequestListener
{
	/** http������� */
	public void httpRequestRecieved(HTTPRequest httpReq);
}
