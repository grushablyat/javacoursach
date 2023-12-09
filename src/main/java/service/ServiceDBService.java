package service;

import model.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDBService {
    public List<Service> getAll() {
        List<Service> services = new ArrayList<>();

        try {
            ResultSet rs = new DBService().select("SELECT * FROM service");
            while (rs.next()) {
                services.add(new Service(
                        rs.getInt("id"),
                        rs.getInt("request"),
                        rs.getInt("master")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return services;
    }

    public Service getByID(int id) {
        Service service = null;

        try {
            ResultSet rs = new DBService().select("SELECT * FROM service WHERE id=" + id);
            if (rs.next()) {
                service = new Service(
                        rs.getInt("id"),
                        rs.getInt("request"),
                        rs.getInt("master")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return service;
    }

    public boolean create(Service service) {
        String sql = "INSERT INTO service(request, master) values("
                + service.getRequest() + ", "
                + service.getMaster()
                + ")";

        return new DBService().insert(sql);
    }

    public boolean edit(int id, Service service) {
        boolean result = true;

        try {
            ResultSet rs = new DBService().select("SELECT * FROM service WHERE id=" + id);
            if (rs.next()) {
                rs.updateInt("request", service.getRequest());
                rs.updateInt("master", service.getMaster());
                rs.updateRow();
            } else throw new SQLException();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return result;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM service WHERE id=" + id;
        return new DBService().delete(sql);
    }
}
