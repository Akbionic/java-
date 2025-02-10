import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Car {
    String model;
    String make;
    int year;
    double price;

    public Car(String model, String make, int year, double price) {
        this.model = model;
        this.make = make;
        this.year = year;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Model: " + model + ", Make: " + make + ", Year: " + year + ", Price: $" + price;
    }
}

public class CarStockManager {
    private ArrayList<Car> cars;
    private Scanner scanner;

    public CarStockManager() {
        cars = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("\nCar Stock Management Menu:");
            System.out.println("1. Add Car");
            System.out.println("2. View Cars");
            System.out.println("3. Update Car");
            System.out.println("4. Delete Car");
            System.out.println("5. Exit");
            
            int choice = getMenuChoice();
            
            switch (choice) {
                case 1:
                    addCar();
                    break;
                case 2:
                    viewCars();
                    break;
                case 3:
                    updateCar();
                    break;
                case 4:
                    deleteCar();
                    break;
                case 5:
                    running = false;
                    break;
            }
        }
        System.out.println("Exiting program.");
        scanner.close();
    }

    private int getMenuChoice() {
        while (true) {
            try {
                System.out.print("Enter your choice (1-5): ");
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline
                if (choice >= 1 && choice <= 5) {
                    return choice;
                } else {
                    System.out.println("Please enter a number between 1 and 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();  // Clear invalid input
            }
        }
    }

    private void addCar() {
        System.out.print("Enter car model: ");
        String model = scanner.nextLine();
        System.out.print("Enter car make: ");
        String make = scanner.nextLine();
        
        int year = getValidYear();
        double price = getValidPrice();

        cars.add(new Car(model, make, year, price));
        System.out.println("Car added successfully.");
    }

    private void viewCars() {
        if (cars.isEmpty()) {
            System.out.println("No cars in stock.");
        } else {
            for (Car car : cars) {
                System.out.println(car);
            }
        }
    }

    private void updateCar() {
        if (cars.isEmpty()) {
            System.out.println("No cars in stock to update.");
            return;
        }

        System.out.print("Enter the model of the car to update: ");
        String modelToUpdate = scanner.nextLine();

        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).model.equalsIgnoreCase(modelToUpdate)) {
                System.out.println("Enter new details:");
                System.out.print("Enter car make: ");
                String make = scanner.nextLine();
                
                int year = getValidYear();
                double price = getValidPrice();

                cars.set(i, new Car(modelToUpdate, make, year, price));
                System.out.println("Car updated successfully.");
                return;
            }
        }
        System.out.println("Car not found.");
    }

    private void deleteCar() {
        if (cars.isEmpty()) {
            System.out.println("No cars in stock to delete.");
            return;
        }

        System.out.print("Enter the model of the car to delete: ");
        String modelToDelete = scanner.nextLine();

        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).model.equalsIgnoreCase(modelToDelete)) {
                cars.remove(i);
                System.out.println("Car deleted successfully.");
                return;
            }
        }
        System.out.println("Car not found.");
    }

    private int getValidYear() {
        while (true) {
            try {
                System.out.print("Enter car year: ");
                int year = scanner.nextInt();
                scanner.nextLine();  // Consume newline
                return year;
            } catch (InputMismatchException e) {
                System.out.println("Invalid year. Please enter a number.");
                scanner.nextLine();  // Clear invalid input
            }
        }
    }

    private double getValidPrice() {
        while (true) {
            try {
                System.out.print("Enter car price: ");
                double price = scanner.nextDouble();
                scanner.nextLine();  // Consume newline
                return price;
            } catch (InputMismatchException e) {
                System.out.println("Invalid price. Please enter a number.");
                scanner.nextLine();  // Clear invalid input
            }
        }
    }

    public static void main(String[] args) {
        CarStockManager manager = new CarStockManager();
        manager.run();
    }
}