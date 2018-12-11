package idv.hsiehpinghan.springbootstarterwebboot2.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
public class HttpLoggingFilter implements Filter {
	private final Logger LOGGER = LoggerFactory.getLogger(HttpLoggingFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// do nothing
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		try {
			chain.doFilter(request, response);
		} catch (Throwable a) {
			LOGGER.error(a.getMessage());
		} finally {
			LOGGER.info("request : method({}) uri({}); response : status({})", httpServletRequest.getMethod(),
					httpServletRequest.getRequestURI(), httpServletResponse.getStatus());
		}
	}

	@Override
	public void destroy() {
		// do nothing
	}

}
