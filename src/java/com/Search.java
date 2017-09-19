/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;



import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.TargetDataLine;
import java.io.File;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.scene.SceneBuilder;
import javafx.scene.control.LabelBuilder;
import javafx.scene.text.Font;
import javafx.stage.StageBuilder;
import javafx.stage.WindowEvent;
import javax.swing.SwingUtilities;
import org.apache.commons.io.FilenameUtils;
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
               //System.out.println(song.getSname()+" is found at: "+file.getAbsolutePath());
              session.save(temp);
           } 
           finally{
               temp=null;
               session.close();
           }
       
       
    }
    void playmedia(){
       Media hit = new Media(file.toURI().toString());
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
    public void listFiles(String dir){
        File folder = new File(dir);
        File[] listOfFiles = folder.listFiles();
        int count=0;
        for (int i = 0; i < listOfFiles.length; i++) {
            //System.out.println("File " + listOfFiles[i].getName());
            
            String ext1 = FilenameUtils.getExtension(listOfFiles[i].getAbsolutePath());
            String ext2 = listOfFiles[i].getAbsolutePath();
            //System.out.println("Extension "+ext1);
         if (listOfFiles[i].isFile() && "mp3".equalsIgnoreCase(ext1)) {
             count++;
             Song song = new Song();
             song.setSpath(ext2);
             addSong(song);
        System.out.println(count+" File " + listOfFiles[i].getName()+"with extension "+ext1+"\n "+ext2);
      } else if (listOfFiles[i].isDirectory()) {
        System.out.println("Directory " + listOfFiles[i].getName());
      }
      
    }
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
        
        song.setSname("Mann Bharaya");
        song.setSartist("JayZ");
        String path="E:\\gal.mp3";
        song.setSpath(path);
        Search s=new Search();
        s.addSong(song);
        s.playmedia();
       // s.listFiles(path);
        
    }
  
}

