package view;

import view.guiElements.Botao;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.util.Vector;

import materia.Materia;
import materia.MateriaHistorico;

public class TelaEstado extends JFrame implements ActionListener{

  private JPanel botoes;

  private Botao bMenu;
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

  private Font fonte;

  public static synchronized TelaEstado getInstance(){
    if (instancia == null)
      instancia = new TelaEstado();
    return instancia;
  }

  private TelaEstado(){
    this.setLayout(new BorderLayout(10,10));
    fonte = new Font("Hack", Font.BOLD, 16);

    JLabel titulo = new JLabel("Estado das matérias atuais");
    titulo.setHorizontalAlignment(SwingConstants.CENTER);
    titulo.setFont(fonte);

    JLabel meio = new JLabel("PlaceHolder");
    meio.setHorizontalAlignment(SwingConstants.CENTER);
    meio.setFont(fonte);

    fazBotoes();

    this.add(botoes, BorderLayout.PAGE_END);
    this.add(titulo, BorderLayout.PAGE_START);
    this.add(meio, BorderLayout.CENTER);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();
    this.setTitle("Estado das matérias");
  }

  private void fazBotoes(){
    bMenu = new Botao("Voltar", fonte, this);

    botoes = new JPanel(new GridLayout(1, 2, 10, 10));
    botoes.add(bMenu);
  }

  @Override
  public void actionPerformed(ActionEvent e){
    if (e.getSource() == bMenu){
      inicio = TelaInicial.getInstance();
      inicio.setLocationRelativeTo(this);
      this.setVisible(false);
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
