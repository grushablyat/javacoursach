package service;

import model.Request;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestDBService {
    DBService dbs = new DBService();

    public List<Request> getAll() {
        List<Request> requests = new ArrayList<>();

        try {
            ResultSet rs = dbs.select("SELECT * FROM request");
            while (rs.next()) {
                requests.add(new Request(
                        rs.getInt("id"),
                        rs.getInt("client"),
                        rs.getDate("request_date"),
                        rs.getString("description"),
                        rs.getInt("status")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {}

        return requests;
    }

    public Request getByID(int id) {
        Request request = null;

        try {
            ResultSet rs = dbs.select("SELECT * FROM request WHERE id=" + id);
            if (rs.next()) {
                request = new Request(
                        rs.getInt("id"),
                        rs.getInt("client"),
                        rs.getDate("request_date"),
                        rs.getString("description"),
                        rs.getInt("status")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {}

        return request;
    }

    public boolean create(Request request) {
        String sql = "INSERT INTO request(client, request_date, description, status) values("
                + request.getClient() + ", \'"
                + request.getDateString() + "\', \'"
                + request.getDescription() + "\', \'"
                + request.getStatus()
                + "\')";

        return dbs.insert(sql);
    }

    public boolean edit(int id, Request request) {
        try {
            ResultSet rs = dbs.select("SELECT * FROM request WHERE id=" + id);
            if (rs.next()) {
                rs.updateInt("client", request.getClient());
                rs.updateDate("request_date", request.getDate());
                rs.updateString("description", request.getDescription());
                rs.updateInt("status", request.getStatus());
                rs.updateRow();
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (NullPointerException e) {
            return false;
        }

        return true;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM request WHERE id=" + id;
        return dbs.delete(sql);
    }
}
