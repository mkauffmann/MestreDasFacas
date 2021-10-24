//package br.com.rd.HPProjetoServico.service;
//
//import br.com.rd.HPProjetoServico.model.dto.PedidoDTO;
//import br.com.rd.HPProjetoServico.model.dto.StatusPedidoDTO;
//import br.com.rd.HPProjetoServico.model.enity.Pedido;
//import br.com.rd.HPProjetoServico.model.enity.StatusPedido;
//import br.com.rd.HPProjetoServico.repository.contract.PedidoRepository;
//import br.com.rd.HPProjetoServico.repository.contract.StatusPedidoRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class PedidoService {
//    @Autowired
//    PedidoRepository pedidoRepository;
//    @Autowired
//    StatusPedidoRepository statusPedidoRepository;
//
////    public PedidoDTO createVehicle(PedidoDTO pedido){
////        Pedido newPedido = this.dtoToBusiness(pedido);
////
////        Long id = newPedido.getPedidoStatus().getId_status_pedido();
////        if(id != null){
////            StatusPedido m = this.statusPedidoRepository.getById(id);
////            newPedido.setPedidoStatus(m);
////        }
////
////        newPedido = pedidoRepository.save(newPedido);
////        return this.businessToDto(newPedido);
////
////    }
//
////    public Pedido dtoToBusiness(PedidoDTO dto) {
////        Pedido business = new Pedido();
////        business.setFrete_fixo(dto.getFrete_fixo());
////        business.setData_compra(dto.getData_compra());
////        business.setData_pagamento(dto.getData_pagamento());
////        if(dto.getStatusPedido() !=null){
////            StatusPedido m = new StatusPedido();
////            if (dto.getStatusPedido().getId_status_pedido() != null){
////                m.setId_status_pedido(dto.getStatusPedido().getId_status_pedido());
////            }else {
////                m.setDescription_status_pedido(dto.getStatusPedido().getDescription_status_pedido());
////            }
////            business.setPedidoStatus(m);
////        }
////        return business;
////    }
//
//    public PedidoDTO businessToDto(Pedido business) {
//        PedidoDTO dto = new PedidoDTO();
//        dto.setId_numero_pedido(business.getId_numero_pedido());
//        dto.setFrete_fixo(business.getFrete_fixo());
//        dto.setData_compra(business.getData_compra());
//        dto.setData_pagamento(business.getData_pagamento());
//        if (business.getPedidoStatus() != null){
//            StatusPedidoDTO mediaDTO = new StatusPedidoDTO();
//            mediaDTO.setId_status_pedido(business.getPedidoStatus().getId_status_pedido());
//            mediaDTO.setDescription_status_pedido(business.getPedidoStatus().getDescription_status_pedido());
//            dto.setStatusPedido(mediaDTO);
//        }
//        return dto;
//    }
//
//
//}
//
