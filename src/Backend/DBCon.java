package Backend;
import java.sql.*;

public class DBCon {

    public static void createConnection (){
        try{
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hangman", "root", "BIlxMrSP57E$sMp8ijGfbu");

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
