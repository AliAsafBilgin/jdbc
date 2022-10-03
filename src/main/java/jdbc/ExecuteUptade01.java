package jdbc;

import java.sql.*;

public class ExecuteUptade01 {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed2", "postgres", "selimql");
        Statement st = con.createStatement();

        //1. Örnek: number_of_employees değeri ortalama çalışan sayısından az olan number_of_employees değerlerini 16000 olarak UPDATE edin.

        String sql1 = "UPDATE companies\n" +
                "SET number_of_employees =16000\n" +
                "WHERE number_of_employees < (SELECT AVG(number_of_employees)\n" +
                "\t\t\t\t\t\t\tFrom companies)";

        int updateSatirSayisi = st.executeUpdate(sql1);
        System.out.println("updateSatirSayisi = " + updateSatirSayisi);

        String sql2 = "SELECT * FROM companies";

        ResultSet result1 = st.executeQuery(sql2);

        while (result1.next()) {

            System.out.println(result1.getInt(1)+"--"+result1.getString(2)+"--"+result1.getInt(3));
        }

        con.close();
        st.close();
    }
}
