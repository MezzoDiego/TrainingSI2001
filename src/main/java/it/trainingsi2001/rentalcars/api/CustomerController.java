package it.trainingsi2001.rentalcars.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.trainingsi2001.rentalcars.dto.UtenteDTO;
import it.trainingsi2001.rentalcars.dto.mapper.UtenteMapper;
import it.trainingsi2001.rentalcars.service.UtenteService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    UtenteService customerService;

    @Autowired
    UtenteMapper customerMapper;

    @PutMapping("/updateUtenza")
    public UtenteDTO updateUtenza(@RequestBody UtenteDTO bodyCustomer) {

        return customerMapper.toDto(customerService.aggiorna(customerMapper.toEntity(bodyCustomer)));

    }

}
