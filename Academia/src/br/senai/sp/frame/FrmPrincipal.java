package br.senai.sp.frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import br.senai.sp.calculos.Aluno;
import br.senai.sp.dao.AlunoDAO;
import br.senai.sp.utils.Data;

public class FrmPrincipal extends JFrame {
	
	public JTable tabelaAlunos = new JTable();
	public FrmAluno telaCadastro;
	public JPanel painelTabela;
	public JScrollPane scroll = new JScrollPane();
	
	public FrmPrincipal() {
		
		setTitle("Academia Boa Forma");
		setSize(600,625);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null); 
		
		//Criar painel da tabela
		painelTabela = new JPanel();
		painelTabela.setBounds(10, 10, 560, 430);
		TitledBorder bordaTabela = new TitledBorder("Lista de Alunos");
		//OUTRA MANEIRA DE INSERIR BORDA TITULADA -> Border bordaTabela = BorderFactory.createTitledBorder("Lista de Alunos"); 
		painelTabela.setBorder(bordaTabela);
		painelTabela.setLayout(null);
		
		//Criar painel dos bot�es
		JPanel painelBotoes = new JPanel();
		painelBotoes.setBounds(12, 460, 556, 100);
		TitledBorder bordaBotoes = new TitledBorder("");
		painelBotoes.setBorder(bordaBotoes);
		painelBotoes.setLayout(null);
		
		//Cria��o dos Bot�es
		JButton btnAdicionar = new JButton("");
		btnAdicionar.setBounds(10,15,100,70);
		btnAdicionar.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/br/senai/sp/imagens/novo48.png")));
		btnAdicionar.setToolTipText("Adicionar novo aluno");
		
		JButton btnExcluir = new JButton("");
		btnExcluir.setBounds(120,15,100,70);
		btnExcluir.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/br/senai/sp/imagens/excluir48.png")));
		btnExcluir.setToolTipText("Excluir aluno selecionado");
		
		JButton btnEditar= new JButton("");
		btnEditar.setBounds(230,15,100,70);
		btnEditar.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/br/senai/sp/imagens/editar48.png")));
		btnEditar.setToolTipText("Editar registro do aluno selecionado");
		
		JButton btnSair= new JButton("");
		btnSair.setBounds(445,15,100,70);
		btnSair.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/br/senai/sp/imagens/sair48.png")));
		btnSair.setToolTipText("Sair da aplica��o");
		
		//Adi��o dos bot�es
		painelBotoes.add(btnAdicionar);
		painelBotoes.add(btnExcluir);
		painelBotoes.add(btnEditar);
		painelBotoes.add(btnSair);
		
		//Adi��o dos pain�is no painel principal
		getContentPane().add(painelTabela);
		getContentPane().add(painelBotoes);
		
		//OUVINTES DE A��O
		btnAdicionar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				FrmAluno telaCadastro = new FrmAluno();
				telaCadastro.setVisible(true); 
			}
		});
		
		btnEditar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				abrirFormularioAluno("Atualizar Aluno","ATUALIZAR","editar48.png");
			}
		});
		
		btnExcluir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				abrirFormularioAluno("Excluir Aluno","EXCLUIR","excluir48.png");			
				
			}
		});
		
		btnSair.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//Janela de confirma��o ao executar a a��o
				//Primeiro par�metro � o pai da mensagem, o segundo � o t�tulo do painel, o terceiro � o tipo de op��o e o �ltimo � o �cone
				//O retorno � um inteiro, que ser� armazenado na resposta
				int resposta = JOptionPane.showConfirmDialog(null,"Tem certeza que deseja sair?", "Fechar Sistema", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				Object[] options = { "Sim", "N�o" };
				//int resposta = JOptionPane.showOptionDialog(null, "Tem certeza que deseja sair?", "Fechar Sistema", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				//O Yes retorna n�mero 0, e No retorna n�mero 1					
				if(resposta == 0) {
					//M�todo utilizado para encerrar o sistema.
					//O m�todo exit() recebe um inteiro do status do sistema
					//Existem constantes de status do sistema que s�o associadas a n�meros inteiros, geradas pela classe WindowConstants
					//EXIT_ON_CLOSE, por exemplo, � o n�mero 3
					//� poss�vel passar o n�mero ou a constante
					System.exit(EXIT_ON_CLOSE);	
				}			
			}
		});
		
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {}
			
			@Override
			public void windowIconified(WindowEvent e) {}
			
			@Override
			public void windowDeiconified(WindowEvent e) {}
			
			@Override
			public void windowDeactivated(WindowEvent e) {}
			
			@Override
			public void windowClosing(WindowEvent e) {}
			
			@Override
			public void windowClosed(WindowEvent e) {}
			
			@Override
			public void windowActivated(WindowEvent e) {
				criarTabela();
				
			}
		});
		
		setVisible(true);
		
	}

	private void criarTabela() {
		//Criar os vetores matriz para preencher a tabela
		String[] cabecalho = {
				"MAT", 
				"NOME",
				"EMAIL",
				"TELEFONE"
				};
		
		// Conectar com o banco de dados
		AlunoDAO dao = new AlunoDAO();
		ArrayList<Aluno> alunos = dao.listarAlunos();
		
		//Exibi��o em tela, puxando os dados do banco
		int linhas = alunos.size();
		String[][] dados = new String[linhas][4];
		
		int linha = 0;
		
		for(Aluno aluno : alunos) {
			dados[linha][0] = String.valueOf(aluno.getMatricula());
			dados[linha][1] = aluno.getNome();
			dados[linha][2] = aluno.getEmail();
			dados[linha][3] = aluno.getTelefone();
			linha++;
		}
		
		//Ordem na cria��o de tabelas
		//1 - Cria-se um objeto scroll (a barra de rolagem) com JScrollPane
		//2 - Cria-se um modelo de tabela com DefaultTableModel (tabela padr�o) que leva como argumento um vetor de cabe�alho e um array de dados
		//3 - Cria-se um objeto tabela com JTable
		//4 - � o Scroll que � adicionado ao painel de visualiza��o criado, ent�o o painel de visualiza��o adiciona o Scroll
		//5 - Toda tabela precisa de um modelo. Ent�o o modelo criado deve ser adicionado ao objeto tabela pelo m�todo setModel(modelo criado)
		//6 - O objeto scroll exibe a tabela, que j� possui o modelo inserido, com o m�todo setViewportView(tabela)
		
		//Criar a tabela com a lista de alunos
		scroll.setBounds(10, 30, 540, 380);
				
		//Definir o modelo para a JTable
		DefaultTableModel modelo = new DefaultTableModel(dados, cabecalho);
		
		//Criar a tabela (JTable)
		//tabelaAlunos = new JTable();
		tabelaAlunos.setModel(modelo);
		
		//Determinar a largura das colunas da tabela
		//Primeiro, desabilitar o tamanho atribu�do pelo Java de acordo com o n�mero de colunas. Esse valor � atribuido automaticamente, por isso o "auto"
		tabelaAlunos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//Definindo o tamanho desejado por coluna, cada coluna � identificada pelo seu �ndice no getColumn()
		tabelaAlunos.getColumnModel().getColumn(0).setPreferredWidth(50);
		tabelaAlunos.getColumnModel().getColumn(1).setPreferredWidth(190);
		tabelaAlunos.getColumnModel().getColumn(2).setPreferredWidth(190);
		tabelaAlunos.getColumnModel().getColumn(3).setPreferredWidth(110);
		
		//Impedir edi��o da tabela
		tabelaAlunos.setDefaultEditor(Object.class, null);
		
		//Impedir drag and drop (arrastar) das colunas da tabela (Movimenta��o das colunas)
		//As colunas s�o movimentadas pelo cabe�alho (header)
		//No caso, a reorganiza��o N�O est� permitida pelo false
		tabelaAlunos.getTableHeader().setReorderingAllowed(false);
		
		//Impedir o redimensionamento das colunas da tabela
		tabelaAlunos.getTableHeader().setResizingAllowed(false);
		
		//Colocar a tabela dentro do scroll
		scroll.setViewportView(tabelaAlunos);
		
		//Adi��o de itens no painel Tabela
		painelTabela.add(scroll);
	}
	
	private void abrirFormularioAluno(String textoDoTitulo, String textoDoBotao, String nomeDaImagem) {
		//Obter o n�mero da matr�cula do aluno
		int linha = tabelaAlunos.getSelectedRow();
		int coluna = 0; //Sempre � 0, pois � a coluna da matr�cula
		
		//Verificando se a linha foi selecionada. Quando a linha n�o � selecionada, ela retorna -1, onde � lan�ada uma mensagem ao usu�rio
		//Caso o valor da linha seja diferente de -1, o programa prossegue normalmente.
		if(linha == -1) {
			JOptionPane.showMessageDialog(null, "Por favor, selecione um aluno na lista.", "Aluno n�o selecionado", JOptionPane.ERROR_MESSAGE);
		} else {
			
			int matricula = Integer.parseInt(tabelaAlunos.getValueAt(linha, coluna).toString());
			
			//Obtendo informa��es do aluno espec�fico no banco de dados
			Aluno aluno = new Aluno();
			AlunoDAO dao = new AlunoDAO();
			aluno = dao.getAluno(matricula);
			
			//Abertura de tela com as informa��es do aluno
			telaCadastro = new FrmAluno();
			telaCadastro.txtNome.setText(aluno.getNome());
			telaCadastro.txtAltura.setText(String.valueOf(aluno.getAltura()));
			telaCadastro.txtEmail.setText(aluno.getEmail());
			telaCadastro.txtEndereco.setText(aluno.getEndereco());
			telaCadastro.txtDataNascimento.setText(Data.converterParaUsuario(aluno.getDataDeNascimento()));
			telaCadastro.txtTelefone.setText(aluno.getTelefone());
			telaCadastro.txtPeso.setText(String.valueOf(aluno.getPeso()));
			telaCadastro.txtIdade.setText(String.valueOf(Data.calcularIdade(telaCadastro.txtDataNascimento.getText())));
			telaCadastro.btrMasculino.setSelected(aluno.getSexo() == 'M'? true:false);
			telaCadastro.btrFeminino.setSelected(aluno.getSexo() == 'F'? true:false);

			telaCadastro.btGravar.setText(textoDoBotao);
			telaCadastro.lblTitulo.setText(textoDoTitulo);
			telaCadastro.lblMatricula.setText(String.valueOf(aluno.getMatricula()));
			telaCadastro.lblIcone.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/br/senai/sp/imagens/" + nomeDaImagem)));
			
			//Ao comparar string, usar .equals, pois String � um objeto
			if(textoDoBotao.equals("EXCLUIR")) {
				telaCadastro.lblTitulo.setForeground(new Color(255,0,0));
				telaCadastro.lblMatricula.setForeground(new Color(255,0,0));
			} else if(textoDoBotao.equals("ATUALIZAR")) {
				telaCadastro.lblTitulo.setForeground(new Color(255,183,73));
				telaCadastro.lblMatricula.setForeground(new Color(255,183,73));
			}
			
			telaCadastro.calcularBiometria(aluno);
		
			telaCadastro.setVisible(true);
		}
		
		
	}

}
