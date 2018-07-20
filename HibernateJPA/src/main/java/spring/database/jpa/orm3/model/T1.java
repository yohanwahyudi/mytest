package spring.database.jpa.orm3.model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@DiscriminatorColumn(name="discriminatorValue")
@Entity
@Inheritance
@Table(name="Table1")
public class T1 {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="t_generator")
//	@SequenceGenerator(name="t_generator", sequenceName="t_sequence", initialValue=1, allocationSize=1)
	@SequenceGenerator(name="t_generator", sequenceName="t_sequence")
	private int id;
	private String str1;
	private String str2;
	
	public T1(String str1, String str2) {
		this.str1=str1;
		this.str2=str2;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStr1() {
		return str1;
	}
	public void setStr1(String str1) {
		this.str1 = str1;
	}
	public String getStr2() {
		return str2;
	}
	public void setStr2(String str2) {
		this.str2 = str2;
	}
	
	
	

}
