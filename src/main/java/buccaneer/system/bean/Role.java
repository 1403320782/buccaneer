package buccaneer.system.bean;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {
	
	
	private Integer id;
	private String name = "";
	private Set<Permission> list = null;

	public Role() {
	}

	public Role(String name, Set<Permission> list) {
		this.name = name;
		this.list = list;
	}

	public Role(Integer id, String name, Set<Permission> list) {
		this.id = id;
		this.name = name;
		this.list = list;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name="role_permissions",joinColumns={@JoinColumn(columnDefinition="role_id",referencedColumnName="id")},inverseJoinColumns={@JoinColumn(referencedColumnName="id",name="permissions_id")})
	public Set<Permission> getList() {
		return list;
	}

	public void setList(Set<Permission> list) {
		this.list = list;
	}
}
