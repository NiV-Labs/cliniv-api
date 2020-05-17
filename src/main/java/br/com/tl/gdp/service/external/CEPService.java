package br.com.tl.gdp.service.external;

import org.springframework.stereotype.Service;

import br.com.tl.gdp.models.dto.AddressDTO;
import br.com.tl.gdp.rest.client.cep.viacep.ViaCepAddress;
import br.com.tl.gdp.rest.client.cep.viacep.ViaCepClient;

@Service
public class CEPService {

    public AddressDTO get(String cep) {
        return getByViaCep(cep);
    }

    private AddressDTO getByViaCep(String cep) {
        ViaCepAddress address = new ViaCepClient().getAddressByCep(cep);
        AddressDTO response = new AddressDTO();
        response.setCity(address.getLocalidade());
        response.setNeighborhood(address.getBairro());
        response.setPostalCode(cep);
        response.setState(address.getUf());
        response.setStreet(address.getLogradouro());
        return response;
    }
}
