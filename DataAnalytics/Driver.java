import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Driver {

    private void writeData() {
        String line = "";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("test_data.txt"));
            for (int i = 1; i <= 50; i++) {
                int size = (int)(i * i * 3.14);
                line = Double.valueOf(String.valueOf(i))
                        + " "
                        + size
                        + " "
                        + (BigInteger.valueOf(size).isProbablePrime(100) ? "Bad" : "Good")
                        + " "
                        + (BigInteger.valueOf(size).isProbablePrime(100) ? "true" : "false")
                        + "\n";
                writer.write(line);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initAndShowGUI(Double accuracy, Double precision) {
        JFrame myFrame = new JFrame();
        myFrame.setTitle("CS 112 Project Part 1: Project Setup");
        Container contentPane = myFrame.getContentPane();
        contentPane.setLayout(new GridLayout(2, 2));
        contentPane.add(new Label("Accuracy: "));
        String acc = new DecimalFormat("##.##").format(accuracy);
        contentPane.add(new Label(acc));
        String pre = new DecimalFormat("##.##").format(precision);
        contentPane.add(new Label("Precision: "));
        contentPane.add(new Label(pre));
        myFrame.pack();
        myFrame.setVisible(true);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String args[]) {
        Driver driver = new Driver();
        driver.writeData();
        DummyPredictor dummyPredictor = new DummyPredictor();
        ArrayList<DataPoint> dataPoints = dummyPredictor.readData("test_data.txt");
        System.out.println(dummyPredictor.test(new DataPoint(Double.valueOf(101), Double.valueOf(10800), "Bad", false)));
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        driver.initAndShowGUI(dummyPredictor.getAccuracy(dataPoints), dummyPredictor.getPrecision(dataPoints));
                    }
                }
        );
    }
}
