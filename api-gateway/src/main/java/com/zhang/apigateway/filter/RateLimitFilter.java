package com.zhang.apigateway.filter;

import com.google.common.util.concurrent.RateLimiter;

import com.netflix.zuul.ZuulFilter;
import com.zhang.apigateway.exception.RateLimitException;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;

/**
 * 限流
 * Created by 廖师兄
 * 2018-03-11 23:44
 */
@Component
public class RateLimitFilter extends ZuulFilter{

	private static final RateLimiter RATE_LIMITER = RateLimiter.create(100);

	/**
	 * to classify a filter by type. Standard types in Zuul are "pre" for pre-routing filtering,
	 * "route" for routing to an origin, "post" for post-routing filters, "error" for error handling.
	 * We also support a "static" type for static responses see  StaticResponseFilter.
	 * Any filterType made be created or added and run by calling FilterProcessor.runFilters(type)
	 *
	 * @return A String representing that type
	 */
	@Override
	public String filterType() {
		return PRE_TYPE;
	}

	/**
	 * filterOrder() must also be defined for a filter. Filters may have the same  filterOrder if precedence is not
	 * important for a filter. filterOrders do not need to be sequential.
	 *
	 * @return the int order of a filter
	 */
	@Override
	public int filterOrder() {
		return SERVLET_DETECTION_FILTER_ORDER - 1;
	}

	/**
	 * a "true" return from this method means that the run() method should be invoked
	 *
	 * @return true if the run() method should be invoked. false will not invoke the run() method
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/**
	 * if shouldFilter() is true, this method will be invoked. this method is the core method of a ZuulFilter
	 *
	 * @return Some arbitrary artifact may be returned. Current implementation ignores it.
	 */
	@Override
	public Object run() {
		if (!RATE_LIMITER.tryAcquire()) {
			throw new RateLimitException();
		}

		return null;
	}
}
