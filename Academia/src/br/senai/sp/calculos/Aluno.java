package br.senai.sp.calculos;

public class Aluno {
	
	private int matricula;
	private String nome;
	public int idade;
	private char sexo; 
	private double peso;
	private int altura;
	private String endereco;
	private String telefone;
	private String email;
	// datas no banco de dados devem ser inseridos no formato 'yyyy-MM-dd'
	private String dataDeNascimento;
	
	// M�TODOS DE ACESSO - SETTER E GETTER
	//O m�todo set � sempre void, ou seja, nunca tem retorno
	
	//Set e Get - Nome
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	//Get - Idade
		
	public int getIdade() {
		return this.idade;
	}
	
	//Set e Get - Sexo
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
			
	public char getSexo() {
		return this.sexo;
	}
	
	//Set e Get - Peso
	public void setPeso(double peso) {
		this.peso = peso;
	}
				
	public double getPeso() {
		return this.peso;
	}
	
	//Set e Get - Altura
	public void setAltura(int altura) {
		this.altura = altura;
	}
					
	public int getAltura() {
		return this.altura;
	}
	
	//Set e Get - Endere�o
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	//Set e Get - Telefone
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	//Set e Get - Email
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	//Set e Get - Data de Nascimento
	public String getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
	
	//Get - Matr�cula (Ser� gerada pelo MySQL, ou seja, nunca ser� setada)
	public int getMatricula() {
		return matricula;
	}
	
	//Set - Matr�cula (SOMENTE PARA EFEITO DE TESTO)
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	@Override
	public String toString() {
		return "Aluno [matricula=" + matricula + ", nome=" + nome + ", idade=" + idade + ", sexo=" + sexo + ", peso="
				+ peso + ", altura=" + altura + ", endereco=" + endereco + ", telefone=" + telefone + ", email=" + email
				+ ", dataDeNascimento=" + dataDeNascimento + "]";
	}
	
	//Maneira r�pida de produzir todos os GETTERS AND SETTERS -> Bot�o direito > Sources > Generatte Getters and Setters
		
	
}
