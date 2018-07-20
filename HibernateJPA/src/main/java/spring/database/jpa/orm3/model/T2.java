package spring.database.jpa.orm3.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;

@SecondaryTable(name="Table2", pkJoinColumns= {@PrimaryKeyJoinColumn(name="id",referencedColumnName="id")})
@DiscriminatorValue("tbl2")
@Entity
public class T2 extends T1{
	
	@Column(table="Table2")
	private String str3;
	
	public T2(String str1, String str2, String str3) {
		super(str1, str2);
		this.str3=str3;		
	}

	public String getStr3() {
		return str3;
	}

	public void setStr3(String str3) {
		this.str3 = str3;
	}

}
