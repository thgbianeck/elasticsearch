package br.com.bnck.elasticsearch.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.nio.file.Files;

/**
 * Criado utilizando IntelliJ IDEA.
 * Projeto: elasticsearch
 * Usuário: Thiago Bianeck (Bianeck)
 * Data: 02/02/2022
 * Hora: 15:28
 */
@Slf4j
public class Util {

    public static String loadAsString(final String path) {
        try{
            final File resource = new ClassPathResource(path).getFile();
            return new String(Files.readAllBytes(resource.toPath()));
        } catch (final Exception e){
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
