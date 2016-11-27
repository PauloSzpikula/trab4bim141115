package br.univel.pauloszpikula.pessoa.controller;

import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.PieChartModel;

import br.univel.pauloszpikula.repository.PessoaRepository;

/**
 * Controladdor dografico de pizza.
 * @author Paulo Szpikula 27/11/2016 17:16
 */
@Named(value="graficoPizzaPessoaController")
@RequestScoped
public class GraficoPizzaPessoaController {

	@Inject
	private PessoaRepository pessoaRepository;

	private PieChartModel pieChartModel;

	/***
	 * Responsável por retornar o gráfico montado.
	 * @return pieChartModel o gráfico.
	 */
	public PieChartModel getPieChartModel() {
		return pieChartModel;
	}

	/***
	 * Responsável por montar o gráfico.
	 */
	@PostConstruct
	public void init(){
		this.pieChartModel = new PieChartModel();
		this.MontaGrafico();
	}

	/***
	 * Responsável por montar o gráfico na página
	 */
	private void MontaGrafico(){

		Hashtable<String, Integer> hashtableRegistros = pessoaRepository.GetOrigemPessoa(); // Consulta os dados para a montagem do gráfico

		hashtableRegistros.forEach((chave,valor) -> {
			pieChartModel.set(chave, valor);
		}); // Valores informados para a montagem do gráfico
		pieChartModel.setTitle("Total de Pessoas cadastrado por Tipo");
		pieChartModel.setShowDataLabels(true);
		pieChartModel.setLegendPosition("e");
	}
}