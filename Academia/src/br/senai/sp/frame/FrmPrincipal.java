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
		
		//Criar painel dos botões
		JPanel painelBotoes = new JPanel();
		painelBotoes.setBounds(12, 460, 556, 100);
		TitledBorder bordaBotoes = new TitledBorder("");
		painelBotoes.setBorder(bordaBotoes);
		painelBotoes.setLayout(null);
		
		//Criação dos Botões
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
		btnSair.setToolTipText("Sair da aplicação");
		
		//Adição dos botões
		painelBotoes.add(btnAdicionar);
		painelBotoes.add(btnExcluir);
		painelBotoes.add(btnEditar);
		painelBotoes.add(btnSair);
		
		//Adição dos painéis no painel principal
		getContentPane().add(painelTabela);
		getContentPane().add(painelBotoes);
		
		//OUVINTES DE AÇÃO
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
				
				//Janela de confirmação ao executar a ação
				//Primeiro parâmetro é o pai da mensagem, o segundo é o título do painel, o terceiro é o tipo de opção e o último é o ícone
				//O retorno é um inteiro, que será armazenado na resposta
				int resposta = JOptionPane.showConfirmDialog(null,"Tem certeza que deseja sair?", "Fechar Sistema", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				Object[] options = { "Sim", "Não" };
				//int resposta = JOptionPane.showOptionDialog(null, "Tem certeza que deseja sair?", "Fechar Sistema", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				//O Yes retorna número 0, e No retorna número 1					
				if(resposta == 0) {
					//Método utilizado para encerrar o sistema.
					//O método exit() recebe um inteiro do status do sistema
					//Existem constantes de status do sistema que são associadas a números inteiros, geradas pela classe WindowConstants
					//EXIT_ON_CLOSE, por exemplo, é o número 3
					//É possível passar o número ou a constante
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
		
		//Exibição em tela, puxando os dados do banco
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
		
		//Ordem na criação de tabelas
		//1 - Cria-se um objeto scroll (a barra de rolagem) com JScrollPane
		//2 - Cria-se um modelo de tabela com DefaultTableModel (tabela padrão) que leva como argumento um vetor de cabeçalho e um array de dados
		//3 - Cria-se um objeto tabela com JTable
		//4 - É o Scroll que é adicionado ao painel de visualização criado, então o painel de visualização adiciona o Scroll
		//5 - Toda tabela precisa de um modelo. Então o modelo criado deve ser adicionado ao objeto tabela pelo método setModel(modelo criado)
		//6 - O objeto scroll exibe a tabela, que já possui o modelo inserido, com o método setViewportView(tabela)
		
		//Criar a tabela com a lista de alunos
		scroll.setBounds(10, 30, 540, 380);
				
		//Definir o modelo para a JTable
		DefaultTableModel modelo = new DefaultTableModel(dados, cabecalho);
		
		//Criar a tabela (JTable)
		//tabelaAlunos = new JTable();
		tabelaAlunos.setModel(modelo);
		
		//Determinar a largura das colunas da tabela
		//Primeiro, desabilitar o tamanho atribuído pelo Java de acordo com o número de colunas. Esse valor é atribuido automaticamente, por isso o "auto"
		tabelaAlunos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//Definindo o tamanho desejado por coluna, cada coluna é identificada pelo seu índice no getColumn()
		tabelaAlunos.getColumnModel().getColumn(0).setPreferredWidth(50);
		tabelaAlunos.getColumnModel().getColumn(1).setPreferredWidth(190);
		tabelaAlunos.getColumnModel().getColumn(2).setPreferredWidth(190);
		tabelaAlunos.getColumnModel().getColumn(3).setPreferredWidth(110);
		
		//Impedir edição da tabela
		tabelaAlunos.setDefaultEditor(Object.class, null);
		
		//Impedir drag and drop (arrastar) das colunas da tabela (Movimentação das colunas)
		//As colunas são movimentadas pelo cabeçalho (header)
		//No caso, a reorganização NÃO está permitida pelo false
		tabelaAlunos.getTableHeader().setReorderingAllowed(false);
		
		//Impedir o redimensionamento das colunas da tabela
		tabelaAlunos.getTableHeader().setResizingAllowed(false);
		
		//Colocar a tabela dentro do scroll
		scroll.setViewportView(tabelaAlunos);
		
		//Adição de itens no painel Tabela
		painelTabela.add(scroll);
	}
	
	private void abrirFormularioAluno(String textoDoTitulo, String textoDoBotao, String nomeDaImagem) {
		//Obter o número da matrícula do aluno
		int linha = tabelaAlunos.getSelectedRow();
		int coluna = 0; //Sempre é 0, pois é a coluna da matrícula
		
		//Verificando se a linha foi selecionada. Quando a linha não é selecionada, ela retorna -1, onde é lançada uma mensagem ao usuário
		//Caso o valor da linha seja diferente de -1, o programa prossegue normalmente.
		if(linha == -1) {
			JOptionPane.showMessageDialog(null, "Por favor, selecione um aluno na lista.", "Aluno não selecionado", JOptionPane.ERROR_MESSAGE);
		} else {
			
			int matricula = Integer.parseInt(tabelaAlunos.getValueAt(linha, coluna).toString());
			
			//Obtendo informações do aluno específico no banco de dados
			Aluno aluno = new Aluno();
			AlunoDAO dao = new AlunoDAO();
			aluno = dao.getAluno(matricula);
			
			//Abertura de tela com as informações do aluno
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
			
			//Ao comparar string, usar .equals, pois String é um objeto
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
