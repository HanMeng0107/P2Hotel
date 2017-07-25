/******************************************************************
*
*	CyberHTTP for Java
*
*	Copyright (C) Satoshi Konno 2002
*
*	File: HTTPHeader.java
*
*	Revision;
*
*	11/19/02
*		- first revision.
*	05/26/04
*		- Jan Newmarch <jan.newmarch@infotech.monash.edu.au> (05/26/04)
*		- Fixed getValue() to compare using String::equals() instead of String::startWidth().
*	
******************************************************************/

package com.hejunlin.liveplayback.playfile;

import com.hejunlin.liveplayback.utils.Debug;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;


public class HTTPHeader 
{
	private static int MAX_LENGTH = 1024;
	
	private String name;
	private String value;

	/** ����һ�� HTTPHeader����
	 *	@param name ��ֵ��HTTPHeader��name�ֶ�
	 *	@param value ��ֵ��HTTPHeader��value�ֶ�
	 */
	public HTTPHeader(String name, String value)
	{
		setName(name);
		setValue(value);
	}

	/** ͨ����Ϣͷ������Ϣͷ�����֣�����Ϣͷ��ֵ */
	public HTTPHeader(String lineStr)
	{
		setName("");
		setValue("");
		if (lineStr == null){
			return;
		}
		int colonIdx = lineStr.indexOf(':');
		if (colonIdx < 0){
			return;
		}
		//��ȡ��Ϣͷ������
		String name = new String(lineStr.getBytes(), 0, colonIdx);		
		//��ȡ��Ϣͷ��ֵ
		String value = new String(lineStr.getBytes(), colonIdx+1, lineStr.length()-colonIdx-1);		
		//������Ϣͷ������
		setName(name.trim());
		//������Ϣͷ��ֵ
		setValue(value.trim());
	}

	////////////////////////////////////////////////
	//	Member
	////////////////////////////////////////////////
	
	/** ����name */
	public void setName(String name)
	{
		this.name = name;
	}
	/** ����value */
	public void setValue(String value)
	{
		this.value = value;
	}

	/** ��ȡname */
	public String getName()
	{
		return name;
	}

	/** ��ȡvalue */
	public String getValue()
	{
		return value;
	}

	/** �ж���Ϣͷ�������Ƿ�Ϊnull����ַ������Ƿ���false�����򷵻�true */
	public boolean hasName()
	{
		if (name == null ||  name.length() <= 0){
			return false;
		}
		return true;
	}
	
	////////////////////////////////////////////////
	//	static methods
	////////////////////////////////////////////////
	//HOST: 239.255.255.250:1900
	/** ���ݶ�Ӧ��name������Ϣͷ��ֵ */
	public final static String getValue(LineNumberReader reader, String name)
	{
		//��nameת���ɴ�д
		String bigName = name.toUpperCase();
		try {
			//��ȡһ��
			String lineStr = reader.readLine();
			while (lineStr != null && 0 < lineStr.length()) {
				//������Ϣͷ
				HTTPHeader header = new HTTPHeader(lineStr);
				if (header.hasName() == false) {
					 lineStr = reader.readLine();
					continue;
				}
				String bigLineHeaderName = header.getName().toUpperCase();
				// Thanks for Jan Newmarch <jan.newmarch@infotech.monash.edu.au> (05/26/04)
				if (bigLineHeaderName.equals(bigName) == false) {
					 lineStr = reader.readLine();
					 continue;
				}
				return header.getValue();
			}
		}
		catch (IOException e) {
			Debug.warning(e);
			return "";
		}
		return "";
	}

	/** ���ݶ�Ӧ��name������Ϣͷ��ֵ */
	public final static String getValue(String data, String name)
	{
		/* Thanks for Stephan Mehlhase (2010-10-26) */
		//����һ��StringReader
		StringReader strReader = new StringReader(data);
		//����LineNumberReader ����
		LineNumberReader lineReader = new LineNumberReader(strReader, Math.min(data.length(), MAX_LENGTH));
		return getValue(lineReader, name);
	}

	/** ���ݶ�Ӧ��name������Ϣͷ��ֵ */
	public final static String getValue(byte[] data, String name)
	{
		return getValue(new String(data), name);
	}

	public final static int getIntegerValue(String data, String name)
	{
		try {
			return Integer.parseInt(getValue(data, name));
		}
		catch (Exception e) {
			return 0;
		}
	}

	/** ����name��ȡdata�е���Ϣͷ������int */
	public final static int getIntegerValue(byte[] data, String name)
	{
		try {
			return Integer.parseInt(getValue(data, name));
		}
		catch (Exception e) {
			return 0;
		}
	}
}
