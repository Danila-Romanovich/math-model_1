import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("������ ������ �������: ");
        double minValue = sc.nextDouble();
        System.out.print("������ ������� �������: ");
        double maxValue = sc.nextDouble();
        System.out.print("������ N - ���-�� ��������� �������: ");
        int n = sc.nextInt();

        MathModel mathModel = new MathModel(minValue,maxValue,n);
        mathModel.result();
    }
}