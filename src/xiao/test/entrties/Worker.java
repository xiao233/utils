package xiao.test.entrties;

public class Worker {
	private String name;
	private Character sex;
	private String phone;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Character getSex() {
		return sex;
	}
	public void setSex(Character sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Worker [name=" + name + ", sex=" + sex + ", phone=" + phone + "]";
	}
	
}
