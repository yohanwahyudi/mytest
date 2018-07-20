package spring.hibernate.saveupdate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Employee")
public class EmployeeEntity implements Serializable {
	private static final long serialVersionUID = -1798070786993154676L;
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	private Integer employeeId;

	@Column(name = "FIRST_NAME", unique = false, nullable = false, length = 100)
	private String firstName;

	@Column(name = "LAST_NAME", unique = false, nullable = false, length = 100)
	private String lastName;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof EmployeeEntity))
			return false;

		EmployeeEntity otherEmployee = (EmployeeEntity) o;

		if (getEmployeeId() != null ? !getEmployeeId().equals(otherEmployee.getEmployeeId())
				: otherEmployee.getEmployeeId() != null)
			return false;
		if (getFirstName() != null ? !getFirstName().equals(otherEmployee.getFirstName())
				: otherEmployee.getFirstName() != null)
			return false;
		if (getLastName() != null ? !getLastName().equals(otherEmployee.getLastName())
				: otherEmployee.getLastName() != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = getEmployeeId() != null ? getEmployeeId().hashCode() : 0;
		result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
		result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
		return result;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}