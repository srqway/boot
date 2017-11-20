package idv.hsiehpinghan.springsecurityoauth2boot.utility;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import idv.hsiehpinghan.springsecurityoauth2boot.entity.ClientEntity;
import idv.hsiehpinghan.springsecurityoauth2boot.entity.GrantedAuthorityEntity;
import idv.hsiehpinghan.springsecurityoauth2boot.entity.RoleEntity;

public class ConvertUtility {

	public static BaseClientDetails convertToBaseClientDetails(ClientEntity clientEntity) {
		Integer accessTokenValiditySeconds = clientEntity.getAccessTokenValiditySeconds();
		Map<String, ?> additionalInformation = clientEntity.getAdditionalInformation();
		Collection<? extends GrantedAuthority> authorities = convertToGrantedAuthorities(clientEntity.getAuthorities());
		Collection<String> authorizedGrantTypes = clientEntity.getAuthorizedGrantTypes();
		Collection<String> autoApproveScopes = clientEntity.getAutoApproveScopes();
		String clientId = clientEntity.getClientId();
		String clientSecret = clientEntity.getClientSecret();
		Integer refreshTokenValiditySeconds = clientEntity.getRefreshTokenValiditySeconds();
		Set<String> registeredRedirectUris = clientEntity.getRegisteredRedirectUris();
		Collection<String> resourceIds = clientEntity.getResourceIds();
		Collection<String> scope = clientEntity.getScope();
		BaseClientDetails baseClientDetails = new BaseClientDetails();
		if (accessTokenValiditySeconds != null) {
			baseClientDetails.setAccessTokenValiditySeconds(accessTokenValiditySeconds);
		}
		if (additionalInformation != null) {
			baseClientDetails.setAdditionalInformation(additionalInformation);
		}
		if (authorities != null) {
			baseClientDetails.setAuthorities(authorities);
		}
		if (authorizedGrantTypes != null) {
			baseClientDetails.setAuthorizedGrantTypes(authorizedGrantTypes);
		}
		if (autoApproveScopes != null) {
			baseClientDetails.setAutoApproveScopes(autoApproveScopes);
		}
		if (clientId != null) {
			baseClientDetails.setClientId(clientId);
		}
		if (clientSecret != null) {
			baseClientDetails.setClientSecret(clientSecret);
		}
		if (refreshTokenValiditySeconds != null) {
			baseClientDetails.setRefreshTokenValiditySeconds(refreshTokenValiditySeconds);
		}
		if (registeredRedirectUris != null) {
			baseClientDetails.setRegisteredRedirectUri(registeredRedirectUris);
		}
		if (resourceIds != null) {
			baseClientDetails.setResourceIds(resourceIds);
		}
		if (scope != null) {
			baseClientDetails.setScope(scope);
		}
		return baseClientDetails;
	}

	public static Collection<? extends GrantedAuthority> convertToGrantedAuthorities(
			Collection<RoleEntity> roleEntitys) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>(roleEntitys.size());
		for (RoleEntity roleEntity : roleEntitys) {
			GrantedAuthority grantedAuthority = convertToGrantedAuthority(roleEntity);
			grantedAuthorities.add(grantedAuthority);
		}
		return grantedAuthorities;
	}

	private static GrantedAuthority convertToGrantedAuthority(RoleEntity roleEntity) {
		String roleId = roleEntity.getRoleId();
		GrantedAuthority grantedAuthoritie = new SimpleGrantedAuthority(roleId);
		return grantedAuthoritie;
	}

	private static Collection<GrantedAuthority> convertToGrantedAuthorities(
			Set<GrantedAuthorityEntity> grantedAuthorityEntities) {
		int size = grantedAuthorityEntities.size();
		Collection<GrantedAuthority> grantedAuthorities = new HashSet<>(size);
		for (GrantedAuthorityEntity grantedAuthorityEntity : grantedAuthorityEntities) {
			GrantedAuthority grantedAuthority = convertToGrantedAuthority(grantedAuthorityEntity);
			grantedAuthorities.add(grantedAuthority);
		}
		return grantedAuthorities;
	}

	private static GrantedAuthority convertToGrantedAuthority(GrantedAuthorityEntity grantedAuthorityEntity) {
		String authority = grantedAuthorityEntity.getAuthority();
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority);
		return grantedAuthority;
	}
}
