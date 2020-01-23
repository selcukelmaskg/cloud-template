package com.poc.converterservice;

import com.crmpoc.catalog.CatalogPort;
import com.crmpoc.catalog.CatalogPortService;
import com.crmpoc.customer.CustomerPort;
import com.crmpoc.customer.CustomerPortService;
import com.poc.shared.util.DefaultProfileUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import javax.xml.ws.BindingProvider;
import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class ConverterServiceApplication {

	private static final Logger log = LoggerFactory.getLogger(ConverterServiceApplication.class);

	@Value("${customer-service.url}")
	private String customerServiceUrl;

	@Value("${catalog-service.url}")
	private String catalogServiceUrl;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ConverterServiceApplication.class);
		DefaultProfileUtil.addDefaultProfile(app);
		Environment env = app.run(args).getEnvironment();
		logApplicationStartup(env);
	}

	private static void logApplicationStartup(Environment env) {
		String protocol = "http";
		if (env.getProperty("server.ssl.key-store") != null) {
			protocol = "https";
		}
		String serverPort = env.getProperty("server.port");
		String contextPath = env.getProperty("server.servlet.context-path");
		if (StringUtils.isBlank(contextPath)) {
			contextPath = "/";
		}
		String hostAddress = "localhost";
		try {
			hostAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			log.error("The host name could not be determined, using `localhost` as fallback");
		}
		log.info("\n----------------------------------------------------------\n\t" +
						"Application '{}' is running! Access URLs:\n\t" +
						"Local: \t\t{}://localhost:{}{}\n\t" +
						"External: \t{}://{}:{}{}\n\t" + "\n----------------------------------------------------------",
				env.getProperty("spring.application.name"),
				protocol,
				serverPort,
				contextPath,
				protocol,
				hostAddress,
				serverPort,
				contextPath);
	}

	@Bean
	public CustomerPort customerClient() {
		CustomerPort port = new CustomerPortService().getCustomerPortSoap11();

		((BindingProvider)port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY ,  this.customerServiceUrl);

		return port;
	}

	@Bean
	public CatalogPort catalogClient() {
		CatalogPort port = new CatalogPortService().getCatalogPortSoap11();

		((BindingProvider)port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY ,  this.catalogServiceUrl);

		return port;
	}
}
