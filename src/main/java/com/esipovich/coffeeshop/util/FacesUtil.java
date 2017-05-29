package com.esipovich.coffeeshop.util;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

//для получения полей с jsf
//оставить для примера
public class FacesUtil {

    public static String getActionAttribute(ActionEvent event, String name) {
        return (String) event.getComponent().getAttributes().get(name);
    }

    public static String getRequestParameter(String name) {
        return FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get(name);
    }
}
