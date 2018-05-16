package mongodb.models;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.List;

import javax.annotation.Generated;

@Entity(value = "ccpholiday", noClassnameStored = true)
@Indexes({
    @Index(fields = @Field("date")),
    @Index(fields = @Field("state"))
})
public class CCPHoliday extends BaseEntity{
	@JsonSerialize(using = ToStringSerializer.class)
	private String date;
	private String holiday;
	private List<String> state;
	private String description;
	private String status;
	
	public CCPHoliday() {
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHoliday() {
		return holiday;
	}

	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}

	public List<String> getState() {
		return state;
	}

	public void setState(List<String> state) {
		this.state = state;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private CCPHoliday(CCPHolidayBuilder builder) {
		this.date = builder.date;
		this.holiday = builder.holiday;
		this.state = builder.state;
		this.description = builder.description;
		this.status = builder.status;
	}
	
	public static CCPHolidayBuilder builder() {
		return new CCPHolidayBuilder();
	}
	
	public static class CCPHolidayBuilder extends Builder<CCPHolidayBuilder> {
		private String date;
		private String holiday;
		private List<String> state;
		private String description;
		private String status;

        protected CCPHolidayBuilder() {
            super(CCPHolidayBuilder.class);
        }

		public CCPHolidayBuilder withDate(String date) {
			this.date = date;
			return this;
		}

		public CCPHolidayBuilder withHoliday(String holiday) {
			this.holiday = holiday;
			return this;
		}
		
		public CCPHolidayBuilder withState(List<String> state) {
			this.state = state;
			return this;
		}
		
		public CCPHolidayBuilder withDescription(String description) {
			this.description = description;
			return this;
		}

		public CCPHolidayBuilder withStatus(String status) {
			this.status = status;
			return this;
		}

		public CCPHoliday build() {
			return new CCPHoliday(this);
		}
	}

	
}
