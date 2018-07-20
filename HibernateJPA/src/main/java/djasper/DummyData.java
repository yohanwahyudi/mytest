package djasper;

import java.util.ArrayList;
import java.util.List;

import djasper.model.Sales;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class DummyData {
	
	public JRDataSource getDataSource() {
		List<Sales> salesList = new ArrayList<Sales>();
		
		Sales sales = new Sales();
		sales.setState("State1");
		sales.setAmount(100);
		sales.setBranch("Branch1");
		sales.setItem("Item1");
		sales.setItemCode(1);
		sales.setProductLine("productLine 1");
		sales.setQuantity(4);
		salesList.add(sales);
		
		
		sales = new Sales();
		sales.setState("State2");
		sales.setAmount(200);
		sales.setBranch("Branch1");
		sales.setItem("Item2");
		sales.setItemCode(2);
		sales.setProductLine("productLine 2");
		sales.setQuantity(40);
		salesList.add(sales);
		
		JRDataSource ds = new JRBeanCollectionDataSource(salesList); 
		
		return ds;
	}

}
