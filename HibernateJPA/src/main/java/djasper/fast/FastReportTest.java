	package djasper.fast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.DJValueFormatter;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import net.sf.jasperreports.view.JasperDesignViewer;
import net.sf.jasperreports.view.JasperViewer;

public class FastReportTest {
	
	public DynamicReport buildReport() throws Exception {
		  
		  
  		/**
  		 * Creates the DynamicReportBuilder and sets the basic options for
  		 * the report
  		 */
  		FastReportBuilder drb = new FastReportBuilder();
  		drb.addColumn("State", "state", String.class.getName(),30)
  			.addColumn("Branch", "branch", String.class.getName(),30)
  			.addColumn("Product Line", "productLine", String.class.getName(),50)
  			.addColumn("Item", "item", String.class.getName(),50)
  			.addColumn("Item Code", "id", Long.class.getName(),30,true)
  			.addColumn("Quantity", "quantity", Long.class.getName(),60,true)
  			.addColumn("Amount", "amount", Float.class.getName(),70,true)
  			.addGroups(2)
  			.setTitle("November " + getYear() + " sales report")
  			.setSubtitle("This report was generated at " + new Date())
  			.setPrintBackgroundOnOddRows(true)			
  			.setUseFullPageWidth(true);
  
          drb.addGlobalFooterVariable(drb.getColumn(4), DJCalculation.COUNT, null, new DJValueFormatter() {
  
              public String getClassName() {
                  return String.class.getName();
              }
  
  
              public Object evaluate(Object value, Map fields, Map variables,   Map parameters) {
                  return (value == null ? "0" : value.toString()) + " Clients";
              }
          });
  
  
  		DynamicReport dr = drb.build();
  
  		return dr;
  	}
  
  	private String getYear() {
  		Date date = new Date();
  		Calendar calendar = new GregorianCalendar();
  		calendar.setTime(date);
  		int year = calendar.get(Calendar.YEAR);
  		//Add one to month {0 - 11}
  		int month = calendar.get(Calendar.MONTH) + 1;
  		int day = calendar.get(Calendar.DAY_OF_MONTH);
  		
  		return String.valueOf(year);
	}

	public static void main(String[] args) throws Exception {
  		FastReportTest test = new FastReportTest();
//  		test.testReport();
//  		test.exportToJRXML();
//  		JasperViewer.viewReport(test.jp);	//finally display the report report
//  		JasperDesignViewer.viewReportDesign(test.jr);
  		
  		
  	}

}
