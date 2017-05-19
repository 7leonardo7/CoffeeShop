package com.esipovich.coffeeshop.controller;

import com.esipovich.coffeeshop.dao.CoffeeOrderDao;
import com.esipovich.coffeeshop.dao.CoffeeOrderDaoHibernate;
import com.esipovich.coffeeshop.model.CoffeeOrder;
import com.esipovich.coffeeshop.util.Coffee;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class CoffeeOrderBean {

    private CoffeeOrder order;
    private DataModel orderList;
    private CoffeeOrderDao orderDao;
    private int delivery = 1;

    public CoffeeOrderBean() {
    }

    @PostConstruct
    public void init(){
        orderDao = new CoffeeOrderDaoHibernate();
    }

    public int getDelivery() {
        return delivery;
    }

    public void setDelivery(int delivery) {
        this.delivery = delivery;
    }

    @SuppressWarnings("unchecked")
    public DataModel<CoffeeOrder> getListOfOrders(){
        List<CoffeeOrder> orders = orderDao.getAll();
        orderList = new ListDataModel<>(orders);
        return orderList;
    }

    public CoffeeOrder getOrder() {
        return order;
    }

    public void setOrder(CoffeeOrder order) {
        this.order = order;
    }

    public List<String> getDetails() {
        List<String> ordersDetails = new ArrayList<>();
        for(CoffeeOrder order : orderDao.getAll()) {
            ordersDetails.add(order.toString());
        }
        return ordersDetails;
    }

    public String addOrder(){
        orderDao.save(order);
        return "orders";
    }

    public String updateOrder(){
        orderDao.update(order);
        return "orders";
    }

    public String deleteOrder(){
        orderDao.delete(order);
        return "orders";
    }

    public String prepareOrderToAdd(){
        order = new CoffeeOrder();
        return "order";
    }

    //если использовать для передачи данных setPropertyActionListener на orders.xhtml,
    //то закомментировать order = (CoffeeOrder)(orderList.getRowData());
    public String prepareOrderToUpdate(){
        order = (CoffeeOrder)(orderList.getRowData());
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
}
