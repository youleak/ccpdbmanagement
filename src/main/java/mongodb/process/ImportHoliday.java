package mongodb.process;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;

import mongodb.models.CCPHoliday;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Component;
import utils.DateHelper;
import utils.MongoDbProvider;

/**
 * 
 *
 */
public class ImportHoliday 
{
    public static void main( String[] args )
    {
		new ImportHoliday().loadIcs();
		
    }
    
    private void loadIcs() {
        

        try {
			MongoDbProvider mongoDbProvider = new MongoDbProvider();
			
			Datastore datastore = mongoDbProvider.get();
			
			CalendarBuilder builder = new CalendarBuilder();
            net.fortuna.ical4j.model.Calendar calendar = 
            		builder.build(this.getClass().getClassLoader().
            				getResourceAsStream("ics_malaysia.ics"));

            List<CCPHoliday> ccpHolidayList = new ArrayList<CCPHoliday>();

            for (Iterator i = calendar.getComponents().iterator(); i.hasNext();) {
                Component component = (Component) i.next();
                String summary = component.getProperty("SUMMARY").getValue();
                
                if(!(summary.indexOf("[Not a public holiday]") > 0)) {
	                String date = component.getProperty("DTSTART").getValue();
	                String description = component.getProperty("DESCRIPTION").getValue();
	                String[] states = {"ALL"};
	                
	                int region = description.indexOf("A regional holiday in ");
	                
	                if(region >= 0) {
	                	states = description.substring(region + "A regional holiday in ".length(),
	                			description.indexOf(".", region)
	                			).trim().toUpperCase().replace(", ", ",").split(",");
	                }
	                
	                CCPHoliday ccpHolidayExist = datastore.createQuery(CCPHoliday.class).disableValidation()
	            			.filter("date =", DateHelper.convertToString(date))
	            			.get();
	                if(ccpHolidayExist == null) {
		                CCPHoliday ccpHoliday  = CCPHoliday.builder()
			                	.withStatus("active")
			                	.withDate(DateHelper.convertToString(date))
			                	.withState(Arrays.asList(states))
			                	.withDescription(description)
			                	.withHoliday(summary).build();
		                
		                ccpHolidayList.add(ccpHoliday);
	                }
	                
	                
                }
            }
            
            datastore.save(ccpHolidayList);
            
            System.out.println(ccpHolidayList.size() + " Added.");
            System.out.println("done");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}
