package br.senai.sp.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import br.senai.sp.calculos.Calculos;
import br.senai.sp.dao.AlunoDAO;
import br.senai.sp.utils.Data;
import br.senai.sp.calculos.Aluno;

//import br.senai.sp.calculos.Calculos;

public class FrmAluno extends JDialog{
	
	//Declaração de atributos públicos da classe FrmAluno
	//O objetivo dessa declaração é o acesso desses elementos por outras classes
	public JTextField txtNome;
	public JTextField txtIdade;
	public JTextField txtPeso;
	public JTextField txtAltura;
	public JTextField txtTelefone;
	public JTextField txtEmail;
	public JTextArea txtEndereco;
	public JTextField txtDataNascimento;
	
	public JButton btGravar;
	
	public JRadioButton btrMasculino;
	public JRadioButton btrFeminino;
	
	public JLabel lblTitulo;
	public JLabel lblRespostaImc;
	public JLabel lblRespostaTmb;
	public JLabel lblRespostaFcm;
	public JLabel lblSituacaoPeso;
	public JLabel lblIcone;
	public JLabel lblMatricula;
	
	public Color azul;
	public Color amarelo;
	public Color branco;
	public Color verde;
	public Color vermelho;
	public Color cinza;
	public Color cinzaClaro;
	public Color preto;
	
	public JPanel painelResposta;
	public JPanel painelImc;
	
	//Bordas
	public Border bordaPreta;
	public Border bordaVerde;
	public Border bordaVermelha;
	public Border bordaAmarela;
	
	
	//Declaração de classe construtora com métodos da JFrame
		public FrmAluno() {
			this.setSize(455, 630); //Tamanho da tela, width and height
			this.setResizable(false); //Comando que permite ou não ser redimensionado pelo usuário
			this.setTitle("Cadastro de Alunos"); //Título para aparecer na barra de título
			this.setLayout(null); //Com o null, existe a liberação de controle padrão, fica tudo por parte do desenvolvedor
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); //Comando utilizado para fechar a aplicação //ao fecha-la definitivamente 
			this.setLocationRelativeTo(null); //Comando para aparecer no centro da tela
			
			// ** Declarando os componentes
			JLabel lblNome = new JLabel("Nome:");
			JLabel lblIdade = new JLabel("Idade:");
			JLabel lblSexo = new JLabel("Sexo:");
			JLabel lblPeso = new JLabel("Peso:");
			JLabel lblAltura = new JLabel("Altura:");
			JLabel lblImc = new JLabel("IMC:");
			JLabel lblTmb = new JLabel("TMB:");
			JLabel lblFcm = new JLabel("FCM:");
			JLabel lblKg = new JLabel("kg (Ex.: 00.00)");
			JLabel lblCm = new JLabel("cm");
			JLabel lblTelefone = new JLabel("Telefone:");
			JLabel lblEmail = new JLabel("E-mail:");
			JLabel lblEndereco = new JLabel("Endereço:");
			JLabel lblDataNascimento = new JLabel("Data Nasc.:");
			JLabel lblDescricaoImc = new JLabel("IMC - Índice de Massa Corporal");
			JLabel lblDescricaoTmb = new JLabel("TMB - Taxa Metabólica Basal");
			JLabel lblDescricaoFcm = new JLabel("FCM - Frequência Cardíaca Máxima");
			
			lblRespostaImc = new JLabel(" ");
			lblRespostaTmb = new JLabel(" ");
			lblRespostaFcm = new JLabel(" ");
			lblSituacaoPeso = new JLabel(" ");
			lblTitulo = new JLabel("Cadastro de Alunos");
			lblMatricula = new JLabel(" ");
			
			txtNome = new JTextField();
			txtIdade = new JTextField();
			txtPeso = new JTextField();
			txtAltura = new JTextField();
			txtTelefone = new JTextField();
			txtEmail = new JTextField();
			txtEndereco = new JTextArea();
			txtDataNascimento = new JTextField();
			
			btGravar = new JButton("GRAVAR");
			btGravar.setIcon(new ImageIcon(FrmAluno.class.getResource("/br/senai/sp/imagens/salvar48.png")));
			btGravar.setToolTipText("Cadastrar novo aluno");
			
			ButtonGroup buttonSexo = new ButtonGroup();
			btrMasculino = new JRadioButton("M");
			btrFeminino = new JRadioButton("F");
			buttonSexo.add(btrMasculino);
			buttonSexo.add(btrFeminino);
			
			JPanel painelTitulo = new JPanel();
			JPanel painelPrincipal = new JPanel();
			JPanel painelDadosPessoais = new JPanel();
			JPanel painelComunicacao = new JPanel();
			JPanel painelMedidas = new JPanel();
			painelResposta = new JPanel();
			painelImc = new JPanel();
			
			azul = new Color(0,21,109);
			amarelo = new Color(255,240,45);
			branco = new Color(255,255,255);
			verde = new Color(10,255,23);
			vermelho = new Color(255,10,0);
			cinza = new Color(224, 222, 222);
			cinzaClaro = new Color(237,234,234);
			preto = new Color(0,0,0);
			
			Font fonteTitulo = new Font("Roboto", Font.BOLD, 26);
			
			// ** Posicionamento de componentes - Telas e títulos
			
			//Bordas
			bordaPreta= BorderFactory.createLineBorder(preto);
			bordaVerde= BorderFactory.createLineBorder(verde);
			bordaVermelha= BorderFactory.createLineBorder(vermelho);
			bordaAmarela= BorderFactory.createLineBorder(amarelo);
			
			//Painel Título
			painelTitulo.setBounds(0, 0, 455, 80);
			painelTitulo.setLayout(null);
			painelTitulo.setBackground(cinzaClaro);
			
			lblIcone = new JLabel();
			lblIcone.setIcon(new ImageIcon(FrmAluno.class.getResource("/br/senai/sp/imagens/adicionar64.png")));
			painelTitulo.add(lblIcone);
			lblIcone.setBounds(10, 10, 64, 64);
			
			lblTitulo.setBounds(90, 10, 300, 50);
			lblTitulo.setFont(fonteTitulo);
			lblTitulo.setForeground(azul);
			
			lblMatricula.setBounds(400, 20, 100, 30);
			lblMatricula.setFont(fonteTitulo);
			
			TitledBorder bordaPainelTitulo = new TitledBorder(bordaPreta, "");
			painelTitulo.setBorder(bordaPainelTitulo);
			
			//Painel Principal
			painelPrincipal.setBounds(0, 80, 455, 630);
			painelPrincipal.setLayout(null);
			painelPrincipal.setBackground(cinzaClaro);
			
			TitledBorder bordaPainelPrincipal = new TitledBorder(bordaPreta, "");
			painelPrincipal.setBorder(bordaPainelPrincipal);
			
			//Painel Dados Pessoais
			painelDadosPessoais.setBounds(10, 10, 420, 100);
			painelDadosPessoais.setLayout(null);
			painelDadosPessoais.setBackground(cinza);
			
			TitledBorder bordaPainelDadosPessoais = new TitledBorder(bordaPreta, "Dados pessoais:");
			painelDadosPessoais.setBorder(bordaPainelDadosPessoais);
			
			//Painel Comunicação
			painelComunicacao.setBounds(10, 120, 420, 160);
			painelComunicacao.setLayout(null);
			painelComunicacao.setBackground(cinza);
			
			TitledBorder bordaPainelComunicao = new TitledBorder(bordaPreta, "Comunicação:");
			painelComunicacao.setBorder(bordaPainelComunicao);
			
			//Painel Medidas
			painelMedidas.setBounds(10, 290, 205, 150);
			painelMedidas.setLayout(null);
			painelMedidas.setBackground(cinza);
			
			TitledBorder bordaPainelMedidas = new TitledBorder(bordaPreta, "Medidas:");
			painelMedidas.setBorder(bordaPainelMedidas);
			
			//Painel Resposta
			painelResposta.setBounds(225, 290, 205, 150);
			painelResposta.setLayout(null);
			painelResposta.setBackground(cinza);			
			
			TitledBorder bordaPainelResposta = new TitledBorder(bordaPreta, "Resultados:");
			painelResposta.setBorder(bordaPainelResposta);
			
			// ** Adicionar os componentes no painel Título
			painelTitulo.add(lblTitulo);
			painelTitulo.add(lblMatricula);
			
			// ** Adicionando componentes no painel Dados Pessoais
			painelDadosPessoais.add(lblNome);
			painelDadosPessoais.add(lblIdade);
			painelDadosPessoais.add(lblSexo);
			painelDadosPessoais.add(lblDataNascimento);
			
			painelDadosPessoais.add(txtNome);
			painelDadosPessoais.add(txtIdade);
			painelDadosPessoais.add(txtDataNascimento);
			
			painelDadosPessoais.add(btrMasculino);
			painelDadosPessoais.add(btrFeminino);
			btrMasculino.setBackground(cinza);
			btrFeminino.setBackground(cinza);
			
			// ** Adicionando componentes no painel Comunicação
			painelComunicacao.add(lblTelefone);
			painelComunicacao.add(lblEmail);
			painelComunicacao.add(lblEndereco);
			
			painelComunicacao.add(txtTelefone);
			painelComunicacao.add(txtEmail);
			
			// ** Adicionando componentes no painel Medidas
			painelMedidas.add(lblPeso);
			painelMedidas.add(lblAltura);
			painelMedidas.add(lblKg);
			painelMedidas.add(lblCm);
			
			painelMedidas.add(txtPeso);
			painelMedidas.add(txtAltura);
			
			// ** Adicionar os componentes no painel principal
			painelPrincipal.add(painelDadosPessoais);
			painelPrincipal.add(painelComunicacao);
			painelPrincipal.add(painelMedidas);
			painelPrincipal.add(painelResposta);
			
			painelPrincipal.add(lblDescricaoImc);
			painelPrincipal.add(lblDescricaoTmb);
			painelPrincipal.add(lblDescricaoFcm);
			
			painelPrincipal.add(btGravar);

			// ** Adicionar os componentes no painel resposta
			painelResposta.add(lblTmb);
			painelResposta.add(lblFcm);
			painelResposta.add(lblRespostaTmb);
			painelResposta.add(lblRespostaFcm);
						
			painelResposta.add(painelImc);
			
			// ** Adicionar os componentes no painel IMC
			painelImc.add(lblImc);
			painelImc.add(lblSituacaoPeso);
			painelImc.add(lblRespostaImc);
			
			// ** Posicionar os componentes no painel Dados Pessoais
			lblNome.setBounds(10, 30, 40, 20);
			lblDataNascimento.setBounds(190, 30, 80, 20);
			lblIdade.setBounds(280, 30, 50, 20);
			lblSexo.setBounds(340, 30, 50, 20);
			
			txtNome.setBounds(10, 55, 170, 25);
			txtDataNascimento.setBounds(190, 55, 80, 25);
			txtIdade.setBounds(280, 55, 50, 25);
			btrFeminino.setBounds(340, 55, 40, 25);
			btrMasculino.setBounds(375, 55, 40, 25);
			
			//Campo idade será somente de leitura
			txtIdade.setEditable(false);
			
			//Alinhamento de caixas
			txtIdade.setHorizontalAlignment(JTextField.CENTER);
			txtDataNascimento.setHorizontalAlignment(JTextField.CENTER);
			
			// ** Posicionar os componentes no painel Comunicação
			lblEndereco.setBounds(10, 30, 100, 20);
			lblEmail.setBounds(240, 30, 100, 20);
			lblTelefone.setBounds(240, 85, 100, 20);
			
			txtEndereco.setBackground(branco);
			txtEndereco.setBorder(bordaPainelTitulo);
			txtEndereco.setLineWrap(true);
			
			JScrollPane scrollEndereco = new JScrollPane();
			scrollEndereco.setBounds(10, 55, 220, 80);
			scrollEndereco.setViewportView(txtEndereco);
			painelComunicacao.add(scrollEndereco);
			
			txtTelefone.setBounds(240, 110, 170, 25);
			txtEmail.setBounds(240, 55, 170, 25);
			
			// ** Posicionar os componentes no painel Medidas
			lblPeso.setBounds(10, 30, 100, 20);
			lblAltura.setBounds(10, 90, 100, 20);
			lblKg.setBounds(95, 55, 110, 20);
			lblCm.setBounds(95, 115, 80, 20);
			
			txtPeso.setBounds(10, 55, 80, 25);
			txtAltura.setBounds(10, 115, 80, 25);
			
			// ** Posicionar os componentes no painel Principal
			btGravar.setBounds(280, 450, 150, 45);
			
			lblDescricaoImc.setBounds(10, 440, 300, 30);
			lblDescricaoTmb.setBounds(10, 460, 300, 30);
			lblDescricaoFcm.setBounds(10, 480, 300, 30);
			
			// ** Posicionar os componentes no painel resposta
			painelImc.setBounds(10, 20, 180, 50);
			painelImc.setLayout(null);
			painelImc.setBackground(cinza);
			
			lblTmb.setBounds(10, 75, 100, 20);
			lblFcm.setBounds(10, 115, 100, 20);
			
			lblRespostaTmb.setBounds(10, 90, 100, 20);
			lblRespostaFcm.setBounds(10, 130, 100, 20);
			
			// ** Posicionar os componentes no painel IMC
			lblImc.setBounds(0, 0, 170, 20);
			lblRespostaImc.setBounds(0, 15, 100, 20);
			lblSituacaoPeso.setBounds(0, 30, 200, 20);
			
			// ** Inserir os painéis no Frame e tornar visível
			this.getContentPane().add(painelTitulo);
			this.getContentPane().add(painelPrincipal);
			
			// ** Ouvinte de evento
			btGravar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if(btGravar.getText().equals("GRAVAR")) {
						gravar();
					} else if(btGravar.getText().equals("EXCLUIR")){
						excluir(); 
					} else if(btGravar.getText().equals("ATUALIZAR")){
						atualizar();
					}
					
				};
			});
			
			//Ouvinte de foco - Quando o cursor sair do campo de data de nascimento, calcular a idade
			txtDataNascimento.addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent e) {
					txtIdade.setText(String.valueOf(Data.calcularIdade(txtDataNascimento.getText())));
				}
				
				@Override
				public void focusGained(FocusEvent e) {
					
				}
			});
			
			//Tornando a tela de cadastro Modal
			//Tornando o principal foco da tela, bloqueando a anterior
			this.setModal(true);
			
		}
		
		//Método para excluir um aluno
		private void excluir() {
			
			int resposta = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o aluno(a) " + txtNome.getText() + " ?", "Excluir aluno", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if(resposta == 0) {
				AlunoDAO dao = new AlunoDAO();
				dao.excluir(Integer.parseInt(lblMatricula.getText()));
				this.dispose();
			}
		
		}
		
		//Método para atualizar um aluno
		private void atualizar() {
			
			AlunoDAO dao = new AlunoDAO(criarAluno());
			dao.atualizar();
			JOptionPane.showMessageDialog(null,"Aluno atualizado com sucesso!","Atualização de Aluno", JOptionPane.INFORMATION_MESSAGE); 
			
		}

		//Método para gravar um aluno
		public void gravar() {

			//Salvando no banco de dados
			AlunoDAO dao = new AlunoDAO(criarAluno());
			
			dao.gravar();
				
			JOptionPane.showMessageDialog(null,"Aluno Gravado com Sucesso!","Novo Aluno", JOptionPane.INFORMATION_MESSAGE);
				
			//Impede que o usuário salve mais de uma vez
			btGravar.setEnabled(false);
		}

		private Aluno criarAluno() {
			Aluno aluno = new Aluno();
			
			//Seta a matrícula somente se já for um aluno existente
			if(!btGravar.getText().equals("GRAVAR")) {
				aluno.setMatricula(Integer.parseInt(lblMatricula.getText()));
			}
			
			String nome = txtNome.getText();
			String idadeString = txtIdade.getText();
			String alturaString = txtAltura.getText();
			String pesoString = txtPeso.getText();
			char sexo = ' ';
			
			//Setando valores
			aluno.setNome(nome);
			aluno.idade = Integer.parseInt((txtIdade.getText()));
			aluno.setAltura(Integer.parseInt(alturaString));
			aluno.setPeso(Double.parseDouble(pesoString));
			aluno.setEndereco(txtEndereco.getText());
			aluno.setTelefone(txtTelefone.getText());
			aluno.setEmail(txtEmail.getText());
			aluno.setDataDeNascimento(Data.converterParaBanco(txtDataNascimento.getText()));
			
			//Condicional de sexo
			if (btrMasculino.isSelected()) {
                String sexoTexto = (btrMasculino.getText());
                sexo = sexoTexto.charAt(0);
                aluno.setSexo(sexo);
            } else if (btrFeminino.isSelected()) {
                String sexoTexto = (btrFeminino.getText());
                sexo = sexoTexto.charAt(0);
                aluno.setSexo(sexo);
            } else {
            	JOptionPane.showMessageDialog(null, "Por gentileza, selecione um sexo.");
            }

			calcularBiometria(aluno);
			
			return aluno;
			
			/**********************************TESTES DE VALIDAÇÃO CONDICIONAL COM REGEX**********************************/
			//Condicional - Validação de Nome
			//if (nome.matches("^[A-Za-zÀ-ÿ ]+$")) {
				//aluno.setNome(nome);
				
				//Condicional - Validação de Idade
				//if (idadeString.matches("^[0-9][0-9]?$")) {
//					int idade = Integer.parseInt((txtIdade.getText()));
//					aluno.idade = idade;
					
					//Condicional - Validação de Altura
					//if (alturaString.matches("^[0-9][0-9][0-9]?$")) {
//						int altura = Integer.parseInt(alturaString);
//						aluno.setAltura(altura);
						
						//Condicional - Validação de Peso
						//if (pesoString.matches("^[0-9][0-9][0-9]?[.][0-9][0-9][0-9]?$")) {
//							double peso = Double.parseDouble(pesoString);
//							aluno.setPeso(peso);
							
//							//Condicional de sexo
//							if (btrMasculino.isSelected()) {
//				                String sexoTexto = (btrMasculino.getText());
//				                sexo = sexoTexto.charAt(0);
//				                aluno.setSexo(sexo);
//				            } else if (btrFeminino.isSelected()) {
//				                String sexoTexto = (btrFeminino.getText());
//				                sexo = sexoTexto.charAt(0);
//				                aluno.setSexo(sexo);
//				            } else {
//				            	JOptionPane.showMessageDialog(null, "Por gentileza, selecione um sexo.");
//				            }
							
							//Coletando os outros campos
//							aluno.setEndereco(txtEndereco.getText());
//							aluno.setTelefone(txtTelefone.getText());
//							aluno.setEmail(txtEmail.getText());
							
							//Coleta a data de nascimento, trata para enviar ao banco e seta com a data retornada
//							aluno.setDataDeNascimento(Data.converterParaBanco(txtDataNascimento.getText()));
							
							//Chamada do método calcularBiometria()
//							calcularBiometria(aluno);
							
//						} else {
//							JOptionPane.showMessageDialog(null, "Por gentileza, digite um peso válido.");
//						}
//						
//					} else {
//						JOptionPane.showMessageDialog(null, "Por gentileza, digite uma altura válida.");
//					}
//				} else {
//					JOptionPane.showMessageDialog(null, "Por gentileza, digite uma idade válida.");
//				}
//				
//			} else {
//				JOptionPane.showMessageDialog(null, "Por gentileza, digite um nome válido.");
//			}
			
		}
		
		//O método calcularBiometria faz os cálculos de IMC, FCM e TMB
		public void calcularBiometria(Aluno aluno) {
			Calculos calcular = new Calculos();
			calcular.aluno = aluno;
			
			double resultadoImc = calcular.calcularImc();
			String resultadoTmb = String.format("%.2f", calcular.calcularTmb());
			String resultadoFcm = String.valueOf(calcular.calcularFcm());
			double imc = resultadoImc;
			String resultadoSituacao = calcular.situacaoPeso(imc);
								
			//Exibição de Resultados
			lblRespostaImc.setText(String.format("%.2f", resultadoImc));
			lblRespostaTmb.setText(resultadoTmb);
			lblRespostaFcm.setText(resultadoFcm);
			lblSituacaoPeso.setText(resultadoSituacao);
			
			//Condicional de exibição de cores em painelImc
			if (resultadoSituacao == "Abaixo do peso" || resultadoSituacao == "Acima do Peso") {
				painelImc.setBackground(amarelo);	
				TitledBorder bordaPainelResposta = new TitledBorder(bordaAmarela, "Resultados:");
				painelResposta.setBorder(bordaPainelResposta);
			} else if (resultadoSituacao == "Peso normal") {
				painelImc.setBackground(verde);
				TitledBorder bordaPainelResposta = new TitledBorder(bordaVerde, "Resultados:");
				painelResposta.setBorder(bordaPainelResposta);
			} else {
				painelImc.setBackground(vermelho);
				TitledBorder bordaPainelResposta = new TitledBorder(bordaVermelha, "Resultados:");
				painelResposta.setBorder(bordaPainelResposta);
			}
		}
		
}
