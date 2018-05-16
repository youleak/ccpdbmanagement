package mongodb.models;

public class LocationEntry {
	private double[] coordinates;
	private String type;

	private LocationEntry(Builder builder) {
		this.coordinates = builder.coordinates;
		this.type = builder.type;
	}

	public double[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(double[] coordinates) {
		this.coordinates = coordinates;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private double[] coordinates;
		private String type;

		private Builder() {
		}

		public Builder withCoordinates(double[] coordinates) {
			this.coordinates = coordinates;
			return this;
		}

		public Builder withType(String type) {
			this.type = type;
			return this;
		}

		public LocationEntry build() {
			return new LocationEntry(this);
		}
	}

}
