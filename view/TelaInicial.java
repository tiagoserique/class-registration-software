package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class TelaInicial extends JFrame implements ActionListener{

  private JButton bEstadoMaterias;
  private TelaEstado estadoMaterias;

  private JButton bSolicitarMaterias;
  private TelaSolicitar solicitarMaterias;
  private static TelaInicial instancia = null;

  public static synchronized TelaInicial getInstance(){
    if (instancia == null)
      instancia = new TelaInicial();
    return instancia;
  }

  private TelaInicial(){
    bEstadoMaterias = new JButton("Estado das matérias");
    bEstadoMaterias.addActionListener(this);

    bSolicitarMaterias = new JButton("Solicitar matérias");
    bSolicitarMaterias.addActionListener(this);

    this.setLayout(new GridLayout(2,2,10,10));
    this.add(bEstadoMaterias);
    this.add(bSolicitarMaterias);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Quebra de barreiras");
    this.pack();
    this.setVisible(true);
  }

  public static void main(String[] args){
    TelaInicial.getInstance();
    return;
  }

  @Override
  public void actionPerformed(ActionEvent e){
    Object source = e.getSource();
    this.setVisible(false);
    if(source == bEstadoMaterias){
      estadoMaterias = TelaEstado.getInstance();
      estadoMaterias.setVisible(true);
    } else if(source == bSolicitarMaterias){
      solicitarMaterias = TelaSolicitar.getInstance();
      solicitarMaterias.setVisible(true);
    }
  }
}
