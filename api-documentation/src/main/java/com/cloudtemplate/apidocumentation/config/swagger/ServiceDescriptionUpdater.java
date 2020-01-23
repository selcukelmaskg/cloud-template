package com.cloudtemplate.apidocumentation.config.swagger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static com.cloudtemplate.shared.constans.ApplicationConstants.DEFAULT_SWAGGER_URL;
import static com.cloudtemplate.shared.constans.ApplicationConstants.KEY_SWAGGER_URL;

@Component
public class ServiceDescriptionUpdater {
    private static final Logger logger = LoggerFactory.getLogger(ServiceDescriptionUpdater.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    private final RestTemplate template;

    public ServiceDescriptionUpdater(){
        this.template = new RestTemplate();
    }

    @Autowired
    private ServiceDefinitionsContext definitionContext;

    @Scheduled(fixedDelayString= "${swagger.config.refreshrate}")
    public void refreshSwaggerConfigurations(){
        discoveryClient.getServices().forEach(serviceId -> {
            List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceId);
            if(serviceInstances == null || serviceInstances.isEmpty()){ //Should not be the case kept for failsafe
                logger.info("No instances available for service : {} ",serviceId);
            }else{
                ServiceInstance instance = serviceInstances.get(0);
                String swaggerURL =  getSwaggerURL( instance);

                Optional<Object> jsonData = getSwaggerDefinitionForAPI(serviceId, swaggerURL);

                if(jsonData.isPresent()){
                    String content = getJSON(jsonData.get());
                    definitionContext.addServiceDefinition(serviceId, content);
                }else{
                    logger.error("Skipping service id : {} Error : Could not get Swagegr definition from API ",serviceId);
                }
            }
        });
    }

    private String getSwaggerURL( ServiceInstance instance){
        String swaggerURL = instance.getMetadata().get(KEY_SWAGGER_URL);
        return swaggerURL != null ? instance.getUri()+swaggerURL : instance.getUri()+DEFAULT_SWAGGER_URL;
    }

    private Optional<Object> getSwaggerDefinitionForAPI(String serviceName, String url){
        try{
            Object jsonData = template.getForObject(url, Object.class);
            return Optional.of(jsonData);
        }catch(RestClientException ex){
            logger.error("Error while getting service definition for service : {} Error : {} ", serviceName, ex.getMessage());
            return Optional.empty();
        }

    }

    private String getJSON(Object jsonData){
        try {
            return new ObjectMapper().writeValueAsString(jsonData);
        } catch (JsonProcessingException e) {
            logger.error("Error : {} ", e.getMessage());
            return "";
        }
    }
}
