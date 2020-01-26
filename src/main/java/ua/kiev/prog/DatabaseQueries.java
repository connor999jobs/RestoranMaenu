package ua.kiev.prog;
import javax.persistence.*;
import java.util.List;

public class DatabaseQueries {
    public static void addMenuFood(EntityManager em, String name, int price, double weight, boolean discount) {
        em.getTransaction().begin();
        try {
            Food menu = new Food(name, price, weight, discount);
            em.persist(menu);
            em.getTransaction().commit();
            System.out.println("Food add to Menu");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error");
        }
    }


    public static List<Food> findPrice(EntityManager em, int minPrice,int maxPrice) {
        List<Food> result = null;
        try {
            Query query = em.createNamedQuery("Menu.Price", Food.class);
            query.setParameter("min", minPrice);
            query.setParameter("max", maxPrice);
            result = query.getResultList();
            if (result.size() == 0) {
                System.err.println("Dish not found!");
            }
        } catch (NoResultException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static List<Food> findByWeight(EntityManager em, double maxWeight) {
        List<Food> result = null;
        try {
            Query query = em.createNamedQuery("Menu.weight", Food.class);
            query.setParameter("max", maxWeight);

            result = query.getResultList();
        } catch (NoResultException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static List<Food> findbydDiscount(EntityManager em){
        List<Food> result = null;
        try {
            Query query = em.createNamedQuery("Menu.Discount",Food.class);
            result = query.getResultList();
            if (result.size() ==0 ) {
                System.out.println("No Discount");
            }
        } catch (NoResultException e){
            e.printStackTrace();
        }
        return  result;
    }

    public static Food findId(EntityManager em, long id) {
        Food result = null;
        try {
            Query query = em.createNamedQuery("Menu.Id", Food.class);
            query.setParameter("id", id);
            result = (Food) query.getSingleResult();
        } catch (NoResultException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static List<Food> getAll(EntityManager em){
        List<Food> result = null;
        try {
            Query query = em.createNamedQuery("Menu.All",Food.class);
            result = query.getResultList();
        }
        catch (NoResultException e){
            e.printStackTrace();
        }
        return result;
    }
}


