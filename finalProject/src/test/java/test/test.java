/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import com.mycompany.finalproject.model.*;

/**
 *
 * @author JCSF
 */
public class test {
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testBankGetters() {
        Bank bank1 = new Bank(1, "Scotia", "St jean street.", "5148122345","scotia@scotiabank.ca");
        Bank bank2 = new Bank("RandomBank", "Random Street", "514 091 0911", "email@randomEmail.ca");
        Bank bank3 = new Bank(2, "ShortBank");
        //test bank_id getter and test Bank constructor
        assertEquals(1, bank1.getBankId());
        assertEquals(-1, bank2.getBankId());
        assertEquals(2, bank3.getBankId());

        assertEquals("Scotia", bank1.getBankName());
        assertEquals("RandomBank", bank2.getBankName());
        assertEquals("ShortBank", bank3.getBankName());
        
        assertEquals("St jean street.", bank1.getAddress());
        assertEquals("Random Street", bank2.getAddress());
        if(bank3.getAddress() != null)
            fail("bank3 address should be null");
        assertEquals(null, bank3.getAddress());
         
        assertEquals("5148122345", bank1.getPhone());
        assertEquals("514 091 0911", bank2.getPhone());
        if(bank3.getPhone() != null)
            fail("bank3 phone should be null.");
        
        assertEquals("scotia@scotiabank.ca", bank1.getEmail());
        assertEquals("email@randomEmail.ca", bank2.getEmail());
        if(bank3.getEmail() != null )
            fail("bank3 email should be null");
        
    }
    
    @Test
    public void testBankSetters(){
        Bank bank1 = new Bank(1, "Scotia", "St jean street.", "5148122345","scotia@scotiabank.ca");
        bank1.setAddress("address");
        bank1.setBankId(5);
        bank1.setBankName("anotherBank");
        bank1.setEmail("juan-carlos.sreng-flores@dawsoncollege.qc.ca");
        bank1.setPhone("514 212 1232");
        assertEquals("address", bank1.getAddress());
        assertEquals(5, bank1.getBankId());
        assertEquals("anotherBank", bank1.getBankName());
        assertEquals("juan-carlos.sreng-flores@dawsoncollege.qc.ca", bank1.getEmail());
        assertEquals("514 212 1232", bank1.getPhone());
    }
    @Test
    public void testCondoSetters(){
        
    }
    @Test
    public void testCondoGetters(){

    }
    
    @Test 
    public void testContractorGetters(){
        Contractor contractor = new Contractor(
                67,
                "ContactName", 
                "RentGage Company", 
                "St Jean Luc Street", 
                "Electricity", 
                "5140216546", 
                "another@mail.com");
        assertEquals("St Jean Luc Street", contractor.getAddress());
        assertEquals("RentGage Company", contractor.getCompanyName());
        assertEquals("ContactName", contractor.getContactName());
        assertEquals(67, contractor.getContractorId());
        assertEquals("another@mail.com", contractor.getEmail());
        assertEquals("5140216546", contractor.getPhone());
        assertEquals("Electricity", contractor.getSpecialty());
    }
    
    @Test 
    public void testContractorSetters(){
        Contractor contractor = new Contractor(
                1,
                "", 
                "", 
                "", 
                "", 
                "", 
                "");
        contractor.setAddress("St Jean Luc Street");
        contractor.setCompanyName("RentGage Company");
        contractor.setContactName("ContactName");
        contractor.setContractorId(67);
        contractor.setEmail("another@mail.com");
        contractor.setPhone("5140216546");
        contractor.setSpecialty("Electricity");
        assertEquals("St Jean Luc Street", contractor.getAddress());
        assertEquals("RentGage Company", contractor.getCompanyName());
        assertEquals("ContactName", contractor.getContactName());
        assertEquals(67, contractor.getContractorId());
        assertEquals("another@mail.com", contractor.getEmail());
        assertEquals("5140216546", contractor.getPhone());
        assertEquals("Electricity", contractor.getSpecialty());
    }
    
    @Test
    public void testHouseGetters(){
        House house = new House(56, "address", "country", "state", "city", "postalCode", "details", 1.5);
        assertEquals(56, house.getPropertyId());
        assertEquals("address", house.getAddress());
        assertEquals("country", house.getCountry());
        assertEquals("state", house.getState());
        assertEquals("city", house.getCity());
        assertEquals("postalCode", house.getPostalCode());
        assertEquals("details", house.getDetails());
        assertEquals(1.5, house.getSize(), 0.00001);
    }
    
    @Test
    public void testHouseSetters(){
        House house = new House(56, "address", "country", "state", "city", "postalCode", "details", 1.5);
        house.setPropertyId(56);
        house.setAddress("address");
        house.setCountry("country");
        house.setState("state");
        house.setCity("city");
        house.setPostalCode("postalCode");
        house.setDetails("details");
        house.setSize(1.5);
        
    }
    
    @Test
    public void testLeaseGetters(){
        Lease lease = new Lease(1, "Random adddress", 0, "Juan-Carlos", "Sreng-Flores", "docx");
        assertEquals(1, lease.getPropertyId());
        assertEquals("Random adddress", lease.getPropertyAddress());
        assertEquals(0, lease.getTenantId());
        assertEquals("Juan-Carlos", lease.getTenantFirstName());
        assertEquals("Sreng-Flores", lease.getTenantLastName());
        assertEquals("docx", lease.getExtension());
    }
    
    @Test
    public void testLeaseSetters(){
        Lease lease = new Lease(1, "Random adddress", 0, "Juan-Carlos", "Sreng-Flores", "docx");

    }
}
