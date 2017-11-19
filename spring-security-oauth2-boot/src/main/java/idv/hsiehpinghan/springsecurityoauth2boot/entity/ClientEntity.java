package idv.hsiehpinghan.springsecurityoauth2boot.entity;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;

@Entity
public class ClientEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String clientId;
	private String clientSecret;
	@ElementCollection
	private Set<String> scope;
	@ElementCollection
	private Set<String> resourceIds;
	@ElementCollection
	private Set<String> authorizedGrantTypes;
	@ElementCollection
	private Set<String> registeredRedirectUris;
	@ElementCollection
	private Set<String> autoApproveScopes;
	@OneToMany(cascade = CascadeType.ALL)
	private Set<GrantedAuthorityEntity> authorities;
	private Integer accessTokenValiditySeconds;
	private Integer refreshTokenValiditySeconds;
	@ElementCollection
	@CollectionTable(name = "additionalInformation")
	@MapKeyColumn(name = "key")
	@Column(name = "value")
	private Map<String, String> additionalInformation;

	public ClientEntity() {
		super();
	}

	public ClientEntity(String clientId, String clientSecret, Set<String> scope, Set<String> resourceIds,
			Set<String> authorizedGrantTypes, Set<String> registeredRedirectUris, Set<String> autoApproveScopes,
			Set<GrantedAuthorityEntity> authorities, Integer accessTokenValiditySeconds,
			Integer refreshTokenValiditySeconds, Map<String, String> additionalInformation) {
		super();
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.scope = scope;
		this.resourceIds = resourceIds;
		this.authorizedGrantTypes = authorizedGrantTypes;
		this.registeredRedirectUris = registeredRedirectUris;
		this.autoApproveScopes = autoApproveScopes;
		this.authorities = authorities;
		this.accessTokenValiditySeconds = accessTokenValiditySeconds;
		this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
		this.additionalInformation = additionalInformation;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public Set<String> getScope() {
		return scope;
	}

	public void setScope(Set<String> scope) {
		this.scope = scope;
	}

	public Set<String> getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(Set<String> resourceIds) {
		this.resourceIds = resourceIds;
	}

	public Set<String> getAuthorizedGrantTypes() {
		return authorizedGrantTypes;
	}

	public void setAuthorizedGrantTypes(Set<String> authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}

	public Set<String> getRegisteredRedirectUris() {
		return registeredRedirectUris;
	}

	public void setRegisteredRedirectUris(Set<String> registeredRedirectUris) {
		this.registeredRedirectUris = registeredRedirectUris;
	}

	public Set<String> getAutoApproveScopes() {
		return autoApproveScopes;
	}

	public void setAutoApproveScopes(Set<String> autoApproveScopes) {
		this.autoApproveScopes = autoApproveScopes;
	}

	public Set<GrantedAuthorityEntity> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<GrantedAuthorityEntity> authorities) {
		this.authorities = authorities;
	}

	public Integer getAccessTokenValiditySeconds() {
		return accessTokenValiditySeconds;
	}

	public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
		this.accessTokenValiditySeconds = accessTokenValiditySeconds;
	}

	public Integer getRefreshTokenValiditySeconds() {
		return refreshTokenValiditySeconds;
	}

	public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
		this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
	}

	public Map<String, String> getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(Map<String, String> additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clientId == null) ? 0 : clientId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientEntity other = (ClientEntity) obj;
		if (clientId == null) {
			if (other.clientId != null)
				return false;
		} else if (!clientId.equals(other.clientId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ClientEntity [clientId=" + clientId + ", clientSecret=" + clientSecret + ", scope=" + scope
				+ ", resourceIds=" + resourceIds + ", authorizedGrantTypes=" + authorizedGrantTypes
				+ ", registeredRedirectUris=" + registeredRedirectUris + ", autoApproveScopes=" + autoApproveScopes
				+ ", authorities=" + authorities + ", accessTokenValiditySeconds=" + accessTokenValiditySeconds
				+ ", refreshTokenValiditySeconds=" + refreshTokenValiditySeconds + ", additionalInformation="
				+ additionalInformation + "]";
	}

}
