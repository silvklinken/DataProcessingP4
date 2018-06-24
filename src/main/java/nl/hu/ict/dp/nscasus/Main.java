package nl.hu.ict.dp.nscasus;
import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    private static String orclcfg = "nl.hu.ict.jpa.oracle";
    private static EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {
        // get the Hibernate - JPA entityManager
        EntityManager em = null;
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory(orclcfg);
            em = entityManagerFactory.createEntityManager();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

//OPDRACHT 2 VAN P4
        
        // Aanmaken van de domein objecten
         
        OVChipkaart kaart = new OVChipkaart();
        kaart.setKaartopdruk("mijn eerste kaart");
        
        //aanmaken van een r1 domein object
        //voor het toevoegen in de tabel
        Reiziger r1 = new Reiziger();
        r1.setVoorletters("E");
        r1.setAchternaam("Salamanis");
        r1.setGeboortedatum(Date.valueOf("1996-05-04"));
        
        //voor het toevoegen in de tabel
        Reiziger r2 = new Reiziger();
        r2.setVoorletters("A");
        r2.setAchternaam("Tahr");
        r2.setGeboortedatum(Date.valueOf("2001-05-04"));
        
        //om r1 te updaten
        Reiziger r3 = new Reiziger();
        r3.setVoorletters("A");
        r3.setAchternaam("Salamanis");
        r3.setGeboortedatum(Date.valueOf("2002-06-30"));
        
        
        //Opslaan van de data in de domein objecten
         
        
        em.getTransaction().begin();
        //Create
        em.persist(kaart);
        em.persist(r1);
        em.persist(r2);
        em.persist(r3);
        
        //Read
        Reiziger r = em.find(Reiziger.class, r1.getReizigerID());
        System.out.println("Reiziger opgehaald: " + r);
        
        //Update
        Reiziger rr =  em.find(Reiziger.class, r2.getReizigerID());
        System.out.println("voor verandering: " + rr);
        rr.setAchternaam("Tahrioui");
        System.out.println("na verandering: " + rr);
        
        //Delete
        Reiziger rrr = em.find(Reiziger.class, r3.getReizigerID());
        System.out.println("voor verwijderen: " + rrr);
        em.remove(r3);
        
        em.getTransaction().commit();
        em.close();
        System.out.println("Verwijderd: " + r3);
        System.out.println("-- einde --");  
       
        
//OPDRACHT 3 VAN P4
        //domein objecten aanmaken:
        Reiziger r4 = new Reiziger();
        r4.setVoorletters("E");
        r4.setAchternaam("Salamanis");
        r4.setGeboortedatum(Date.valueOf("1996-05-04"));
        
        OVChipkaart k1 = new OVChipkaart();
        k1.setReiziger(r4);
        k1.setKaartopdruk("mijn eerste kaart");
        
        OVChipkaart k2 = new OVChipkaart();
        k2.setReiziger(r4);
        k2.setKaartopdruk("mijn tweede kaart");
        
        OVChipkaart k3 = new OVChipkaart();
        k3.setReiziger(r4);
        k3.setKaartopdruk("mijn derde kaart");
                
        em.getTransaction().begin();
        //CREATE
        em.persist(r4);
        em.persist(k1);
        em.persist(k2);
        em.persist(k3);
        
        //READ
        OVChipkaart k = em.find(OVChipkaart.class, k3.getKaartnr());
        System.out.println("Kaart gelezen: " + k);
        
        //UPDATE
        k.setKaartopdruk("Lorum ipsum dolor");
        
        //DELETE
        em.remove(k3);
        
        em.getTransaction().commit();
        em.close();
    }
}
