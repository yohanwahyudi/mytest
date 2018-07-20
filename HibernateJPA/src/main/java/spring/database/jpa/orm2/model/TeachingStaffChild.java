package spring.database.jpa.orm2.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

//@Entity
//@PrimaryKeyJoinColumn(referencedColumnName = "staff_id")
public class TeachingStaffChild extends TeachingStaff{
	
	private int subordinateCount;

	public TeachingStaffChild(String sname, String qualification, String subjectexpertise, int subordinateCount) {
		super(sname, qualification, subjectexpertise);
		this.subordinateCount = subordinateCount;		
	}
	
	public int getSubordinate() {
		return subordinateCount;
	}

	public void setSubordinate(int subordinateCount) {
	}
	
	

}
