package gui.utils.dbUtils;

import gui.model.User;
import gui.utils.Roles;
import javafx.collections.FXCollections;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.List;

import static gui.utils.FormatedDate.SIMPLE_DATE_FORMAT;
import static gui.utils.InitLogger.initLogger;
import static gui.utils.dbUtils.DBUtils.convertToMysqlDate;
import static gui.utils.dbUtils.RelationDB.removeFromRelation;
import static gui.utils.dbUtils.dbLoggin.LOGIN;
import static gui.utils.dbUtils.dbLoggin.URLOFDB;

public class UserDB {

    public static String checkUsername(String input) {
        try (
                Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
        ) {
            PreparedStatement statement = con.prepareStatement("SELECT password from Users where username = ? ; ");
            statement.setString(1, input);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getString("password");
        } catch (SQLException e) {
            try {
                initLogger(UserDB.class.toString(), e.getMessage());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return null;
        }
    }

    public static Roles getRole(String username) {

        try (
                Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
        ) {
            PreparedStatement statement = con.prepareStatement("SELECT role from Users where username = ? ; ");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return Roles.valueOf(resultSet.getString("role"));
        } catch (SQLException e) {
            try {
                initLogger(UserDB.class.toString(), e.getMessage());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return null;
        }
    }

    public static void deleteUserDB(int userID) {
        try (
                Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
        ) {
            removeFromRelation(true, userID);
            PreparedStatement statement = con.prepareStatement("DELETE FROM Users where ID = ? ; ");
            statement.setInt(1, userID);
            statement.execute();

        } catch (SQLException e) {
            try {
                initLogger(UserDB.class.toString(), e.getMessage());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.out.println("failed to delete user");
        }
    }

    public static void createUserDB(User user) {
        try (
                Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
        ) {
            PreparedStatement statement = con.prepareStatement("INSERT INTO Users (NAME, LASTNAME, PASSWORD, " +
                    "USERNAME, ROLE, EMAIL, DATEOFBIRTH, ADDRESS ) VALUES (?,?,?,?,?,?,?,?); ");
            if (user.getFirstName().isEmpty() || user.getFirstName() == null) {
                statement.setString(1, null);
            } else {
                statement.setString(1, user.getFirstName());
            }
            if (user.getLastName().isEmpty() || user.getLastName() == null) {
                statement.setString(2, null);
            } else {
                statement.setString(2, user.getLastName());
            }
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getUsername());
            statement.setString(5, String.valueOf(user.getRole()));
            if (user.getEmail().isEmpty() || user.getEmail() == null) {
                statement.setString(6, null);
            } else {
                statement.setString(6, user.getEmail());
            }
            if (user.getDateOfBirth() != null) {
                try {
                    statement.setDate(7, convertToMysqlDate(SIMPLE_DATE_FORMAT.parse(user.getDateOfBirth())));

                } catch (ParseException e) {
                    try {
                        initLogger(UserDB.class.toString(), e.getMessage());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    statement.setDate(7, null);
                }
            } else {
                statement.setDate(7, null);
            }
            if (user.getAddress().isEmpty() || user.getAddress() == null) {
                statement.setString(8, null);
            } else {
                statement.setString(8, user.getAddress());
            }
            statement.execute();
        } catch (SQLException e) {
            try {
                initLogger(UserDB.class.toString(), e.getMessage());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.out.println("failed to update user");

        }
    }

    public static void updateUserDB(User user) {
        try (
                Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
        ) {
            PreparedStatement statement = con.prepareStatement("UPDATE Users SET name = ?,  LASTNAME = ?," +
                    " PASSWORD = ?,  USERNAME = ?,  ROLE = ?,  EMAIL = ?, DATEOFBIRTH = ?," +
                    " ADDRESS = ? WHERE ID = ?; ");
            if (user.getFirstName().isEmpty() || user.getFirstName() == null) {
                statement.setString(1, null);
            } else {
                statement.setString(1, user.getFirstName());
            }
            if (user.getLastName().isEmpty() || user.getLastName() == null) {
                statement.setString(2, null);
            } else {
                statement.setString(2, user.getLastName());
            }
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getUsername());
            statement.setString(5, String.valueOf(user.getRole()));
            if (user.getEmail().isEmpty() || user.getEmail() == null) {
                statement.setString(6, null);
            } else {
                statement.setString(6, user.getEmail());
            }
            if (user.getDateOfBirth() != null) {
                try {
                    statement.setDate(7, convertToMysqlDate(SIMPLE_DATE_FORMAT.parse(user.getDateOfBirth())));
                } catch (ParseException e) {
                    try {
                        initLogger(UserDB.class.toString(), e.getMessage());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    statement.setDate(7, null);
                }
            } else {
                statement.setDate(7, null);
            }
            if (user.getAddress().isEmpty() || user.getAddress() == null) {
                statement.setString(8, null);
            } else {
                statement.setString(8, user.getAddress());
            }
            statement.setInt(9, Integer.parseInt(user.getID()));
            statement.execute();
            System.out.println("ok");
        } catch (SQLException e) {
            try {
                initLogger(UserDB.class.toString(), e.getMessage());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.out.println("failed to update user");

        }

    }

    public static void updateUserProfileDB(User user) {
        try (
                Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
        ) {
            PreparedStatement statement = con.prepareStatement("UPDATE Users SET name = ?, " +
                    " LASTNAME = ?,  PASSWORD = ?,  EMAIL = ?, " +
                    " ADDRESS = ? WHERE ID = ?; ");
            if (user.getFirstName().isEmpty()) {
                statement.setString(1, null);
            } else {
                statement.setString(1, user.getFirstName());
            }
            if (user.getLastName().isEmpty()) {
                statement.setString(2, null);
            } else {
                statement.setString(2, user.getLastName());
            }
            statement.setString(3, user.getPassword());
            if (user.getEmail().isEmpty()) {
                statement.setString(4, null);
            } else {
                statement.setString(4, user.getEmail());
            }
            if (user.getAddress().isEmpty()) {
                statement.setString(5, null);
            } else {
                statement.setString(5, user.getAddress());
            }
            statement.setInt(6, Integer.parseInt(user.getID()));
            statement.execute();
        } catch (SQLException e) {
            try {
                initLogger(UserDB.class.toString(), e.getMessage());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.out.println("failed to update user");

        }

    }

    public static List getUsers() {
        List<User> list = FXCollections.observableArrayList();
        try (
                Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
        ) {
            PreparedStatement statement = con.prepareStatement("SELECT * from Users; ");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setID(String.valueOf(resultSet.getInt("ID")));
                user.setUsername(resultSet.getString("USERNAME"));
                user.setPassword(resultSet.getString("PASSWORD"));
                user.setRole(Roles.valueOf(resultSet.getString("ROLE")));
                user.setFirstName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setEmail(resultSet.getString("EMAIL"));
                user.setAddress(resultSet.getString("ADDRESS"));
                try {
                    user.setDateOfBirth(SIMPLE_DATE_FORMAT.format(resultSet.getDate("DATEOFBIRTH")));
                } catch (Exception e) {
                    user.setDateOfBirth(null);
                }
                list.add(user);
            }

        } catch (Exception e) {
            try {
                initLogger(UserDB.class.toString(), e.getMessage());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.out.println("failed to get users (getUsers)");
        }
        return list;
    }

    public static User getUser(String username) {
        User user = new User();
        try (
                Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
        ) {
            PreparedStatement statement = con.prepareStatement("SELECT * from Users where USERNAME= ?; ");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            user.setID(String.valueOf(resultSet.getInt("ID")));
            user.setUsername(resultSet.getString("USERNAME"));
            user.setPassword(resultSet.getString("PASSWORD"));
            user.setRole(Roles.valueOf(resultSet.getString("ROLE")));
            user.setFirstName(resultSet.getString("NAME"));
            user.setLastName(resultSet.getString("LASTNAME"));
            user.setEmail(resultSet.getString("EMAIL"));
            user.setAddress(resultSet.getString("ADDRESS"));
            try {
                user.setDateOfBirth(SIMPLE_DATE_FORMAT.format(resultSet.getDate("DATEOFBIRTH")));
            } catch (Exception e) {
                try {
                    initLogger(UserDB.class.toString(), e.getMessage());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                user.setDateOfBirth(null);
            }

        } catch (Exception e) {
            try {
                initLogger(UserDB.class.toString(), e.getMessage());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.out.println("failed to get users (getUsers)");
        }
        return user;
    }

    public static int getUserID(String username) {
        try (
                Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
        ) {
            PreparedStatement statement = con.prepareStatement("SELECT ID from Users where username = ? ; ");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt("ID");
        } catch (SQLException e) {
            try {
                initLogger(UserDB.class.toString(), e.getMessage());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return 0;
        }
    }

    public static int getUserCredits(int ID) {

        try (
                Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
        ) {
            PreparedStatement statement = con.prepareStatement("SELECT sum(CREDITS) FROM COURSES " +
                    "join COURSERELATION on COURSERELATION .ID_COURSE = COURSES .ID_COURSE where ID_USER  = ?; ");
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt("SUM(CREDITS)");
        } catch (SQLException e) {
            try {
                initLogger(UserDB.class.toString(), e.getMessage());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return 0;
        }
    }

}
