package com.example.ominitrixw.entities;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.util.Properties;
import java.util.stream.Stream;

public class IdGenerator implements IdentifierGenerator {
    private String prefix;
    private String table;
    private String column;
    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        prefix = params.getProperty("prefix");
        table = params.getProperty("table");
        column = params.getProperty("column");
    }

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        String query = "SELECT e."+column+" FROM "+table+" e";
        Stream<String> ids = session.createQuery(query, String.class).stream();
        Long max = ids.map(o -> o.replace(prefix, "")).mapToLong(Long::parseLong).max().orElse(0L);
        return prefix + (String.format("%02d", max + 1));

    }
}
