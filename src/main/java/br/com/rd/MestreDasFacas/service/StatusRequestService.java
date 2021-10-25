package br.com.rd.MestreDasFacas.service;

import br.com.rd.MestreDasFacas.model.dto.StatusRequestDTO;
import br.com.rd.MestreDasFacas.model.enity.StatusRequest;
import br.com.rd.MestreDasFacas.repository.contract.StatusRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StatusRequestService {
    @Autowired
    StatusRequestRepository statusPedidoRepository;


    public StatusRequestDTO createStatusPedido(StatusRequestDTO statusPedido){
        StatusRequest newStatusPedido = this.dtoToBusiness(statusPedido);
        newStatusPedido = statusPedidoRepository.save(newStatusPedido);
        return this.businessToDto(newStatusPedido);
    }

    public List<StatusRequestDTO> findAllMedia() {
        List<StatusRequest> allList = statusPedidoRepository.findAll();
        return this.listToDto(allList);
    }

    private List<StatusRequestDTO> listToDto(List<StatusRequest> list){
        List<StatusRequestDTO> listDto = new ArrayList<StatusRequestDTO>();
        for (StatusRequest v : list) {
            listDto.add(this.businessToDto(v));
        }
        return listDto;
    }
    public StatusRequestDTO updateById(StatusRequestDTO dto, Long id){
        Optional<StatusRequest> op = statusPedidoRepository.findById(id);

        if (op.isPresent()){
            StatusRequest obj = op.get();

            if(dto.getId_status_pedido() != null){
                obj.setDescription_status_pedido(dto.getDescription_status_pedido());
            }

            statusPedidoRepository.save(obj);
            return  businessToDto(obj);

        }
        return null;
    }

    public void deleteById(Long id){
        if(statusPedidoRepository.existsById(id)){
            statusPedidoRepository.deleteById(id);
        }
    }

    public StatusRequestDTO searchStatusPedidoById(Long id) {
        Optional<StatusRequest> op = statusPedidoRepository.findById(id);

        if (op.isPresent()){
            return businessToDto(op.get());
        }
        return null;
    }


    public StatusRequest dtoToBusiness(StatusRequestDTO dto) {
        StatusRequest business = new StatusRequest();
        business.setDescription_status_pedido(dto.getDescription_status_pedido());
        return business;
    }

    public StatusRequestDTO businessToDto(StatusRequest business) {
        StatusRequestDTO dto = new StatusRequestDTO();
        dto.setId_status_pedido(business.getId_status_pedido());
        dto.setDescription_status_pedido(business.getDescription_status_pedido());
        return dto;
    }
}
