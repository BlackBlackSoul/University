// Jakub Grobelny
// Pracownia PO, czwartek, s. 108
// L7, Edytor obiekt�w.
// Abstrakcyjna klasa �Editor�.
// Editor.java
// 2018-04-14

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Abstrakcyjna klasa, z kt�rej dziedzicz� klasy, kt�re s�u��
// do edycji obiekt�w. Implementuje interfejs �ActionListener�.
public abstract class Editor implements ActionListener
{
    protected Container container;
    public abstract void actionPerformed(ActionEvent e);
}