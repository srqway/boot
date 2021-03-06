package idv.hsiehpinghan.springbootstartersecurityboot.source;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import idv.hsiehpinghan.springbootstartersecurityboot.entity.ResourceEntity;
import idv.hsiehpinghan.springbootstartersecurityboot.entity.RoleEntity;
import idv.hsiehpinghan.springbootstartersecurityboot.service.ResourceService;

@Component
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	private AntPathMatcher antPathMatcher = new AntPathMatcher();
	@Autowired
	private ResourceService resourceService;
//	private final Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;

	// ~ Constructors
	// ===================================================================================================

	/**
	 * Sets the internal request map from the supplied map. The key elements should be of
	 * type {@link RequestMatcher}, which. The path stored in the key will depend on the
	 * type of the supplied UrlMatcher.
	 *
	 * @param requestMap order-preserving map of request definitions to attribute lists
	 */
//	public MyFilterInvocationSecurityMetadataSource(
//			LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap) {
//
//		this.requestMap = requestMap;
//	}

	// ~ Methods
	// ========================================================================================================

	public Collection<ConfigAttribute> getAllConfigAttributes() {
//		Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();
//
//		for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap
//				.entrySet()) {
//			allAttributes.addAll(entry.getValue());
//		}
//
//		return allAttributes;
		return null;
	}

	public Collection<ConfigAttribute> getAttributes(Object object) {
		
		System.err.println(object);
		
//		final HttpServletRequest request = ((FilterInvocation) object).getRequest();
//		for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap
//				.entrySet()) {
//			if (entry.getKey().matches(request)) {
//				return entry.getValue();
//			}
//		}
		
//		HashMap<String, Collection<ConfigAttribute>> resourceMap = new LinkedHashMap<String, Collection<ConfigAttribute>>();
//		Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
////		ConfigAttribute ca = new SecurityConfig("ROLE_USER");
////		atts.add(ca);
////		resourceMap.put("/index.jsp", atts);
//		Collection<ConfigAttribute> attsno = new ArrayList<ConfigAttribute>();
//		attsno.add(new SecurityConfig("ROLE_USER"));
////		attsno.add(new SecurityConfig("ROLE_ADMIN"));
//		resourceMap.put("/user/**", attsno);
		

		
		String url = ((FilterInvocation) object).getRequestUrl();
//		Iterator<String> ite = resourceMap.keySet().iterator();
//		while (ite.hasNext()) {
//			String resURL = ite.next();
//			 if (antPathMatcher.match(resURL, url) == true) {
//				 return resourceMap.get(resURL);
//			 }
//		}
		
		Collection<ConfigAttribute> configAttributes = new LinkedList<>();
		List<ResourceEntity> resourceEntities = resourceService.findAll();
		for(ResourceEntity resourceEntity : resourceEntities) {
			String path = resourceEntity.getPath();
			if (antPathMatcher.match(path, url) == true) {
				Collection<RoleEntity> roles = resourceEntity.getRoles();
				for(RoleEntity role : roles) {
					String roleId = role.getRoleId();
					ConfigAttribute configAttribute = new SecurityConfig(roleId);
					configAttributes.add(configAttribute);
				}
				return configAttributes;
			 }
		}
		
		return null;
	}

	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}
/**
	// private UrlMatcher urlMatcher = new AntUrlPathMatcher();
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	// tomcat启动时实例化一次
	public MyFilterInvocationSecurityMetadataSource() {
		loadResourceDefine();
	}

	// 参数是要访问的url，返回这个url对于的所有权限（或角色）
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		// 将参数转为url
		String url = ((FilterInvocation) object).getRequestUrl();
		Iterator<String> ite = resourceMap.keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();
			// if (urlMatcher.pathMatchesUrl(resURL, url)) {
			// return resourceMap.get(resURL);
			// }
		}
		return null;

	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	// tomcat开启时加载一次，加载所有url和权限（或角色）的对应关系
	private void loadResourceDefine() {
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
		ConfigAttribute ca = new SecurityConfig("ROLE_USER");
		atts.add(ca);
		resourceMap.put("/index.jsp", atts);
		Collection<ConfigAttribute> attsno = new ArrayList<ConfigAttribute>();
		ConfigAttribute cano = new SecurityConfig("ROLE_NO");
		attsno.add(cano);
		resourceMap.put("/other.jsp", attsno);
	}
**/
}
