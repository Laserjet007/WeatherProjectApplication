package lesson_8;

//примеры что можно делать из джавы в таблице
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteExamples {
    public static void main(String[] args) {
        Connection connection = null;                                                        //подключаемся к sqllite
        try {
            Class.forName("org.sqlite.JDBC");                                       // к любой базе данных подключение идет таким образом:
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");  // конекшен создаем с помощью класса DriverManager
            Statement statement = connection.createStatement();                  //что бы прогонять строки из базы данных по отдельности нужно создать еще один обьект который будет привязан к основному запросу Statement

            statement.executeUpdate("update faculties set name = 'Тест1' where id = 1");  //заменить строчку IT  на  Test1

            ResultSet resultSet = statement.executeQuery("select * from students"); // показать строки из студентс

            while (resultSet.next()) {                                            //resultSet.next() - нака есть следующий элемент. в цикле пройтись по множеству и показать обьекты
                System.out.print(resultSet.getInt("id"));    // id - взять строчку
                System.out.print(" ");
                System.out.print(resultSet.getString("name"));
                System.out.println();
            }
//выполнить запрос с авто коммитом и засечь время
            //Long time = System.currentTimeMillis();
            //for (int i = 0; i < 10000; i++) {
            //    statement.executeUpdate(String.format("insert into students (name, score, fac_id) values ('%s', %d, %d)",
            //            "student" + i, i, i));
            //}
            //System.out.println("Время выполнения с автокоммитом: " + (System.currentTimeMillis() - time));
//выполнить запрос без авто коммита и засечь время
            //time = System.currentTimeMillis();
            //connection.setAutoCommit(false);              //выключить автокомит
            //for (int i = 10000; i < 20000; i++) {
            //    statement.executeUpdate(String.format("insert into students (name, score, fac_id) values ('%s', %d, %d)",
            //            "student" + i, i, i));
            //}
            //connection.commit();                            //закомитить в ручную
            //System.out.println("Время выполнения без автокоммита: " + (System.currentTimeMillis() - time));

            PreparedStatement preparedStatement = connection.prepareStatement(      // PreparedStatement - заготовка при выполнении программы в нее можно передавать значения
                    "insert into students (name, score, faculty_id) values (?, ?, ?)");
            connection.setAutoCommit(false);
            preparedStatement.setString(1, "Олег");                 //заполняем переменные ( вопросительные знаки)
            preparedStatement.setInt(2, 10);
            preparedStatement.setInt(3, 12);
            preparedStatement.addBatch();
            preparedStatement.setString(1, "Anton");
            preparedStatement.setInt(2, 10);
            preparedStatement.setInt(3, 12);
            preparedStatement.addBatch();
            preparedStatement.setString(1, "Anton");
            preparedStatement.setInt(2, 10);
            preparedStatement.setInt(3, 12);
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
