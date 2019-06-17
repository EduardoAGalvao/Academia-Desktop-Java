package br.senai.sp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexao {
	
	//Inicializando a vari�vel con
	private static Connection con;

	public static Connection getConexao() {
		
		//O m�todo try/catch serve para tratar blocos de c�digo que podem gerar Exce��es, ou seja, Exception Error. 
		try {
			//Obtendo classe Driver que foi baixada. Esse tipo de classe n�o pode ser referenciada simplesmente como import
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Protocolo de comunica��o entre Java e MySQL
			//O in�cio jdbc:mysql://10.107.134.23 � um endere�o para o computador com servidor instalado
			//db_academia_a � o banco de dados, pelo qual queremos acesso
			//?useTimezone=true&serverTimezone=UTC -> valida para a regi�o do Brasil, necess�rio que n�o d� esse tipo de erro
			//useSSL=false evita erros no console
			String dbUrl = "jdbc:mysql://localhost/db_academia?useTimezone=true&serverTimezone=UTC&useSSL=false";
		
			//Usando o Driver do java.sql
			con = DriverManager.getConnection(dbUrl, "root","");
			//JOptionPane.showMessageDialog(null, "Conex�o efetuada com sucesso!");
			
		} catch (ClassNotFoundException | SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Ocorreu um erro na abertura da conex�o!");
			e.printStackTrace();
			//printStackTrace() � um m�todo que poder� ser usado de forma hier�rquica de acordo com os erros que aparecem.
			//M�todo da biblioteca Exceptions do Java, retorna a pilha de erros caso ele seja identificado.
		}
		
		return con;
		
	}
	
	//Fecha a conex�o caso ela esteja aberta (diferente de null)
	//Sempre deve fechar a conex�o ap�s abri-la
	public static void fecharConexao() {
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
