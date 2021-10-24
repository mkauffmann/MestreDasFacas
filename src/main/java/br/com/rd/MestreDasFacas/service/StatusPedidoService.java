package br.com.rd.MestreDasFacas.service;

import br.com.rd.MestreDasFacas.model.dto.StatusPedidoDTO;
import br.com.rd.MestreDasFacas.model.enity.StatusPedido;
import br.com.rd.MestreDasFacas.repository.contract.StatusPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StatusPedidoService {
    @Autowired
    StatusPedidoRepository statusPedidoRepository;


    public StatusPedidoDTO createStatusPedido(StatusPedidoDTO statusPedido){
        StatusPedido newStatusPedido = this.dtoToBusiness(statusPedido);
        newStatusPedido = statusPedidoRepository.save(newStatusPedido);
        return this.businessToDto(newStatusPedido);
    }

    public List<StatusPedidoDTO> findAllMedia() {
        List<StatusPedido> allList = statusPedidoRepository.findAll();
        return this.listToDto(allList);
    }

    private List<StatusPedidoDTO> listToDto(List<StatusPedido> list){
        List<StatusPedidoDTO> listDto = new ArrayList<StatusPedidoDTO>();
        for (StatusPedido v : list) {
            listDto.add(this.businessToDto(v));
        }
        return listDto;
    }
    public StatusPedidoDTO updateById(StatusPedidoDTO dto, Long id){
        Optional<StatusPedido> op = statusPedidoRepository.findById(id);

        if (op.isPresent()){
            StatusPedido obj = op.get();

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

    public StatusPedidoDTO searchStatusPedidoById(Long id) {
        Optional<StatusPedido> op = statusPedidoRepository.findById(id);

        if (op.isPresent()){
            return businessToDto(op.get());
        }
        return null;
    }


    public StatusPedido dtoToBusiness(StatusPedidoDTO dto) {
        StatusPedido business = new StatusPedido();
        business.setDescription_status_pedido(dto.getDescription_status_pedido());
        return business;
    }

    public StatusPedidoDTO businessToDto(StatusPedido business) {
        StatusPedidoDTO dto = new StatusPedidoDTO();
        dto.setId_status_pedido(business.getId_status_pedido());
        dto.setDescription_status_pedido(business.getDescription_status_pedido());
        return dto;
    }
}
