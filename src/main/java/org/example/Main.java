package org.example;

import org.example.address.Address;
import org.example.student.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Project started");

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();

        List<Student> students = new ArrayList<>();
        List<Address> addresses = new ArrayList<>();

        // Creating multiple students and addresses
        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setId(100 + i);
            student.setName("Student " + (i + 1));
            student.setCity("City " + (i + 1));

            Address address = new Address();
            address.setStreet("Street " + (i + 1));
            address.setCity("City " + (i + 1));
            address.setOpen(true);
            address.setAddedDate(new Date());
            address.setX(1234.234);

            students.add(student);
            addresses.add(address);
        }

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        // Saving students and addresses
        for (int i = 0; i < students.size(); i++) {
            session.save(addresses.get(i));
            session.save(students.get(i));
        }

        tx.commit();
        session.close();

        System.out.println("Program successfully run");
    }
}
