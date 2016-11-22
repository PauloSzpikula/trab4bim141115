package br.univel.pauloszpikula.uteis;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

/**
 * Responsável pela formatação de um objeto do tipo LocalDateTime em uma página JSF.
 * @author Paulo Szpikula 22/11/2016 19:31
 */
@FacesConverter(value = LocalDateTimeConverter.ID)
public class LocalDateTimeConverter extends DateTimeConverter {
	public static final String ID = "br.com.pauloszpikula.uteis.LocalDateTimeConverter";

	/**
	 * Responsável por converter um Date em um localDateTime.
	 * @param facesContext face de contexto
	 * @param uiComponent o componente
	 * @param value o valor
	 * @return um localDateTime especificando a data e hora.
	 * @throws uma exceção caso não seja possível executar a formatação.
	 */
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {

		Date date = null;
		LocalDateTime localDateTime = null;
		Object object = super.getAsObject(facesContext, uiComponent, value);

		if (object instanceof Date) {
			date = (Date) object;
			Instant instant = Instant.ofEpochMilli(date.getTime());
			localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
			return localDateTime;
		} else {
			throw new IllegalArgumentException(
					String.format("value=%s Não foi possível converter LocalDateTime, resultado super.getAsObject=%s", value, object));
		}
	}

	/**
	 * Responsável passar um LocalDateTime no formato estabelecido.
	 * @param facesContext face de contexto
	 * @param uiComponent o componente
	 * @param value o valor
	 * @return uma String com o conteúdo formatado.
	 * @throws uma exceção caso não seja um LocalDateTime.
	 */
	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {

		if (value == null)
			return super.getAsString(facesContext, uiComponent, value);

		if (value instanceof LocalDateTime) {
			LocalDateTime localDateTime = (LocalDateTime) value;
			Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
			Date date = Date.from(instant);
			return super.getAsString(facesContext, uiComponent, date);
		} else {
			throw new IllegalArgumentException(String.format("value=%s não é um LocalDateTime", value));
		}
	}
}
