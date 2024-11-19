package org.example.models;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    // Create (Insert)
    public void addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO product (nombre, descripcion, cantidad, precio) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getNombre());
            stmt.setString(2, product.getDescripcion());
            stmt.setInt(3, product.getCantidad());
            stmt.setDouble(4, product.getPrecio());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read (Select)
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio")
                );
                products.add(product);
            }
        }
        return products;
    }

    // Update
    public void updateProduct(Product product) throws SQLException {
        String sql = "UPDATE product SET nombre = ?, descripcion = ?, cantidad = ?, precio = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, product.getNombre());
            stmt.setString(2, product.getDescripcion());
            stmt.setInt(3, product.getCantidad());
            stmt.setDouble(4, product.getPrecio());
            stmt.setInt(5, product.getId());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete
    public void deleteProduct(int id) throws SQLException {
        String sql = "DELETE FROM product WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Product getProductById(int id) throws SQLException {
        String sql = "SELECT * FROM product WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getInt("cantidad"),
                            rs.getDouble("precio")
                    );
                }
            }
        }
        return null; // Return null if no product is found with the given ID
    }
}
