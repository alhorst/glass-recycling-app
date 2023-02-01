package com.techelevator.dao;

import com.techelevator.model.Routes;

import java.util.Date;
import java.util.List;

public interface RoutesDao {

    Routes getRoutesByRouteId(int routeId);

    List<Routes> getRoutesByDate(Date routeDate);

    Routes createRoute(Routes route);

    void updateRoute(Routes route);

    void deleteRoute(int routeId);

}
