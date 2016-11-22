package br.univel.pauloszpikula.uteis;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Conversor de LocalDateTime em Timestamp e vice-versa.
 * @author Paulo Szpikula 14/11/2016 20:35
 */
@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

	/**
	 * Transforma em Timestamp quando for persistir no banco de dados.
	 * @return um Timestamp especificando a data e hora.
	 */
	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
		if (localDateTime != null)
			return Timestamp.valueOf(localDateTime);
		return null;
	}

	/**
	 * Transforma um Timestamp em LocalDateTime quando retornar do banco para a entidade.
	 * @return um LocalDateTime especificando a data e hora.
	 */
	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
		if (timestamp != null)
			return timestamp.toLocalDateTime();
		return null;
	}
}
