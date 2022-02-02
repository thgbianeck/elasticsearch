package br.com.bnck.elasticsearch.document;

import br.com.bnck.elasticsearch.helper.Indices;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

/**
 * Criado utilizando IntelliJ IDEA.
 * Projeto: elasticsearch
 * Usuário: Thiago Bianeck (Bianeck)
 * Data: 02/02/2022
 * Hora: 14:07
 */
@Getter
@Setter
@Document(indexName = Indices.PERSON_INDEX)
@Setting(settingPath = "static/es-settings.json")
public class Person {

    @Id
    @Field(type = FieldType.Keyword)
    private String id;

    @Field(type = FieldType.Text)
    private String name;

}
