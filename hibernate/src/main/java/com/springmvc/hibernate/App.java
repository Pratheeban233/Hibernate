package com.springmvc.hibernate;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.springmvc.hibernate.pojo.Alien;
import com.springmvc.hibernate.pojo.AlienName;
import com.springmvc.hibernate.pojo.Student;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		AlienName an = new AlienName();
		an.setFname("Abiya");
		an.setMname("Karolin");
		an.setLname("Pratheeban");

		Alien alien = new Alien();
		alien.setAid(104);
		alien.setAname(an);
		alien.setTech("Java");

		// Mapping relations

		/*
		 * Laptop lap = new Laptop(); lap.setLid(103); lap.setlName("lenovo");
		 * 
		 * Student st = new Student(); st.setRollNo(3); st.setsName("Pratheeban");
		 * st.setsMark(80); st.getLaptop().add(lap);
		 * 
		 * lap.getStudent().add(st);
		 */

		Configuration con = new Configuration().configure()
				.addAnnotatedClass(Student.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		SessionFactory sf = con.buildSessionFactory(reg);
	
		// Session session = sf.openSession();

		// Transaction tx = session.beginTransaction();
		/*
		 * session.beginTransaction(); // session.save(lap); // session.save(st);
		 * Student stu = (Student) session.get(Student.class, 1);
		 * System.out.println("output::" + stu.toString());
		 * 
		 * // List<Laptop> laps = stu.getLaptop(); // for (Laptop l : laps) // { //
		 * System.out.println("laptops: " + l); // }
		 * 
		 * 
		 * // tx.commit(); session.getTransaction().commit(); //
		 * System.out.println("Output :: "+alien);
		 */
		
		
		//caching - First level cache
		
	/*	Session s1 = sf.openSession();
		s1.beginTransaction();
		Query q = s1.createQuery("from Alien where aid=104");
		q.setCacheable(true);
		Alien st = (Alien) q.uniqueResult();
	//	Alien st = (Alien) s1.get(Alien.class, 104);
		System.out.println("s1 output : "+ st);
	
		Query q1 = s1.createQuery("from Alien where aid=104");
		q1.setCacheable(true);
		Alien st3 = (Alien) q.uniqueResult();
		System.out.println("s13 output : "+ st3); 
	//	Student st2 = (Student) s1.get(Student.class, 1);
	//  System.out.println("st2 output : "+ st2);
		
		s1.getTransaction().commit();
		s1.close();
		
		//Second level cache
		Session s2 = sf.openSession();
		s2.beginTransaction();
		
		Query q1 = s2.createQuery("from Alien where aid=104");
		q1.setCacheable(true);
		Alien st3 = (Alien) q1.uniqueResult();
		System.out.println("s2 output : "+ st3);
		
	//	Alien st1 = (Alien) s2.get(Alien.class, 104);
	//	System.out.println("s2 output : "+ st1);
		
		s2.getTransaction().commit();
		s2.close();
		*/
		
		//HQL
		int b = 60;
		String c= "Name : 1";
		Session session = sf.openSession();
		session.beginTransaction();
	/*	Query q = session.createQuery("select rollNo,Name,Mark from Student where rollNo = :b and Name = :c");
		q.setInteger("b", b);
		q.setString("c", c);
		List<Object[]> student = (List<Object[]>) q.list();
		
		for ( Object[] s : student)
		{
			System.out.println( s[0]+" | "+s[1]+" | "+s[2]);
		}
		 */
	/*	List<Student> stu =q.list();
		for(Student s: stu)
		{
			System.out.println("output : "+s);
		}
		*/
		
		
		
		//Native Queries...
	
		SQLQuery q= session.createSQLQuery("Select name,mark from student where mark >:b");
		q.setInteger("b", b);
	//	q.addEntity(Student.class);
		q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List<Student> sts = q.list();
		for(Student s: sts)
		{
			Map m = (Map)s;
			System.out.println(m.get("name")+" | "+m.get("mark"));
		}
		
		session.getTransaction().commit();
	//	session.close(); 
	}
}
