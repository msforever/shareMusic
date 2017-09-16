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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
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
    Session session;
    
    public void addSong(Song song){
        Song temp;
        temp=song;
        path=temp.getSpath();
           try {
               session=getSession();     
               file=new File(path);
               System.out.println(song.getSname()+" is found at: "+file.getAbsolutePath());
              session.save(temp);
           } 
           finally{
               temp=null;
               session.close();
           }
       
       
    }
    void playmedia(){
       Media hit =new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(hit);
                mediaPlayer.play();
    }
    
    public Session getSession(){
        AnnotationConfiguration con = new AnnotationConfiguration().configure();
        SessionFactory sf = con.buildSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        return s;
        
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

