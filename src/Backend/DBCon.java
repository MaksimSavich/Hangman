package Backend;

import Config.Accessconf;
import java.sql.*;

public class DBCon {

    public static void createConnection (){

        try{
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hangman", Accessconf.getValue("DB_USER"), Accessconf.getValue("DB_PASSWORD"));

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
