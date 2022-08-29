package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.util.Vector;

import materia.Materia;
import materia.MateriaHistorico;

public class TelaEstado extends JFrame implements ActionListener{

  private JLabel texto;
  private JButton menu;
  private TelaInicial inicio;

  private Double porcentAprovacao;
  private int quantidadeReprovacao;
  private Vector<Materia> materiasBarreira;
  private Vector<Vector<MateriaHistorico>> materiasCursadasPorPeriodo;

  private static TelaEstado instancia = null;

  public static synchronized TelaEstado getInstance(){
    if (instancia == null)
      instancia = new TelaEstado();
    return instancia;
  }

  private TelaEstado(){
    texto = new JLabel("Placeholder");
    menu = new JButton("Voltar");
    menu.addActionListener(this);

    this.setLayout(new GridLayout(2,2,10,10));
    this.add(texto);
    this.add(menu);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();
    this.setTitle("Estado das matérias");
    this.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e){
    if (e.getSource() == menu){
      this.setVisible(false);
      inicio = TelaInicial.getInstance();
      inicio.setVisible(true);
    }
  }

  public Double getPorcentAprovacao(){ return porcentAprovacao; }
  public void setPorcentAprovacao(Double porcentAprovacao){ this.porcentAprovacao = porcentAprovacao; }
  public int getQuantidadeReprovacao(){ return quantidadeReprovacao; }
  public void setQuantidadeReprovacao(int quantidadeReprovacao){ this.quantidadeReprovacao = quantidadeReprovacao; }
  public Vector<Materia> getMateriasBarreira(){ return materiasBarreira; }
  public void setMateriasBarreira(Vector<Materia> materiasBarreira){ this.materiasBarreira = materiasBarreira; }
  public Vector<Vector<MateriaHistorico>> getMateriasCursadasPorPeriodo() {
    return materiasCursadasPorPeriodo;
  }
  public void setMateriasCursadasPorPeriodo(Vector<Vector<MateriaHistorico>> materiasCursadasPorPeriodo) {
    this.materiasCursadasPorPeriodo = materiasCursadasPorPeriodo;
  }

  
}
