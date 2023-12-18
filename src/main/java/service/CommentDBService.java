package service;

import model.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDBService {
    DBService dbs = new DBService();

    public List<Comment> getByService(int service) {
        List<Comment> comments = new ArrayList<>();

        try {
            ResultSet rs = dbs.select(
                    "SELECT id, text " +
                        "FROM comments " +
                        "WHERE service=" + service
            );
            while (rs.next()) {
                comments.add(new Comment(
                        rs.getInt("id"),
                        rs.getString("text")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e ) {}

        return comments;
    }
}
