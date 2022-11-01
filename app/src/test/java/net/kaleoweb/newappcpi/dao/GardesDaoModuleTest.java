package net.kaleoweb.newappcpi.dao;

import android.app.Application;

import junit.framework.TestCase;

import net.kaleoweb.newappcpi.databases.GardesDatabase;
import net.kaleoweb.newappcpi.utilities.Gardes;

import org.junit.Assert;
import org.junit.Before;

public class GardesDaoModuleTest extends TestCase {
    
    Gardes gardes = new Gardes("2021/11/08","CHOTARD","PIERROT","RENZO","NASI","NEANT",1,"");
    
    @Before
    public void avant(){
        System.out.println("Avant chaque tests");
    }
    
    public void testInsertGardes() {
        Assert.assertNotNull(gardes);
      
    }
    
    public void testUpdateGardes() {
    }
    
    public void testDeletegarde() {
    }
    
    public void testGetAll() {
    }
    
    public void testGettout() {
    }
    
    public void testCount() {
    }
    
    public void testI() {
    }
}