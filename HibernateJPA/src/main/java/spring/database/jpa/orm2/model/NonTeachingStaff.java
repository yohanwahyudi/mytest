package spring.database.jpa.orm2.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "staff_id")
public class NonTeachingStaff extends Staff{

	private String areaexpertise;

	public NonTeachingStaff(String sname, String areaexpertise) {
		super(sname);
		this.areaexpertise = areaexpertise;
	}

	public NonTeachingStaff() {
		super();
	}

	public String getAreaexpertise() {
		return areaexpertise;
	}

	public void setAreaexpertise(String areaexpertise) {
		this.areaexpertise = areaexpertise;
	}

}
