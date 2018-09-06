package subreport.simple.chart;

import java.util.HashMap;
import java.util.Map;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import subreport.simple.Product;
import subreport.simple.dependency.ChartReportTest;
import subreport.simple.dependency.ReportExporter;
import subreport.simple.dependency.TestRepositoryProducts;

public class SimpleXYChart {

	protected final Map<String, Object> params = new HashMap<String, Object>();
	private DynamicReport dr;
	private JasperReport jr;
	private JasperPrint jp;

	public DynamicReport buildReport() throws Exception {
		Style titleStyle = new Style();
		
		DynamicReportBuilder drb = new DynamicReportBuilder();
//		Integer margin = 20;
//		drb.setTitleStyle(titleStyle).setTitle("Concatenated reports") // defines the title of the report
//				.setSubtitle("All the reports shown here are concatenated as sub reports").setDetailHeight(15)
//				.setLeftMargin(margin).setRightMargin(margin).setTopMargin(margin).setBottomMargin(margin)
//				.setUseFullPageWidth(true).setWhenNoDataAllSectionNoDetail()
//				.addAutoText(AutoText.AUTOTEXT_PAGE_X_OF_Y, AutoText.POSITION_FOOTER, AutoText.ALIGNMENT_CENTER)
//				.addConcatenatedReport(createSubreport1("Sub report 1"), new ClassicLayoutManager(), "statistics",
//						DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION, false)
//				.addConcatenatedReport(createSubreport2("Sub report 2"), "statistics",
//						DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION, true)
//				.addConcatenatedReport(createSubreport2("Sub report 3"), "statistics",
//						DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION, true)
//				.addConcatenatedReport(createSubreport2("Sub report 4"), "statistics",
//						DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION, true);
//
//		params.put("statistics", Product.statistics);

		
		ChartReportTest crt = new ChartReportTest();
		JasperReport chartJr = DynamicJasperHelper.generateJasperReport(crt.buildReport(), new ClassicLayoutManager(),
				new HashMap());
		
		params.put("subreportsDataSource", TestRepositoryProducts.getDummyCollection());


		return drb.build();
	}

	public void testReport() throws Exception {
		dr = buildReport();

		jr = DynamicJasperHelper.generateJasperReport(dr, new ClassicLayoutManager(), params);

		/*
		 * Creates the JasperPrint object, we pass as a Parameter the JasperReport
		 * object, and the JRDataSource
		 */
		jp = JasperFillManager.fillReport(jr, params);
		ReportExporter.exportReport(jp, System.getProperty("user.dir") + "/target/ConcatenatedReportTest.pdf");

	}

	public static void main(String args[]) {

	}

}
