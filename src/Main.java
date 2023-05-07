import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static int[] prices = {100, 200, 300};
    static String[] products = {"Хлеб", "Яблоко", "Молоко"};

    static File saveFile = new File("basket.bin");


    public static void main(String[] args) throws FileNotFoundException {
        Basket basket = null;

        if (saveFile.exists()) {
            basket = Basket.loadFromBinFile(saveFile);
        } else {
            basket = new Basket(products, prices);
        }

        while (true) {
            showPrice();
            System.out.println("Выбирите товар и количество через пробел или на бирите end");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                break;
            }

            String[] parts = input.split(" ");
            int productNumber = Integer.parseInt(parts[0]) - 1;
            int productCount = Integer.parseInt(parts[1]);
            basket.addToCart(productNumber, productCount);
            basket.saveBin(saveFile);
        }

        basket.printCart();

    }

    public static void showPrice() {
        System.out.println("Список возможных товаров для покупки");
        for (int i = 0; i < products.length; i++) {
            System.out.println(products[i] + " " + prices[i] + " руб./шт");
        }
    }
}

