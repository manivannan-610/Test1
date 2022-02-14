package com.securekloud.demo;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xml.sax.helpers.DefaultHandler;

import com.securekloud.demo.repository.UserInfoRepo;
import com.securekloud.demo.service.EmpInfoRead;
import com.securekloud.demo.service.PreInsertDataInDb;
import com.securekloud.demo.service.SalaryInfoRead;

@SpringBootApplication
public class XmlFileReaderApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(XmlFileReaderApplication.class, args);
	}
	
	@Autowired
	private UserInfoRepo userRepo;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("commandListn runner....");
		PreInsertDataInDb insert = new PreInsertDataInDb();
		insert.insertXmlDataInDb(userRepo);
	}


}
