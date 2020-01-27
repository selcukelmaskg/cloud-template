package com.cloudtemplate.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import java.util.Set;

import static com.cloudtemplate.shared.constans.ApplicationConstants.AUTHORIZATION_HEADER;

@Component
public class AuthorizationHeaderFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 999;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();

        Set<String> headers = (Set<String>) ctx.get("ignoredHeaders");
        headers.remove(AUTHORIZATION_HEADER);

        return null;
    }
}