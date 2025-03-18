package com.dmdev;

import com.dmdev.entity.User;
import com.dmdev.entity.UserChat;
import com.dmdev.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.graph.GraphSemantic;
import org.hibernate.graph.RootGraph;

import java.sql.SQLException;
import java.util.Map;

@Slf4j
public class HibernateRunner {

    // без сабграфа и с сабграфом для определения аннотации над классом User @NamedEntityGraph
//    public static void main(String[] args) throws SQLException {
//        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
//             Session session = sessionFactory.openSession()) {
//            session.beginTransaction();
//
////            RootGraph<?> userGraph = session.getEntityGraph("WithCompanyAndChat");
//
//            Map<String, Object> properties = Map.of(
//                    GraphSemantic.LOAD.getJpaHintName(), session.getEntityGraph("WithCompanyAndChat")
//            );
//            var user = session.find(User.class, 1L, properties);
//            System.out.println(user.getCompany().getName());
//            System.out.println(user.getUserChats().size());
//
//
//            var users = session.createQuery(
//                            "select u from User u " +
//                                    "where 1 = 1", User.class)
//                    .setHint(GraphSemantic.LOAD.getJpaHintName(), session.getEntityGraph("WithCompanyAndChat"))
////                    .setHint(GraphSemantic.LOAD.getJpaHintName(), userGraph)
//                    .list();
//            users.forEach(it -> System.out.println(it.getUserChats().size()));
//            users.forEach(it -> System.out.println(it.getCompany().getName()));
//
//            session.getTransaction().commit();
//        }
//    }

    //для программного опеределния entity графа
    public static void main(String[] args) throws SQLException {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            var userGraph = session.createEntityGraph(User.class);
            userGraph.addAttributeNodes("company", "userChats");
            var userChatsSubgraph = userGraph.addSubgraph("userChats", UserChat.class);
            userChatsSubgraph.addAttributeNodes("chat");

            //передаем граф в наши запросы
            Map<String, Object> properties = Map.of(
                    GraphSemantic.LOAD.getJpaHintName(), userGraph
            );
            var user = session.find(User.class, 1L, properties);
            System.out.println(user.getCompany().getName());
            System.out.println(user.getUserChats().size());

            var users = session.createQuery(
                    "select u from User u " +
                            "where 1 = 1", User.class)
                    .setHint(GraphSemantic.LOAD.getJpaHintName(), userGraph)
                    .list();
            users.forEach(it -> System.out.println(it.getUserChats().size()));
            users.forEach(it -> System.out.println(it.getCompany().getName()));

            session.getTransaction().commit();
        }
    }












}
