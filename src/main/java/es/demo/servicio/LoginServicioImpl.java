package es.demo.servicio;

import es.demo.iservicio.ILoginServicio;
import es.demo.dao.LoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.demo.domain.Login;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class LoginServicioImpl implements ILoginServicio {

    @Autowired
    private LoginDao loginDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Login guardarEditar(Login login) {
        login.setPASSWORD(passwordEncoder.encode(login.getPASSWORD()));
        return loginDao.save(login);
    }

    @Override
    public Login buscarLogin(Login login) {
        return loginDao.getById(login.getID_PERSONAL());
    }
}
