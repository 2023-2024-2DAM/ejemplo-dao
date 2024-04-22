package org.ies.tierno.library;

import org.ies.tierno.library.db.dao.BookDao;
import org.ies.tierno.library.db.ConnectionManager;
import org.ies.tierno.library.model.Book;

public class Main {

    public static void main(String[] args) {
        try {
            ConnectionManager connectionManager = new ConnectionManager(
                    "jdbc:mysql://localhost:3306/library",
                    "root",
                    "VuestroPassword"
            );

            BookDao bookDao = new BookDao(connectionManager);

            bookDao.save(new Book("23123", "Programación  en Java", "Bob Esponjar", 2024));

            var books = bookDao.list();

            for(var book: books) {
                System.out.println(book);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }




    }
}