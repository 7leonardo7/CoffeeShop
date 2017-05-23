package com.esipovich.coffeeshop.controller;

import com.esipovich.coffeeshop.dao.CoffeeOrderDao;
import com.esipovich.coffeeshop.dao.CoffeeOrderDaoHibernate;
import com.esipovich.coffeeshop.model.CoffeeOrder;
import com.esipovich.coffeeshop.util.Coffee;
import com.esipovich.coffeeshop.util.DateTimeUtil;
import com.esipovich.coffeeshop.util.FacesUtil;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import java.util.Date;
import java.util.List;

@ManagedBean
@SessionScoped
public class CoffeeOrderBean {

    private CoffeeOrder order;
    private DataModel orderList;
    private CoffeeOrderDao orderDao;
    private String delivery, orderStatus;

    public CoffeeOrderBean() {
    }

    @PostConstruct
    public void init(){
        orderDao = new CoffeeOrderDaoHibernate();
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    @SuppressWarnings("unchecked")
    public DataModel<CoffeeOrder> getListOfOrders(){
        if(orderList == null) {
            List<CoffeeOrder> orders = orderDao.getAll();
            orderList = new ListDataModel<>(orders);
        }
        return orderList;
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

    /*public List<String> getDetails() {
        List<String> ordersDetails = new ArrayList<>();
        for(CoffeeOrder order : orderDao.getAll()) {
            ordersDetails.add(order.toString());
        }
        return ordersDetails;
    }*/

    public String addOrder(){
        orderDao.save(order);
        orderStatus = "The order is accepted";
        return orderList();
    }

    public String updateOrder(){
        orderDao.update(order);
        orderStatus = "The order was successfully changed";
        return orderList();
    }

    public String deleteOrder(){
        orderDao.delete(order);
        orderStatus = "The order was successfully deleted";
        return orderList();
    }

    public String prepareOrderToAdd(){
        order = new CoffeeOrder();
        return "order";
    }

    //если использовать для передачи данных setPropertyActionListener на orders.xhtml,
    //то закомментировать order = (CoffeeOrder)(orderList.getRowData());
    public String prepareOrderToUpdate(){
        order = (CoffeeOrder)(orderList.getRowData());
        System.out.println("prepare to upd: " + order.getDeliveryTimeFrom() + " " + order.getDeliveryTimeTo());
        delivery = order.getDeliveryTimeFrom() == null ? "0" : "1";
        System.out.println("delivery after get order: " + delivery);
        return "order";
    }

    //при использовании  c f:param можно передать id и по нему получить объект
    //CoffeeOrder
    //актуально, если на orders.xhtml использовать объект CoffeeOrder и выводить в
    //таблице каждое его поле отдельно
    public String prepareOrderToDelete(){
        //String id = FacesUtil.getRequestParameter("id");
        //order = orderDao.get(Integer.valueOf(id));
        order = (CoffeeOrder)(orderList.getRowData());
        return "delete";
    }

    public String orderList() {
        List<CoffeeOrder> orders = orderDao.getAll();
        orderList = new ListDataModel<>(orders);
        return "orders";
    }

    public List coffeeKindsList(){
        return Coffee.getCoffeeKinds();
    }

    public double countCost(){
        double cost = Coffee.getCoffeePrice(order.getCoffeeKind()) * order.getQuantity() + Double.valueOf(delivery);
        order.setCost(cost);
        return cost;
    }

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

    public void showTime(){
        System.out.println(order.getDeliveryTimeFrom());
    }

}
