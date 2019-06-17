package br.senai.sp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.senai.sp.calculos.Aluno;

public class AlunoDAO {

	private Aluno aluno;

	public AlunoDAO(Aluno aluno) {
		this.aluno = aluno;
	}

	public AlunoDAO() {
	}

	// CREATE
	public void gravar() {
		String sql = "INSERT INTO tbl_aluno " + "(nome, telefone, email, " + "data_nascimento, sexo, "
				+ "peso, altura, endereco) " + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

		// Cada número do método setString() representa um ponto de interrogação nos
		// VALUES, começando pelo 1
		try {
			PreparedStatement stm = Conexao.getConexao().prepareStatement(sql);
			stm.setString(1, aluno.getNome());
			stm.setString(2, aluno.getTelefone());
			stm.setString(3, aluno.getEmail());
			stm.setString(4, aluno.getDataDeNascimento());
			stm.setString(5, String.valueOf(aluno.getSexo()));
			stm.setDouble(6, aluno.getPeso());
			stm.setInt(7, aluno.getAltura());
			stm.setString(8, aluno.getEndereco());

			// Executar o comando no banco
			stm.execute();
			Conexao.fecharConexao();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// READ
	public Aluno getAluno(int matricula) {
		Aluno aluno = new Aluno();

		String sql = "SELECT * FROM tbl_aluno WHERE matricula = ?";

		try {
			PreparedStatement stm = Conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, matricula);
			
			//A classe ResultSet armazenará todas as informações que virão do banco
			ResultSet rs;
			
			//O método executeQuery retorna um ResultSet com todas as informações a partir do que foi solicitado no stm
			rs = stm.executeQuery();

			// Necessário para acertar o cursor no Result Set.
			// Quando o result set é obtido, o ponteiro inicia na primeira linha, o next()
			// vai indica-lo para a próxima linha, puxando resultados ao invés do nome da
			// coluna.
			rs.next();

			aluno.setMatricula(rs.getInt("matricula"));
			aluno.setNome(rs.getString("nome"));
			aluno.setTelefone(rs.getString("telefone"));
			aluno.setEmail(rs.getString("email"));
			aluno.setDataDeNascimento(rs.getString("data_nascimento"));
			aluno.setPeso(rs.getDouble("peso"));
			aluno.setAltura(rs.getInt("altura"));
			aluno.setEndereco(rs.getString("endereco"));
			aluno.setSexo(rs.getString("sexo").charAt(0));
			
			Conexao.fecharConexao();

			// Teste para tentar obter o dado da coluna "nome" do objeto, 2 é o número da
			// coluna nome
			// Existem dois métodos, por número de índice (NÃO USAR, POIS O BANCO MUDA
			// CONSTANTEMENTE, E OS NÚMEROS TAMBÉM), ou por nome de coluna (RECOMENDADO)
			// System.out.println(rs.getString(2));
			// System.out.println(rs.getString("nome"));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return aluno;
	}

	public ArrayList<Aluno> listarAlunos() {
		
		//ArrayList é uma matriz de objetos, no caso, o tipo desses objetos é Aluno, mas poderia ser Integer, Double, etc.
		ArrayList<Aluno> alunos = new ArrayList<Aluno>();

		Aluno aluno;

		String sql = "SELECT * FROM tbl_aluno ORDER BY nome";

		try {
			PreparedStatement stm = Conexao.getConexao().prepareStatement(sql);

			ResultSet rs;

			rs = stm.executeQuery();
			
			while(rs.next()) {
				aluno = new Aluno();
				
				aluno.setMatricula(rs.getInt("matricula"));
				aluno.setNome(rs.getString("nome"));
				aluno.setTelefone(rs.getString("telefone"));
				aluno.setEmail(rs.getString("email"));
				aluno.setDataDeNascimento(rs.getString("data_nascimento"));
				aluno.setPeso(rs.getDouble("peso"));
				aluno.setAltura(rs.getInt("altura"));
				aluno.setEndereco(rs.getString("endereco"));
				aluno.setSexo(rs.getString("sexo").charAt(0));
				
				//Adicionando novo aluno criado ao ArrayList alunos
				alunos.add(aluno);
			}

			// Teste para tentar obter o dado da coluna "nome" do objeto, 2 é o número da
			// coluna nome
			// Existem dois métodos, por número de índice (NÃO USAR, POIS O BANCO MUDA
			// CONSTANTEMENTE, E OS NÚMEROS TAMBÉM), ou por nome de coluna (RECOMENDADO)
			// System.out.println(rs.getString(2));
			// System.out.println(rs.getString("nome"));
			
			//Ao final da execução, retorna o ArrayList
			Conexao.fecharConexao();
			return alunos;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public ArrayList<Aluno> listarAlunosPorSexo(char sexo) {
		
		//Criação de um ArrayList() onde os objetos Aluno serão armazenados
		ArrayList<Aluno> alunos = new ArrayList<Aluno>();

		Aluno aluno;

		String sql = "SELECT * FROM tbl_aluno WHERE sexo = ?";

		try {
			PreparedStatement stm = Conexao.getConexao().prepareStatement(sql);
			stm.setString(1, String.valueOf(sexo));

			ResultSet rs;
			
			//Diferente do execute(), que retorna um resultado booleano, o executeQuery() retorna um ResultSet com os dados do banco
			rs = stm.executeQuery();
			
			//Enquanto o next() tiver linhas do ResultSet para ler, retornará true e adicionará os alunos
			while(rs.next()) {
				aluno = new Aluno();
				
				//getInt(), getString() e getDouble() são métodos usados para coletar dados do ResultSet
				aluno.setMatricula(rs.getInt("matricula"));
				aluno.setNome(rs.getString("nome"));
				aluno.setTelefone(rs.getString("telefone"));
				aluno.setEmail(rs.getString("email"));
				aluno.setDataDeNascimento(rs.getString("data_nascimento"));
				aluno.setPeso(rs.getDouble("peso"));
				aluno.setAltura(rs.getInt("altura"));
				aluno.setEndereco(rs.getString("endereco"));
				aluno.setSexo(rs.getString("sexo").charAt(0));
				
				//Adicionando novo aluno criado ao ArrayList alunos
				alunos.add(aluno);
			}
			
			Conexao.fecharConexao();
			return alunos;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	

	}

	// UPDATE
	public void atualizar() {
		String sql = "UPDATE tbl_aluno " + "SET nome = ?, telefone = ?, " + "email = ?, data_nascimento = ?, "
				+ "sexo = ?, peso = ?, altura = ?, " + "endereco = ? WHERE matricula = ?";

		// A linha abaixo foi utilizada apenas quando o método getConexao() não era
		// estático
		// Conexao con = new Conexao();

		// Quando existem métodos estáticos em uma classe, ela não precisa ser
		// instanciada para seu uso.
		// Então é possível puxar o método diretamente da classe
		try {
			PreparedStatement stm = Conexao.getConexao().prepareStatement(sql);
			stm.setString(1, aluno.getNome());
			stm.setString(2, aluno.getTelefone());
			stm.setString(3, aluno.getEmail());
			stm.setString(4, aluno.getDataDeNascimento());
			stm.setString(5, String.valueOf(aluno.getSexo()));
			stm.setDouble(6, aluno.getPeso());
			stm.setInt(7, aluno.getAltura());
			stm.setString(8, aluno.getEndereco());
			stm.setInt(9, aluno.getMatricula());

			// Executar o comando no banco
			stm.execute();
			Conexao.fecharConexao();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// DELETE
	public void excluir(int matricula) {

		String sql = "DELETE FROM tbl_aluno WHERE matricula = ?";

		try {
			PreparedStatement stm = Conexao.getConexao().prepareStatement(sql);
			stm.setInt(1, matricula);

			// Executar o comando no banco
			stm.execute();
			Conexao.fecharConexao();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
