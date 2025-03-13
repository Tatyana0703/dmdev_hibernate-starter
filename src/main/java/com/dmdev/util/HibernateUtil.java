package com.dmdev.util;

import com.dmdev.converter.BirthdayConverter;
import com.dmdev.entity.User;
//import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {

    public static SessionFactory buildSessionFactory() {
        Configuration configuration = buildConfiguration();
        configuration.configure();

        return configuration.buildSessionFactory();
    }

    public static Configuration buildConfiguration() {
        Configuration configuration = new Configuration();
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        configuration.addAnnotatedClass(User.class);
        configuration.addAttributeConverter(new BirthdayConverter());
//        configuration.registerTypeOverride(new JsonBinaryType()); //регистрация нужна только для hibernate 5, для hibernate 6 регистрация не нужна
        return configuration;
    }
}
