package gui.utils.dbUtils;


import gui.model.Course;
import gui.model.User;
import gui.utils.Roles;
import javafx.collections.FXCollections;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static gui.utils.dbUtils.DBUtils.convertToUtilDate;
import static gui.utils.dbUtils.dbLoggin.LOGIN;
import static gui.utils.dbUtils.dbLoggin.URLOFDB;


public class RelationDB {

    public static void removeFromRelation(Boolean removeUser, int id) {
        if (removeUser) {
            try (
                    Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
            ) {
                PreparedStatement statement = con.prepareStatement("DELETE FROM COURSERELATION where ID_USER = ? ; ");
                statement.setInt(1, id);
                statement.execute();
                System.out.println("User deleted");
            } catch (SQLException e) {
                System.out.println("failed to delete user from relation");
            }
        } else {
            try (
                    Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
            ) {
                PreparedStatement statement = con.prepareStatement("DELETE FROM COURSERELATION where ID_COURSE = ? ; ");
                statement.setInt(1, id);
                statement.execute();
                System.out.println("Course deleted");
            } catch (SQLException e) {
                System.out.println("failed to delete course from relation");
            }
        }

    }

    public static void addToCourse(int user_id, int course_id) {
        try (
                Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
        ) {
            PreparedStatement statement = con.prepareStatement("INSERT INTO COURSERELATION  (ID_USER, ID_COURSE) " +
                    "VALUES (?,?); ");
            statement.setInt(1, user_id);
            statement.setInt(2, course_id);
            statement.execute();
            System.out.println("User added to course");


        } catch (Exception e) {
            System.out.println("failed to add user to course");
        }
    }

    public static void removeFromCourseDB(int user_id, int course_id) {
        try (
                Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
        ) {
            PreparedStatement statement = con.prepareStatement("DELETE FROM COURSERELATION where ID_USER = ? AND ID_COURSE = ? ; ");
            statement.setInt(1, user_id);
            statement.setInt(2, course_id);
            statement.execute();
            System.out.println("User deleted from course");
        } catch (SQLException e) {
            System.out.println("failed to delete user from course");
        }
    }

    public static boolean isInCourse(int user_id, int user_course) {
        boolean value = false;
        try (
                Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
        ) {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM COURSERELATION " +
                    "WHERE ID_USER  = ? AND ID_COURSE = ? ; ");
            statement.setInt(1, user_id);
            statement.setInt(2, user_course);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            if (resultSet.getInt("ID_COURSE") > 0) {
                value = true;
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return value;
    }

    public static HashMap getUserCourses(int user_id) {
        HashMap<Integer, Course> courseHashMap = new HashMap<>();
        try (
                Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
        ) {
            PreparedStatement statement = con.prepareStatement("SELECT COURSES.ID_COURSE, NAME, DESCRIPTION, STARTDATE, CREDITS" +
                    " From COURSERELATION JOIN COURSES ON COURSERELATION.ID_COURSE = COURSES.ID_COURSE WHERE ID_USER = ?");
            statement.setInt(1, user_id);
            ResultSet resultSet = statement.executeQuery();
            int counter = 0;
            while (resultSet.next()) {
                Course course = new Course();
                course.setID(String.valueOf(resultSet.getInt("ID_COURSE")));
                course.setName(resultSet.getString("NAME"));
                course.setDescription(resultSet.getString("DESCRIPTION"));
//                course.setStartDate(convertToUtilDate(resultSet.getDate("STARTDATE")));
                course.setCredits(String.valueOf(resultSet.getInt("CREDITS")));
                courseHashMap.put(counter++, course);
            }

        } catch (Exception e) {
            System.out.println("failed to get courses");
        }
        return courseHashMap;
    }

    public static List getUsersInCourse(int courseID) {
        List<User> list = FXCollections.observableArrayList();
        try (
                Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
        ) {
            PreparedStatement statement = con.prepareStatement("SELECT ID, NAME, LASTNAME, USERNAME, ROLE from COURSERELATION " +
                    "JOIN USERS ON  ID_USER = ID where ID_COURSE = ?");
            statement.setInt(1, courseID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setID(String.valueOf(resultSet.getInt("ID")));
                user.setFirstName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setUsername(resultSet.getString("USERNAME"));
                user.setRole(Roles.valueOf(resultSet.getString("ROLE")));
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("failed to get users in course (getUsersInCourse)");
        }
        return list;
    }

    public static List getUsersNotInCourse(int courseID) {


        List<User> list = FXCollections.observableArrayList();
        try (
                Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
        ) {
            PreparedStatement statement = con.prepareStatement(
                    "SELECT u.ID, u.NAME, u.LASTNAME" +
                    "  FROM USERS u" +
                    " WHERE NOT EXISTS (SELECT 1" +
                    "                     FROM COURSERELATION s" +
                    "                    WHERE s.id_user = u.id" +
                    "                      AND s.id_course = ?)");
            statement.setInt(1, courseID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setID(String.valueOf(resultSet.getInt("ID")));
                user.setFirstName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                list.add(user);

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("failed to get users (getUsersNotInCourse)");
        }
        return list;
    }

    public static List getUsersInCourseIDs(int courseID) {
        List<String> list = new ArrayList<>();
        try (
                Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
        ) {
            PreparedStatement statement = con.prepareStatement("SELECT ID, NAME, LASTNAME, USERNAME, ROLE from COURSERELATION " +
                    "JOIN USERS ON  ID_USER = ID where ID_COURSE = ?");
            statement.setInt(1, courseID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(String.valueOf(resultSet.getInt("ID")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("failed to get users in course(getUsersInCourseIDs)");
        }
        return list;
    }




    public static boolean lecturerInCourse(int user_course) {
        boolean value = false;
        try (
                Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
        ) {
            PreparedStatement statement = con.prepareStatement("SELECT role FROM USERS " +
                    "join COURSERELATION on ID_USER  = ID where ID_COURSE = ? ; ");
            statement.setInt(1, user_course);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                if (Roles.valueOf(resultSet.getString("ROLE")) == Roles.LECTURER) {
                    value = true;
                }
        } catch (SQLException e) {
        }
        return value;
    }


}
