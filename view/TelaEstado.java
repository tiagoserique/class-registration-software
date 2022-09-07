package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import materia.Materia;
import materia.MateriaHistorico;
import view.guiElements.Botao;
import view.guiElements.Tabela;

public class TelaEstado extends JFrame implements ActionListener{

  // panel para organização de botões
  private JPanel botoes;

  // botao para voltar para tela inicial
  private Botao bMenu;
  private Botao bSolicitarMaterias;

  // tabela de materias cursadas a ser mostrada
  private JPanel materiasCursadasPanel;
  private JLabel materiasCursadasLabel;
  private Tabela materiasCursadasTabela;
  private JScrollPane materiasCursadasSp;
  // ofertadas e ainda nao cursadas, ou nao aprovadas, por periodo
  private Vector<Vector<MateriaHistorico>> materiasCursadas;

  // tabela de materias da barreira a ser mostrada
  private JPanel materiasBarreiraPanel;
  private JLabel materiasBarreiraLabel;
  private Tabela materiasBarreiraTabela;
  private JScrollPane materiasBarreiraSp;
  // lista de materias que faltam para passar a barreira
  private Vector<Materia> materiasBarreira;

  // informações relacionadas a aprovação do ultimo periodo
  private JPanel infoUltPeriodoPanel;
  private JLabel infoUltPeriodoLabel;
  // porcentagem de aprovacao do ultimo periodo
  private Double porcentAprovacao;
  // quantidade de reprovacao do ultimo periodo
  private int quantidadeReprovacaoFalta;

  // quantidade de campos a serem exibidos para cada materia
  private final int numeroCamposC = 5;
  private final int numeroCamposB = 4;


  private static TelaEstado instancia = null;

  private Font fonte;

  // Para ser singleton
  public static synchronized TelaEstado getInstance(){
    if (instancia == null)
      instancia = new TelaEstado();
    return instancia;
  }

  // Cria layout inicial da janela
  private TelaEstado(){
    this.setLayout(new BorderLayout(10,10));
    fonte = new Font("Hack", Font.BOLD, 16);

    JLabel titulo = new JLabel("Estado das matérias atuais", SwingConstants.CENTER);
    titulo.setFont(fonte);

    criaTabelaCursadas();
    criaTabelaBarreira();
    criaInfoAprovacoes();

    fazBotoes();

    this.add(botoes, BorderLayout.PAGE_END);
    this.add(titulo, BorderLayout.PAGE_START);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Estado das matérias");
    this.setMinimumSize(new Dimension(500,300));
  }

  // Cria o botão de voltar e coloca na tela
  private void fazBotoes(){
    bMenu = new Botao("MenuInicial", fonte, this);
    bSolicitarMaterias = new Botao("Solicitar matérias", fonte, this);

    botoes = new JPanel(new GridLayout(1, 2, 10, 10));
    botoes.add(bSolicitarMaterias);
    botoes.add(bMenu);
  }

  private void criaTabelaCursadas(){
    materiasCursadasPanel = new JPanel(new BorderLayout(10,10));

    materiasCursadasLabel = new JLabel("Matérias ja cursadas:", SwingConstants.CENTER);
    materiasCursadasLabel.setFont(fonte);
    materiasCursadasPanel.add(materiasCursadasLabel, BorderLayout.PAGE_START);

    materiasCursadasSp = new JScrollPane(materiasCursadasTabela);
    materiasCursadasSp.setFont(fonte);
    materiasCursadasPanel.add(materiasCursadasSp, BorderLayout.CENTER);

    this.add(materiasCursadasPanel, BorderLayout.WEST);
    updateTabelaCursadas();
  }

  private void criaTabelaBarreira(){
    materiasBarreiraPanel = new JPanel(new BorderLayout(10,10));

    materiasBarreiraLabel = new JLabel("Matérias que faltam para barreira:", SwingConstants.CENTER);
    materiasBarreiraLabel.setFont(fonte);
    materiasBarreiraPanel.add(materiasBarreiraLabel, BorderLayout.PAGE_START);

    materiasBarreiraSp = new JScrollPane(materiasBarreiraTabela);
    materiasBarreiraSp.setFont(fonte);
    materiasBarreiraPanel.add(materiasBarreiraSp, BorderLayout.CENTER);

    this.add(materiasBarreiraPanel, BorderLayout.EAST);
    updateTabelaBarreira();
  }

  // Gera a tabela pra ser mostrada
  private void updateTabelaCursadas(){
    // this.remove(materiasCursadasTabela);
    if(materiasCursadas == null){
      return;
    }
    materiasCursadasPanel.remove(materiasCursadasSp);

    String colunas[] = {"Código", "Nome", "Media Final", "Carga Horária", "Período"};
    String data[][]  = fromMateriaMatrizToStringMatriz(materiasCursadas);
    materiasCursadasTabela = new Tabela(data, colunas, fonte);
    materiasCursadasSp = new JScrollPane(materiasCursadasTabela);
    materiasCursadasSp.setFont(fonte);
    materiasCursadasSp.setBounds(2, 2, 20, 20);

    // materiasCursadasPanel.add(materiasCursadasTabela, BorderLayout.CENTER);
    materiasCursadasPanel.add(materiasCursadasSp, BorderLayout.CENTER);
  }

  // Gera a tabela pra ser mostrada
  private void updateTabelaBarreira(){
    // this.remove(materiasBarreiraTabela);
    if(materiasBarreira == null){
      return;
    }
    materiasBarreiraPanel.remove(materiasBarreiraSp);

    String colunas[] = {"Código", "Nome", "Carga Horária", "Período"};
    String data[][] = new String[materiasBarreira.size()][numeroCamposB];
    for(int i = 0; i < materiasBarreira.size(); i++){
      data[i] = fromMateriaToString(materiasBarreira.get(i));
    }
    materiasBarreiraTabela = new Tabela(data, colunas, fonte);
    materiasBarreiraSp = new JScrollPane(materiasBarreiraTabela);
    materiasBarreiraSp.setFont(fonte);
    materiasBarreiraSp.setBounds(2, 2, 20, 20);

    // materiasBarreiraPanel.add(materiasBarreiraTabela, BorderLayout.CENTER);
    materiasBarreiraPanel.add(materiasBarreiraSp, BorderLayout.CENTER);
  }

  // Transforma materia em vetor de Strings
  private String[] fromMateriaToString(MateriaHistorico mat, int periodo){
    String[] res = new String[numeroCamposC];
    res[0] = mat.getCodCurso();
    res[1] = mat.getNomeAtivCurri();
    res[2] = mat.getMediaFinal();
    res[3] = mat.getChTotal();
    res[4] = Integer.toString(periodo);
    return res;
  }

  private String[] fromMateriaToString(Materia mat){
    String[] res = new String[numeroCamposB];
    res[0] = mat.getCodCurso();
    res[1] = mat.getNomeDisci();
    res[2] = Integer.toString(mat.getChTotal());
    res[3] = Integer.toString(mat.getPeriodo());
    return res;
  }


  // Transforma matriz de matéria em matriz de string
  private String[][] fromMateriaMatrizToStringMatriz(Vector<Vector<MateriaHistorico>> mat){
    // contar quantas materias tem na matriz
    int amountMat = 0;

    for(int i = 0; i < mat.size(); i++)
      amountMat += mat.get(i).size();

    String[][] res = new String[amountMat][numeroCamposC];

    // Marca qual posição deve ser alterada
    int toChange = 0;

    for(int i = 0; i < mat.size(); i++){
      for(int j = 0; j < mat.get(i).size(); j++){
        res[toChange] = fromMateriaToString(mat.get(i).get(j), i+1);
        toChange++;
      }
    }

    return res;
  }

  private void criaInfoAprovacoes(){
    infoUltPeriodoPanel = new JPanel(new BorderLayout(10, 10));
    infoUltPeriodoPanel.setFont(fonte);

    infoUltPeriodoLabel = new JLabel("Informações sobre o último período:", SwingConstants.CENTER);
    infoUltPeriodoLabel.setFont(fonte);

    this.add(infoUltPeriodoPanel, BorderLayout.CENTER);
    updateInfoAprovacoes();
  }

  private void updateInfoAprovacoes(){
    infoUltPeriodoPanel.removeAll();
    infoUltPeriodoPanel.add(infoUltPeriodoLabel, BorderLayout.PAGE_START);

    String s = "<html>Porcentagem de Aprovação: " + porcentAprovacao + "%<br>"
      + "Quantidade de Reprovações por falta: " + quantidadeReprovacaoFalta + "</html";
    JLabel info = new JLabel(s, SwingConstants.CENTER);
    info.setFont(fonte);
    infoUltPeriodoPanel.add(info, BorderLayout.CENTER);
  }

  // Função a ser executada quando aperta botão
  @Override
  public void actionPerformed(ActionEvent e){
    Object source = e.getSource();
    JFrame proxTela;
    if(source == bMenu){
      proxTela = TelaInicial.getInstance();
    } else if(source == bSolicitarMaterias){
      proxTela = TelaSolicitar.getInstance();
    } else {
      return;
    }
    proxTela.setLocationRelativeTo(this);
    proxTela.setBounds(this.getBounds());
    this.setVisible(false);
    proxTela.setVisible(true);
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
    updateInfoAprovacoes();
  }
  public void setQuantidadeReprovacaoFalta(int quantidadeReprovacaoFalta){ 
    this.quantidadeReprovacaoFalta = quantidadeReprovacaoFalta; 
    updateInfoAprovacoes();
  }
  public void setMateriasBarreira(Vector<Materia> materiasBarreira){ 
    this.materiasBarreira = materiasBarreira; 
    updateTabelaBarreira();
  }
  public void setMateriasCursadas(Vector<Vector<MateriaHistorico>> materiasCursadas) {
    this.materiasCursadas = materiasCursadas;
    updateTabelaCursadas();
  }
}
