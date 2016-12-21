import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import com.venkatesh.data.sorter.beans.ModelBean;
import com.venkatesh.data.sorter.core.ApplicationContext;

public class DataSorter {
	
	ApplicationContext context = ApplicationContext.getInstance();
	
	public float getOpeningBalance(ModelBean bean, ModelBean foundBean) {
		
		float oBalance = 0.0F;
		if(foundBean != null) {
			oBalance = foundBean.getDieselBalance();
		}
		
		float balance = oBalance + bean.getDieselReceived() - bean.getDieselConsumed();
		context.fillOpeningBalance(balance);
		return balance;
	}
	
	public static void main(String[] args) throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		DataSorter sorter = new DataSorter();
		
		ModelBean bean1 = new ModelBean();
		bean1.setSiNo(1);
		bean1.setEntryDate(dateFormat.parse("01/01/2016"));
		bean1.setDieselReceived(1000.0F);
		bean1.setDieselConsumed(0.0F);
		
		sorter.addToList(bean1);
		
		ModelBean bean2 = new ModelBean();
		bean2.setSiNo(2);
		bean2.setEntryDate(dateFormat.parse("01/01/2016"));
		bean2.setDieselReceived(500.0F);
		bean2.setDieselConsumed(0.0F);
		
		sorter.addToList(bean2);
		
		ModelBean bean3 = new ModelBean();
		bean3.setSiNo(3);
		bean3.setEntryDate(dateFormat.parse("02/01/2016"));
		bean3.setDieselReceived(0.0F);
		bean3.setDieselConsumed(100.0F);
		
		sorter.addToList(bean3);
		
		ModelBean bean4 = new ModelBean();
		bean4.setSiNo(4);
		bean4.setEntryDate(dateFormat.parse("03/01/2016"));
		bean4.setDieselReceived(0.0F);
		bean4.setDieselConsumed(100.0F);
		
		sorter.addToList(bean4);
		
		ModelBean bean5 = new ModelBean();
		bean5.setSiNo(5);
		bean5.setEntryDate(dateFormat.parse("01/01/2016"));
		bean5.setDieselReceived(0.0F);
		bean5.setDieselConsumed(100.0F);
		
		sorter.addToList(bean5);
		
		
		sorter.display();
	}
	
	public void addToList(ModelBean bean) {
		if(context.getBeanList().isEmpty()) {
			bean.setDieselBalance(getOpeningBalance(bean, null));
			context.addBean(bean);
		} else {
			if(checkBeanAvailableWithOlderDates(bean)) {
				ModelBean foundBean = context.getTheBeansByDate(bean.getEntryDate()).getLast();
				bean.setDieselBalance(getOpeningBalance(bean, foundBean));
				
				LinkedList<ModelBean> beanWithOlderDateList = context.getTheBeansGreaterThanSpecifiedDate(bean.getEntryDate());
				foundBean = bean;
				for(ModelBean b : beanWithOlderDateList) {
					b.setDieselBalance(getOpeningBalance(b,foundBean));
					foundBean = b;
				}
				context.addBean(bean);
			} else {
				bean.setDieselBalance(getOpeningBalance(bean, context.getBeanList().getLast()));
				context.addBean(bean);
			}
		}
		
	}
	
	public boolean checkBeanAvailableWithOlderDates(ModelBean bean) {
		if(context.getTheBeansGreaterThanSpecifiedDate(bean.getEntryDate()).isEmpty())
			return false;
		else
			return true;
	}
	
	public void display() {
		context.printList();
	}
	
}
