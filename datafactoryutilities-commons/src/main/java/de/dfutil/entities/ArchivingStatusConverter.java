package de.dfutil.entities;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class ArchivingStatusConverter implements AttributeConverter<ArchivingStatus, String> {

    @Override
    public String convertToDatabaseColumn(ArchivingStatus archivingStatus) {
        if (archivingStatus == null) {
            return null;
        }
        return archivingStatus.getStatusValue();
    }

    @Override
    public ArchivingStatus convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(ArchivingStatus.values())
                .filter(c -> c.getStatusValue().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
