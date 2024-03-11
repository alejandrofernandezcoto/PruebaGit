/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import iu.VentanaPrincipal;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

/**
 * @author a22davidil
 */
public class Xogo {

    private static int LADOCADRADO = 32;
    private static int MAXX = LADOCADRADO * 10;
    private static int MAXY = LADOCADRADO * 20;
    private boolean pausa;
    private int numeroLineas = 0;
    private ArrayList<Cadrado> cadradosChan = new ArrayList();
    private Ficha fichaActual;
    private VentanaPrincipal ventana;
    private Sonido sing;

    public Xogo(VentanaPrincipal ventana) {
        this.ventana = ventana;
    }

    public int getLadoCadrado() {
        return LADOCADRADO;
    }

    public void setLadoCadrado(int ladoCadrado) {
        this.LADOCADRADO = ladoCadrado;
    }

    public int getMaxX() {
        return MAXX;
    }

    public void setMaxX(int maxX) {
        this.MAXX = maxX;
    }

    public int getMaxY() {
        return MAXY;
    }

    public void setMaxY(int maxY) {
        this.MAXY = maxY;
    }

    public boolean isPausa() {
        return pausa;
    }

    public void setPausa(boolean pausa) {
        this.pausa = pausa;
    }

    public int getNumeroLineas() {
        return numeroLineas;
    }

    public void setNumeroLineas(int numeroLineas) {
        this.numeroLineas = numeroLineas;
    }

    public ArrayList<Cadrado> getCadradosChan() {
        return cadradosChan;
    }

    public void setCadradosChan(ArrayList<Cadrado> cadradosChan) {
        this.cadradosChan = cadradosChan;
    }

    public Ficha getFichaActual() {
        return fichaActual;
    }

    public void setFichaActual(Ficha fichaActual) {
        this.fichaActual = fichaActual;
    }

    public VentanaPrincipal getVentana() {
        return ventana;
    }

    public void setVentana(VentanaPrincipal ventana) {
        this.ventana = ventana;
    }

    public void xenerarNovaFicha() {
        boolean perder = false;
        int pieza = (int) (Math.random() * 7);
        switch (pieza) {
            case 0:
                FichaCadrada fichaC = new FichaCadrada(this);
                setFichaActual(fichaC);
                break;
            case 1:
                FichaBarra fichaB = new FichaBarra(this);
                setFichaActual(fichaB);
                break;
            case 2:
                FichaL fichaL = new FichaL(this);
                setFichaActual(fichaL);
                break;
            case 3:
                FichaT fichaT = new FichaT(this);
                setFichaActual(fichaT);
                break;
            case 4:
                FichaL2 fichal2 = new FichaL2(this);
                setFichaActual(fichal2);
                break;
            case 5:
                FichaZ fichaZ = new FichaZ(this);
                setFichaActual(fichaZ);
                break;
            case 6:
                FichaS fichaS = new FichaS(this);
                setFichaActual(fichaS);
                break;
        }
        Iterator<Cadrado> fichaCadrados = fichaActual.getCadrados().iterator();
        while (fichaCadrados.hasNext() && !perder) {
            Cadrado temporal = fichaCadrados.next();
            perder = cadradoEnXY(temporal.getX(), temporal.getY());
        }
        if (perder) {
            ventana.mostrarFinDoXogo();
        } else {
            fichaCadrados = fichaActual.getCadrados().iterator();
            while (fichaCadrados.hasNext()) {
                ventana.pintarCadrado(fichaCadrados.next().getLblCadrado());
            }
        }

    }

    public void moverFichaAbaixo() {
        if (!pausa && chocarFichaCoChan()) {
            engadirFichaAoChan();
            borrarLinasCompletas();
        } else {
            getFichaActual().moverAbaixo();
        }
    }

    public void moverFichaDereita() {
        if (!pausa && validar('d')) {
            getFichaActual().moverDereita();
        }
    }

    public void moverFichaEsquerda() {
        if (!pausa && validar('e')) {
            getFichaActual().moverEsquerda();
        }
    }

    public void rotarFicha() {
        if (!pausa) {
            getFichaActual().rotar();
        }
    }

    public boolean chocarFichaCoChan() {
        for (int contador = 0; contador < getFichaActual().getCadrados().size(); contador++) {
            if (chocarChan(getFichaActual().getCadrados().get(contador).getX(), getFichaActual().getCadrados().get(contador).getY())) {

                return true;
            }
        }
        return false;
    }

    public void engadirFichaAoChan() {
        getCadradosChan().addAll(getFichaActual().getCadrados());
        xenerarNovaFicha();
    }

    public void borrarLinasCompletas() {
        boolean eliminarFila;
        for (int contador = 0; contador < cadradosChan.size(); contador++) {
            eliminarFila = true;
            for (int posicion = 0; posicion < 10 && eliminarFila; posicion++) {
                if (!cadradoEnXY(posicion * LADOCADRADO, cadradosChan.get(contador).getY())) {
                    eliminarFila = false;
                } else if (posicion == 9) {
                    borrarLina(cadradosChan.get(contador).getY());
                    numeroLineas++;
                }
            }
        }
    }

    public void borrarLina(int y) {
        sing = new Sonido();
        for (int contador = 0; contador < getCadradosChan().size(); contador++) {
            if (getCadradosChan().get(contador).getY() == y) {
                getVentana().borrarCadrado(getCadradosChan().get(contador).getLblCadrado());
                getCadradosChan().remove(getCadradosChan().get(contador));
                contador--;
            }
        }
        baixarCadrados(y);
        sing.reproducirSonido1();
    }

    public boolean ePosicionValida(int x, int y) {
        if (validarXY(x, y)) {
            return true;
        } else {
            return false;
        }
    }

    public void engadirLineas() {
        Iterator<Cadrado> actualizarCadrados = cadradosChan.iterator();
        while (actualizarCadrados.hasNext()) {
            Cadrado temporal = actualizarCadrados.next();
            ventana.borrarCadrado(temporal.getLblCadrado());
            temporal.setY(temporal.getY() - LADOCADRADO);
            ventana.pintarCadrado(temporal.getLblCadrado());
        }
        boolean cadradoEngadido = false;
        for (int contador = 0; contador < MAXX; contador = contador + LADOCADRADO) {
            double aleatorio = Math.random();
            if (aleatorio > 0.7) {
                Cadrado engadido = new Cadrado(contador, MAXY - LADOCADRADO, Color.LIGHT_GRAY, LADOCADRADO);
                pintarCadradoXogo(engadido);
                cadradoEngadido = true;
            } else if (contador + LADOCADRADO == MAXX && !cadradoEngadido) {
                contador = 0;
            }
        }
    }

    private boolean validarXY(int x, int y) {
        return !cadradoEnXY(x, y) && x < getMaxX() && y < getMaxY() && x >= 0 && y >= 0;
    }

    private boolean chocarChan(int x, int y) {
        if (cadradoEnXY(x, y + LADOCADRADO) || y + getLadoCadrado() == getMaxY()) {
            return true;
        }
        return false;
    }

    private boolean cadradoEnXY(int x, int y) {
        for (int contador = 0; contador < getCadradosChan().size(); contador++) {
            if (x == getCadradosChan().get(contador).getX()) {
                if (y == getCadradosChan().get(contador).getY()) {
                    return true;
                }
            }
        }
        return false;
    }

    private void baixarCadrados(int y) {
        for (int contador = 0; contador < cadradosChan.size(); contador++) {
            if (cadradosChan.get(contador).getY() < y) {
                ventana.borrarCadrado(cadradosChan.get(contador).getLblCadrado());
                cadradosChan.get(contador).setY(cadradosChan.get(contador).getY() + LADOCADRADO);
                ventana.pintarCadrado(cadradosChan.get(contador).getLblCadrado());
            }
        }
    }

    private boolean validar(char lado) {
        Iterator<Cadrado> iterCadrados = getFichaActual().getCadrados().iterator();
        while (iterCadrados.hasNext()) {
            Cadrado temporal = iterCadrados.next();
            if (lado == 'e') {
                if (!ePosicionValida(temporal.getX() - LADOCADRADO, temporal.getY())) {
                    return false;
                }
            } else if (lado == 'd') {
                if (!ePosicionValida(temporal.getX() + LADOCADRADO, temporal.getY())) {
                    return false;
                }
            }
        }
        return true;
    }

    private void pintarCadradoXogo(Cadrado engadido) {
        Border borde = BorderFactory.createLineBorder(Color.black);
        engadido.setLblCadrado(new JLabel());
        engadido.getLblCadrado().setBorder(borde);
        engadido.getLblCadrado().setBackground(engadido.getCorRecheo());
        engadido.getLblCadrado().setOpaque(true);
        engadido.getLblCadrado().setSize(LADOCADRADO, LADOCADRADO);
        engadido.getLblCadrado().setLocation(engadido.getX(), engadido.getY());
        cadradosChan.add(engadido);
        ventana.pintarCadrado(engadido.getLblCadrado());
    }
}
