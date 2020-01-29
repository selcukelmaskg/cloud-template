package com.cloudtemplate.apigateway;

import com.cloudtemplate.shared.util.DefaultProfileUtil;
import com.cloudtemplate.shared.util.HealthCheckUtil;
import io.micrometer.core.instrument.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@EnableAsync
public class ApiGatewayApplication {
	private static final Logger log = LoggerFactory.getLogger(ApiGatewayApplication.class);

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ApiGatewayApplication.class);
		DefaultProfileUtil.addDefaultProfile(app);
		HealthCheckUtil.configServerHealthCheck();
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

		String configServerUri = env.getProperty("spring.cloud.config.uri");
		log.info("\n----------------------------------------------------------\n\t" +
				"Config Server: \t{}\n----------------------------------------------------------", configServerUri);
	}
}
