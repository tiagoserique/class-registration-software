package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.util.Vector;

import materia.Materia;
import model.MateriaHistorico;

public class TelaEstado extends JFrame implements ActionListener{

  private JLabel texto;
  private JButton menu;
  private TelaInicial inicio;

  
  // porcentagem de aprovacao do ultimo periodo
  private Double porcentAprovacao;
  // quantidade de reprovacao do ultimo periodo
  private int quantidadeReprovacaoFalta;
  // lista de materias que faltam para passar a barreira
  private Vector<Materia> materiasBarreira;
  // ofertadas e ainda nao cursadas, ou nao aprovadas, por periodo
  private Vector<Vector<MateriaHistorico>> materiasCursadas;


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


  public Double getPorcentAprovacao(){ 
    return porcentAprovacao; 
  }
  public int getQuantidadeReprovacaoFalta(){ 
    return quantidadeReprovacaoFalta; 
  }
  public Vector<Materia> getMateriasBarreira(){ 
    return materiasBarreira; 
  }
  public Vector<Vector<MateriaHistorico>> getMateriasCursadas() {
    return materiasCursadas;
  }
  
  public void setPorcentAprovacao(Double porcentAprovacao){
    this.porcentAprovacao = porcentAprovacao; 
  }
  public void setQuantidadeReprovacaoFalta(int quantidadeReprovacaoFalta){ 
    this.quantidadeReprovacaoFalta = quantidadeReprovacaoFalta; 
  }
  public void setMateriasBarreira(Vector<Materia> materiasBarreira){ 
    this.materiasBarreira = materiasBarreira; 
  }
  public void setMateriasCursadas(Vector<Vector<MateriaHistorico>> materiasCursadas) {
    this.materiasCursadas = materiasCursadas;
  }
}
