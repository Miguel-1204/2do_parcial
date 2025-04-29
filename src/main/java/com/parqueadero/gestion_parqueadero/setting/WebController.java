package com.parqueadero.gestion_parqueadero.setting;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    
//    @GetMapping("/error/acceso-denegado")
//    public String accesoDenegado() {
//        return "error/acceso-denegado";
//    }
    @GetMapping("/lista")
    public String lista() {
        return "/vehiculo/lista";
    }
}

