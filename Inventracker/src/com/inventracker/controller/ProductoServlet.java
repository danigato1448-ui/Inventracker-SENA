package com.inventracker.controller;

import com.inventracker.dao.ProductoDAO;
import com.inventracker.model.producto;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProductoServlet")
public class ProductoServlet extends HttpServlet {
    private final ProductoDAO dao = new ProductoDAO();

    // REQUISITO: Método GET para listar
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setAttribute("lista", dao.listar());
        request.getRequestDispatcher("consultarProductos.jsp").forward(request, response);
    }

    // REQUISITO: Método POST para registrar
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        producto p = new producto();
        p.setNombreProducto(request.getParameter("txtNombre"));
        p.setPrecioVenta(Double.parseDouble(request.getParameter("txtPrecio")));
        p.setStockActual(Integer.parseInt(request.getParameter("txtStock")));
        
        if (dao.registrar(p)) {
            response.sendRedirect("ProductoServlet"); // Redirige al GET para ver la lista
        } else {
            response.getWriter().println("Error al guardar el producto.");
        }
    }
}

