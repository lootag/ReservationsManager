package Persistence.Entities;

public class User{
	private long _Id;
	private String _Email;
	private byte[] _Hash;
	private byte[] _Salt;

	public User(long Id, String Email, byte[] Hash, byte[] Salt){
		this._Id = Id;
		this._Email = Email;
		this._Hash = Hash;
		this._Salt = Salt;
	}


	public long getId(){
		return this._Id;
	}

	public String getEmail(){
		return this._Email;
	}

	public byte[] getHash(){
		return this._Hash;
	}

	public byte[] getSalt(){
		return this._Salt;
	}
}
