package com.securekloud.demo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.helpers.DefaultHandler;

import com.securekloud.demo.model.UserInfo;
import com.securekloud.demo.repository.UserInfoRepo;

import antlr.collections.List;

@Service
public class PreInsertDataInDb {
	
	private UserInfoRepo userRepo;

	public void insertXmlDataInDb(UserInfoRepo userRepo) {
		this.userRepo=userRepo;
		String file1="src/main/webapp/xml/firstXml.xml";
		String file2="src/main/webapp/xml/secondXml.xml";
		
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();  
			SAXParser saxParser = factory.newSAXParser();
			EmpInfoRead empHandler = new EmpInfoRead();
			SalaryInfoRead salaryHandler = new SalaryInfoRead();
			saxParser.parse(file1, empHandler);
			saxParser.parse(file2, salaryHandler);
			setXmlDataInObj(empHandler.getList(), salaryHandler.getList());
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	public void setXmlDataInObj(ArrayList<String[]> cusInfo
			, ArrayList<String[]> salInfo) {
		//delete all data in table 
		userRepo.deleteAll();
		
		Iterator<String[]> s1List = cusInfo.iterator();
		Set<UserInfo> userList = new HashSet<UserInfo>();
		
		
		while(s1List.hasNext()) {
			UserInfo obj = new UserInfo();
			String[] s1 = s1List.next();
			System.out.println("\n"+"\n"+"\n");
			System.out.println("UINFO = "+s1[0]+"-"+s1[1]+"-"+s1[2]);
			obj.setName(s1[0]);
			obj.setAddress(s1[1]);
			obj.setNumber(s1[2]);
			for(int i=0; i<salInfo.size(); i++) {
				String[] s2 = salInfo.get(i);
				System.out.println("\n"+"\n"+"\n");
				System.out.println("SALARYINFO = "+s2[0]+"-"+s2[1]+"-"+s2[2]);
				System.out.println("check if hava name= "+s2[0]+"="+s1[0]+"\t"+s2[0].equals(s1[0]));
				if(s2[0].equals(s1[0])) {
					obj.setSalary(s2[1]);
					obj.setPension(s2[2]);
					salInfo.remove(i);
					break;
				}
			}
			userList.add(obj);
		}
		
		System.out.println("lenght of object list="+userList.size());
		for(UserInfo u : userList) {
			System.out.println(u.getName()+"-"+u.getAddress()+"-"
					+u.getSalary()+"-"+u.getPension());
			userRepo.save(u);
			
		}
	}
}
