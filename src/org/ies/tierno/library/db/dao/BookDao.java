package org.ies.tierno.library.db.dao;

import org.ies.tierno.library.db.ConnectionManager;
import org.ies.tierno.library.model.Book;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

    private final ConnectionManager connectionManager;

    public BookDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public List<Book> list() {
        Connection con = null;
        try {
            con = connectionManager.getConnection();

            var ps = con.prepareStatement("SELECT * FROM book");

            var rs = ps.executeQuery();
            List<Book> books = new ArrayList<>();
            while (rs.next()) {
                Book book = new Book(
                        rs.getString("isbn"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("year")
                );
                books.add(book);
            }

            return books;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public boolean save(Book book) {
        Connection con = null;
        try {
            con = connectionManager.getConnection();

            var ps = con.prepareStatement("INSERT INTO book(isbn, title, author, year) VALUES(?,?,?,?)");

            ps.setString(1, book.getIsbn());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getAuthor());
            ps.setInt(4, book.getYear());
            int insertedRows = ps.executeUpdate();
            return insertedRows == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                }
            }
        }
    }
}
