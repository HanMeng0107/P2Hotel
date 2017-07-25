/******************************************************************
 *
 *	CyberHTTP for Java
 *
 *	Copyright (C) Satoshi Konno 2002-2004
 *
 *	File: HTTPRequest.java
 *
 *	Revision;
 *
 *	11/18/02
 *		- first revision.
 *	05/23/03
 *		- Giordano Sassaroli <sassarol@cefriel.it>
 *		- Add a relative URL check to setURI().
 *	09/02/03
 *		- Giordano Sassaroli <sassarol@cefriel.it>
 *		- Problem : Devices whose description use absolute urls receive wrong http requests
 *		- Error : the presence of a base url is not mandatory, the API code makes the assumption that control and event subscription urls are relative
 *		- Description: The method setURI should be changed as follows
 *	02/01/04
 *		- Added URI parameter methods.
 *	03/16/04
 *		- Removed setVersion() because the method is added to the super class.
 *		- Changed getVersion() to return the version when the first line string has the length.
 *	05/19/04
 *		- Changed post(HTTPResponse *) to close the socket stream from the server.
 *	08/19/04
 *		- Fixed getFirstLineString() and getHTTPVersion() no to return "HTTP/HTTP/version".
 *	08/25/04
 *		- Added isHeadRequest().
 *	08/26/04
 *		- Changed post(HTTPResponse) not to close the connection.
 *		- Changed post(String, int) to add a connection header to close.
 *	08/27/04
 *		- Changed post(String, int) to support the persistent connection.
 *	08/28/04
 *		- Added isKeepAlive().
 *	10/26/04
 *		- Brent Hills <bhills@openshores.com>
 *		- Added a fix to post() when the last position of Content-Range header is 0.
 *		- Added a Content-Range header to the response in post().
 *		- Changed the status code for the Content-Range request in post().
 *		- Added to check the range of Content-Range request in post().
 *	03/02/05
 *		- Changed post() to suppot chunked stream.
 *	06/10/05
 *		- Changed post() to add a HOST headedr before the posting.
 *	07/07/05
 *		- Lee Peik Feng <pflee@users.sourceforge.net>
 *		- Fixed post() to output the chunk size as a hex string.
 *
 ******************************************************************/

package com.hejunlin.liveplayback.playfile;

import com.hejunlin.liveplayback.utils.Debug;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.StringTokenizer;



/**
 * HTTPRequest �̳� HTTPPacket ����http�������Ϣ
 * 
 * This class rappresnet an HTTP <b>request</b>, and act as HTTP client when it
 * sends the request<br>
 * 
 * @author Satoshi "skonno" Konno
 * @author Stefano "Kismet" Lenzi
 * @version 1.8
 * 
 */
public class HTTPRequest extends HTTPPacket
{
    private static final String TAG = "org.cybergarage.http.HTTPRequest";	
	
	// //////////////////////////////////////////////
	// Constructor
	// //////////////////////////////////////////////

	/** ����һ��HTTPRequest ����versionֵΪ1.0 */
	public HTTPRequest()
	{
		setVersion(HTTP.VERSION_10);
	}

	public HTTPRequest(InputStream in)
	{
		super(in);
	}

	public HTTPRequest(HTTPSocket httpSock)
	{
		this(httpSock.getInputStream());
		setSocket(httpSock);
	}

	// //////////////////////////////////////////////
	// Method
	// //////////////////////////////////////////////

	/** ˽�е�String���� method ������ķ�����������POST��GET��HEAD��OPTIONS��DELETE��TRACE��PUT */
	private String method = null;

	/**
	 * ����method��ֵ. method ������ķ�����������POST��GET��HEAD��OPTIONS��DELETE��TRACE��PUT
	 **/
	public void setMethod(String value)
	{
		method = value;
	}

	/**
	 * ��ȡMethod method ������ķ�����������POST��GET��HEAD��OPTIONS��DELETE��TRACE��PUT
	 **/
	public String getMethod()
	{
		if (method != null)
		{
			return method;
		}
		return getFirstLineToken(0);
	}

	/**
	 * �ж�method��ֵ�Ƿ������method��ֵ��ͬ�������ִ�Сд ��ͬ����true�����򷵻�false
	 */
	public boolean isMethod(String method)
	{
		String headerMethod = getMethod();
		if (headerMethod == null)
		{
			return false;
		}
		return headerMethod.equalsIgnoreCase(method);
	}

	/**
	 * �ж��ǲ���GET������
	 * 
	 * @return �Ƿ���true�����򷵻�false
	 */
	public boolean isGetRequest()
	{
		return isMethod(HTTP.GET);
	}

	/**
	 * �ж��ǲ���POST������
	 * 
	 * @return �Ƿ���true�����򷵻�false
	 */
	public boolean isPostRequest()
	{
		return isMethod(HTTP.POST);
	}

	/**
	 * �ж��ǲ���HEAD������
	 * 
	 * @return �Ƿ���true�����򷵻�false
	 */
	public boolean isHeadRequest()
	{
		return isMethod(HTTP.HEAD);
	}

	/**
	 * �ж��ǲ���SUBSCRIBE������
	 * 
	 * @return �Ƿ���true�����򷵻�false
	 */
	public boolean isSubscribeRequest()
	{
		return isMethod(HTTP.SUBSCRIBE);
	}

	/**
	 * �ж��ǲ���UNSUBSCRIBE������
	 * 
	 * @return �Ƿ���true�����򷵻�false
	 */
	public boolean isUnsubscribeRequest()
	{
		return isMethod(HTTP.UNSUBSCRIBE);
	}

	/**
	 * �ж��ǲ���NOTIFY������
	 * 
	 * @return �Ƿ���true�����򷵻�false
	 */
	public boolean isNotifyRequest()
	{
		return isMethod(HTTP.NOTIFY);
	}

	// //////////////////////////////////////////////
	// URI
	// //////////////////////////////////////////////

	/** String uri */
	private String uri = null;

	/**
	 * ���� uri value uri��ʽ���ַ��� isCheckRelativeURL true���� http��Ե�uri ��false������
	 */
	public void setURI(String value, boolean isCheckRelativeURL)
	{
		uri = value;
		if (isCheckRelativeURL == false)
		{
			return;
		}
		// Thanks for Giordano Sassaroli <sassarol@cefriel.it> (09/02/03)
		uri = HTTP.toRelativeURL(uri);
	}

	/** ����uri */
	public void setURI(String value)
	{
		setURI(value, false);
	}

	/** ��ȡuri */
	public String getURI()
	{
		if (uri != null)
		{
			return uri;
		}
		return getFirstLineToken(1);
	}

	// //////////////////////////////////////////////
	// URI Parameter
	// //////////////////////////////////////////////

	/** ��ȡURI�еĲ��� ���� /ExportContent?id=2 */
	public ParameterList getParameterList()
	{
		// ����ParameterList
		ParameterList paramList = new ParameterList();
		// ��ȡuri
		String uri = getURI();
		if (uri == null)
		{
			return paramList;
		}
		int paramIdx = uri.indexOf('?');
		if (paramIdx < 0)
		{
			return paramList;
		}
		while (0 < paramIdx)
		{
			int eqIdx = uri.indexOf('=', (paramIdx + 1));
			String name = uri.substring(paramIdx + 1, eqIdx);
			int nextParamIdx = uri.indexOf('&', (eqIdx + 1));
			String value = uri.substring(eqIdx + 1,
					(0 < nextParamIdx) ? nextParamIdx : uri.length());
			// ����Parameter����
			Parameter param = new Parameter(name, value);
			// ��ӵ�������
			paramList.add(param);
			paramIdx = nextParamIdx;
		}
		return paramList;
	}

	public String getParameterValue(String name)
	{
		ParameterList paramList = getParameterList();
		return paramList.getValue(name);
	}

	// //////////////////////////////////////////////
	// SOAPAction
	// //////////////////////////////////////////////

	/** �ж���û��SOAPACTIONͷ �з���true�����򷵻�false */
	public boolean isSOAPAction()
	{
		return hasHeader(HTTP.SOAP_ACTION);
	}

	// //////////////////////////////////////////////
	// Host / Port
	// //////////////////////////////////////////////

	/** �����ַ */
	private String requestHost = "";

	/** ���������ַ */
	public void setRequestHost(String host)
	{
		requestHost = host;
	}

	/** ��ȡ�����ַ */
	public String getRequestHost()
	{
		return requestHost;
	}

	/** ����˿� */
	private int requestPort = -1;

	/** ��������˿� */
	public void setRequestPort(int host)
	{
		requestPort = host;
	}

	/** ��ȡ����˿� */
	public int getRequestPort()
	{
		return requestPort;
	}

	// //////////////////////////////////////////////
	// Socket
	// //////////////////////////////////////////////

	/** HTTPSocket httpSocket */
	private HTTPSocket httpSocket = null;

	/** ����httpSocket */
	public void setSocket(HTTPSocket value)
	{
		httpSocket = value;
	}

	/** ��ȡhttpSocket */
	public HTTPSocket getSocket()
	{
		return httpSocket;
	}

	// ///////////////////////// /////////////////////
	// local address/port
	// //////////////////////////////////////////////

	/** ��ȡ������ַ */
	public String getLocalAddress()
	{
		return getSocket().getLocalAddress();
	}

	/** ��ȡ�����˿� */
	public int getLocalPort()
	{
		return getSocket().getLocalPort();
	}

	// //////////////////////////////////////////////
	// parseRequest
	// //////////////////////////////////////////////

	public boolean parseRequestLine(String lineStr)
	{
		StringTokenizer st = new StringTokenizer(lineStr,
				HTTP.REQEST_LINE_DELIM);
		if (st.hasMoreTokens() == false)
			return false;
		setMethod(st.nextToken());
		if (st.hasMoreTokens() == false)
			return false;
		setURI(st.nextToken());
		if (st.hasMoreTokens() == false)
			return false;
		setVersion(st.nextToken());
		return true;
	}

	// //////////////////////////////////////////////
	// First Line
	// //////////////////////////////////////////////

	public String getHTTPVersion()
	{
		if (hasFirstLine() == true)
			return getFirstLineToken(2);
		return "HTTP/" + super.getVersion();
	}

	/** ��ȡ�������ַ��� */
	public String getFirstLineString()
	{
		return getMethod() + " " + getURI() + " " + getHTTPVersion()
				+ HTTP.CRLF;
	}

	// //////////////////////////////////////////////
	// getHeader
	// //////////////////////////////////////////////

	/** ��ȡһ��������http���󣬰��������кͶ����Ϣͷ */
	public String getHeader()
	{
		StringBuffer str = new StringBuffer();

		str.append(getFirstLineString());

		String headerString = getHeaderString();
		str.append(headerString);

		return str.toString();
	}

	// //////////////////////////////////////////////
	// isKeepAlive
	// //////////////////////////////////////////////

	public boolean isKeepAlive()
	{
		if (isCloseConnection() == true)
			return false;
		if (isKeepAliveConnection() == true)
			return true;
		String httpVer = getHTTPVersion();
		boolean isHTTP10 = (0 < httpVer.indexOf("1.0")) ? true : false;
		if (isHTTP10 == true)
			return false;
		return true;
	}

	// //////////////////////////////////////////////
	// read
	// //////////////////////////////////////////////

	/** ��ȡ���ݵķ��� */
	public boolean read()
	{
		return super.read(getSocket());
	}

	// //////////////////////////////////////////////
	// POST (Response)
	// //////////////////////////////////////////////

	/**
	 * ������Ӧ��Ϣ
	 * 
	 * @param httpRes
	 * @return
	 */
	public boolean post(HTTPResponse httpRes)
	{
		// ��ȡHTTPSocket
		HTTPSocket httpSock = getSocket();
		long offset = 0;
		// ��ȡ���ݵĳ���
		long length = httpRes.getContentLength();

		if (hasContentRange() == true)
		{
			// ��ȡ��λֵ
			long firstPos = getContentRangeFirstPosition();
			// ��ȡ����λ��
			long lastPos = getContentRangeLastPosition();

			// Thanks for Brent Hills (10/26/04)
			if (lastPos <= 0)
			{
				lastPos = length - 1;
			}
			if ((firstPos > length) || (lastPos > length))
			{
				return returnResponse(HTTPStatus.INVALID_RANGE);
			}
			// ����ContentRangeͷ
			httpRes.setContentRange(firstPos, lastPos, length);
			// ����״̬��
			httpRes.setStatusCode(HTTPStatus.PARTIAL_CONTENT);

			offset = firstPos;
			length = lastPos - firstPos + 1;
		}
		return httpSock.post(httpRes, offset, length, isHeadRequest());
		// httpSock.close();
	}

	// //////////////////////////////////////////////
	// POST (Request)
	// //////////////////////////////////////////////

	/** POST �ύ��Socket */
	private Socket postSocket = null;

	/** �������� */
	public HTTPResponse post(String host, int port, boolean isKeepAlive)
	{
		// ����һ��HTTPResponse����
		HTTPResponse httpRes = new HTTPResponse();

		// ����������host
		setHost(host);

		// ���ñ�������
		setConnection((isKeepAlive == true) ? HTTP.KEEP_ALIVE : HTTP.CLOSE);

		// �ж�method�ǲ���HEAD
		boolean isHeaderRequest = isHeadRequest();

		OutputStream out = null;
		InputStream in = null;

		try
		{
			if (postSocket == null)
			{
				// Thanks for Hao Hu
				// ����һ��socket
				postSocket = new Socket();
				// ���ӣ���ָ�����ӳ�ʱΪ80000����
				postSocket.connect(new InetSocketAddress(host, port),
						HTTPServer.DEFAULT_TIMEOUT);
			}

			// ��ȡ�����
			out = postSocket.getOutputStream();
			PrintStream pout = new PrintStream(out);
			// д������ͷ��Ϣ
			pout.print(getHeader());
			// д��һ���س�����
			pout.print(HTTP.CRLF);

			boolean isChunkedRequest = isChunked();
			// ��ȡ�����ַ���
			String content = getContentString();
			int contentLength = 0;
			if (content != null)
			{
				// �������ݵĳ���
				contentLength = content.length();
			}

			if (0 < contentLength)
			{
				if (isChunkedRequest == true)
				{
					// Thanks for Lee Peik Feng <pflee@users.sourceforge.net>
					// (07/07/05)
					// �����ݵĳ���ת��Ϊ16���Ƶ��ַ���
					String chunSizeBuf = Long.toHexString(contentLength);
					// д�����ݳ���
					pout.print(chunSizeBuf);
					// д���س�����
					pout.print(HTTP.CRLF);
				}
				// д������
				pout.print(content);
				if (isChunkedRequest == true)
				{
					pout.print(HTTP.CRLF);
				}
			}

			if (isChunkedRequest == true)
			{
				pout.print("0");
				pout.print(HTTP.CRLF);
			}
			// ˢ�»�����
			pout.flush(); 

			// ��ȡInputStream
			in = postSocket.getInputStream();
			httpRes.set(in, isHeaderRequest); 
			
 
		}
		catch (SocketException e)
		{
			// ����״̬��Ϊ500
			httpRes.setStatusCode(HTTPStatus.INTERNAL_SERVER_ERROR);
			Debug.warning(e);
		}
		catch (IOException e)
		{
			// Socket create but without connection
			// TODO Blacklistening the device
			// ����״̬��Ϊ500
			httpRes.setStatusCode(HTTPStatus.INTERNAL_SERVER_ERROR);
			Debug.warning(e);
		}
		finally
		{
			// ������������Ӿ͹ر���Դ
			if (isKeepAlive == false)
			{
				try
				{
					in.close();
				}
				catch (Exception e)
				{
				}

				if (in != null)
				{
					try
					{
						out.close();
					}
					catch (Exception e)
					{
					}
				}
				if (out != null)
				{
					try
					{
						postSocket.close();
					}
					catch (Exception e)
					{
					}
				}
				postSocket = null;
			}
		}

		return httpRes;
	}

	/**
	 * post�ύ
	 * 
	 * @param host
	 *            �ύ��������ַ
	 * @param port
	 *            �ύ�������˿�
	 * @return ����һ�� HTTPResponse
	 */
	public HTTPResponse post(String host, int port)
	{
		return post(host, port, false);
	}

	// //////////////////////////////////////////////
	// set
	// //////////////////////////////////////////////

	/**
	 * ��httpReq������ֵ��ֵ�������Ӧ������ֵ,����socket��ֵ�������socket
	 * 
	 * @param httpReq
	 */
	public void set(HTTPRequest httpReq)
	{
		set((HTTPPacket) httpReq);
		setSocket(httpReq.getSocket());
	}

	// //////////////////////////////////////////////
	// OK/BAD_REQUEST
	// //////////////////////////////////////////////

	/** ����״̬����Ӧ */
	public boolean returnResponse(int statusCode)
	{
		// ����HTTPResponse����
		HTTPResponse httpRes = new HTTPResponse();
		// ����״̬��
		httpRes.setStatusCode(statusCode);
		// �������ݳ���
		httpRes.setContentLength(0);
		return post(httpRes);
	}

	/** ����200��״̬�� */
	public boolean returnOK()
	{
		return returnResponse(HTTPStatus.OK);
	}

	/** ����400��״̬�� ָ���ͻ��������е��﷨���� */
	public boolean returnBadRequest()
	{
		return returnResponse(HTTPStatus.BAD_REQUEST);
	}

	// //////////////////////////////////////////////
	// toString
	// //////////////////////////////////////////////

	/** ����һ��������http���� */
	@Override
	public String toString()
	{
		StringBuffer str = new StringBuffer();

		str.append(getHeader());
		str.append(HTTP.CRLF);
		str.append(getContentString());

		return str.toString();
	}

	/** ��ӡһ��������http���� */
	public void print()
	{
		System.out.println(toString());
	}
}
