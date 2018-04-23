package redsocial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories("redsocial")
public class RedSocialAplicacion{

    private final static Logger log = LoggerFactory.getLogger(RedSocialAplicacion.class);
	public static void main(String[] args) {
		SpringApplication.run(RedSocialAplicacion.class, args);
	}

}