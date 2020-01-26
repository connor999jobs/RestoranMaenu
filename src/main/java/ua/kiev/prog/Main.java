package ua.kiev.prog;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAMenu");
        EntityManager em = emf.createEntityManager();
        try {
            ClientMenu cm = new ClientMenu(em);
            cm.clientMenu();
        }
        finally {
            em.close();
            emf.close();
        }
    }
}
