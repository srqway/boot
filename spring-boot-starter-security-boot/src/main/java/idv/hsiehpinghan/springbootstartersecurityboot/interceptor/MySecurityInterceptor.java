package idv.hsiehpinghan.springbootstartersecurityboot.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

//@Component
public class MySecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

	// 配置文件注入
	@Autowired
	@Qualifier("myFilterInvocationSecurityMetadataSource")
	private SecurityMetadataSource myFilterInvocationSecurityMetadataSource;
	@Autowired
	@Qualifier("myAccessDecisionManager")
	private AccessDecisionManager myAccessDecisionManager;

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// do nothing
	}

	@Override
	public void destroy() {
		// do nothing
	}

	public void afterPropertiesSet() throws Exception {
		setAccessDecisionManager(myAccessDecisionManager);
		super.afterPropertiesSet();
	}
	
	// 登陆后，每次访问资源都通过这个拦截器拦截
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		FilterInvocation fi = new FilterInvocation(request, response, chain);
		invoke(fi);
	}

	@Override
	public Class<? extends Object> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	public void invoke(FilterInvocation fi) throws IOException, ServletException {
		// fi里面有一个被拦截的url
		// 里面调用MyInvocationSecurityMetadataSource的getAttributes(Object
		// object)这个方法获取fi对应的所有权限
		// 再调用MyAccessDecisionManager的decide方法来校验用户的权限是否足够
		InterceptorStatusToken token = super.beforeInvocation(fi);
		try {
			// 执行下一个拦截器
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		} finally {
			super.afterInvocation(token, null);
		}
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.myFilterInvocationSecurityMetadataSource;
	}

//	public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
//		return this.securityMetadataSource;
//	}
//
//	public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource newSource) {
//		this.securityMetadataSource = newSource;
//	}

}
