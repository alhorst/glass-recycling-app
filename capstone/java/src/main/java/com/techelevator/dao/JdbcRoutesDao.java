package com.techelevator.dao;

import com.techelevator.model.Routes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class JdbcRoutesDao implements RoutesDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcRoutesDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Routes getRoutesByRouteId(int routeId) {

        Routes routes = null;
        String sql = "SELECT route_id, route_date, driver_id " +
                "FROM routes " +
                "WHERE route_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, routeId);
        if (results.next()) {
            routes = mapRowToRoutes(results);
        }

        return routes;
    }

    @Override
    public List<Routes> getRoutesByDate(Date routeDate) {
        List<Routes> routesList = new ArrayList<>();
        String sql = "SELECT route_id, route_date, driver_id " +
                "FROM routes " +
                "WHERE route_date = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, routeDate);
        while (results.next()) {
            routesList.add(mapRowToRoutes(results));
        }

        return routesList;
    }

    @Override
    public Routes createRoute(Routes routes) {
        String sql = "INSERT INTO routes (route_date, driver_id) " +
                "VALUES (?, ?) RETURNING route_id;";
        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class,
        routes.getRouteDate(), routes.getDriverId());

        return getRoutesByRouteId(newId);
    }

    @Override
    public void updateRoute(Routes routes) {
        String sql = "UPDATE routes " +
                "SET route_id = ?, route_date = ?, driver_id = ? " +
                "WHERE routes_id = ?;";
        jdbcTemplate.update(sql, routes.getRouteId(), routes.getRouteDate(), routes.getDriverId());
    }

    @Override
    public void deleteRoute(int routeId) {
        String sql = "DELETE FROM routes WHERE route_id = ?;";
        jdbcTemplate.update(sql, routeId);

    }

    private Routes mapRowToRoutes(SqlRowSet rowSet) {

        Routes routes = new Routes();

        routes.setRouteId(rowSet.getInt("route_id"));
        routes.setRouteDate(rowSet.getDate("route_date"));
        routes.setDriverId(rowSet.getInt("driver_id"));

        return routes;
    }
}
