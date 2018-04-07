package gui.utils.dbUtils;

import gui.model.User;
import gui.utils.Roles;
import javafx.collections.FXCollections;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static gui.utils.FormatedDate.FORMAT;
import static gui.utils.dbUtils.DBUtils.convertToMysqlDate;
import static gui.utils.dbUtils.RelationDB.removeFromRelation;
import static gui.utils.dbUtils.dbLoggin.LOGIN;
import static gui.utils.dbUtils.dbLoggin.URLOFDB;

public class UserDB {


    public static void newUserToDB(String name, String lastName, String password, String userName, Roles role) {

        try (
                Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
        ) {
            PreparedStatement statement = con.prepareStatement("INSERT INTO Users (Name,LastName,Password,Username,Role) " +
                    "VALUES (?,?,?,?,?); ");
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setString(3, password);
            statement.setString(4, userName);
            statement.setString(5, role.toString());
            statement.execute();
//            ResultSet generatedKeys = statement.getGeneratedKeys();
//            if (generatedKeys.next()) {
//                long id = generatedKeys.getLong(1);
//                System.out.println(id);
//            } else {
//                System.out.println("Error on retrieving ID");
//            }
        } catch (Exception e) {
            System.out.println("can't create user");
        }
    }

    public static void newUserToDBexpress(String name, String lastName, String password, String userName, Roles role,
                                          String email, java.util.Date dateOfBirth, String address) {

        try (
                Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
        ) {
            PreparedStatement statement = con.prepareStatement("INSERT INTO Users " +
                    "(Name,LastName,Password,Username,Role,Email,DateOfBirth,address) " +
                    "VALUES (?,?,?,?,?,?,?,?); ");
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setString(3, password);
            statement.setString(4, userName);
            statement.setString(5, role.toString());
            statement.setString(6, email);
            statement.setDate(7, convertToMysqlDate(dateOfBirth));
            statement.setString(8, address);

            statement.execute();

        } catch (Exception e) {
            System.out.println("can't create user");
        }
    }

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
            System.out.println("failed to delete user");
        }
    }

    public static boolean userExist(String input) {
        boolean value = false;
        try (
                Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
        ) {
            PreparedStatement statement = con.prepareStatement("Select ID FROM Users where ID = ? ;");
            statement.setInt(1, Integer.parseInt(input));
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            if (resultSet.getInt("ID") > 0) {
                value = true;
            }
        } catch (SQLException e) {

        }
        return value;
    }

    public static void createUserDB(User user) {
        try (
                Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
        ) {
            PreparedStatement statement = con.prepareStatement("INSERT INTO Users (NAME, LASTNAME, PASSWORD, " +
                    "USERNAME, ROLE, EMAIL, DATEOFBIRTH, ADDRESS ) VALUES (?,?,?,?,?,?,?,?); ");
            if (user.getFirstName().isEmpty() || user.getFirstName()==null) {
                statement.setString(1, null);
            } else {
                statement.setString(1, user.getFirstName());
            }
            if (user.getLastName().isEmpty()|| user.getLastName()==null) {
                statement.setString(2, null);
            } else {
                statement.setString(2, user.getLastName());
            }
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getUsername());
            statement.setString(5, String.valueOf(user.getRole()));
            if (user.getEmail().isEmpty() || user.getEmail()==null) {
                statement.setString(6, null);
            } else {
                statement.setString(6, user.getEmail());
            }
            if (user.getDateOfBirth()!= null ){
                try {
                    statement.setDate(7, convertToMysqlDate(FORMAT.parse(user.getDateOfBirth())));

                } catch (ParseException e) {
                    statement.setDate(7, null);
                }
            } else {
                statement.setDate(7, null);
            }
            if (user.getAddress().isEmpty() || user.getAddress()==null ) {
                statement.setString(8, null);
            } else {
                statement.setString(8, user.getAddress());
            }
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
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
            if (user.getFirstName().isEmpty() || user.getFirstName()==null) {
                statement.setString(1, null);
            } else {
                statement.setString(1, user.getFirstName());
            }
            if (user.getLastName().isEmpty()|| user.getLastName()==null) {
                statement.setString(2, null);
            } else {
                statement.setString(2, user.getLastName());
            }
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getUsername());
            statement.setString(5, String.valueOf(user.getRole()));
            if (user.getEmail().isEmpty() || user.getEmail()==null) {
                statement.setString(6, null);
            } else {
                statement.setString(6, user.getEmail());
            }
            if(user.getDateOfBirth()!= null){
                try {
                    statement.setDate(7, convertToMysqlDate(FORMAT.parse(user.getDateOfBirth())));
                } catch (ParseException e) {
                    statement.setDate(7, null);
                }
            } else {
                statement.setDate(7, null);
            }
            if (user.getAddress().isEmpty() || user.getAddress()==null) {
                statement.setString(8, null);
            } else {
                statement.setString(8, user.getAddress());
            }
            statement.setInt(9, Integer.parseInt(user.getID()));
            statement.execute();
            System.out.println("ok");
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
            System.out.println("failed to update user");

        }

    }

    public static void editUserLastname(String input, Integer id) {
        try (
                Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
        ) {
            PreparedStatement statement = con.prepareStatement("UPDATE Users SET lastname = ? WHERE ID = ?; ");
            statement.setString(1, input);
            statement.setInt(2, id);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("failed to update user");

        }

    }

    public static void editUserPassword(String input, Integer id) {
        try (
                Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
        ) {
            PreparedStatement statement = con.prepareStatement("UPDATE Users SET password = ? WHERE ID = ?; ");
            statement.setString(1, input);
            statement.setInt(2, id);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("failed to update user");
        }

    }

    public static void editUserEmail(String input, Integer id) {
        try (
                Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
        ) {
            PreparedStatement statement = con.prepareStatement("UPDATE Users SET email = ? WHERE ID = ?; ");
            statement.setString(1, input);
            statement.setInt(2, id);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("failed to update user");
        }

    }

    public static void editUserAddress(String input, Integer id) {
        try (
                Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
        ) {
            PreparedStatement statement = con.prepareStatement("UPDATE Users SET address = ? WHERE ID = ?; ");
            statement.setString(1, input);
            statement.setInt(2, id);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("failed to update user");
        }

    }

    public static void editUserRole(Roles input, Integer id) {
        try (
                Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
        ) {
            PreparedStatement statement = con.prepareStatement("UPDATE Users SET role = ? WHERE ID = ?; ");
            statement.setString(1, input.toString());
            statement.setInt(2, id);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("failed to update user");
        }

    }

    public static void editUserUsername(String input, Integer id) {
        try (
                Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
        ) {
            PreparedStatement statement = con.prepareStatement("UPDATE Users SET username = ? WHERE ID = ?; ");
            statement.setString(1, input);
            statement.setInt(2, id);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("failed to update user");
        }

    }

    public static void editUserDate(java.util.Date input, Integer id) {
        try (
                Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
        ) {
            PreparedStatement statement = con.prepareStatement("UPDATE Users SET dateofbirth = ? WHERE ID = ?; ");
            statement.setDate(1, convertToMysqlDate(input));
            statement.setInt(2, id);
            statement.execute();
        } catch (SQLException e) {
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
                    user.setDateOfBirth(FORMAT.format(resultSet.getDate("DATEOFBIRTH")));
                } catch (Exception e) {
                    user.setDateOfBirth(null);
                }
                list.add(user);
            }

        } catch (Exception e) {
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
                user.setDateOfBirth(FORMAT.format(resultSet.getDate("DATEOFBIRTH")));
            } catch (Exception e) {
                user.setDateOfBirth(null);
            }

        } catch (Exception e) {
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
            return 0;
        }
    }

    public static List getUsersIDs() {
        List<String> list = new ArrayList<>();
        try (
                Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
        ) {
            PreparedStatement statement = con.prepareStatement("SELECT ID, NAME, LASTNAME from Users; ");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(String.valueOf(resultSet.getInt("ID")));
            }

        } catch (Exception e) {
            System.out.println("failed to get users(getUsersIDs)");
        }
        return list;
    }


}
