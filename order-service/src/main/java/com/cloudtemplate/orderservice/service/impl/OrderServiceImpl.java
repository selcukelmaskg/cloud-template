package com.cloudtemplate.orderservice.service.impl;

import com.cloudtemplate.orderservice.domain.Order;
import com.cloudtemplate.orderservice.service.OrderService;
import com.cloudtemplate.orderservice.client.feign.AssetClient;
import com.cloudtemplate.orderservice.client.feign.ConverterClient;
import com.cloudtemplate.orderservice.dto.CreateOrderRequest;
import com.cloudtemplate.orderservice.dto.CreateOrderResponse;
import com.cloudtemplate.orderservice.repository.OrderRepository;
import com.cloudtemplate.shared.dto.asset.AssetBindingRequest;
import com.cloudtemplate.shared.enumeration.OrderStatus;
import com.cloudtemplate.shared.util.ObjectMapperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.cloudtemplate.shared.constans.ApplicationConstants.ORDER_FAIL_ASSET_MSG;
import static com.cloudtemplate.shared.constans.ApplicationConstants.ORDER_FAIL_CATALOG_MSG;
import static com.cloudtemplate.shared.constans.ApplicationConstants.ORDER_FAIL_CUSTOMER_MSG;
import static com.cloudtemplate.shared.constans.ApplicationConstants.ORDER_FAIL_SYSTEM_MSG;
import static com.cloudtemplate.shared.constans.ApplicationConstants.ORDER_SUCCESS_MSG;

@Service
@CacheConfig(cacheNames = "order")
public class OrderServiceImpl implements OrderService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final OrderRepository repository;
    private final AssetClient assetClient;
    private final ConverterClient converterClient;
    private final Environment environment;

    public OrderServiceImpl(OrderRepository repository,
                            AssetClient assetClient,
                            Environment environment,
                            ConverterClient converterClient) {
        this.repository = repository;
        this.assetClient = assetClient;
        this.environment = environment;
        this.converterClient = converterClient;
    }

    @Override
    @CacheEvict(allEntries = true)
    public CreateOrderResponse save(CreateOrderRequest createOrderRequest) {
        Order order = ObjectMapperUtils.map(createOrderRequest, Order.class);
        order.setStatus(OrderStatus.PENDING);
        order = repository.save(order);

        Order finalOrder = order;
        new Thread(() -> {
            captureOrder(finalOrder);
        }).start();

        log.info("[orderService -> save]: order created !");

        return new CreateOrderResponse(order.getId());
    }

    @Override
    public Boolean existOrder(String orderId) {
        return repository.existsById(orderId);
    }

    @Override
    @Cacheable
    public PageImpl<Order> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Order> orders = repository.findAll(pageRequest);
        return new PageImpl<>(orders.getContent(), pageRequest, repository.count());
    }

    @Override
    public List<Order> orderByTckn(Long tckn) {
        return repository.findByTckn(tckn);
    }

    private void captureOrder(Order order) {
        try {
            if (converterClient.customerValidation(order.getTckn())) {
                if (converterClient.orderFulfilment(order.getProductId())) {
                    String ip = assetClient.assetBinding(new AssetBindingRequest(order.getId()));
                    if (ip != null) {
                        order.setSystemMessage(environment.getProperty(ORDER_SUCCESS_MSG));
                        order.setStatus(OrderStatus.COMPLETED);
                    } else {
                        order.setSystemMessage(environment.getProperty(ORDER_FAIL_ASSET_MSG));
                        order.setStatus(OrderStatus.FAIL);
                    }
                } else {
                    order.setSystemMessage(environment.getProperty(ORDER_FAIL_CATALOG_MSG));
                    order.setStatus(OrderStatus.FAIL);
                }
            } else {
                order.setSystemMessage(environment.getProperty(ORDER_FAIL_CUSTOMER_MSG));
                order.setStatus(OrderStatus.FAIL);
            }
        } catch (Exception e) {
            order.setSystemMessage(environment.getProperty(ORDER_FAIL_SYSTEM_MSG));
            order.setStatus(OrderStatus.FAIL);
        }
        log.info("[orderService -> captureOrder]: send email");
        repository.save(order);
    }
}
