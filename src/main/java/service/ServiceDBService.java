package service;

import model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDBService {
    DBService dbs = new DBService();

    public List<Service> getAll() {
        List<Service> services = new ArrayList<>();

        try {
            ResultSet rs = dbs.select("SELECT * FROM service");
            while (rs.next()) {
                services.add(new Service(
                        rs.getInt("id"),
                        rs.getInt("request"),
                        rs.getInt("master")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {}

        return services;
    }

    public List<model.client.Service> getByRequest(int request) {
        List<model.client.Service> services = new ArrayList<>();

        try {
            ResultSet rs = dbs.select(
                    "SELECT s.id as id, u.name as masterName " +
                        "FROM service s " +
                        "JOIN users u ON s.master=u.id " +
                        "WHERE s.request=" + request);
            while (rs.next()) {
                services.add(new model.client.Service(
                        rs.getInt("id"),
                        rs.getString("masterName")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {}

        return services;
    }

    public Service getByID(int id) {
        Service service = null;

        try {
            ResultSet rs = dbs.select("SELECT * FROM service WHERE id=" + id);
            if (rs.next()) {
                service = new Service(
                        rs.getInt("id"),
                        rs.getInt("request"),
                        rs.getInt("master")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {}

        return service;
    }

    public model.client.Service getForClientByID(int id) {
        model.client.Service service = null;

        try {
            ResultSet rs = dbs.select(
                    "SELECT s.id as id, s.request as request, u.name as masterName " +
                            "FROM service s " +
                            "JOIN users u ON s.master=u.id " +
                            "WHERE s.id=" + id);
            if (rs.next()) {
                service = new model.client.Service(
                        rs.getInt("id"),
                        rs.getInt("request"),
                        rs.getString("masterName")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {}

        return service;
    }

    public model.master.Service getForMasterByID(int id) {
        model.master.Service service = null;

        try {
            ResultSet rs = dbs.select(
                    "SELECT s.*, u.id as clientId, u.name as clientName, r.request_date, r.description, st.name as statusName " +
                            "FROM service s " +
                            "JOIN request r ON s.request=r.id " +
                            "JOIN users u ON r.client=u.id " +
                            "JOIN status st ON r.status=st.id " +
                            "WHERE s.id=" + id
            );
            if (rs.next()) {
                service = new model.master.Service(
                        rs.getInt("id"),
                        rs.getInt("request"),
                        rs.getInt("master"),
                        rs.getInt("clientId"),
                        rs.getString("clientName"),
                        rs.getDate("request_date"),
                        rs.getString("description"),
                        rs.getString("statusName")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {}

        return service;
    }

    public List<model.master.Service> getByMaster(int master) {
        List<model.master.Service> services = new ArrayList<>();

        try {
            ResultSet rs = dbs.select(
                    "SELECT s.*, u.id as clientId, u.name as clientName, r.request_date, r.description, st.name as statusName " +
                            "FROM service s " +
                            "JOIN request r ON s.request=r.id " +
                            "JOIN users u ON r.client=u.id " +
                            "JOIN status st ON r.status=st.id " +
                            "WHERE s.master=" + master
            );
            while (rs.next()) {
                services.add(new model.master.Service(
                        rs.getInt("id"),
                        rs.getInt("request"),
                        rs.getInt("master"),
                        rs.getInt("clientId"),
                        rs.getString("clientName"),
                        rs.getDate("request_date"),
                        rs.getString("description"),
                        rs.getString("statusName")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e ) {}

        return services;
    }

    public boolean create(Service service) {
        String sql = "INSERT INTO service(request, master) values("
                + service.getRequest() + ", "
                + service.getMaster()
                + ")";

        return dbs.insert(sql);
    }

    public boolean edit(int id, Service service) {
        boolean result = true;

        try {
            ResultSet rs = dbs.select("SELECT * FROM service WHERE id=" + id);
            if (rs.next()) {
                rs.updateInt("request", service.getRequest());
                rs.updateInt("master", service.getMaster());
                rs.updateRow();
            } else throw new SQLException();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (NullPointerException e) {
            return false;
        }

        return result;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM service WHERE id=" + id;
        return dbs.delete(sql);
    }
}
