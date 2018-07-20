package spring.database.jpa.orm2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity

//@Table
//@Inheritance(strategy = InheritanceType.JOINED)

@PrimaryKeyJoinColumn(referencedColumnName = "staff_id")
public class TeachingStaff extends Staff{

//	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="teachinfstaff_generator")
//	@Column(name="staff_id", updatable=false, nullable=false)
//	private int id;
	private String qualification;
	private String subjectexpertise;

	public TeachingStaff(String sname,

		String qualification, String subjectexpertise) {
		super(sname);
		this.qualification = qualification;
		this.subjectexpertise = subjectexpertise;
	}

	public TeachingStaff() {
		super();
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getSubjectexpertise() {
		return subjectexpertise;
	}

	public void setSubjectexpertise(String subjectexpertise) {
		this.subjectexpertise = subjectexpertise;
	}

//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}

}
