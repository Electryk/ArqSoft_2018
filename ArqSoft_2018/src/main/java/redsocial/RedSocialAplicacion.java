package redsocial;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.*;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.server.Neo4jServer;
import org.springframework.data.neo4j.server.RemoteServer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EntityScan("redsocial.dominio")
@EnableNeo4jRepositories("redsocial.repositorio")
public class RedSocialAplicacion extends Neo4jConfiguration {

	public RedSocialAplicacion() {
		System.setProperty("username", "neo4j");
		System.setProperty("password", "1234");
}
	public static void main(String[] args) {
		SpringApplication.run(RedSocialAplicacion.class, args);
	}

	@Override
	@Bean
	public SessionFactory getSessionFactory() {
		return new SessionFactory(getConfiguration(), "redsocial.dominio");
	}
	
	@Bean
	public Neo4jTransactionManager transactionManager() throws Exception {
		return new Neo4jTransactionManager(getSessionFactory());
	}
	  
	@Bean
	public org.neo4j.ogm.config.Configuration getConfiguration() {
	    return new org.neo4j.ogm.config.Configuration.Builder()
	    	.uri("bolt://localhost")
	    	.credentials(System.getProperty("username"),
	    				 System.getProperty("password"))
	    	.build();
	}
	
	@Override
	@Bean
	@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
	public Session getSession() throws Exception {
		return super.getSession();
	}

}