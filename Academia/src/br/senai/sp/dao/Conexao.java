package br.senai.sp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexao {
	
	//Inicializando a variável con
	private static Connection con;

	public static Connection getConexao() {
		
		//O método try/catch serve para tratar blocos de código que podem gerar Exceções, ou seja, Exception Error. 
		try {
			//Obtendo classe Driver que foi baixada. Esse tipo de classe não pode ser referenciada simplesmente como import
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Protocolo de comunicação entre Java e MySQL
			//O início jdbc:mysql://10.107.134.23 é um endereço para o computador com servidor instalado
			//db_academia_a é o banco de dados, pelo qual queremos acesso
			//?useTimezone=true&serverTimezone=UTC -> valida para a região do Brasil, necessário que não dê esse tipo de erro
			//useSSL=false evita erros no console
			String dbUrl = "jdbc:mysql://localhost/db_academia?useTimezone=true&serverTimezone=UTC&useSSL=false";
		
			//Usando o Driver do java.sql
			con = DriverManager.getConnection(dbUrl, "root","");
			//JOptionPane.showMessageDialog(null, "Conexão efetuada com sucesso!");
			
		} catch (ClassNotFoundException | SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Ocorreu um erro na abertura da conexão!");
			e.printStackTrace();
			//printStackTrace() é um método que poderá ser usado de forma hierárquica de acordo com os erros que aparecem.
			//Método da biblioteca Exceptions do Java, retorna a pilha de erros caso ele seja identificado.
		}
		
		return con;
		
	}
	
	//Fecha a conexão caso ela esteja aberta (diferente de null)
	//Sempre deve fechar a conexão após abri-la
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
