package br.senai.sp.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import br.senai.sp.calculos.Calculos;
import br.senai.sp.calculos.Aluno;
import br.senai.sp.dao.AlunoDAO;
import br.senai.sp.dao.Conexao;
import br.senai.sp.frame.FrmPrincipal;
import br.senai.sp.frame.FrmAluno;

public class AcademiaApp {

	public static void main(String[] args) {
		
		//Testes de Cálculos
		//Cliente c = new Cliente();
		//Calculos calc = new Calculos();
		//calc.cliente = c;
		
		//Capturando a skin do sistema para montar o projeto (pode ser Windows, Linux, etc)
		try {
			// Set cross-platform Java L&F (also called "Metal")
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exception
		}
		
		//Capturando a skin Java para montar o projeto
		/*try {
            // Set cross-platform Java L&F (also called "Metal")
			 UIManager.setLookAndFeel(
			            UIManager.getCrossPlatformLookAndFeelClassName());
			 
			 //Fonte dos códigos de exibição: https://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		} 
		catch (UnsupportedLookAndFeelException e) {
			// handle exception
		}
		catch (ClassNotFoundException e) {
			// handle exception
		}
		catch (InstantiationException e) {
			// handle exception
		}
		catch (IllegalAccessException e) {
			// handle exception
		}*/
		
		//Internacionalização da aplicação, onde troca-se o idioma dos botões de OptionPanes 
		UIManager.put("OptionPane.yesButtonText", "Sim");
		UIManager.put("OptionPane.noButtonText", "Não");
		UIManager.put("OptionPane.cancelButtonText", "Cancelar");
		
		FrmPrincipal frmTela = new FrmPrincipal();
		
		/************************************************* TESTES *************************************************/
		//FrmAluno tela = new FrmAluno();
		
		//Teste - Validação de Conexão
		//Conexao conexao = new Conexao();
		//conexao.getConexao();
		
		/*Aluno aluno = new Aluno();
		aluno.setNome("Leonardo Fernandez");
		aluno.setEmail("leonardo.fernandez@email.com");
		aluno.setTelefone("(11)1234-5678");
		aluno.setDataDeNascimento("1982-02-18");
		aluno.setPeso(95.56);
		aluno.setAltura(189);
		aluno.setSexo('M');
		aluno.setEndereco("Rua Lourenço Nunes de Souza, 1600 - São Paulo/SP");*/
		
		//Teste de adição - Método gravar() do AlunoDAO
		//AlunoDAO dao = new AlunoDAO(aluno);
		//dao.gravar();
		
		//Teste de remoção - Método exclur() do AlunoDAO
		//AlunoDAO dao = new AlunoDAO();
		//dao.excluir(52);
		
		//Teste de Atualização - Método atualizar() do AlunoDAO
//		Aluno aluno = new Aluno();
//		aluno.setNome("Eduardo Augusto Galvão"); //mantido
//		aluno.setDataDeNascimento("1997-08-02"); //mantido
//		aluno.setAltura(174); //mantido
//		aluno.setEmail("novoemail.parateste@senai.com"); //alterado
//		aluno.setSexo('M'); //mantigo
//		aluno.setEndereco("Rua teste, 256. Itapevi-SP"); //alterado
//		aluno.setMatricula(17); //mantido
//		aluno.setPeso(85.5); //alterado
//		aluno.setTelefone("(11)1111-0666"); //alterado
//		
//		AlunoDAO dao = new AlunoDAO(aluno);
//		dao.atualizar();
		
		//Teste de Exibição a partir de dados do banco de dados - Método getAluno() do AlunoDAO
//		Aluno aluno = new Aluno();
//		AlunoDAO dao = new AlunoDAO();
//		aluno = dao.getAluno(17);
//		
//		System.out.println(aluno.toString());
		
		//Teste de Exibição - Método listarAlunos() do AlunoDAO
		//Aluno aluno = new Aluno();
		//AlunoDAO dao = new AlunoDAO();
		
		//ArrayList<Aluno> alunos = new ArrayList<Aluno>();
		//alunos = dao.listarAlunos();
		
		//for (Aluno a: alunos) {
		//	System.out.println(a.toString());
		//}
		
		//Teste de Exibição - Método listarAlunosPorSexo() do AlunoDAO
		//Aluno aluno = new Aluno();
		//AlunoDAO dao = new AlunoDAO();
				
		//ArrayList<Aluno> alunos = new ArrayList<Aluno>();
		//alunos = dao.listarAlunosPorSexo('F');
				
		//for (Aluno a: alunos) {
			//System.out.println(a.toString());
		//}
		
		
	}

}
