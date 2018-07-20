package subreport.simple.fastreport;

import java.util.Date;
import java.util.Map;

import javax.swing.text.StyledEditorKit;

import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.DJValueFormatter;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import net.sf.jasperreports.view.JasperDesignViewer;
import net.sf.jasperreports.view.JasperViewer;
import subreport.simple.dependency.BaseDjReportTest;

public class FastReportTest extends BaseDjReportTest {

	@Override
	public DynamicReport buildReport() throws Exception {
		
		Font font = new Font(20,"Roboto Black","/fonts/Roboto-Black.ttf",Font.PDF_ENCODING_Identity_H_Unicode_with_horizontal_writing,true);
		Style titleStyle = new StyleBuilder(true).setFont(font).setHorizontalAlign(HorizontalAlign.CENTER).build();
		
		Font fontSub = new Font(8,"Calibri","/fonts/calibri.ttf", Font.PDF_ENCODING_Identity_H_Unicode_with_horizontal_writing,false);
		Style subTitleStyle = new StyleBuilder(true).setFont(fontSub).setHorizontalAlign(HorizontalAlign.LEFT).build();
		
		/*
		 * Creates the DynamicReportBuilder and sets the basic options for the report
		 */
		FastReportBuilder drb = new FastReportBuilder();
		drb.addColumn("State", "state", String.class.getName(), 30)
				.addColumn("Branch", "branch", String.class.getName(), 30)
				.addColumn("Product Line", "productLine", String.class.getName(), 50)
				.addColumn("Item", "item", String.class.getName(), 50)
				.addColumn("Item Code", "id", Long.class.getName(), 30, true)
				.addColumn("Quantity", "quantity", Long.class.getName(), 60, true)
				.addColumn("Amount", "amount", Float.class.getName(), 70, true)
				.addGroups(2)
				.setTitle("November " + getYear() + " sales report")
				.setTitleStyle(titleStyle)
				.setSubtitle("This report was generated at " + new Date()).setSubtitleStyle(subTitleStyle)
				.setPrintBackgroundOnOddRows(true)
				.setUseFullPageWidth(true);

		
//		drb.addGlobalFooterVariable(drb.getColumn(4), DJCalculation.COUNT, null, new DJValueFormatter() {
//
//			public String getClassName() {
//				return String.class.getName();
//			}
//
//			public Object evaluate(Object value, Map fields, Map variables, Map parameters) {
//				return (value == null ? "0" : value.toString()) + " Clients";
//			}
//		});

		DynamicReport dr = drb.build();

		return dr;
	}

	public static void main(String[] args) throws Exception {
		FastReportTest test = new FastReportTest();
		test.testReport();
//		test.exportReport();
//		test.exportToJRXML();
		JasperViewer.viewReport(test.jp); // finally display the report report
		// JasperDesignViewer.viewReportDesign(test.jr);
	}

}
