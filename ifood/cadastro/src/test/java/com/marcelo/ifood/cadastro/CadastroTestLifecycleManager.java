package com.marcelo.ifood.cadastro;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;
import java.util.HashMap;
import java.util.Map;
public class CadastroTestLifecycleManager implements QuarkusTestResourceLifecycleManager {

    public static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:12.2");

    @Override
    public Map<String, String> start(){
        System.out.println("Executando start container");
        POSTGRES.start();
        Map<String, String> propriedades = new HashMap<String, String>();

        propriedades.put("quarkus.datasource.url", POSTGRES.getJdbcUrl());
        propriedades.put("quarkus.datasource.username", POSTGRES.getUsername());
        propriedades.put("quarkus.datasource.password", POSTGRES.getPassword());
        System.out.println("Retornadno  start container");
        return propriedades;
    }

    @Override
    public void stop(){
        if(POSTGRES != null && POSTGRES.isRunning()){
            POSTGRES.stop();
        }
    }
}
