package br.univel.pauloszpikula.pessoa.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.univel.pauloszpikula.model.PessoaModel;
import br.univel.pauloszpikula.repository.PessoaRepository;

/**
 * Controlador do exportador de arquivos XML.
 * @author Paulo Szpikula 27/11/2016 17:30
 */
@Named(value="exportarRegistrosXmlController")
@RequestScoped
public class ExportarRegistrosXmlController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject transient
	PessoaRepository pessoaRepository;

	private StreamedContent arquivoDownload;

	/***
	 * Responsável por realizar o dowload do arquivo.
	 * @return arquivoDownload o arquivo.
	 */
	public StreamedContent getArquivoDownload() {
		this.DownlaodArquivoXmlPessoa();
		return arquivoDownload;
	}

	/***
	 * Responsável por gerar o arquivo XML para exportação.
	 * @return o arquivo XML;
	 */
	private File GerarXmlPessoas() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); // Máscara para formatação de datas do arquivo
		List<PessoaModel> pessoasModel = pessoaRepository.GetPessoas();

		Element elementPessoas = new Element("Pessoas"); // Elemento raiz do arquivo XML
		Document documentoPessoas = new Document(elementPessoas);

		pessoasModel.forEach(pessoa -> {
			Element elementPessoa = new Element("Pessoa"); // Mostrando as tags do XML
			elementPessoa.addContent(new Element("codigo").setText(pessoa.getCodigo().toString()));
			elementPessoa.addContent(new Element("nome").setText(pessoa.getNome()));
			elementPessoa.addContent(new Element("sexo").setText(pessoa.getSexo()));
			String dataCadastroFormatada = pessoa.getDataCadastro().format(dateTimeFormatter); // Formatação da data
			elementPessoa.addContent(new Element("dataCadastro").setText(dataCadastroFormatada));
			elementPessoa.addContent(new Element("email").setText(pessoa.getEmail()));
			elementPessoa.addContent(new Element("endereco").setText(pessoa.getEndereco()));
			elementPessoa.addContent(new Element("origemCadastro").setText(pessoa.getOrigemCadastro()));
			elementPessoa.addContent(new Element("usuarioCadastro").setText(pessoa.getUsuarioModel().getUsuario()));
			elementPessoas.addContent(elementPessoa);
		});
		XMLOutputter xmlGerado = new XMLOutputter();
		try {
			String nomeArquivo =  "pessoas_".concat(java.util.UUID.randomUUID().toString()).concat(".xml"); // Gerando o nome do arquivo
			File arquivo = new File("C:\\Users\\Ti\\git\\trab4bim141115".concat(nomeArquivo)); // Caminho onde será gerado o XML
			FileWriter fileWriter =  new FileWriter(arquivo);
			xmlGerado.output(documentoPessoas, fileWriter);
			return arquivo;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/***
	 * Responsável por preparar o arquivo para download.
	 */
	public void DownlaodArquivoXmlPessoa(){

		File arquivoXml = this.GerarXmlPessoas();
		InputStream inputStream;
		try {
			inputStream = new FileInputStream(arquivoXml.getPath());
			arquivoDownload = new DefaultStreamedContent(inputStream,"application/xml",arquivoXml.getName());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}