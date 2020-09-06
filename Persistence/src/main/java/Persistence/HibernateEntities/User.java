package Persistence.HibernateEntities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
@Table(name = "User")
public class User{

	@Id
	@Column(name = "Id")
	private long Id;
	@Column(name = "Email")
	private String Email;
	@Column(name = "Hash")
	private byte[] Hash;
	@Column(name = "Salt")
	private byte[] Salt;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public byte[] getHash() {
		return Hash;
	}

	public void setHash(byte[] hash) {
		Hash = hash;
	}

	public byte[] getSalt() {
		return Salt;
	}

	public void setSalt(byte[] salt) {
		Salt = salt;
	}



	public User(){
	}

	public User(String Email, byte[] Hash, byte[] Salt){
		this.Email = Email;
		this.Hash = Hash;
		this.Salt = Salt;
	}
}
