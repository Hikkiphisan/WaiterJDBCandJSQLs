package org.example.jdbcnhanvienquancaphe.service;

import org.example.jdbcnhanvienquancaphe.model.Waiter;

import java.sql.SQLException;
import java.util.List;

//Những phương thức này dùng để truy xuất thông tin, update,... thông tin lên CSDL

public interface IDaoWaiter {
    List<Waiter> findAllWaiter();


    void addWaiter(Waiter waiter) throws SQLException;

    Waiter findbyId(int id) throws SQLException;

    void updateWaiter(int id, Waiter waiter) throws SQLException;

    boolean removeWaiter(int id) throws SQLException;

}
