package buccaneer.system.bean;

import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import buccaneer.utils.ForMat;




@Entity
@Table(name = "user")
public class User {
	private Integer id;
	private String name;
	private String password;
	private Long birthday;
	private Set<Role> list = null;
	
	public User() {
	}

	public User(Long birthday) {
		this.birthday = ForMat.dateTolong(new Date());
	}

	public User(String name, Set<Role> list) {
		super();
		this.name = name;
		this.list = list;
		this.birthday = ForMat.dateTolong(new Date());
	}

	public User(Integer id, String name, Long birthday, Set<Role> list) {
		super();
		this.id = id;
		this.name = name;
		this.birthday = birthday;
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
	@Column(name = "password", nullable = true)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "birthday", nullable = true)
	public Long getBirthday() {
		return birthday;
	}

	public void setBirthday(Long birthday) {
		this.birthday = birthday;
	}
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name="user_roles",joinColumns={@JoinColumn(columnDefinition="user_id",referencedColumnName="id")},inverseJoinColumns={@JoinColumn(referencedColumnName="id",name="roles_id")})
	public Set<Role> getList() {
		return list;
	}

	public void setList(Set<Role> list) {
		this.list = list;
	}
}
