package spring.database.jpa.orm3.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;

@SecondaryTable(name="Table3", pkJoinColumns= {@PrimaryKeyJoinColumn(name="id", referencedColumnName="id")})
@DiscriminatorValue("tbl3")
@Entity
public class T3 extends T2{
	
	@Column(table="Table3")
	private String str4;

	public T3(String str1, String str2, String str3, String str4) {
		super(str1, str2, str3);
		this.str4 = str4;
	}
	
	public String getStr4() {
		return str4;
	}

	public void setStr4(String str4) {
		this.str4 = str4;
	}

}
