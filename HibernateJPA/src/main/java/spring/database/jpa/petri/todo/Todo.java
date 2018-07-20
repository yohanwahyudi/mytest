package spring.database.jpa.petri.todo;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Type;

//@Entity
//@Table(name = "todos")
public //enable public
final class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "creation_time", nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
	private ZonedDateTime creationTime;

	@Column(name = "description", length = 500)
	private String description;

	@Column(name = "modification_time")
	@Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
	private ZonedDateTime modificationTime;

	@Column(name = "title", nullable = false, length = 100)
	private String title;

	@Version
	private long version;
	
	/**
     * Required by Hibernate.
     */
    private Todo() {}

    private Todo(Builder builder) {
        this.title = builder.title;
        this.description = builder.description;
    }
    
    static Builder getBuilder() {
        return new Builder();
    }
    
    Long getId() {
        return id;
    }

    ZonedDateTime getCreationTime() {
        return creationTime;
    }

    String getDescription() {
        return description;
    }

    ZonedDateTime getModificationTime() {
        return modificationTime;
    }

    String getTitle() {
        return title;
    }

    long getVersion() {
        return version;
    }
    
   
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("creationTime", this.creationTime)
                .append("description", this.description)
                .append("id", this.id)
                .append("modificationTime", this.modificationTime)
                .append("title", this.title)
                .append("version", this.version)
                .toString();
    }
    
    
    /**
     * This entity is so simple that you don't really need to use the builder pattern
     * (use a constructor instead). I use the builder pattern here because it makes
     * the code a bit more easier to read.
     */
    static class Builder {
        private String description;
        private String title;

        private Builder() {}

        Builder description(String description) {
            this.description = description;
            return this;
        }

        Builder title(String title) {
            this.title = title;
            return this;
        }

        
    }
}
