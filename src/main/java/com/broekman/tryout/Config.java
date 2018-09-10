package com.broekman.tryout;

import org.apache.camel.processor.idempotent.jdbc.JdbcMessageIdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

@Configuration
public class Config {

    /*@Autowired
    private EntityManagerFactory em;*/

    @Autowired
    private DataSource dataSource;

    /*@Autowired
    TransactionManager transactionManager;*/

    @Autowired
    TransactionTemplate transactionTemplate;

    private TransactionTemplate transactionTemplate() {
        //System.out.println("before: " + transactionTemplate.getIsolationLevel());
        transactionTemplate.setIsolationLevel(Isolation.SERIALIZABLE.value());
        //System.out.println("after: " + transactionTemplate.getIsolationLevel());
        return transactionTemplate;
    }

    /*@Value("${instance.name}")
    private String name;*/

    /*@Bean(name = "fileRepo")
    public FileIdempotentRepository fileItem() {
        FileIdempotentRepository r = new FileIdempotentRepository();
        r.setFileStore(new File("target/filestore.dat"));
        return r;
    }*/

    /*@Bean(name = "jpaRepo")
    public JpaMessageIdRepository jpaItem() {
        JpaMessageIdRepository r = new JpaMessageIdRepository(em, name);
        return r;
    }*/

    @Bean(name = "jdbcRepoIdem")
    public JdbcMessageIdRepository jdbcRepoIdem() {
        JdbcMessageIdRepository r = new JdbcMessageIdRepository(dataSource, transactionTemplate(), "idem");
        return r;
    }

    /*@Bean(name = "jdbcRepoProg")
    public JdbcMessageIdRepository jdbcRepoProg() {
        JdbcMessageIdRepository r = new JdbcMessageIdRepository(dataSource, transactionTemplate(), "prog");

        return r;
    }*/

}
