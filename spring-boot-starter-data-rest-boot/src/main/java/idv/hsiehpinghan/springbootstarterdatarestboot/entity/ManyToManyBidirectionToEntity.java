package idv.hsiehpinghan.springbootstarterdatarestboot.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class ManyToManyBidirectionToEntity {
	@Id
	private Integer id;
	private String name;

	@ManyToMany(mappedBy = "tos")
	private Collection<ManyToManyBidirectionFromEntity> froms;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Collection<ManyToManyBidirectionFromEntity> getFroms() {
		return froms;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFroms(Collection<ManyToManyBidirectionFromEntity> froms) {
		this.froms = froms;
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
		ManyToManyBidirectionToEntity other = (ManyToManyBidirectionToEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
