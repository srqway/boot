package idv.hsiehpinghan.springcloudconfigclientboot.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CrudEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Integer id;
	private String string;
	private String configurationProperty;

	public CrudEntity() {
		super();
	}

	public CrudEntity(Integer id, String string, String configurationProperty) {
		super();
		this.id = id;
		this.string = string;
		this.configurationProperty = configurationProperty;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public String getConfigurationProperty() {
		return configurationProperty;
	}

	public void setConfigurationProperty(String configurationProperty) {
		this.configurationProperty = configurationProperty;
	}

	@Override
	public String toString() {
		return "CrudEntity [id=" + id + ", string=" + string + ", configurationProperty=" + configurationProperty + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		CrudEntity other = (CrudEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}