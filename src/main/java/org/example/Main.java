package org.example;

import org.example.models.Product;
import org.example.models.ProductDAO;
import java.util.Scanner;
import java.util.ArrayList;
import java.sql.SQLException;


public class Main {
    private static String URL = "jdbc:postgresql://ep-long-pine-a4ah2n1q.us-east-1.aws.neon.tech/inventario";
    static String USER = "shopping_cart_owner";
    static String PASSWORD = "DPyCA59TOQhu";
    static ProductDAO dao = new ProductDAO();
    private static ArrayList<Product> productos = new ArrayList<>();

    public static void main(String[] args) throws SQLException {
        menu();
    }

    public static void menu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("1.Agregar Producto");
            System.out.println("2.Eliminar Producto");
            System.out.println("3.Actualizar Producto");
            System.out.println("4.Consultar la lista de productos completa");
            System.out.println("5.Consultar un Producto especifico");
            System.out.println("6.nada");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("1.Agregar Producto");
                    crearProducto(dao);
                    break;
                case 2:
                    System.out.println("2.Eliminar Producto");
                    EliminarProducto(dao);
                    break;
                case 3:
                    System.out.println("3.Actualizar datos");
                    editarProducto(dao);
                    break;
                case 4:
                    System.out.println("Lista de productos completa:");
                    getProductos(dao);
                    break;
                case 5:
                    System.out.println("5.Consultar un producto especifico");
                    getProducto(dao);
                    break;
                case 6:
                    System.out.println("6.Saliendo del sistema de inventario ;)");
                    break;
                default:
                    System.out.println("ingrese una opcion valida");
            }
        } while (opcion != 6);
    }

    public static void crearProducto(ProductDAO dao) throws SQLException {
        Scanner scaCreate = new Scanner(System.in);
        System.out.println("Ingrese el nombre del producto: ");
        String nombre = scaCreate.nextLine();
        scaCreate.nextLine();
        System.out.println("Ingrese la descripción del producto: ");
        String descripcion = scaCreate.nextLine();
        scaCreate.nextLine();
        System.out.println("Ingrese la cantidad del producto: ");
        int cantidad = scaCreate.nextInt();

        System.out.println("Ingrese el precio del producto: ");
        double precio = scaCreate.nextDouble();
        Product newProduct = new Product(0, nombre, descripcion, cantidad, precio);
        try{
            dao.addProduct(newProduct);
            System.out.println("Producto agregado exitosamente.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void editarProducto(ProductDAO dao) throws SQLException{
        Scanner scaEdit = new Scanner(System.in);
        System.out.print("Ingrese el ID del producto: ");
        int id = scaEdit.nextInt();

        Product productById = dao.getProductById(id);

        System.out.println("product id:" + productById.getId());
        System.out.println("product name:" + productById.getNombre());
        System.out.println("product description:" + productById.getDescripcion());
        System.out.println("product precio:" + productById.getPrecio());
        System.out.println("product cantidad:" + productById.getCantidad());

        System.out.println("Ingrese el nombre del producto:");
        String nombre = scaEdit.nextLine();
        scaEdit.nextLine();
        System.out.println("Ingrese la cantidad del producto:");
        int cantidad = scaEdit.nextInt();
        scaEdit.nextLine();
        System.out.println("Ingrese la descripción del producto:");
        String descripcion = scaEdit.nextLine();


        System.out.println("Ingrese el precio del producto:");
        double precio = scaEdit.nextDouble();
        try{
            Product newProduct = new Product(id, nombre, descripcion, cantidad, precio);
            dao.updateProduct(newProduct);
            System.out.println("Producto" + nombre + "Actualizado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Producto con ID " + id + " no encontrado.");
            throw new RuntimeException(e);
        }
    }

    public static void getProductos(ProductDAO dao) throws SQLException{

        try {
            ArrayList<Product> productos = new ArrayList<>();
            for (Product item : dao.getAllProducts()) {
                System.out.println("el nombre del producto: " + item.getNombre());
                System.out.println("la descripcion del producto: " + item.getDescripcion());
                System.out.println("el precio del producto: " + item.getPrecio());
                System.out.println("la cantidad del producto: " + item.getCantidad());
                System.out.println("---------------------");
            }

        }
        catch (Exception e) {

            e.printStackTrace();
        }

    }

    public static void EliminarProducto(ProductDAO dao) throws SQLException{
        Scanner scaElim = new Scanner(System.in);
        System.out.print("Ingrese el ID del producto: ");
        int id = scaElim.nextInt();
        try{
            dao.deleteProduct(id);
            System.out.println("Producto eliminado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Producto con ID " + id + " no encontrado.");
            throw new RuntimeException(e);
        }
    }

    public  static void getProducto(ProductDAO dao) throws SQLException{
        Scanner scaGet = new Scanner(System.in);
        System.out.print("Ingrese el ID del producto: ");
        int id = scaGet.nextInt();

        Product productById = dao.getProductById(id);

        System.out.println("product id:" + productById.getId());
        System.out.println("product name:" + productById.getNombre());
        System.out.println("product description:" + productById.getDescripcion());
        System.out.println("product precio:" + productById.getPrecio());
        System.out.println("product cantidad:" + productById.getCantidad());
    }
}


