package evidentierecarti;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBOper {


    public static void demoCreate(String numeleCartii, String numeleAutorului, Date dataInceperii, Date dataTerminarii) throws ClassNotFoundException, SQLException {

        // 1. load driver, no longer needed in new versions of JDBC
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/denise8";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        PreparedStatement pSt = conn.prepareStatement("INSERT INTO CARTI (NUMELECARTII, NUMELEAUTORULUI, DATAINCEPERII, DATATERMINARII) VALUES (?,?,?,?)");
        pSt.setString(1, numeleCartii);
        pSt.setString(2, numeleAutorului);
        pSt.setDate(3, dataInceperii);
        pSt.setDate(4,dataTerminarii);

        // 5. execute a prepared statement
        int rowsInserted = pSt.executeUpdate();

        // 6. close the objects
        pSt.close();
        conn.close();
    }

//  public static int login(String username, String password) throws ClassNotFoundException, SQLException {
//int gasit = -1;
//
//Class.forName("org.postgresql.Driver");
//
//final String URL = "jdbc:postgresql://54.93.65.5:5432/denise8";
//final String USERNAME = "fasttrackit_dev";
//final String PASSWORD = "fasttrackit_dev";
//
//Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//
//Statement st = conn.createStatement();
//
//ResultSet rs = st.executeQuery("SELECT idabonat FROM usercarti WHERE username="+username+ "AND password="+password);
//while (rs.next()) {
//gasit = rs.getInt("id");
//}
//int ok = 0;
//if (rs.next()) { // cat timp sunt inregistrari
// System.out.print(rs.getLong("id"));
// System.out.print("---");
//String user_name = rs.getString("username").trim();
// System.out.print("---");
//String password = rs.getString("password").trim();
//
//if(username.equals(user_name) && password.equals(password)) {
// gasit=1;
// }
//
// if(gasit==true) {
// System.out.println("ai acces"); // sa porneasca site-ul
// break;
//} else
//{
// ok=1;
// }
//}
//
// if(ok==1) {
// System.out.println("nu ai acces");
//}
//
//
//rs.close();
//st.close();
//conn.close();
//return gasit;
//



    public static List<Carti> demoRead() throws ClassNotFoundException, SQLException {


        // 1. load driver, no longer needed in new versions of JDBC
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/denise8";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";


        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        PreparedStatement pst= conn.prepareStatement("SELECT * from carti " );

        ResultSet rs = pst.executeQuery();

        List ListaDeCarti = new ArrayList<Carti>();
        while (rs.next()){

            String numeleCartii = rs.getString("numelecartii").trim();
            String numeleAutorului = rs.getString("numeleautorului").trim();
            Date dataInceperii = rs.getDate("datainceperii");
            Date dataTerminarii = rs.getDate("dataterminarii");
            Carti c = new Carti();
            c.setNumeleCartii(numeleCartii);
            c.setAutorulCartii(numeleAutorului);
            c.setDataInceperii(dataInceperii);
            c.setDataTerminarii(dataTerminarii);

            ListaDeCarti.add(c);
            }


        rs.close();
        pst.close();
        conn.close();
        return ListaDeCarti;
    }
}
