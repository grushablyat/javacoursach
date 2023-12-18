package service;

import model.Request;
import model.RequestExtended;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestExtendedDBService {
    DBService dbs = new DBService();

    public List<RequestExtended> getAll() {
        List<RequestExtended> requests = new ArrayList<>();

        try {
            ResultSet rs = dbs.select(
                    "SELECT r.*, s.name as statusName, u.name as clientName " +
                        "FROM request r " +
                        "JOIN status s ON r.status=s.id " +
                        "JOIN users u ON r.client=u.id");
            while (rs.next()) {
                requests.add(new RequestExtended(
                        rs.getInt("id"),
                        rs.getInt("client"),
                        rs.getDate("request_date"),
                        rs.getString("description"),
                        rs.getInt("status"),
                        rs.getString("statusName"),
                        rs.getString("clientName")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {}

        return requests;
    }

    public List<RequestExtended> getByClient(int client) {
        List<RequestExtended> requests = new ArrayList<>();

        try {
            ResultSet rs = dbs.select(
                    "SELECT r.*, s.name as statusName " +
                        "FROM request r " +
                        "JOIN status s ON r.status=s.id " +
                        "WHERE r.client=" + client);
            while (rs.next()) {
                requests.add(new RequestExtended(
                        rs.getInt("id"),
                        rs.getDate("request_date"),
                        rs.getString("description"),
                        rs.getString("statusName")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {}

        return requests;
    }

    public RequestExtended getByID(int id) {
        RequestExtended request = null;

        try {
            ResultSet rs = dbs.select(
                    "SELECT r.*, s.name as statusName, u.name as clientName "+
                        "FROM request r "+
                        "JOIN status s ON r.status=s.id " +
                        "JOIN users u ON r.client=u.id " +
                        "WHERE r.id=" + id);
            if (rs.next()) {
                request = new RequestExtended(
                        rs.getInt("id"),
                        rs.getInt("client"),
                        rs.getDate("request_date"),
                        rs.getString("description"),
                        rs.getInt("status"),
                        rs.getString("statusName"),
                        rs.getString("clientName")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {}

        return request;
    }

    public boolean create(Request request) {
        String sql = "INSERT INTO request(client, description) values("
                + request.getClient() + ", \'"
                + request.getDescription() + "\')";

        return dbs.insert(sql);
    }

    public boolean edit(int id, Request request) {
        try {
            ResultSet rs = dbs.select("SELECT * FROM request WHERE id=" + id);
            if (rs.next()) {
                rs.updateInt("client", request.getClient());
                rs.updateString("description", request.getDescription());
                rs.updateDate("request_date", request.getDate());
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
