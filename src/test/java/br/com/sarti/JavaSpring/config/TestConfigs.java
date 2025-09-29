package br.com.sarti.JavaSpring.config;

public interface TestConfigs {
    int SERVER_PORT = 8888;

    String HEADER_PARAM_AUTHORIZATION = "Authorization";
    String HEADER_PARAM_ORIGIN = "Origin";

    String ORIGIN_SARTI = "https://github.com/Victor-Sarti";
    String ORIGIN_SEMERU = "https://www.linkedin.com/in/victor-sarti-ab6807249/";
    String ORIGIN_LOCAL = "http://localhost:" + SERVER_PORT ;
}