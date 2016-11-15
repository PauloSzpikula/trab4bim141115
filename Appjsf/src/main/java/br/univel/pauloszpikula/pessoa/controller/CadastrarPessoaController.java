package br.univel.pauloszpikula.pessoa.controller;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.primefaces.model.UploadedFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import br.univel.pauloszpikula.model.PessoaModel;
import br.univel.pauloszpikula.repository.PessoaRepository;
import br.univel.pauloszpikula.usuario.controller.UsuarioController;
import br.univel.pauloszpikula.uteis.Uteis;

/**
 * Controlador de pessoa.
 * @author Paulo Szpikula 14/11/2016 20:33
 */
@Named(value="cadastrarPessoaController")
@RequestScoped
public class CadastrarPessoaController {

	@Inject
	PessoaModel pessoaModel;

	@Inject
	UsuarioController usuarioController;

	@Inject
	PessoaRepository pessoaRepository;

	private UploadedFile file;

	/**
	 * Fazer o upload de um arquivo.
	 * @return um file especificando um arquivo.
	 */
	public UploadedFile getFile() {
		return file;
	}

	/**
	 * Setar um arquivo para a variável file.
	 * @param file o arquivo.
	 */
	public void setFile(UploadedFile file) {
		this.file = file;
	}

	/**
	 * Pegar a pessoa.
	 * @return uma PessoaModel especificando a pessoa.
	 */
	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}

	/**
	 * Setar uma pessoa para a variável pessoaModel.
	 * @param pessoaModel a pessoa.
	 */
	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}

	/**
	 * Salva um novo registro via imput.
	 */
	public void SalvarNovaPessoa(){
		pessoaModel.setUsuarioModel(this.usuarioController.GetUsuarioSession());
		pessoaModel.setOrigemCadastro("I"); // Informa que o cadastro foi feito via imput
		pessoaRepository.SalvarNovoRegistro(this.pessoaModel);
		this.pessoaModel = null;
		Uteis.MensagemInfo("Registro cadastrado com sucesso");
	}

	/**
	 * Realiza o upload de arquivo
	 */
	 public void UploadRegistros() {
		 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		 try {
			if (this.file.getFileName().equals("")){
				Uteis.MensagemAtencao("Nenhum arquivo selecionado!");
				return;
			 }
			 DocumentBuilder builder = factory.newDocumentBuilder();
	         Document doc = builder.parse(this.file.getInputstream());
	         Element element = doc.getDocumentElement();
	         NodeList nodes = element.getChildNodes();
	         for (int i = 0; i < nodes.getLength(); i++) {
	        	 Node node  = nodes.item(i);
	        	 if(node.getNodeType() == Node.ELEMENT_NODE){
	        		 Element elementPessoa =(Element) node;
 	        		 String nome = elementPessoa.getElementsByTagName("nome").item(0).getChildNodes().item(0).getNodeValue(); // Pegando os valores do arquivo XML
	        		 String sexo = elementPessoa.getElementsByTagName("sexo").item(0).getChildNodes().item(0).getNodeValue(); // Pegando os valores do arquivo XML
	        		 String email = elementPessoa.getElementsByTagName("email").item(0).getChildNodes().item(0).getNodeValue(); // Pegando os valores do arquivo XML
	        		 String endereco = elementPessoa.getElementsByTagName("endereco").item(0).getChildNodes().item(0).getNodeValue(); // Pegando os valores do arquivo XML
	        		 PessoaModel newPessoaModel = new PessoaModel();
	        		 newPessoaModel.setUsuarioModel(this.usuarioController.GetUsuarioSession());
	        		 newPessoaModel.setEmail(email);
	        		 newPessoaModel.setEndereco(endereco);
	        		 newPessoaModel.setNome(nome);
	        		 newPessoaModel.setOrigemCadastro("X");
	        		 newPessoaModel.setSexo(sexo);
	        		 pessoaRepository.SalvarNovoRegistro(newPessoaModel); // Salvando um registro que veio do arquivo XML
	        	 }
	         }
	         Uteis.MensagemInfo("Registros cadastrados com sucesso!");
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
 		} catch (SAXException e) {
 			e.printStackTrace();
 		} catch (IOException e) {
 			e.printStackTrace();
		}
	}
}