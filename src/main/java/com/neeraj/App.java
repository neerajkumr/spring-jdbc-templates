package com.neeraj;

import java.util.Scanner;
import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

	public static final Logger log = Logger.getLogger("App");

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(SpringConfig.class);
		NamedParamEmployeeDao dao = (NamedParamEmployeeDao) app.getBean(NamedParamEmployeeDao.class);

		Scanner sc = new Scanner(System.in);
		int type = 1;
		String name = "";
		int id;
		String DOB;
		while (type > 0 && type < 4) {
			System.out.println("1 -> insert \n 2->getAll \n 3->getByID \n 4->exit");
			type = sc.nextInt();
			switch (type) {
			case 1: {
				System.out.println("Enter the name ,id, dob");
				name = sc.next();
				id = sc.nextInt();
				DOB = sc.next();
				dao.save(new Employee(name, id, DOB));
				log.info("Employee inserted successful");
				break;
			}
			case 2: {
				dao.getAll();
				log.info("The Current data present in the table");
				break;
			}
			case 3: {
				System.out.println("Enter ID to get details of the employee");
				id = sc.nextInt();
				dao.getByID(id);
				log.info("=Details based on the ID=");
				break;
			}
			default: {
				System.exit(0);
			}
			}
		}
		sc.close();
	}
}