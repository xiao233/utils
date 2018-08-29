package xiao.test.entrties;

public class Teacher {
	private String number;
	private String name;
	private String sex;
	private Integer age;
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Teacher [number=" + number + ", name=" + name + ", sex=" + sex + ", age=" + age + "]";
	}
	
	
}
