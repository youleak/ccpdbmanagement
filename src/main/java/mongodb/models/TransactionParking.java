package mongodb.models;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Entity(value = "transactionparking", noClassnameStored = true)
public class TransactionParking {
	@JsonSerialize(using = ToStringSerializer.class)
	@Id
	private ObjectId _id;
	private String customerId;
	private String vehicleId;
	private String prpTransactionID;
	private String prpCallDateTime;
	private String parkingStartDateTime;
	private String parkingEndDateTime;
	private long amount;
	private long gracePeriod;
	private String terminalNo;
	private String zoneNo;
	private String permitNo;
	private String permitType;

	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getPrpTransactionID() {
		return prpTransactionID;
	}
	public void setPrpTransactionID(String prpTransactionID) {
		this.prpTransactionID = prpTransactionID;
	}
	public String getPrpCallDateTime() {
		return prpCallDateTime;
	}
	public void setPrpCallDateTime(String prpCallDateTime) {
		this.prpCallDateTime = prpCallDateTime;
	}
	public String getParkingStartDateTime() {
		return parkingStartDateTime;
	}
	public void setParkingStartDateTime(String parkingStartDateTime) {
		this.parkingStartDateTime = parkingStartDateTime;
	}
	public String getParkingEndDateTime() {
		return parkingEndDateTime;
	}
	public void setParkingEndDateTime(String parkingEndDateTime) {
		this.parkingEndDateTime = parkingEndDateTime;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public long getGracePeriod() {
		return gracePeriod;
	}
	public void setGracePeriod(long gracePeriod) {
		this.gracePeriod = gracePeriod;
	}
	public String getTerminalNo() {
		return terminalNo;
	}
	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}
	public String getZoneNo() {
		return zoneNo;
	}
	public void setZoneNo(String zoneNo) {
		this.zoneNo = zoneNo;
	}
	public String getPermitNo() {
		return permitNo;
	}
	public void setPermitNo(String permitNo) {
		this.permitNo = permitNo;
	}
	public String getPermitType() {
		return permitType;
	}
	public void setPermitType(String permitType) {
		this.permitType = permitType;
	}
}
