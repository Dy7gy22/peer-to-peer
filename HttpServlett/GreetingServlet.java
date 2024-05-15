package exple1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * Servlet implementation class GreetingServlet
 */
@SuppressWarnings("serial")
public class GreetingServlet extends HttpServlet {

//Initialisez la liste noire dans le servlet
 private List<String> blacklist;

 @Override
 public void init() throws ServletException {
     blacklist = new ArrayList<>();
     blacklist.add("John");
     blacklist.add("Jane");
     // Ajoutez d'autres noms à la liste noire si nécessaire
 }
 public void doGet(HttpServletRequest request,HttpServletResponse
response) throws ServletException, IOException {
 
 response.setContentType("text/html");
 PrintWriter out = response.getWriter();
 String docType =
 "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
 "Transitional//EN\">\n";
 String title = null;
 String votreNom = null;
 String nomPrenom = "Anonymous";
 votreNom = request.getParameter("nom");
//Vérifiez si le nom introduit figure dans la liste noire
 if (blacklist.contains(votreNom)) {
     // Si le nom est dans la liste noire, affichez un message d'accès refusé
     out.println("Accès refusé. Vous êtes sur liste noire.");
     return; // Arrêtez l'exécution de la servlet si le nom est dans la liste noire
 }
 
 if (votreNom != null)
 nomPrenom = votreNom.toUpperCase();
 title = "<H1>Greetings " + nomPrenom + "!</H1>\n";
 out.println(docType +
 "<HTML>\n" +
 "<HEAD><TITLE>Greetings Servlet</TITLE></HEAD>\n" +
 "<BODY BGCOLOR=\"#FDF5E6\">\n" +
 title +
 "</BODY></HTML>");
 Double gagne=Math.random()* 10;
 out.println("Vous avez gagne: "+ gagne);
 out.println(" millions de dollars!");
 Connection con=null;
 try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url="jdbc:mysql://localhost/Mydatabase";
		String user="root";
		String pwd="Lhouc1234";
	    con=DriverManager.getConnection(url, user, pwd);
		PreparedStatement pst=con.prepareStatement("insert into ma_table (username,gagner) values(?,?) ");
		pst.setString(1, votreNom);
		String gagner = String.valueOf(gagne);
		pst.setString(2, gagner);
		// Exécuter la requête d'insertion
	    pst.executeUpdate();
 }
 catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	catch (SQLException e) {
		e.printStackTrace();
	}
	finally {
		try {
			con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
 }
 public void doPost(HttpServletRequest request, 
		 HttpServletResponse response)
		  throws ServletException, IOException {
		 doGet(request,response);
		  }
//Méthode doPut pour gérer les requêtes PUT
 @Override
 protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     // Récupérer les données envoyées dans la requête PUT
     String newData = request.getParameter("data");

     // Mettre à jour la ressource avec les nouvelles données
     // Ici, nous allons simplement afficher les nouvelles données dans la réponse
     response.setContentType("text/html");
     PrintWriter out = response.getWriter();
     out.println("<html><body>");
     out.println("<h1>Données mises à jour avec succès :</h1>");
     out.println("<p>Nouvelles données : " + newData + "</p>");
     out.println("</body></html>");
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