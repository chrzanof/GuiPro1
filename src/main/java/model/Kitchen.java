package model;

import dao.CookDao;
import dao.DeliveryOrderDao;
import dao.StationaryOrderDao;

import java.util.ArrayList;
import java.util.List;

public class Kitchen {
    private static Kitchen INSTANCE = null;
    private static final int REALIZATION_TIME = 30;
    private static final int DELIVERY_TIME = 120;
    private CookDao cookDao;
    private DeliveryOrderDao deliveryOrderDao;
    private StationaryOrderDao stationaryOrderDao;

    private Kitchen() {

    }

    public static Kitchen getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Kitchen();
        }
        return INSTANCE;
    }

    public DeliveryOrderDao getDeliveryOrderDao() {
        return deliveryOrderDao;
    }

    public void setDeliveryOrderDao(DeliveryOrderDao deliveryOrderDao) {
        this.deliveryOrderDao = deliveryOrderDao;
    }

    public StationaryOrderDao getStationaryOrderDao() {
        return stationaryOrderDao;
    }

    public void setStationaryOrderDao(StationaryOrderDao stationaryOrderDao) {
        this.stationaryOrderDao = stationaryOrderDao;
    }

    public CookDao getCookDao() {
        return cookDao;
    }

    public void setCookDao(CookDao cookDao) {
        this.cookDao = cookDao;
    }
}
