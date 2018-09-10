package com.broekman.tryout;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.idempotent.jdbc.JdbcMessageIdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MySpringBootRouter extends RouteBuilder {

    @Autowired
    JdbcMessageIdRepository jdbcRepoIdem;

    static String age = "1 minute";

    //readLock=idempotent&idempotentRepository=#jdbcRepo&
    //?inProgressRepository=#jdbcRepo
    @Override
    public void configure() {
        from("file:in?{{file.params}}")
                .routeId("poller")
                //.delayer(5000l)
                .to("log:{{instance.name}}")
                .to("file:out/{{instance.name}}");

        from("timer:clean?period=30s&delay=30s")
                .log("Cleaning " + age + " old entries with processor name " + jdbcRepoIdem.getProcessorName() + " from read lock repo")
                .to("sql:delete from camel_messageprocessed where processorname = '" + jdbcRepoIdem.getProcessorName() + "' and createdat < NOW() - INTERVAL '" + age + "';?dataSource=#jdbcRepoIdem");
    }
}
