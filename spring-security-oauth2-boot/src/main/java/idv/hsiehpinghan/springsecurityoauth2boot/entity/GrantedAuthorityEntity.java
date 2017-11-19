package idv.hsiehpinghan.springsecurityoauth2boot.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GrantedAuthorityEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String authority;

	public GrantedAuthorityEntity() {
		super();
	}

	public GrantedAuthorityEntity(String authority) {
		super();
		this.authority = authority;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authority == null) ? 0 : authority.hashCode());
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
		GrantedAuthorityEntity other = (GrantedAuthorityEntity) obj;
		if (authority == null) {
			if (other.authority != null)
				return false;
		} else if (!authority.equals(other.authority))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GrantedAuthorityEntity [authority=" + authority + "]";
	}

}
