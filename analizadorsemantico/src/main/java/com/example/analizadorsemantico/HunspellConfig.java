package com.example.analizadorsemantico;

/**
 *
 * @author Usuario
 */
import com.atlascopco.hunspell.Hunspell;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Configuration
public class HunspellConfig {

    @Bean
    public Hunspell hunspell() throws IOException {
        // Cargar los archivos desde el classpath
        InputStream dicStream = getClass().getResourceAsStream("/hunspell/es_ANY.dic");
        InputStream affStream = getClass().getResourceAsStream("/hunspell/es_ANY.aff");

        // Verificar si los archivos fueron encontrados
        if (dicStream == null) {
            throw new IOException("Diccionario no encontrado en el classpath");
        }
        if (affStream == null) {
            throw new IOException("Archivo de configuración (aff) no encontrado en el classpath");
        }

        // Crear archivos temporales
        Path tempDic = Files.createTempFile("hunspell-dic", ".dic");
        Path tempAff = Files.createTempFile("hunspell-aff", ".aff");

        // Copiar los archivos del classpath a los archivos temporales
        Files.copy(dicStream, tempDic, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        Files.copy(affStream, tempAff, java.nio.file.StandardCopyOption.REPLACE_EXISTING);

        // Inicializar Hunspell con los archivos temporales
        return new Hunspell(tempDic.toString(), tempAff.toString());
    }
}