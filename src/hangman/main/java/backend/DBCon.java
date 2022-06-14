package hangman.main.java.backend;

import hangman.main.java.config.Config;
import java.sql.*;

public class DBCon {

    public static void createConnection (){

        try{
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hangman", Config.getValue("DB_USER"), Config.getValue("DB_PASSWORD"));

            Statement myStmt = myConn.createStatement();

            ResultSet myRs = myStmt.executeQuery("SELECT * FROM test");

            while (myRs.next()){
                System.out.println(myRs.getString("name") + ", " + myRs.getInt("age"));
            }
        }
        catch (Exception exc){
            exc.printStackTrace();
        }
    }

}
