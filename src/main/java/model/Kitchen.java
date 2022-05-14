package model;

import dao.CookDao;

import java.util.ArrayList;
import java.util.List;

public class Kitchen {
    private static Kitchen INSTANCE = null;
    private static final int REALIZATION_TIME = 30;
    private static final int DELIVERY_TIME = 120;
    private CookDao cookDao;
    private List<DeliveryOrder> deliveryOrders;
    private List<StationaryOrder> stationaryOrders;

    private Kitchen() {

    }

    public static Kitchen getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Kitchen();
        }
        return INSTANCE;
    }

    public CookDao getCookDao() {
        return cookDao;
    }

    public void setCookDao(CookDao cookDao) {
        this.cookDao = cookDao;
    }
}
