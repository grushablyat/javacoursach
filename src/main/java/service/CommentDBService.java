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
                    "SELECT * " +
                        "FROM comments " +
                        "WHERE service=" + service + " " +
                        "ORDER BY id"
            );
            while (rs.next()) {
                comments.add(new Comment(
                        rs.getInt("id"),
                        rs.getInt("service"),
                        rs.getString("text"),
                        rs.getDate("comment_date")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e ) {}

        return comments;
    }

    public boolean create(Comment comment) {
        String sql = "INSERT INTO comments(service, text) values ("
                + comment.getService() + ", \'"
                + comment.getText() + "\')";

        return dbs.insert(sql);
    }
}
