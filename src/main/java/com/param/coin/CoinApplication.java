package com.param.coin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class CoinApplication {

	private static final Logger log = LoggerFactory.getLogger(CoinApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CoinApplication.class, args);
	}

	@Autowired
	JdbcTemplate jdbcTemplate;

	@PostConstruct
	private void initDb() {

		log.info("Creating tables");

		jdbcTemplate.execute("DROP TABLE invoice IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE invoice(" +
									 "dca VARCHAR(100), amount double, PAID_AMOUNT double, status VARCHAR(15), "
									 + "creation_time BIGINT, PRIMARY KEY (`dca`))");

		jdbcTemplate.execute("DROP TABLE payment IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE payment(" +
									 "id VARCHAR(36), amount double, type VARCHAR(15), "
									 + "creation_time BIGINT, invoice_dca VARCHAR(100), PRIMARY KEY (`id`),"
									 + "CONSTRAINT `FK_i_p` FOREIGN KEY (`invoice_dca`) REFERENCES `invoice` (`dca`))");
	}
}
