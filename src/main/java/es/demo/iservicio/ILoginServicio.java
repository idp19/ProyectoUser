package es.demo.iservicio;

import es.demo.domain.Login;

public interface ILoginServicio {

    public Login guardarEditar(Login login);

    public Login buscarLogin(Login login);
}
