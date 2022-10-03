package jdbc;

import java.sql.*;

public class ExecuteQuery01 {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed2", "postgres", "selimql");
        Statement st = con.createStatement();

        //1. Örnek: companies tablosundan en yüksek ikinci number_of_employees değeri olan company ve number_of_employees değerlerini çağırın.
        String sql1 = "SELECT company, number_of_employees\n" +
                "FROM companies\n" +
                "ORDER BY number_of_employees DESC\n" +
                "OFFSET 1 ROW\n" +
                "FETCH NEXT 1 ROW ONLY";

        st.executeQuery(sql1);
        ResultSet result1 = st.executeQuery(sql1);

        while (result1.next()) {

           System.out.println(result1.getString("company")+ "---" +result1.getInt("number_of_employees"));
        }

        String sql2 = "select company, number_of_employees\n" +
                        "from companies\n" +
                        "where number_of_employees = (SELECT MAX(number_of_employees)\n" +
                                                    "from companies\n" +
                                                    "where number_of_employees < (SELECT MAX(number_of_employees)\n" +
                                                    "from companies))";

        st.executeQuery(sql2);
        ResultSet result2 = st.executeQuery(sql2);
        while(result2.next()) {
            System.out.println(result2.getString("company")+"--"+result2.getInt("number_of_employees"));
        }

        con.close();
        st.close();
        result1.close();
        result2.close();

        
    }
}
