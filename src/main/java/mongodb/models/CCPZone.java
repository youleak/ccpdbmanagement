package mongodb.models;

import org.mongodb.morphia.annotations.Entity;

@Entity(value = "ccpzone", noClassnameStored = true)
public class CCPZone extends BaseEntity{
	private String rateId;
	private String zoneNumber;
	private String zoneType;
	private String address;
	private String city;
	private String status;
	private LocationEntry location;

	private CCPZone(CCPZoneBuilder builder) {
		this.rateId = builder.rateId;
		this.zoneNumber = builder.zoneNumber;
		this.zoneType = builder.zoneType;
		this.address = builder.address;
		this.city = builder.city;
		this.status = builder.status;
		this.location = builder.location;
	}
	
	public String getRateId() {
		return rateId;
	}

	public void setRateId(String rateId) {
		this.rateId = rateId;
	}

	public String getZoneNumber() {
		return zoneNumber;
	}

	public void setZoneNumber(String zoneNumber) {
		this.zoneNumber = zoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getZoneType() {
		return zoneType;
	}

	public void setZoneType(String zoneType) {
		this.zoneType = zoneType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public LocationEntry getLocation() {
		return location;
	}

	public void setLocation(LocationEntry location) {
		this.location = location;
	}

	public static CCPZoneBuilder builder() {
		return new CCPZoneBuilder();
	}

	public static final class CCPZoneBuilder extends Builder<CCPZoneBuilder> {
		private String rateId;
		private String zoneNumber;
		private String zoneType;
		private String address;
		private String city;
		private String status;
		private LocationEntry location;

		protected CCPZoneBuilder() {
			super(CCPZoneBuilder.class);
		}

		public CCPZoneBuilder withRateId(String rateId) {
			this.rateId = rateId;
			return this;
		}

		public CCPZoneBuilder withZoneNumber(String zoneNumber) {
			this.zoneNumber = zoneNumber;
			return this;
		}
		
		public CCPZoneBuilder withZoneType(String zoneType) {
			this.zoneType = zoneType;
			return this;
		}

		public CCPZoneBuilder withAddress(String address) {
			this.address = address;
			return this;
		}

		public CCPZoneBuilder withCity(String city) {
			this.city = city;
			return this;
		}
		
		public CCPZoneBuilder withStatus(String status) {
			this.status = status;
			return this;
		}
		
		public CCPZoneBuilder withLocation(LocationEntry location) {
			this.location = location;
			return this;
		}
		
		public CCPZone build() {
			return new CCPZone(this);
		}
	}
	
	
}
