package mongodb.process;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.mongodb.morphia.Datastore;

import com.mongodb.DBObject;
import com.mongodb.client.model.Indexes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import mongodb.models.CCPRate;
import mongodb.models.CCPZone;
import mongodb.models.LocationEntry;
import utils.Constant;
import utils.MongoDbProvider;

/**
 * 
 *
 */
public class ImportZone {
	public static void main(String[] args) {
		try {
			MongoDbProvider mongoDbProvider = new MongoDbProvider();
			Datastore datastore = mongoDbProvider.get();

			XSSFWorkbook wb = new XSSFWorkbook(
					new ImportZone().getClass().getClassLoader().getResourceAsStream("zone.xlsx"));
			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFRow row;
			int rows;
			rows = sheet.getPhysicalNumberOfRows();
			List<CCPZone> ccpZoneList = new ArrayList<CCPZone>();

			for (int r = 1; r < rows; r++) {
				row = sheet.getRow(r);
				if (row != null) {
					CCPRate ccpRate = datastore.createQuery(CCPRate.class)
							.filter("zoneType =", row.getCell(Constant.CELL_ZONE_TYPE).toString())
							.filter("state =", row.getCell(Constant.CELL_ZONE_CITY).toString()).get();

					CCPZone ccpZone = CCPZone.builder().withRateId(ccpRate.getId().toString())
							.withZoneNumber(row.getCell(Constant.CELL_ZONE_NUMBER).toString())
							.withZoneType(row.getCell(Constant.CELL_ZONE_TYPE).toString())
							.withAddress(row.getCell(Constant.CELL_ZONE_ADDRESS).toString())
							.withCity(row.getCell(Constant.CELL_ZONE_CITY).toString())
							.withLocation(
									LocationEntry.builder()
									.withCoordinates(new double[] 
										    {
										    		parseLatLonValue(row.getCell(Constant.CELL_ZONE_LONGITUD).toString()), 
										    		parseLatLonValue(row.getCell(Constant.CELL_ZONE_LATITUD).toString())
													})
									.withType("Point")
									.build())
							.withStatus("active").build();

					ccpZoneList.add(ccpZone);
				}
			}

			datastore.save(ccpZoneList);
			datastore.getCollection(CCPZone.class).createIndex(Indexes.geo2dsphere("ccpzone.location").toString());
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}
	}

	private static String EXPRESSION = "^(?<deg>[-+0-9]+)[^0-9]+(?<min>[0-9]+)[^0-9]+(?<sec>[0-9.,]+)[^0-9.,ENSW]+(?<pos>[ENSW]*)$";

	/**
	 * Parses the latitude and longitude to double
	 * 
	 * @param value
	 * @return
	 */
	private static double parseLatLonValue(String value) {
		value = value.trim().replace(" ", "");
		double result = Double.NaN;
		if (value.startsWith("\"") && value.endsWith("\"")) {
			value = value.substring(1, value.length() - 2).replace("\"\"", "\"");
		}
		Pattern pattern = Pattern.compile(EXPRESSION);
		Matcher matcher = pattern.matcher(value);
		double deg = Double.NaN;
		double min = Double.NaN;
		double sec = Double.NaN;
		char pos = '\0';
		if (matcher.matches()) {
			deg = Double.parseDouble(matcher.group("deg"));
			min = Double.parseDouble(matcher.group("min"));
			sec = Double.parseDouble(matcher.group("sec"));
			pos = matcher.group("pos").charAt(0);
			result = deg + (min / 60) + (sec / 3600);
			result = ((pos == 'S') || (pos == 'W')) ? -result : result;
		}
		return result;
	}

}
