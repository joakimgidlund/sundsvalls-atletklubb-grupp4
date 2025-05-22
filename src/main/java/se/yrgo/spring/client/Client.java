package se.yrgo.spring.client;

import se.yrgo.spring.domain.*;
import se.yrgo.spring.services.*;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

		try {
			CustomerService customerService = context.getBean("customerService", CustomerService.class);


		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		} finally {
			context.close();
		}

	}
}
