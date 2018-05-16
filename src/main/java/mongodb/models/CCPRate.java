package mongodb.models;

import org.mongodb.morphia.annotations.Entity;

@Entity(value = "ccprate", noClassnameStored = true)
public class CCPRate extends BaseEntity{
	private String zoneType;
	private String state;
	private String opHourWeekdayStartTime;
	private String opHourWeekdayEndTime;
	private String opHourSatStartTime;
	private String opHourSatEndTime;
	private String opHourSunHolidayStartTime;
	private String opHourSunHolidayEndTime;
	private double charge1stHour;
	private double chargeWeekday;
	private double chargeSat;
	private double chargeSunHoliday;
	private double chargeOther;
	private double minCharge;
	private double maxCharge;
	private double serviceFee;
	private double serviceFeePercent;
	private double quarantineTime;
	private double blockingTime;
	private double maxHour;
	private String description;
	
	public CCPRate() {
		super();
	}

	private CCPRate(CCPRateBuilder builder) {
		this.zoneType = builder.zoneType;
		this.state = builder.state;
		this.opHourWeekdayStartTime = builder.opHourWeekdayStartTime;
		this.opHourWeekdayEndTime = builder.opHourWeekdayEndTime;
		this.opHourSatStartTime = builder.opHourSatStartTime;
		this.opHourSatEndTime = builder.opHourSatEndTime;
		this.opHourSunHolidayStartTime = builder.opHourSunHolidayStartTime;
		this.opHourSunHolidayEndTime = builder.opHourSunHolidayEndTime;
		this.charge1stHour = builder.charge1stHour;
		this.chargeWeekday = builder.chargeWeekday;
		this.chargeSat = builder.chargeSat;
		this.chargeSunHoliday = builder.chargeSunHoliday;
		this.chargeOther = builder.chargeOther;
		this.minCharge = builder.minCharge;
		this.maxCharge = builder.maxCharge;
		this.serviceFee = builder.serviceFee;
		this.serviceFeePercent = builder.serviceFeePercent;
		this.quarantineTime = builder.quarantineTime;
		this.blockingTime = builder.blockingTime;
		this.maxHour = builder.maxHour;
	}

	public String getZoneType() {
		return zoneType;
	}

	public void setZoneType(String zoneType) {
		this.zoneType = zoneType;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getOpHourWeekdayStartTime() {
		return opHourWeekdayStartTime;
	}

	public void setOpHourWeekdayStartTime(String opHourWeekdayStartTime) {
		this.opHourWeekdayStartTime = opHourWeekdayStartTime;
	}

	public String getOpHourWeekdayEndTime() {
		return opHourWeekdayEndTime;
	}

	public void setOpHourWeekdayEndTime(String opHourWeekdayEndTime) {
		this.opHourWeekdayEndTime = opHourWeekdayEndTime;
	}

	public String getOpHourSatStartTime() {
		return opHourSatStartTime;
	}

	public void setOpHourSatStartTime(String opHourSatStartTime) {
		this.opHourSatStartTime = opHourSatStartTime;
	}

	public String getOpHourSatEndTime() {
		return opHourSatEndTime;
	}

	public void setOpHourSatEndTime(String opHourSatEndTime) {
		this.opHourSatEndTime = opHourSatEndTime;
	}

	public String getOpHourSunHolidayStartTime() {
		return opHourSunHolidayStartTime;
	}

	public void setOpHourSunHolidayStartTime(String opHourSunHolidayStartTime) {
		this.opHourSunHolidayStartTime = opHourSunHolidayStartTime;
	}

	public String getOpHourSunHolidayEndTime() {
		return opHourSunHolidayEndTime;
	}

	public void setOpHourSunHolidayEndTime(String opHourSunHolidayEndTime) {
		this.opHourSunHolidayEndTime = opHourSunHolidayEndTime;
	}

	public double getCharge1stHour() {
		return charge1stHour;
	}

	public void setCharge1stHour(double charge1stHour) {
		this.charge1stHour = charge1stHour;
	}

	public double getChargeWeekday() {
		return chargeWeekday;
	}

	public void setChargeWeekday(double chargeWeekday) {
		this.chargeWeekday = chargeWeekday;
	}

	public double getChargeSat() {
		return chargeSat;
	}

	public void setChargeSat(double chargeSat) {
		this.chargeSat = chargeSat;
	}

	public double getChargeSunHoliday() {
		return chargeSunHoliday;
	}

	public void setChargeSunHoliday(double chargeSunHoliday) {
		this.chargeSunHoliday = chargeSunHoliday;
	}

	public double getChargeOther() {
		return chargeOther;
	}

	public void setChargeOther(double chargeOther) {
		this.chargeOther = chargeOther;
	}

	public double getMinCharge() {
		return minCharge;
	}

	public void setMinCharge(double minCharge) {
		this.minCharge = minCharge;
	}

	public double getMaxCharge() {
		return maxCharge;
	}

	public void setMaxCharge(double maxCharge) {
		this.maxCharge = maxCharge;
	}

	public double getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(double serviceFee) {
		this.serviceFee = serviceFee;
	}

	public double getServiceFeePercent() {
		return serviceFeePercent;
	}

	public void setServiceFeePercent(double serviceFeePercent) {
		this.serviceFeePercent = serviceFeePercent;
	}

	public double getQuarantineTime() {
		return quarantineTime;
	}

	public void setQuarantineTime(double quarantineTime) {
		this.quarantineTime = quarantineTime;
	}

	public double getBlockingTime() {
		return blockingTime;
	}

	public void setBlockingTime(double blockingTime) {
		this.blockingTime = blockingTime;
	}

	public double getMaxHour() {
		return maxHour;
	}

	public void setMaxHour(double maxHour) {
		this.maxHour = maxHour;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static CCPRateBuilder builder() {
		return new CCPRateBuilder();
	}


	public static final class CCPRateBuilder extends Builder<CCPRateBuilder> {
		private String zoneType;
		private String state;
		private String opHourWeekdayStartTime;
		private String opHourWeekdayEndTime;
		private String opHourSatStartTime;
		private String opHourSatEndTime;
		private String opHourSunHolidayStartTime;
		private String opHourSunHolidayEndTime;
		private double charge1stHour;
		private double chargeWeekday;
		private double chargeSat;
		private double chargeSunHoliday;
		private double chargeOther;
		private double minCharge;
		private double maxCharge;
		private double serviceFee;
		private double serviceFeePercent;
		private double quarantineTime;
		private double blockingTime;
		private double maxHour;
		private String description;

		protected CCPRateBuilder() {
			super(CCPRateBuilder.class);
		}

		public CCPRateBuilder withZoneType(String zoneType) {
			this.zoneType = zoneType;
			return this;
		}

		public CCPRateBuilder withState(String state) {
			this.state = state;
			return this;
		}

		public CCPRateBuilder withOpHourWeekdayStartTime(String opHourWeekdayStartTime) {
			this.opHourWeekdayStartTime = opHourWeekdayStartTime;
			return this;
		}

		public CCPRateBuilder withOpHourWeekdayEndTime(String opHourWeekdayEndTime) {
			this.opHourWeekdayEndTime = opHourWeekdayEndTime;
			return this;
		}

		public CCPRateBuilder withOpHourSatStartTime(String opHourSatStartTime) {
			this.opHourSatStartTime = opHourSatStartTime;
			return this;
		}

		public CCPRateBuilder withOpHourSatEndTime(String opHourSatEndTime) {
			this.opHourSatEndTime = opHourSatEndTime;
			return this;
		}

		public CCPRateBuilder withOpHourSunHolidayStartTime(String opHourSunHolidayStartTime) {
			this.opHourSunHolidayStartTime = opHourSunHolidayStartTime;
			return this;
		}

		public CCPRateBuilder withOpHourSunHolidayEndTime(String opHourSunHolidayEndTime) {
			this.opHourSunHolidayEndTime = opHourSunHolidayEndTime;
			return this;
		}

		public CCPRateBuilder withCharge1stHour(double charge1stHour) {
			this.charge1stHour = charge1stHour;
			return this;
		}

		public CCPRateBuilder withChargeWeekday(double chargeWeekday) {
			this.chargeWeekday = chargeWeekday;
			return this;
		}

		public CCPRateBuilder withChargeSat(double chargeSat) {
			this.chargeSat = chargeSat;
			return this;
		}

		public CCPRateBuilder withChargeSunHoliday(double chargeSunHoliday) {
			this.chargeSunHoliday = chargeSunHoliday;
			return this;
		}

		public CCPRateBuilder withChargeOther(double chargeOther) {
			this.chargeOther = chargeOther;
			return this;
		}

		public CCPRateBuilder withMinCharge(double minCharge) {
			this.minCharge = minCharge;
			return this;
		}

		public CCPRateBuilder withMaxCharge(double maxCharge) {
			this.maxCharge = maxCharge;
			return this;
		}

		public CCPRateBuilder withServiceFee(double serviceFee) {
			this.serviceFee = serviceFee;
			return this;
		}

		public CCPRateBuilder withServiceFeePercent(double serviceFeePercent) {
			this.serviceFeePercent = serviceFeePercent;
			return this;
		}

		public CCPRateBuilder withQuarantineTime(double quarantineTime) {
			this.quarantineTime = quarantineTime;
			return this;
		}

		public CCPRateBuilder withBlockingTime(double blockingTime) {
			this.blockingTime = blockingTime;
			return this;
		}

		public CCPRateBuilder withMaxHour(double maxHour) {
			this.maxHour = maxHour;
			return this;
		}
		
		public CCPRateBuilder withDescription(String description) {
			this.description = description;
			return this;
		}

		public CCPRate build() {
			return new CCPRate(this);
		}
	}
}
