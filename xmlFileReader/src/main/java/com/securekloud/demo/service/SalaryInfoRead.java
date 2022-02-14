package com.securekloud.demo.service;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SalaryInfoRead extends DefaultHandler{
	boolean perName=false;
	boolean salary=false;
	boolean pension=false;
	
	ArrayList<String[]> tList = new ArrayList<String[]>();
	String[] array = new String[3];
	
	public void startElement(String uri, String localName,
			String qName, Attributes attributes) throws SAXException{
		System.out.println("start sal= " + qName);
		if("person".equals(qName)) {
			if(attributes!=null && attributes.getLength()==1) {
				array[0]=attributes.getValue(0);
			}
			perName = true;
		}
		if("salary".equals(qName)) {
			salary=true;
		}
		if("pension".equals(qName)) {
			pension=true;
		}
	}
	
	public void characters(char ch[], int start, int length) 
			throws SAXException{
		System.out.println("ch sal= " + new String(ch, start, length));
			if(perName) {perName=false;}
			if(salary) {
				String val = new String(ch, start, length).trim();
				array[1]=val;
				salary=false;
			}
			if(pension) {
				String val = new String(ch, start, length).trim();
				array[2]=val;
				pension=false;
			}
		}
	
	public void endElement(String uri, String localName, String qName) 
			throws SAXException {
		System.out.println("end sal= " + qName);
		if("person".equals(qName)) {
			tList.add(array);
			System.out.println("final = "+array[0]+array[1]+array[2]);
			array=new String[3];
		}
	}
	
	public ArrayList<String[]> getList(){
		return this.tList;
	}
}
