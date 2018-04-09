package gui.utils.dbUtils;

import gui.model.Course;
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

public class CourseDB {


    public static boolean courseNameExist(String input) {
        boolean value = false;

        try (
                Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
        ) {
            PreparedStatement statement = con.prepareStatement("SELECT ID_COURSE from Courses where name = ? ; ");
            statement.setString(1, input);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            if (resultSet.getInt("ID_COURSE") >= 1) {
                value = true;
            }
            //return true;
        } catch (SQLException e) {
            try {
                initLogger(CourseDB.class.toString(), e.getMessage());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return value;
    }

    public static void deleteCourseDB(int courseID) {
        try (
                Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
        ) {
            removeFromRelation(false, courseID);
            PreparedStatement statement = con.prepareStatement("DELETE FROM Courses where ID_COURSE = ? ; ");
            statement.setInt(1, courseID);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("failed to delete course");
            try {
                initLogger(CourseDB.class.toString(), e.getMessage());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void newCourseDB(String name, String description, String startDate, String credits) {
        try (
                Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
        ) {
            PreparedStatement statement = con.prepareStatement("INSERT INTO Courses (NAME ,DESCRIPTION ,StartDate,Credits) " +
                    "VALUES (?,?,?,?); ");
            int creditsINT;
            if (credits.isEmpty()) {
                creditsINT = 0;
            } else {
                creditsINT = Integer.parseInt(credits);
            }

            statement.setString(1, name);
            statement.setString(2, description);
            statement.setDate(3, convertToMysqlDate(SIMPLE_DATE_FORMAT.parse(startDate)));
            statement.setInt(4, creditsINT);

            statement.execute();

        } catch (Exception e) {
            System.out.println("failed to create course");
            try {
                initLogger(CourseDB.class.toString(), e.getMessage());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static List getCourses() {
        List<Course> list = FXCollections.observableArrayList();
        try (
                Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
        ) {
            PreparedStatement statement = con.prepareStatement("SELECT * from Courses; ");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Course course = new Course();
                course.setName(resultSet.getString("NAME"));
                course.setID(resultSet.getString("ID_COURSE"));
                course.setCredits(resultSet.getString("CREDITS"));
                course.setDescription(resultSet.getString("DESCRIPTION"));
                course.setStartDate(SIMPLE_DATE_FORMAT.format(resultSet.getDate("STARTDATE")));
                list.add(course);
            }
        } catch (Exception e) {
            System.out.println("failed to update course");
            try {
                initLogger(CourseDB.class.toString(), e.getMessage());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return list;
    }

    public static int getCourseID(String name) {
        try (
                Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
        ) {
            PreparedStatement statement = con.prepareStatement("SELECT ID_COURSE from Courses where NAME = ?; ");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt("ID_COURSE");

        } catch (Exception e) {
            System.out.println("course doesn't exist");
            try {
                initLogger(CourseDB.class.toString(), e.getMessage());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return 0;
        }
    }

    public static void updateCourseValues(Course course, Integer id) {
        try (
                Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
        ) {
            PreparedStatement statement = con.prepareStatement("UPDATE Courses SET NAME = ?," +
                    "DESCRIPTION = ?, STARTDATE = ?, CREDITS = ? WHERE ID_COURSE = ?; ");
            statement.setString(1, course.getName());
            if (course.getDescription().isEmpty()) {
                statement.setString(2, "");
            } else {
                statement.setString(2, course.getDescription());
            }
            try {
                statement.setDate(3, convertToMysqlDate(SIMPLE_DATE_FORMAT.parse(course.getStartDate())));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            statement.setInt(4, Integer.parseInt(course.getCredits()));
            statement.setInt(5, id);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("failed to update course");
            try {
                initLogger(CourseDB.class.toString(), e.getMessage());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static Course getCourse(int courseID) {
        Course course = new Course();
        try (
                Connection con = DriverManager.getConnection(URLOFDB, LOGIN, LOGIN)
        ) {
            PreparedStatement statement = con.prepareStatement("SELECT * from Courses where ID_COURSE = ?; ");
            statement.setInt(1, courseID);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            course.setName(resultSet.getString("NAME"));
            course.setID(resultSet.getString("ID_COURSE"));
            course.setCredits(resultSet.getString("CREDITS"));
            course.setDescription(resultSet.getString("DESCRIPTION"));
            course.setStartDate(SIMPLE_DATE_FORMAT.format(resultSet.getDate("STARTDATE")));

        } catch (Exception e) {
            System.out.println("failed to select from course");
            try {
                initLogger(CourseDB.class.toString(), e.getMessage());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return course;
    }


}
