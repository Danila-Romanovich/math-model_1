import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MathModel {
    private double minValue;
    private double maxValue;
    private int n;
    private ArrayList<Double> randValArr = new ArrayList<>();
    private double mx;
    private double dispersion;
    private int k;
    private double segmentWidth;
    private double hi;
    private double[] table = new double[]{3.84146, 5.99146, 7.81473, 9.48773, 11.0705, 12.59159, 14.06714, 15.50731, 16.91898, 18.30704};


    public MathModel(double minValue, double maxValue, int n) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.n = n;
    }

    // ���������� ��������� �������� � �������� ���������
    private void random() {
        Random random = new Random();
        for (int i = 0; i < this.n; i++) {
//            this.randValArr.add(random.nextDouble(this.minValue, this.maxValue));
            this.randValArr.add(this.minValue + (this.maxValue - this.minValue) * random.nextDouble());
        }
        Collections.sort(this.randValArr);
    }

    // ����������� �����������
    private void mathExpCalc() {
        double sum = 0;
        for (double item : this.randValArr) {
            sum += item;
        }
        this.mx = sum / this.n;
    }

    // ��������� �����������
    private void dispersion() {
        double sum = 0;
        for (double item : this.randValArr) {
            sum += Math.pow((item - this.mx), 2);
        }
        this.dispersion = sum / this.n;
    }

    private double checkDispersion() {
        return Math.pow((this.randValArr.get(this.n - 1) - this.randValArr.get(0)), 2) / 12;
    }

    // ��-�������
    private void scoreK() {
        this.k = (1 + (int) Math.floor(3.2219 * Math.log10(this.n))); // �������������� ������ � �� ������� ����������
    }

    private void segmentWidthCalc() {
        this.segmentWidth = (this.maxValue - this.minValue) / (double) this.k;
    }

    private void showTableHi() {
        System.out.println(this.table[this.k - 1]);
    }

    private void calcHi() {
        // ����� ����� ���������� � ������ ���������
        int[] interval = new int[this.k];
        for (double item : this.randValArr) {
            int i = (int)((item - this.minValue) / this.segmentWidth);
            interval[i] += 1;
        }

        // ������������� �� ����������
//        for (int item : interval) {
//            System.out.print(item + " ");
//        }

        double cHi = 0;
        double Npi = this.n / (double) this.k;
        for (double ni : interval) {
            cHi += Math.pow((ni - Npi), 2) / Npi;
        }
        this.hi = cHi;
    }

    public void result(){
        random();
        mathExpCalc();
        dispersion();
        scoreK();
        segmentWidthCalc();
        calcHi();

        System.out.println("��������� ��������: " + this.randValArr);
        System.out.println("�����������: " + this.mx);
        System.out.println("���������: " + this.dispersion);
        System.out.println("���������: " + checkDispersion());
        System.out.println("K: " + this.k);
        System.out.println("������ �������: " + this.segmentWidth);
        System.out.print("��������� �������� ��� 0,05: ");
        showTableHi();
        System.out.println("��-�������: " + this.hi);

        try {
            FileWriter writer = new FileWriter("C:\\Users\\fakto\\OneDrive\\������� ����\\results.txt");
            writer.write("������� ������: \n");
            writer.write("a: " + this.minValue + "\n");
            writer.write("b: " + this.maxValue + "\n");
            writer.write("N: " + this.n + "\n\n");
            writer.write("��������������� ��������� ��������: \n");
            for (double item : this.randValArr){
                writer.write(item + "\n");
            }
            writer.write("\n");
            writer.write("�������������� ��������: " + this.mx + "\n");
            writer.write("���������: " + this.dispersion + "\n");
            writer.write("��������� ���������: " + checkDispersion() + "\n");
            writer.write("���������� ����������: " + this.k + "\n");
            writer.write("������ �������: " + this.segmentWidth + "\n");
            writer.write("��������� �������� ��-������� ��� 0,05: " + this.table[this.k - 1] + "\n");
            writer.write("����������� �������� ��-�������: " + this.hi + "\n");
            writer.close();
            System.out.println("���������� �������� � ���� results.txt");
        } catch (IOException e) {
            System.out.println("������ ������ � ����: " + e.getMessage());
        }

    }
}
