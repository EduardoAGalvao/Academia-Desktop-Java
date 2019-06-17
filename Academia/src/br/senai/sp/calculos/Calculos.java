package br.senai.sp.calculos;

public class Calculos {
	public Aluno aluno;
	
	public double calcularTmb(){
		char sexo = this.aluno.getSexo();
		double peso = this.aluno.getPeso();
		int idade = this.aluno.getIdade();
		double altura = this.aluno.getAltura();
		double tmb = 0;
		
		if (sexo == 'M') {
			 tmb = 66 + (13.7 * peso) + (5.0 * altura ) - (6.8 * idade);
		} else if (sexo == 'F') {
			tmb = 665 + (9.6 * peso) + (1.8 * altura ) - (4.7 * idade);
		}
		
		return tmb;
		
	}
	
	public double calcularImc(){
		int altura = this.aluno.getAltura();
		double alturaCalculo = altura / 100.0;
		double peso = this.aluno.getPeso();
		double imc = 0;
		String resultado = " ";
		String imcString = " ";
		
		imc = peso / Math.pow(alturaCalculo, 2);
		
		return imc;
		
	}
	
	public String situacaoPeso(double imc) {
		
		String resultadoSituacao = " ";
		
		if(imc < 17) {
			resultadoSituacao = "Muito abaixo do peso";
		} else if(imc <= 18.49){
			resultadoSituacao = "Abaixo do peso";
		} else if(imc <= 24.99){
			resultadoSituacao = "Peso normal";
		} else if(imc <= 29.99){
			resultadoSituacao = "Acima do Peso";
		} else if(imc <= 34.99){
			resultadoSituacao = "Obesidade I";
		} else if(imc <= 39.99) {
			resultadoSituacao = "Obesidade II (severa)";
		} else {
			resultadoSituacao = "Obesidade III (mórbida)";
		}
		
		return resultadoSituacao;
	}
	
	public int calcularFcm(){
		char sexo = this.aluno.getSexo();
		int idade = this.aluno.getIdade();
		int fcm = 0;
		
		if (sexo == 'M') {
			 fcm = 220 - idade;
		} else if (sexo == 'F') {
			fcm = 226 - idade;;
		}
		
		return fcm;
	}
}
