package com.dmdev;

import com.dmdev.dao.UserDao;
import com.dmdev.entity.Payment;
import com.dmdev.entity.User;
import com.dmdev.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.SQLException;
import java.util.List;

@Slf4j
public class HibernateRunner {

    private static final UserDao userDao = UserDao.getInstance();

    public static void main(String[] args) throws SQLException {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

//            var users = session.createQuery(
//                    "select u from User u " +
//                            "join fetch u.payments " +
//                            "join fetch u.company " +
//                            "where 1 = 1", User.class)
//                    .list();
//            users.forEach(user -> System.out.println(user.getPayments().size()));
//            users.forEach(user -> System.out.println(user.getCompany().getName()));

            List<Payment> applePayments = userDao.findAllPaymentsByCompanyName_(session, "Google");

            session.getTransaction().commit();
        }
    }












}
