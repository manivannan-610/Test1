package com.securekloud.demo.service;

import java.util.ArrayList;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class EmpInfoRead extends DefaultHandler{
	boolean perName=false;
	boolean address=false;
	boolean phone=false;
	Stack<String> stack = new Stack<String>();
	ArrayList<String[]> tList = new ArrayList<String[]>();
	String[] array = new String[3];
	public void startElement(String uri, String localName,
			String qName, Attributes attributes) throws SAXException{
		System.out.println("start El= " + qName);
		if("person".equals(qName)) {
			if(attributes!=null && attributes.getLength()==1) {
				array[0]=attributes.getValue(0);
			}
			perName = true;
		}
		if("address".equals(qName)) {
			address=true;
		}
		if("phonenumber".equals(qName)) {
			phone=true;
		}
	}
	
	public void characters(char ch[], int start, int length) 
			throws SAXException{
		System.out.println("ch El= " + new String(ch, start, length));
			if(perName) {perName=false;}
			if(address) {
				String val = new String(ch, start, length).trim();
				array[1]=val;
				address=false;
			}
			if(phone) {
				String val = new String(ch, start, length).trim();
				array[2]=val;
				phone=false;
			}
		}
	
	public void endElement(String uri, String localName, String qName) 
			throws SAXException {
		System.out.println("end El= " + qName);
		if("person".equals(qName)) {
			tList.add(array);
			System.out.println(array[0]+array[1]+array[2]);
			array=new String[3];
		}
	}
	
	public ArrayList<String[]> getList(){
		return this.tList;
	}
}
