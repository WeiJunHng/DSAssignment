/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hackingthefuture;

import java.sql.DriverManager;

/**
 *
 * @author jze20
 */

import java.sql.*; 
import java.util.Scanner;
import javax.swing.JOptionPane;

public class FutureHacking {

    /**
     * @param args the command line arguments
     */
   
    Connection con;
    Statement stmt;
    DatabaseMetaData dbm;
    ResultSet rs;
    String sql;
    
    public String emailLogin, passwordLogin;
    
    public String name, emailRegister, role, passwordRegister, confirmPassword;
    
    
    public FutureHacking(){
        try{
            connectDB();
            loginGUI();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //Connecting to Database and Creating Tables
    public void connectDB() throws Exception
    {

        Class.forName("com.mysql.cj.jdbc.Driver");
        String path = "jdbc:mysql://localhost:3306/futurehacking?zeroDateTimeBehavior=CONVERT_TO_NULL";
        con = DriverManager.getConnection(path,"root","umisone23/24");

        dbm = con.getMetaData();

        //Student Table
        rs = dbm.getTables(null,null,"tblStudent",null);
        if(rs.next())
        {
                System.out.println("Student Table exists");
        }
        else
        {
                stmt = con.createStatement();
                sql = "CREATE TABLE tblStudent (studentID VARCHAR(255) NOT NULL, "
                        + "studentName VARCHAR(255), studentEmail VARCHAR(255), "
                        + "studentPassword VARCHAR(255), registeredEvent VARCHAR(255), "
                        + "registeredBooking VARCHAR(255), friendRequest VARCHAR(255), parentID VARCHAR(255), "
                        + "PRIMARY KEY(studentID))";

                stmt.executeUpdate(sql);
                System.out.println("Student Table created");
        }

        //Parent Table
        rs = dbm.getTables(null,null,"tblParent",null);
        if(rs.next())
        {
                System.out.println("Parent Table exists");
        }
        else
        {
                stmt = con.createStatement();
                sql = "CREATE TABLE tblParent (parentID VARCHAR(255) NOT NULL, parentName VARCHAR(255), "
                        + "parentEmail VARCHAR(255), parentPassword VARCHAR(255), "
                        + "studentID VARCHAR(255), pastBooking VARCHAR(255), "
                        + "PRIMARY KEY(parentID))";

                stmt.executeUpdate(sql);
                System.out.println("Parent Table created");
        }

        //Educator Table
        rs = dbm.getTables(null,null,"tblEducator",null);
        if(rs.next())
        {
                System.out.println("Educator Table exists");
        }
        else
        {
                stmt = con.createStatement();
                sql = "CREATE TABLE tblEducator (educatorID VARCHAR(255) NOT NULL, "
                        + "educatorName VARCHAR(255), educatorEmail VARCHAR(255), "
                        + "educatorPassword VARCHAR(255), numEvent VARCHAR(255), "
                        + "numQuiz VARCHAR(255), PRIMARY KEY(educatorID))";

                stmt.executeUpdate(sql);
                System.out.println("Educator Table created");
        }

        //Event Table
        rs = dbm.getTables(null,null,"tblEvent",null);
        if(rs.next())
        {
                System.out.println("Event Table exists");
        }
        else
        {
                stmt = con.createStatement();
                sql = "CREATE TABLE tblEvent (eventID VARCHAR(255) NOT NULL, "
                        + "eventTitle VARCHAR(255), eventDesc LONGTEXT, "
                        + "eventVenue VARCHAR(255), eventDate VARCHAR(255), "
                        + "eventTime VARCHAR(255), educatorID VARCHAR(255), "
                        + "PRIMARY KEY(eventID))";

                stmt.executeUpdate(sql);
                System.out.println("Event Table created");
        }

        //Quiz Table
        rs = dbm.getTables(null,null,"tblQuiz",null);
        if(rs.next())
        {
                System.out.println("Quiz Table exists");
        }
        else
        {
                stmt = con.createStatement();
                sql = "CREATE TABLE tblQuiz (quizID VARCHAR(255) NOT NULL, "
                        + "quizTitle VARCHAR(255), quizDesc LONGTEXT, "
                        + "quizTheme VARCHAR(255), quizContent VARCHAR(255), "
                        + "educatorID VARCHAR(255), PRIMARY KEY(quizID))";

                stmt.executeUpdate(sql);
                System.out.println("Quiz Table created");
        }

        //Discussion Table
        rs = dbm.getTables(null,null,"tblDiscussion",null);
        if(rs.next())
        {
                System.out.println("Discussion Table exists");
        }
        else
        {
                stmt = con.createStatement();
                sql = "CREATE TABLE tblDiscussion (discussionID VARCHAR(255) NOT NULL, "
                        + "discussionTitle VARCHAR(255), discussionContent VARCHAR(255), "
                        + "discussionAuthor VARCHAR(255), Comment VARCHAR(255), "
                        + "discussionLike VARCHAR(255), datePublished VARCHAR(255), "
                        + "PRIMARY KEY(discussionID))";

                stmt.executeUpdate(sql);
                System.out.println("Discussion Table created");
        }
     
    }
   
    
    public void loginGUI()throws Exception
    {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Email : ");
        emailLogin = sc.next();
        
        System.out.print("Password : ");
        passwordLogin = sc.next();
        
        validateLogin(emailLogin, passwordLogin);
        
    }
    
    public void registerGUI() throws Exception{
        
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Name : ");
        name = sc.next();
        
        System.out.print("Email : ");
        emailRegister = sc.next();
        
        System.out.print("Role : ");
        role = sc.next();
        
        System.out.print("Password : ");
        passwordRegister = sc.next();
        
        System.out.print("Confirm Password : ");
        confirmPassword = sc.next();
        
        if(passwordRegister.equals(confirmPassword)){
            validateRegister(name, emailRegister,role, passwordRegister);
        }else{
            System.out.println("Password and Confirm Password do not match");
        }
    }
    
    
    public void validateLogin(String email, String password) throws Exception{
       
        stmt = con.createStatement();
        
        sql = "SELECT studentPassword FROM tblStudent WHERE studentEmail = '" + email + "'";
        rs = stmt.executeQuery(sql);
        
        if (rs.next()) 
        {
            if(password.equals(rs.getString("studentPassword"))){
                System.out.println("Go to main page");
            }else{
                System.out.println("Incorrect Password");
                loginGUI();
            }
            
        } 
        else 
        {
            stmt = con.createStatement();
            sql = "SELECT parentPassword FROM tblParent WHERE parentEmail = '" + email + "'";
            rs = stmt.executeQuery(sql);
            
            if (rs.next()) {
                if(password.equals(rs.getString("parentPassword"))){
                    System.out.println("Go to main page");
                }else{
                    System.out.println("Incorrect Password");
                    loginGUI();
                }
            }else{
                stmt = con.createStatement();
                sql = "SELECT educatorPassword FROM tblEducator WHERE educatorEmail = '" + email + "'";
                rs = stmt.executeQuery(sql);
                
                if (rs.next()) {
                    if(password.equals(rs.getString("educatorPassword"))){
                        System.out.println("Go to main page");
                    }else{
                        System.out.println("Incorrect Password");
                        loginGUI();
                    }
                }else{
                   
                    //JOptionPane.showMessageDialog(null, "There is no such email","Alert",JOptionPane.WARNING_MESSAGE);
                    System.out.println("Please register first\n" );
                    registerGUI();
                }
            }
        }
    }
    
    
    public void validateRegister(String name, String email, String role, String password) throws Exception{
        
        if(role.equals("Student")){
            
            stmt = con.createStatement();
        
            sql = "SELECT studentEmail FROM tblStudent WHERE studentEmail = '" + email + "'";
            rs = stmt.executeQuery(sql);
            
            if(rs.next()){
                
                System.out.println("There is a existing email.");
                
            }else{
                
                stmt = con.createStatement();
        
                sql = "SELECT MAX(studentID) FROM tblStudent";
                rs = stmt.executeQuery(sql);
                
                String lastStudentId = null;
                if(rs.next()){
                    lastStudentId = rs.getString(1);
                }else{
                    lastStudentId = "S0";
                }
                
                String nextStudentId = generateNextId(lastStudentId,"Student");
                Scanner sc = new Scanner(System.in);
                String parentID = "";
                while(true){
                     System.out.print("Enter parent email : ");
                     String parentEmail = sc.next();
                     
                     if(parentEmail.equals("stop")){
                         break;
                     }else{
                         parentID += getParentID(parentEmail) + ",";
                     }
                }
               
                
                stmt = con.createStatement();
                sql = "INSERT INTO tblStudent(studentID, studentName, studentEmail, studentPassword, parentID) VALUES ('" + nextStudentId + "', '" + 
                       name + "', '" + email + "' ,'" + password + "', '" + parentID + "')";
                stmt.executeUpdate(sql);
                
                updateParent(nextStudentId, parentID);
                    
            }
        }else if(role.equals("Parent")){
            stmt = con.createStatement();
        
            sql = "SELECT parentEmail FROM tblParent WHERE parentEmail = '" + email + "'";
            rs = stmt.executeQuery(sql);
            
            if(rs.next()){
                
                System.out.println("There is a existing email.");
                
            }else{
                
                stmt = con.createStatement();
        
                sql = "SELECT MAX(parentID) FROM tblParent";
                rs = stmt.executeQuery(sql);
                
                String lastParentId = "";
                if(rs.next()){
                    lastParentId = rs.getString(1);
                }else{
                    lastParentId = "P0";
                }
                
                String nextParentId = generateNextId(lastParentId,"Parent");
                
                stmt = con.createStatement();
                sql = "INSERT INTO tblParent(parentID, parentName, parentEmail, parentPassword) VALUES ('" + nextParentId + "', '" + 
                       name + "', '" + email + "' ,'" + password + "')";
                stmt.executeUpdate(sql);
            }
        }else{
            
        }
            
            
        
    }
    
    private static String generateNextId(String last, String role) {
        // Parse the last student ID to extract the numeric part
        int lastId = 0;
        if (last != null && last.length() > 1) {
            try {
                lastId = Integer.parseInt(last.substring(1));
            } catch (NumberFormatException e) {
                // Handle parsing error
                e.printStackTrace();
            }
        }

        // Increment the numeric part and generate the next student ID
        if(role.equals("Student")){
            return "S" + (lastId + 1);
        }else if(role.equals("Parent")){
            return "P" + (lastId + 1);
        }else{
            return "E" + (lastId + 1);
        }
        
    }
    
    public String getParentID(String email) throws Exception{
        
        String ID = null;
        stmt = con.createStatement();
        
        sql = "SELECT parentID FROM tblParent WHERE parentEmail = '" + email + "'";
        rs = stmt.executeQuery(sql);

        if(rs.next()){
            ID = rs.getString("parentID");
        }else{
            System.out.println("Please register a parent first");
            registerGUI();
        }
        
        return ID;
    }
    
    public void updateParent(String id, String parentID) throws Exception{
        
        stmt = con.createStatement();
        
        sql = "SELECT studentID FROM tblParent WHERE parentID = '" + parentID + "'";
        rs = stmt.executeQuery(sql);
        String studentID = null;
        
        if(rs.next()){
            studentID = rs.getString("studentID") + "," + id;
        }else{
            studentID = id;
        }
        
        stmt = con.createStatement();
        sql = "UPDATE tblParent SET studentID = '" + studentID + "' WHERE parentID = '" + parentID + "'";
        stmt.executeUpdate(sql);
    }
    
    //Run program
    public static void main(String[] args) {
        // TODO code application logic here
        new FutureHacking();
    }
    
    
    
}
