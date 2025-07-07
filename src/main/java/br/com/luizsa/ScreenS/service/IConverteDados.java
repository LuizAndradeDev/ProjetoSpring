package br.com.luizsa.ScreenS.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
