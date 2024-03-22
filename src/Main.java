import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("ведите нижнюю границу: ");
        double minValue = sc.nextDouble();
        System.out.print("ведите верхнюю границу: ");
        double maxValue = sc.nextDouble();
        System.out.print("ведите N - кол-во элементов выборки: ");
        int n = sc.nextInt();

        MathModel mathModel = new MathModel(minValue,maxValue,n);
        mathModel.result();
    }
}