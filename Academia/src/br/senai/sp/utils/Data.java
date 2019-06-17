package br.senai.sp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Data {
	
	public static String converterParaBanco(String data){
		
		// 28/03/2019 -> 2019-03-28
		//Na substring, o primeiro valor de início é a letra desejada, até a próxima letra não desejada
		//Exemplo: extrair SEN de SENAI -> substring(0,3)
		String dataBanco;
		String dia = data.substring(0, 2);
		String mes = data.substring(3, 5);
		String ano = data.substring(6, 10);
		
		dataBanco = ano + "-" + mes + "-" + dia;
		
		return dataBanco;
	}
	
	public static String converterParaUsuario(String data) {
		
		//2019-03-28 -> 28/03/2019
		String dataTela;
		String dia = data.substring(8, 10);
		String mes = data.substring(5,7);
		String ano = data.substring(0,4);
		
		dataTela = dia + "/" + mes + "/" + ano;
		
		return dataTela;
	}
	
	public static int calcularIdade(String dataNascimento) {
		
		//Obter a data de hoje
		Date hoje = new Date();
		
		//Criar um formatador de datas
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); //Para capturar hora, usa-se HH:mm
		Date dataNascimentoDate = null;		
		
		//Transformar a data de nascimento em uma data
		try {
			dataNascimentoDate = dateFormat.parse(dataNascimento);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
				
		//Obter a diferença entre data de hoje e a data de nascimento em milisegundos
		long mils = hoje.getTime() - dataNascimentoDate.getTime();
		
		//Obter a quantidade de anos dos milisegundos
		double idade = mils / (1000*60*60*24*365.25);
		
		return (int)idade;
				
	}

}
