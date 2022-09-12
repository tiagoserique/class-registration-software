package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import view.guiElements.Botao;

public class TelaInicial extends Tela{

  private JPanel botoes;

  private JPanel areaCentral;

  private Botao bEstadoMaterias;

  private Botao bSolicitarMaterias;

  private Botao bImportarMaterias;
  private String importPath;

  private Font fonte;

  private static TelaInicial instancia = null;

  public static synchronized TelaInicial getInstance(){
    if (instancia == null)
      instancia = new TelaInicial();
    return instancia;
  }

  private TelaInicial(){
    this.setLayout(new BorderLayout(10, 10));
    fonte = new Font("Hack", Font.BOLD, 16);

    fazBotoes();
    this.add(botoes, BorderLayout.PAGE_END);

    JLabel titulo = new JLabel("Quebra de Barreiras");
    titulo.setHorizontalAlignment(SwingConstants.CENTER);
    titulo.setFont(fonte);
    this.add(titulo, BorderLayout.PAGE_START);

    fazAreaCentral();
    this.add(areaCentral, BorderLayout.CENTER);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Quebra de barreiras");
    this.setMinimumSize(new Dimension(1200,300));
    this.setVisible(true);
  }

  private void fazAreaCentral(){
    areaCentral = new JPanel(new FlowLayout(FlowLayout.CENTER));

    bImportarMaterias = new Botao("Importar Matérias de um csv", fonte, this);
    bImportarMaterias.setBackground(new Color(0xeeeeee));
    bImportarMaterias.setForeground(new Color(0x44475a));
    bImportarMaterias.setMaximumSize(new Dimension(200, 150));
    bImportarMaterias.setSize(new Dimension(200, 150));

    areaCentral.add(bImportarMaterias);
  }

  private void fazBotoes(){
    bEstadoMaterias = new Botao("Estado das matérias", fonte, this);

    bSolicitarMaterias = new Botao("Solicitar matérias", fonte, this);

    botoes = new JPanel(new GridLayout(1, 2, 10, 10));
    botoes.add(bEstadoMaterias);
    botoes.add(bSolicitarMaterias);
  }

  private void fazerImportacao(){
    JFileChooser arqs = new JFileChooser();
    arqs.setCurrentDirectory(new File("."));
    arqs.setFont(fonte);
    int response = arqs.showOpenDialog(null);

    if(response == JFileChooser.APPROVE_OPTION){
      String path = arqs.getSelectedFile().getAbsolutePath();
      super.setPedido("import");
      this.importPath = path;
      this.updateSub();
      super.setPedido("");
    }
  }

  @Override
  public void actionPerformed(ActionEvent e){
    Object source = e.getSource();
    if(source == bImportarMaterias){
      fazerImportacao();
      return;
    }
    JFrame proxTela;
    if(source == bEstadoMaterias){
      proxTela = TelaEstado.getInstance();
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

  public String getImportPath(){ return this.importPath; }
}
