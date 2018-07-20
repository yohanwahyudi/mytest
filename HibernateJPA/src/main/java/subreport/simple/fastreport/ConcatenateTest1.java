package subreport.simple.fastreport;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class ConcatenateTest1 {
	
	protected final static Map<String, Object> params = new HashMap<String, Object>();
	
	private static Style createHeaderStyle() {
		Font font = new Font();
		font.setFontSize(8);
		font.setBold(true);
		
		Color color = new Color(247, 199, 104);
//		Color color = new Color(255,180,95);
		
		
		StyleBuilder sb = new StyleBuilder(true);
		sb.setFont(font);
//		sb.setBorder(Border.THIN());
//		sb.setBorderBottom(Border.THIN());
//		sb.setBorderColor(Color.BLACK);
		sb.setBackgroundColor(color);
		sb.setTextColor(Color.BLACK);
		sb.setHorizontalAlign(HorizontalAlign.CENTER);
		sb.setVerticalAlign(VerticalAlign.MIDDLE);
		sb.setTransparency(Transparency.OPAQUE);
		return sb.build();
	}

	private static Style createDetailTextStyle() {
		Font font = new Font();
		font.setFontSize(7);
		
		StyleBuilder sb = new StyleBuilder(true);
		sb.setFont(font);
//		sb.setBorder(Border.THIN());
//		sb.setBorderColor(Color.BLACK);
		sb.setTextColor(Color.BLACK);
		sb.setHorizontalAlign(HorizontalAlign.LEFT);
		sb.setVerticalAlign(VerticalAlign.MIDDLE);
//		sb.setPaddingLeft(5);
		return sb.build();
	}
	
	private static Style createOddBgStyle() {
		Font font = new Font();
		font.setFontSize(8);
		font.setBold(true);
		
		Color color = new Color(253, 247, 233);
		
		
		StyleBuilder sb = new StyleBuilder(true);
		sb.setFont(font);
//		sb.setBorder(Border.THIN());
//		sb.setBorderBottom(Border.THIN());
//		sb.setBorderColor(Color.BLACK);
		sb.setBackgroundColor(color);
		sb.setTextColor(Color.BLACK);
		sb.setHorizontalAlign(HorizontalAlign.CENTER);
		sb.setVerticalAlign(VerticalAlign.MIDDLE);
		sb.setTransparency(Transparency.OPAQUE);
		return sb.build();
	}

	private static AbstractColumn createColumn(String property, Class type, String title, int width, Style headerStyle,
			Style detailStyle) throws ColumnBuilderException {
		AbstractColumn columnState = ColumnBuilder.getNew().setColumnProperty(property, type.getName()).setTitle(title)
				.setWidth(Integer.valueOf(width)).setStyle(detailStyle).setHeaderStyle(headerStyle).build();
		return columnState;
	}
	
	public static DynamicReport buildReport() throws Exception{
		
//		AbstractColumn columnDivision = createColumn("division", String.class, "Division", 45, createHeaderStyle(), createDetailTextStyle());
//		AbstractColumn columnName = createColumn("name", String.class, "Name", 150, createHeaderStyle(), createDetailTextStyle());
//		AbstractColumn columnAssigned = createColumn("assigned", Integer.class, "Assigned", 50, createHeaderStyle(), createDetailTextStyle());
//		AbstractColumn columnPending = createColumn("pending", Integer.class, "Pending", 50, createHeaderStyle(), createDetailTextStyle());
//		AbstractColumn columnAchieved = createColumn("achieved", Integer.class, "Achieved", 50, createHeaderStyle(), createDetailTextStyle());
//		AbstractColumn columnMissed = createColumn("missed", Integer.class, "Missed", 50, createHeaderStyle(), createDetailTextStyle());
//		AbstractColumn columnTicket = createColumn("ticketTotal", Integer.class, "Ticket Total", 45, createHeaderStyle(), createDetailTextStyle());
//		AbstractColumn columnAchievement = createColumn("achievement", String.class, "Achievement", 50, createHeaderStyle(), createDetailTextStyle());
//		AbstractColumn columnRemarks = createColumn("remarks", String.class, "Remarks", 50, createHeaderStyle(), createDetailTextStyle());
//		
		
		FastReportBuilder frb = new FastReportBuilder();
		frb.addColumn("Division", "division", String.class, 45,createDetailTextStyle(), createHeaderStyle())
			.addColumn("Name","name",String.class, 150, createDetailTextStyle(), createHeaderStyle())
			.addColumn("Assigned","assigned",Integer.class, 50,createDetailTextStyle(), createHeaderStyle())
			.addColumn("Pending","pending",Integer.class, 50,createDetailTextStyle(), createHeaderStyle())
			.addColumn("Achieved","achieved",Integer.class, 50,createDetailTextStyle(), createHeaderStyle())
			.addColumn("Missed","missed",Integer.class, 50,createDetailTextStyle(), createHeaderStyle())
			.addColumn("Ticket Total","ticketTotal",Integer.class, 40,createDetailTextStyle(), createHeaderStyle())
			.addColumn("Achievement","achievement",String.class, 50,createDetailTextStyle(), createHeaderStyle())
			.addColumn("Remarks","remarks",String.class, 50,createDetailTextStyle(), createHeaderStyle())
			.addGroups(1)
			.setTitle("PERFORMANCE SUPPORT AGENT VDI BASED ON ITOP JULY 2018")
			.setSubtitle("This report was generated at " + new Date()).setSubtitleStyle(createDetailTextStyle())
			.setPrintBackgroundOnOddRows(true).setOddRowBackgroundStyle(createOddBgStyle())
			.setUseFullPageWidth(true);
		
		List<Subreport1> list = new ArrayList<Subreport1>();
		list = getListSubreport1();
		
		frb.addField("subreport1", list.getClass());
		
		params.put("subreport1",list);
		
		DynamicReport subReport1 = createHeaderSubreport();
//		frb.addSubreportInGroupFooter(1,subReport1, new ClassicLayoutManager(), "subreport1", 
//				DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION);
		
		frb.addConcatenatedReport(subReport1, new ClassicLayoutManager(), "subreport1",
				DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION, true);
		
		DynamicReport dr = frb.build();
		
		return dr;
		
	}
	
	public static JasperPrint getReport() throws JRException, Exception {
		List<Agent> list = new ArrayList<Agent>();
		
		Agent agent = new Agent("ITAMS","EXT DIMAS FADHLIR RAHMAN EFFENDI", 10, 0, 30, 0, 100,"100%","");
		list.add(agent);
		list.add(new Agent("ITAMS","EXT FADILLAH NOVA STYAJAYA", 10, 0, 30, 0, 100,"100%",""));
		list.add(new Agent("ITAMS","EXT SYAFI'I AZAMI AZAMI", 10, 0, 30, 0, 100,"100%",""));
		
		params.put("subreport1", getListSubreport1());
		
		JasperPrint jp = DynamicJasperHelper.generateJasperPrint(buildReport(), new ClassicLayoutManager(),
				new JRBeanCollectionDataSource(list));
		
		return jp;
	}
	
	public static JasperPrint getReport2() throws JRException, Exception {
		JRDataSource ds = getDataSource();
		JasperReport jr = DynamicJasperHelper.generateJasperReport(buildReport(), new ClassicLayoutManager(), params);
		JasperPrint jp = JasperFillManager.fillReport(jr, params, ds);
		
		return jp;
	}
	
	public static JRDataSource getDataSource() {
		List<Agent> list = new ArrayList<Agent>();
		
		Agent agent = new Agent("ITAMS","EXT DIMAS FADHLIR RAHMAN EFFENDI", 10, 0, 30, 0, 100,"100%","");
		list.add(agent);
		list.add(new Agent("ITAMS","EXT FADILLAH NOVA STYAJAYA", 10, 0, 30, 0, 100,"100%",""));
		list.add(new Agent("ITAMS","EXT SYAFI'I AZAMI AZAMI", 10, 0, 30, 0, 100,"100%",""));
		
		return new JRBeanCollectionDataSource(list);
	}
	
	private static DynamicReport createHeaderSubreport() throws Exception {
		FastReportBuilder rb = new FastReportBuilder();
		DynamicReport dr = rb.addColumn("ref", "ref", String.class, 50, createDetailTextStyle(), createHeaderStyle())
				.addColumn("title", "title", String.class, 100, createDetailTextStyle(), createHeaderStyle())
				.setMargins(5, 5, 20, 20).setPrintBackgroundOnOddRows(true).setOddRowBackgroundStyle(createOddBgStyle())
				.setTitle("Header Subreport for this group")
				.build();
		return dr;
	}
	
	public static List<Subreport1> getListSubreport1(){
		List<Subreport1> subList = new ArrayList<Subreport1>();
		subList.add(new Subreport1("I-000001", "Incident 1"));
		subList.add(new Subreport1("I-000002", "Incident 2"));
		
		return subList;
	}
	
	public static void main (String args[]) throws JRException, Exception {
		JasperViewer jv = new JasperViewer(getReport2());
		jv.setVisible(true);
	}

}
