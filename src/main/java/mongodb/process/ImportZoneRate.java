package mongodb.process;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.mongodb.morphia.Datastore;

import mongodb.models.CCPRate;
import utils.Constant;
import utils.MongoDbProvider;

/**
 * 
 *
 */
public class ImportZoneRate 
{
	public static final SimpleDateFormat sdfExcelFormat = new SimpleDateFormat( "hh.mma");
	public static final SimpleDateFormat sdfStoreFormat = new SimpleDateFormat( "Hmm");
    
    public static void main( String[] args )
    {
    	try {
    	    XSSFWorkbook wb = new XSSFWorkbook(new ImportZoneRate().getClass().getClassLoader().
    				getResourceAsStream("rate.xlsx"));
    	    XSSFSheet sheet = wb.getSheetAt(0);
    	    XSSFRow row;
    	    int rows; 
    	    rows = sheet.getPhysicalNumberOfRows();
    	    List<CCPRate> ccpRateList = new ArrayList<CCPRate>();

    	    for(int r = 1; r < rows; r++) {
    	        row = sheet.getRow(r);
    	        if(row != null) {
                    CCPRate ccpRate  = CCPRate.builder()
		                	.withZoneType(row.getCell(Constant.CELL_RATE_ZONE_TYPE).toString())
		                	.withState(row.getCell(Constant.CELL_RATE_STATES).toString())
		                	.withOpHourWeekdayStartTime(
		                			processOptHour(row
    	    	                    		.getCell(Constant.CELL_RATE_OPERATIONAL_HOURS_WEEK_DAYS)
    	    	                    		.toString(), 0)
		                			)
		                	.withOpHourWeekdayEndTime(
		                			processOptHour(row
    	    	                    		.getCell(Constant.CELL_RATE_OPERATIONAL_HOURS_WEEK_DAYS)
    	    	                    		.toString(), 1)
    	                    		)
		                	.withOpHourSatStartTime(
		                			processOptHour(row
    	    	                    		.getCell(Constant.CELL_RATE_OPERATIONAL_HOURS_SATURDAYS)
    	    	                    		.toString(), 0)
		                			)
		                	.withOpHourSatEndTime(
		                			processOptHour(row
    	    	                    		.getCell(Constant.CELL_RATE_OPERATIONAL_HOURS_SATURDAYS)
    	    	                    		.toString(), 1)
		                			)
		                	.withOpHourSunHolidayStartTime(
		                			processOptHour(row
    	    	                    		.getCell(Constant.CELL_RATE_OPERATIONAL_HOURS_SUNDAYS_AND_HOLIDAYS)
    	    	                    		.toString(), 0)
    	                    		)
		                	.withOpHourSunHolidayEndTime(
		                			processOptHour(row
    	    	                    		.getCell(Constant.CELL_RATE_OPERATIONAL_HOURS_SUNDAYS_AND_HOLIDAYS)
    	    	                    		.toString(), 1)
		                			)
		                	.withCharge1stHour(parkingChargeCalc(row.getCell(Constant.CELL_RATE_MINIMUM_CHARGE).toString()))
		                	.withChargeWeekday(parkingChargeCalc(row.getCell(Constant.CELL_RATE_CHARGES_WEEK_DAYS).toString()))
		                	.withChargeSat(parkingChargeCalc((row.getCell(Constant.CELL_RATE_CHARGES_SATURDAYS).toString())))
		                	.withChargeSunHoliday(parkingChargeCalc((row.getCell(Constant.CELL_RATE_CHARGES_SUNDAYS_AND_HOLIDAYS).toString())))
		                	.withChargeOther(parkingChargeCalc((row.getCell(Constant.CELL_RATE_CHARGES_OTHER_TIMES).toString())))
		                	.withMinCharge(parkingChargeCalc((row.getCell(Constant.CELL_RATE_MINIMUM_CHARGE).toString())))
		                	.withMaxCharge(parkingChargeCalc((row.getCell(Constant.CELL_RATE_MAXIMUM_CHARGE).toString())))
		                	.withServiceFee(Double.parseDouble((row.getCell(Constant.CELL_RATE_SERVICE_FEE_AMOUNT).toString())))
		                	.withServiceFeePercent(Double.parseDouble((row.getCell(Constant.CELL_RATE_SERVICE_FEE_PERCENT).toString())))
		                	.withQuarantineTime(Double.parseDouble((row.getCell(Constant.CELL_RATE_QUARANTINE_TIME).toString())))
		                	.withBlockingTime(Double.parseDouble((row.getCell(Constant.CELL_RATE_BLOCKING_TIME).toString())))
		                	.withMaxHour(Double.parseDouble((row.getCell(Constant.CELL_RATE_MAXIMUM_CHARGE).toString())))
		                	.withDescription(row.getCell(Constant.CELL_RATE_TARIFF_DESCRIPTION).toString())
		                	.build();
	                
                    ccpRateList.add(ccpRate);
    	        }
    	    }
    	    MongoDbProvider mongoDbProvider = new MongoDbProvider();
			Datastore datastore = mongoDbProvider.get();
    	    datastore.save(ccpRateList);
            System.out.println("done");
    	} catch(Exception ioe) {
    	    ioe.printStackTrace();
    	}
    }
    
    private static double parkingChargeCalc(String fee) {
    	if(fee.equalsIgnoreCase(Constant.FREE)) {
    		return 0.0;
    	}
    	return Double.parseDouble(fee);
    }
    
    private static String processOptHour(String optTime, int index) throws ParseException {
    	if(optTime.equalsIgnoreCase(Constant.FREE)) {
    		return "FREE";
    	}
    	return sdfStoreFormat
		.format(sdfExcelFormat.parse(optTime.split("-")[index]));
    }
    
}
