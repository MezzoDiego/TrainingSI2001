package it.trainingsi2001.rentalcars.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.trainingsi2001.rentalcars.service.VeicoloService;

@RestController
@RequestMapping("/api/veicolo")
public class VeicoloController {

    @Autowired
    VeicoloService veicoloService;

    

}
