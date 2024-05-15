package exple1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GreetingServlet1")
public class GreetingServlet1 extends HttpServlet {
    
    // Méthode doGet pour gérer les requêtes GET
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// Afficher le formulaire HTML/JSP
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
    
    // Méthode doPost pour gérer les requêtes POST
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String votreNom = request.getParameter("nom");
        
        // Génération du montant gagné aléatoire
        Double gagne = Math.random() * 10;
        
        // Connexion à la base de données et insertion des données
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/Mydatabase";
            String user = "root";
            String pwd = "Lhouc1234";
            con = DriverManager.getConnection(url, user, pwd);
            
            // Préparation de la requête d'insertion
            PreparedStatement pst = con.prepareStatement("INSERT INTO ma_table (username, gagner) VALUES (?, ?)");
            pst.setString(1, votreNom);
            pst.setString(2, String.valueOf(gagne));
            
            // Exécution de la requête d'insertion
            pst.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermeture de la connexion
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        
     // Après la génération du montant gagné
        request.setAttribute("gagne", gagne);

        // Redirection vers la page de salutation avec le nom et le montant gagné
        request.getRequestDispatcher("greeting.jsp?nom=" + votreNom + "&gagne=" + gagne).forward(request, response);

    }
    
    // Méthode doPut pour gérer les requêtes PUT
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Vous pouvez implémenter le traitement PUT ici si nécessaire
    }
    
    // Méthode doDelete pour gérer les requêtes DELETE
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Vous pouvez implémenter le traitement DELETE ici si nécessaire
    }
    
    // Méthode doHead pour gérer les requêtes HEAD
    protected void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Vous pouvez implémenter le traitement HEAD ici si nécessaire
    }
    
    // Méthode doOptions pour gérer les requêtes OPTIONS
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Vous pouvez implémenter le traitement OPTIONS ici si nécessaire
    }
    
    // Méthode doTrace pour gérer les requêtes TRACE
    protected void doTrace(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Vous pouvez implémenter le traitement TRACE ici si nécessaire
    }
}

