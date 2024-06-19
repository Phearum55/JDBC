import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        // Call method to display products
        displayProducts();
    }

    // Method to handle database operations
    public static void displayProducts() {
        String url = "jdbc:mysql://localhost:3306/db_product"; // Change to your database URL
        String user = "root"; // Change to your database username
        String password = ""; // Change to your database password, can be empty if no password is set

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            Connection connection = DriverManager.getConnection(url, user, password);

            // Create statement
            Statement statement = connection.createStatement();

            // Execute query
            String query = "SELECT * FROM Product";
            ResultSet resultSet = statement.executeQuery(query);

            // Process result set
            System.out.println("Product Table:");
            System.out.println("ID | Name   | Price per Unit | Active for Sell");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price_per_unit = resultSet.getDouble("price_per_unit");
                boolean active_for_sell = resultSet.getBoolean("active_for_sell");

                System.out.printf("%d | %s | %.2f | %b%n", id, name, price_per_unit, active_for_sell);
            }

            // Close connection
            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
