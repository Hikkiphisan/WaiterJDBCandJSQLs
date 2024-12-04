package org.example.jdbcnhanvienquancaphe.service;

import com.mysql.jdbc.Driver;
import org.example.jdbcnhanvienquancaphe.model.Waiter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoWaiter implements IDaoWaiter {
   private String jdbcURL = "jdbc:mysql://localhost:3306/demo?userSSL=false";
   private String jdbcUsername = "root";
   private String jdbcPassWord = "hikkiroku";


   private static final String ADD_WAITER_SQL = "INSERT INTO waiters (id,name,salary) VALUES (?,?,?);";
   private static final String FIND_WAITER_BY_ID = "SELECT id,name,salary from waiters where id =?";
   private static final String FIND_ALl_WAITER = "SELECT * FROM waiters";
   private static final String DELETE_WAITER_SQL = "DELETE FORM waiters WHERE id = ?";
   private static final String UPDATE_WAITER_SQL = "UPDATE waiters set name = ?, salary= ?";




   public DaoWaiter(){};


   protected Connection getConnection() {
       Connection connection = null;
       try {
           Class.forName("com.mysql.jdbc.Driver");
           connection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassWord);

       } catch (SQLException e) {
           throw new RuntimeException(e);
       } catch (ClassNotFoundException e) {
           throw new RuntimeException(e);
       }
      return connection;
   }


    @Override
    public List<Waiter> findAllWaiter() {
        List<Waiter> waiters = new ArrayList<>();

        try( Connection connection = getConnection() ;
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALl_WAITER)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String salary = rs.getString("salary");
                waiters.add(new Waiter(id,name,salary));

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } ;
        return waiters;


    }


//    /A.K.A insertWaiter
    @Override
    public void addWaiter(Waiter waiter) throws SQLException {
        System.out.println(ADD_WAITER_SQL);
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(ADD_WAITER_SQL)) {;
            preparedStatement.setInt(1,waiter.getId());
            preparedStatement.setString(2,waiter.getName());
            preparedStatement.setString(3,waiter.getSalary());
            System.out.println(preparedStatement);
        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }


   //A.k.a selectWaiter
    @Override
    public Waiter findbyId(int id) throws SQLException {
        Waiter waiter = null;
        try(Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_WAITER_BY_ID);
            preparedStatement.setInt(1,id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String salary = rs.getString("salary");
                waiter = new Waiter(id,name,salary);
            }

        } catch (SQLException e) {
            printSQLException(e);
        }
        return waiter;
    }

    @Override
    public void updateWaiter(int id, Waiter waiter) throws SQLException {;
       boolean rowUpdates;
       try(Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_WAITER_SQL); ) {
           statement.setString(1,waiter.getName());
           statement.setString(2,waiter.getSalary());
       } catch (SQLException e) {
           e.printStackTrace();
       }


    }

    @Override
    public boolean removeWaiter(int id) throws SQLException {
         boolean rowDeleted;

         try (Connection connection = getConnection(); PreparedStatement statement =connection.prepareStatement(DELETE_WAITER_SQL);) {
              statement.setInt(1,id);
              rowDeleted = statement.executeUpdate() > 0;
         }
         return rowDeleted;
    }
    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
