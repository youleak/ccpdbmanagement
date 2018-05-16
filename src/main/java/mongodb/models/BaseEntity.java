package mongodb.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import utils.DateHelper;

import java.io.Serializable;

@Indexes({ @Index(fields = @Field("dateCreated")), @Index(fields = @Field("dateModified")) })
public abstract class BaseEntity implements Serializable {

	@JsonIgnore
	protected static final long serialVersionUID = 1L;

	@Id
	protected ObjectId id;

	@JsonIgnore
	protected String dateCreated;

	@JsonIgnore
	protected String dateModified;

	protected BaseEntity(Builder<?> builder) {
		this.id = builder.id;
		this.dateCreated = builder.dateCreated;
		this.dateModified = builder.dateModified;
	}

	public BaseEntity() {
		dateCreated = DateHelper.nowDateAsString();
		dateModified = DateHelper.nowDateAsString();
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(final ObjectId id) {
		this.id = id;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(final String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getDateModified() {
		return dateModified;
	}

	public void setDateModified(final String dateModified) {
		this.dateModified = dateModified;
	}

	public static Builder<?> builder() {
		return new BaseEntityBuilder();
	}

	public static class BaseEntityBuilder extends Builder<BaseEntityBuilder> {

		protected BaseEntityBuilder() {
			super(BaseEntityBuilder.class);
		}
	}

	protected static class Builder<T> {
		protected final Class<T> builderClass;
		private ObjectId id;
		private String dateCreated;
		private String dateModified;

		protected Builder(Class<T> builderClass) {
			this.builderClass = builderClass;
		}

		public T withId(ObjectId id) {
			this.id = id;
			return builderClass.cast(this);
		}

		public T withDateCreated(String dateCreated) {
			this.dateCreated = dateCreated;
			return builderClass.cast(this);
		}

		public T withDateModified(String dateModified) {
			this.dateModified = dateModified;
			return builderClass.cast(this);
		}
	}

}
