package idv.hsiehpinghan.springbootstartersecurityboot.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class ResourceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "[order]")
	private Integer order;
	private String path;

	@ManyToMany(mappedBy = "resources")
	private Collection<RoleEntity> roles;

	public ResourceEntity() {
		super();
	}

	public ResourceEntity(Integer order, String path) {
		super();
		this.order = order;
		this.path = path;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Collection<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(Collection<RoleEntity> roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((order == null) ? 0 : order.hashCode());
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
		ResourceEntity other = (ResourceEntity) obj;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		return true;
	}

}
