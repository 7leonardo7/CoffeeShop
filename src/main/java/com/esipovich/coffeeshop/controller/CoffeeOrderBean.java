package com.esipovich.coffeeshop.controller;

import com.esipovich.coffeeshop.dao.CoffeeOrderDao;
import com.esipovich.coffeeshop.dao.CoffeeOrderDaoEclipseLink;
import com.esipovich.coffeeshop.dao.CoffeeOrderDaoHibernate;
import com.esipovich.coffeeshop.model.CoffeeOrder;
import com.esipovich.coffeeshop.util.Coffee;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@ManagedBean
@SessionScoped
public class CoffeeOrderBean {

    private CoffeeOrder order;
    private DataModel orderList;
    private CoffeeOrderDao orderDao;
    private String delivery, orderStatus;
    private static final double MIN_QUANTITY = 100;
    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    @PostConstruct
    public void init(){
        //можно переключаться между hibernate/eclipselink
        orderDao = new CoffeeOrderDaoEclipseLink();
    }

    public Locale getLocale() {
        return locale;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public CoffeeOrder getOrder() {
        return order;
    }

    public void setOrder(CoffeeOrder order) {
        this.order = order;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @SuppressWarnings("unchecked")
    //нужно чтобы получить объект из dataTable по нажатию update or delete
    //не используя при этом params or attributes
    public DataModel<CoffeeOrder> getListOfOrders(){
        List<CoffeeOrder> orders = orderDao.getAll();
        orderList = new ListDataModel<>(orders);
        return orderList;
    }

    public String prepareOrderToAdd(){
        order = new CoffeeOrder();
        delivery = "0";
        order.setCoffeeKind(String.valueOf(Coffee.getCoffeeKinds().get(0)));
        order.setQuantity(MIN_QUANTITY);
        countCost();
        return "order";
    }

    public String addOrder(){
        setEmptyTimeIfPickup();
        orderDao.save(order);
        orderStatus = "The order is accepted";
        return "orders";
    }

    //если использовать для передачи данных setPropertyActionListener на orders.xhtml,
    //то закомментировать order = (CoffeeOrder)(orderList.getRowData());
    public String prepareOrderToUpdate(){
        order = (CoffeeOrder)(orderList.getRowData());
        delivery = order.getDeliveryTimeFrom() == null ? "0" : "1";
        return "order";
    }

    public String updateOrder(){
        setEmptyTimeIfPickup();
        orderDao.update(order);
        orderStatus = "The order was successfully changed";
        return "orders";
    }

    //при использовании  c f:param можно передать id и по нему получить объект
    //CoffeeOrder
    //актуально, если на orders.xhtml использовать объект CoffeeOrder и выводить в
    //dataTable каждое его поле отдельно
    public String prepareOrderToDelete(){
        //String id = FacesUtil.getRequestParameter("id");
        //order = orderDao.get(Integer.valueOf(id));
        order = (CoffeeOrder)(orderList.getRowData());
        delivery = order.getDeliveryTimeFrom() == null ? "0" : "1";
        return "delete";
    }

    public String deleteOrder(){
        orderDao.delete(order);
        orderStatus = "The order was successfully deleted";
        return "orders";
    }

    public String backToList(){
        orderStatus = "";
        return "orders";
    }

    public List coffeeKindsList(){
        return Coffee.getCoffeeKinds();
    }

    public void countCost(){
        double cost = Coffee.getCoffeePrice(
                order.getCoffeeKind()) * order.getQuantity() +
                Double.valueOf(delivery);
        order.setCost(cost);
    }

    //подумать насчет изменения статуса другим способом (не вызывая каждый раз метод)
    public String backToMain(){
        orderStatus = "";
        return "index";
    }

    //подумать как сделать по-другому
    public void setEmptyTimeIfPickup(){
        if("0".equals(delivery)) {
            order.setDeliveryTimeFrom(null);
            order.setDeliveryTimeTo(null);
        }
    }

    public void changeLanguage(String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(language));
    }

    public void setCurrentDateTime(){
        if(order.getDeliveryTimeFrom() == null && order.getDeliveryTimeTo() == null) {
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            order.setDeliveryTimeFrom(date);
            calendar.add(Calendar.HOUR, 1);
            order.setDeliveryTimeTo(calendar.getTime());
        }
    }


}
