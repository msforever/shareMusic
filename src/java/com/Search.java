/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;


import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.TargetDataLine;
import org.apache.tika.Tika;
import java.io.File;
import java.net.URI;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.SceneBuilder;
import javafx.scene.control.LabelBuilder;
import javafx.scene.text.Font;
import javafx.stage.StageBuilder;
import javafx.stage.WindowEvent;
import javax.swing.SwingUtilities;
/**
 *
 * @author Mayuresh
 */
public class Search {
    private int searchid;
    TargetDataLine targetDataLine;
    AudioFormat audioForamt;
    AudioSystem audioSystem;
    String filename;
    String path;
    File file;
    byte [] b;
    Connection con;
    
    public void addSong(Song song){
        path=song.getSpath();
      
           try {
               con=getConnection();
               PreparedStatement ps=con.prepareStatement("insert into songs values(?,?,?,?,?)");
               ps.setInt(1, 2);
               ps.setString(2, "laad piya ka");
               ps.setString(3, "Haryana");
               ps.setString(4, "Haryana");
               ps.setString(5, "E:\\");
               ps.executeUpdate();
               file=new File(path);
              // while (file)
               
               System.out.println(song.getSname()+" is found at: "+file.getAbsolutePath());
              
           } catch (SQLException ex) {
               Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
           }
           finally{
               try {
                   con.close();
               } catch (SQLException ex) {
                   Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
       
       
    }
    void playmedia(){
       Media hit =new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(hit);
                mediaPlayer.play();
    }
    
    public Connection getConnection(){
        Connection con=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");  
             con=DriverManager.getConnection("jdbc:mysql://localhost:3306/songs","root","");
              
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }
         catch (SQLException ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
        
    }
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JFXPanel(); // this will prepare JavaFX toolkit and environment
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        StageBuilder.create()
                                .scene(SceneBuilder.create()
                                        .width(320)
                                        .height(240)
                                        .root(LabelBuilder.create()
                                                .font(Font.font("Arial", 54))
                                                .text("JavaFX")
                                                .build())
                                        .build())
                                .onCloseRequest(new EventHandler<WindowEvent>() {
                                    @Override
                                    public void handle(WindowEvent windowEvent) {
                                        System.exit(0);
                                    }
                                })
                                .build()
                                .show();
                    }
                });
            }
        });
    

        
        
        Song song= new Song();
        song.setSid(5);
        song.setSname("Mann Bharaya");
        song.setSartist("JayZ");
        String path="E:\\gal.mp3";
        song.setSpath(path);
        Search s=new Search();
        s.addSong(song);
        s.playmedia();
        
    }
  
}

