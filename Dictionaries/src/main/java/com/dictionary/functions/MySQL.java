package com.dictionary.functions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MySQL {

    public void getConnection(List<Word> wordList) {
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String baseUrl = "jdbc:mysql://localhost/dictionary1";
            String userName = "root";
            String passWord = "";
            connection = DriverManager.getConnection(baseUrl, userName, passWord);

            if (connection != null) {
                System.out.println("Successfully!");
            }

            String query = "select * from tbl_edict";
            assert connection != null;
            var statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            String targetTemp;
            String explainTemp;

            while (resultSet.next()) {
                targetTemp = resultSet.getString(2);
                explainTemp = resultSet.getString(3);
                Word _word = new Word(targetTemp, explainTemp);
                wordList.add(_word);
            }
            connection.close();
        }
        catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
