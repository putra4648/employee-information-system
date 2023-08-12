package id.pradana.learn_jpa_relationship.converter;

import id.pradana.learn_jpa_relationship.model.Gender;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, Character> {

  @Override
  public Character convertToDatabaseColumn(Gender gender) {
    if (gender == null)
      return null;

    return gender.getCode();
  }

  @Override
  public Gender convertToEntityAttribute(Character code) {
    if (code == null)
      return null;

    return Stream.of(Gender.values())
        .filter(c -> c.getCode() == code)
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }
}
