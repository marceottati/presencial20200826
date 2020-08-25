/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.util.Observer;

/**
 *
 * @author usuario
 */
public interface InterfaceVista {
    public void display();
    public void addObserver(Observer o);
}
