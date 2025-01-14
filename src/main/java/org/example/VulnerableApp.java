package com.example.exploration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class VulnerableApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();

        // Consulta insegura que possibilita SQL Injection
        String query = "SELECT * FROM users WHERE id = '" + userId + "'";

        try {
            // Carrega o driver antigo do MySQL
            Class.forName("com.mysql.jdbc.Driver");

            // Conexão fictícia (não precisa existir um BD de verdade para SAST)
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/test", "root", "password");

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println("User: " + rs.getString("username"));
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}