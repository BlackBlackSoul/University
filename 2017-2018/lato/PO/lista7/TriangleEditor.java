// Jakub Grobelny
// Pracownia PO, czwartek, s. 108
// L7, Edytor obiekt�w.
// Implementacja edytora obiekt�w klasy �Triangle�.
// TriangleEditor.java
// 2018-04-14

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Klasa obs�uguj�ca edytor obiekt�w klasy �Tr�jk�t�.
public class TriangleEditor extends Editor
{
    // Edytowany obiekt.
    private Triangle object;
    // Nazwa edytowanego pliku.
    private String fileName;
 
    // Pola znajduj�ce si� w oknie edytora.
    private JTextField name;
    private JTextField[] x;
    private JTextField[] y;
    private JLabel posX;
    private JLabel posY;

    // Metoda aktualizuj�ca pola edytowanego obiektu po zapisaniu go.
    private void updateObject(String name, double[] x, double[] y)
    {
        object.setName(name);

        for (int i = 0; i < 3; i++)
            object.setVertex(i, x[i], y[i]);
    }

    // Metoda interfejsu ActionListener, kt�ra wywo�ywana jest po
    // klikni�ciu na przycisk s�u��cy do zapisywania.
    public void actionPerformed(ActionEvent e)
    {
        // Zmienne, do kt�rych zostan� zapisane warto�ci p�l tekstowych.
        String name;
        double[] x = new double[3];
        double[] y = new double[3];

        try
        {
            // Odczytywanie danych.
            name = this.name.getText();

            for (int i = 0; i <= 2; i++)
            {
                x[i] = Double.parseDouble(this.x[i].getText());
                y[i] = Double.parseDouble(this.y[i].getText());
            }

            // Aktualizowanie obiektu.
            updateObject(name, x, y);

            // Aktualizowanie p�l ze wsp�rz�dnymi �rodka tr�jk�ta.
            posX.setText(Double.toString(this.object.getPosX()));
            posY.setText(Double.toString(this.object.getPosY()));

            // Zapisywanie obiektu do pliku.
            this.object.writeToFile(fileName);
        }
        // B��dne dane s� odrzucane.
        catch (Exception exc)
        {
            // Tworzenie okienka z b��dem.
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, 
                                  "Given input was invalid!", 
                                  "Error!", 
                                  JOptionPane.ERROR_MESSAGE);
        }
    }

    // Konstruktor.
    public TriangleEditor(JFrame frame, String path)
    {
        // Inicjowanie p�l edytora.
        this.object = new Triangle();
        this.fileName = path;
        this.object.readFromFile(this.fileName);
        
        this.container = frame.getContentPane();
        this.name = new JTextField(this.object.getName(), 80);
        this.x = new JTextField[3];
        this.y = new JTextField[3];
        
        // Ustalanie wymiar�w edytora.
        container.setPreferredSize(new Dimension(640, 240));
        container.setLayout(new GridLayout(6, 3));
        
        // Pierwszy wiersz.
        container.add(new JLabel("Name: "));
        container.add(name);
        container.add(new JLabel(""));
        
        // Kolejne wiersze ze wsp�rz�dnymi wierzcho�k�w.
        for (int i = 0; i < 3; i++)
        {
            container.add(new JLabel(String.format("(x%d, y%d)", i + 1, i + 1)));

            String vertX = Double.toString(object.getVertexPosX(i));
            String vertY = Double.toString(object.getVertexPosY(i));
            
            this.x[i] = new JTextField(vertX);
            this.y[i] = new JTextField(vertY);

            container.add(x[i]);
            container.add(y[i]);
        }
        
        // Przedostatni wiersz ze wsp�rz�dnymi �rodka.
        container.add(new JLabel("Centroid coordinates:"));
        posX = new JLabel(Double.toString(object.getPosX()));
        posY = new JLabel(Double.toString(object.getPosY()));
        container.add(posX);
        container.add(posY);

        // Ostatni wiersz z przyciskiem �Save�.
        JButton save = new JButton("Save");
        save.addActionListener(this);
        container.add(new JLabel(""));
        container.add(save);
    }
}