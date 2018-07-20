package spring.database.jpa.update;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

//@Entity
public class Ttest {
	
	private String teamName;
	private int orgId;
	
	@Column
	@CreationTimestamp
	private LocalDateTime createdDate;

	@Column
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	
	@Version
	private int version;

}
