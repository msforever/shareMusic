/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Mayuresh
 
 */
@Entity 
@Table (name="songs")
public class Song {
    @Id
    @GeneratedValue
    private int sid;
    private String sname;
    private String sartist;
    private String salbum;
    private String spath;

    public Song(int sid, String sname, String sartist, String salbum, String spath) {
        this.sid = sid;
        this.sname = sname;
        this.sartist = sartist;
        this.salbum = salbum;
        this.spath = spath;
    }
    
    public Song(){}

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSartist() {
        return sartist;
    }

    public void setSartist(String sartist) {
        this.sartist = sartist;
    }

    public String getSalbum() {
        return salbum;
    }

    public void setSalbum(String salbum) {
        this.salbum = salbum;
    }

    public String getSpath() {
        return spath;
    }

    public void setSpath(String spath) {
        this.spath = spath;
    }
     
}
