package ua.kiev.prog;


import ua.kiev.prog.Food;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientMenu {
    private EntityManager em;

    public ClientMenu(EntityManager em) {
        this.em = em;
    }


    public void clientMenu(){
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("1 - Add dish");
            System.out.println("2 - Find by price");
            System.out.println("3 - Find by discount");
            System.out.println("4 - Select dishes by weight");
            System.out.println("5 - View all menu");
            System.out.println("6 - exit");
            int choice;
            do {
                choice = readInt(sc);
                if (choice < 1 || choice > 6) {
                    System.out.println("Choose number between 1 and 6");
                }
            }
                while (choice < 1 || choice >6);
                switch (choice){
                    case 1:
                        addMenuFood(sc);
                        break;
                    case 2:
                        findPrice(sc);
                        break;
                    case 3:
                        findDiscount();
                        break;
                    case 4:
                        DisheshWeight(sc);
                        break;
                    case 5:
                        allMenu();
                    case 6:
                        return;
                    default:
                        return;
                }
            }
        }
    private static int readInt(Scanner sc){
        while (!sc.hasNext()){
            System.out.println("Illegal parametr");
            sc.next();
        }
        return sc.nextInt();
    }

    private static long readLong(Scanner sc){
        while (!sc.hasNextLong()){
            System.out.println("Illegal parametr");
            sc.next();
        }
        return sc.nextLong();
    }

    public static boolean readBoolean(Scanner sc){
        while (!sc.hasNextBoolean()){
            System.out.println("Illegal parametr");
            sc.next();
        }
        return  sc.nextBoolean();
    }

    public static double readDouble(Scanner sc ){
        while (!sc.hasNextDouble()){
            System.out.println("Illegal parametr");
            sc.next();
        }
        return sc.nextDouble();
    }
    public void addMenuFood(Scanner sc){
            System.out.println("Food name");
            String foodName = sc.next();
            System.out.println("Food price");
            int foodPrice = readInt(sc);
            System.out.println("Enter dish weight: ");
            double foodWeight = readDouble(sc);
            System.out.println("Enter discount: true or false");
            boolean foodDiscount = readBoolean(sc);
            DatabaseQueries.addMenuFood(em, foodName, foodPrice, foodWeight, foodDiscount);
    }

        public void findPrice(Scanner sc){
        int minPrice;
        int maxPrice;
        do {
            System.out.println("Min price = ");
            minPrice = readInt(sc);
            System.out.println("Max price = ");
            maxPrice = readInt(sc);
            if (minPrice > maxPrice) {
                System.err.println("Erorr price");
            }
        }while (minPrice > maxPrice);
            List<Food> list = DatabaseQueries.findPrice(em,minPrice,maxPrice);
            for (Food food: list) {
                System.out.println(food);
                
            }
        }

        public void findDiscount(){
        List<Food> list = DatabaseQueries.findbydDiscount(em);
            for (Food food:list) {
                System.out.println(food);

            }
        }

        public void DisheshWeight(Scanner sc){
        List<Food> foodList = new ArrayList<>();
            System.out.println("Enter weight of food");
            double foodWeight = readDouble(sc);
            System.out.println("Select food from menu");
            double foodName = foodWeight;
            while (true){
                List<Food> list = DatabaseQueries.findByWeight(em, foodName);
                ArrayList<Long> idArrays = new ArrayList<>();
                if (list.size() ==0 ){
                    break;
                }
                System.out.println("Weight food is" + foodName + "kg");
                for (Food a:list) {
                    System.out.println(a);
                    idArrays.add(a.getId());
            }
                long id = checkId(sc, idArrays);
                Food food = DatabaseQueries.findId(em,id);
                foodName = foodName - food.getWeight();
                foodList.add(food);
            }
            System.out.println("Your order: ");
            for (Food a: foodList) {
                System.out.println(a);
            }
            System.out.println("Weight food is " +(foodWeight - foodName) + "kg");
    }

    public void allMenu(){
        System.out.println("MENU");
        List<Food> list = DatabaseQueries.getAll(em);
        for (Food food: list){
            System.out.println(food);
        }
    }

    private long checkId (Scanner sc, ArrayList<Long> idArrays){
        long id = 0;
        boolean check = false;
        while (!check){
            id = readLong(sc);
            for (Long elem:idArrays) {
                if (id == elem){
                    check = true;
                }
            }
            if (!check){
                System.out.println("Error with wrong id");
            }
        }
        return id;
    }
}
