package br.usp.icmc.exemplo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.management.RuntimeErrorException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class TesteCalculadora {
	
	private Calculadora calculadora;
	
	@Before
	public void inicializa(){
		calculadora = new Calculadora();
	}

	@Test
	public void testeSomaComAssertEquals() {
		int valorEsperado = 2;
		int valorRetornado = calculadora.somar(1, 1);
		assertEquals(valorEsperado, valorRetornado);
	}
	
	@Test
	public void testeSomaComAssertTrue() {
		int valorEsperado = 2;
		int valorRetornado = calculadora.somar(1, 1);
		assertTrue(valorEsperado == valorRetornado);
	}
	
	@Test
	public void testeSomaComAssertFalse() {
		int valorEsperado = 2;
		int valorRetornado = calculadora.somar(1, 1);
		assertFalse(valorEsperado != valorRetornado);
	}

	// Para que o método com o AssertThat funcione é necessário colocar o hamcrest antes 
	// do JUnit no arquivo pom.xml, caso contrário será lançada a exceção "java.lang.SecurityException"
	@Test
	public void testeSomaComAssertThat() {
		int valorEsperado = 2;
		int valorRetornado = calculadora.somar(1, 1);		
		assertThat(valorRetornado, is(valorEsperado));
	}

	@Test
	public void testeSubtracao() {
		assertEquals(3, calculadora.subtrair(6, 3), 0.1);
	}

	@Test
	public void testeMultiplicacao() {
		// 0,1 é o delta – o delta é o valor máximo entre o valor real (c.multiplicacao(2,2)) 
		// e o esperado (4.0) para o qual ambos os números ainda são considerados iguais.
		assertEquals(new Double(4.0), new Double(calculadora.multiplicar(2, 2)));
		assertEquals(4.0, calculadora.multiplicar(2, 2), 0.1);
	}

	@Test
	public void testeDivisao() {
		assertEquals(5.0, calculadora.dividir(25, 5), 0.1);
		assertTrue(calculadora.dividir(10, 2) == new Double(5.0));
		assertTrue("Exibe mensagem de erro caso o teste falhe!", calculadora.dividir(10, 2) == new Double(7.0));
		assertFalse(calculadora.dividir(10, 2) != new Double(5.0));
	}
	
	@Test
	public void testeLancaExcecao() {
		// Diferença entre erro e falhas do JUnit (Vermelho e Azul - Eclipse)		
		// Se não estiver esperando uma exceção é erro 
		// Se a informação for diferente do que é esperado - é uma falha
		throw new RuntimeException("Mensagem da Exceção!");
	}
	
	@Test(expected = ArithmeticException.class)
	public void testeDivisaoPorZero() {
		calculadora.dividir(1, 0);		
	}

}
