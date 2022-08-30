package view.guiElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Botao extends JButton{

  public Botao(String texto, Font fonte, ActionListener listenTo){
    super(texto);
    this.addActionListener(listenTo);
    this.setFont(fonte);
    this.setFocusable(false);
    this.setBackground(new Color(0x44475a));
    this.setForeground(new Color(0xf8f8f2));
  }
}
