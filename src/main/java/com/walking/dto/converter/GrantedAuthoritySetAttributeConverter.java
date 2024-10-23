package com.walking.dto.converter;

import jakarta.persistence.AttributeConverter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class GrantedAuthoritySetAttributeConverter implements AttributeConverter<Set<GrantedAuthority>, String> {
    private String comma = ",";
    //엔티티를 DB에 집어넣을때
    @Override
    public String convertToDatabaseColumn(Set<GrantedAuthority> attribute) {
        String data = "";
        for (GrantedAuthority grant: attribute) {
            data += grant.getAuthority();
        }
        return data;
    }

    //DB에서 엔티티 필드로 변환할 때
    @Override
    public Set<GrantedAuthority> convertToEntityAttribute(String dbData) {
        return Arrays.stream(dbData.split(comma)).map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }
}
