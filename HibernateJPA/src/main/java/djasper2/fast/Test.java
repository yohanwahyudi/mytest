package djasper2.fast;

import java.util.HashMap;

import org.apache.log4j.Logger;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import djasper2.SalesDAO;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class Test {
	
	protected static Logger logger = Logger.getLogger(Test.class);
	
	public static void main (String args[]) throws ColumnBuilderException, ClassNotFoundException, JRException {
		
		logger.debug("Downloading Excel report");
		
		SalesDAO datasource = new SalesDAO();
		JRDataSource ds = datasource.getDataSource();
		
		ReportLayout layout = new ReportLayout();
		DynamicReport dr = layout.buildReportLayout();
		
		HashMap params = new HashMap();
		JasperReport jr = DynamicJasperHelper.generateJasperReport(dr,
				new ClassicLayoutManager(), params);
		
		JasperPrint jp = JasperFillManager.fillReport(jr, params, ds);
		JasperViewer.viewReport(jp);
		
	}

}
