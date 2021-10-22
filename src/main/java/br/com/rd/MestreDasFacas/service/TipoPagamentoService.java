package br.com.rd.MestreDasFacas.service;

import br.com.rd.MestreDasFacas.model.dto.TipoPagamentoDTO;
import br.com.rd.MestreDasFacas.model.enity.TipoPagamento;
import br.com.rd.MestreDasFacas.repository.contract.TipoPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TipoPagamentoService {
    @Autowired
    TipoPagamentoRepository tipoPagamentoRepository;


    public TipoPagamentoDTO createTipoPagamento(TipoPagamentoDTO tipoPagamento){
        TipoPagamento newTipoPagamento = this.dtoToBusiness(tipoPagamento);
        newTipoPagamento = tipoPagamentoRepository.save(newTipoPagamento);
        return this.businessToDto(newTipoPagamento);
    }

    public List<TipoPagamentoDTO> findAllMedia() {
        List<TipoPagamento> allList = tipoPagamentoRepository.findAll();
        return this.listToDto(allList);
    }

    private List<TipoPagamentoDTO> listToDto(List<TipoPagamento> list){
        List<TipoPagamentoDTO> listDto = new ArrayList<TipoPagamentoDTO>();
        for (TipoPagamento v : list) {
            listDto.add(this.businessToDto(v));
        }
        return listDto;
    }
    public TipoPagamentoDTO updateById(TipoPagamentoDTO dto, Long id){
        Optional<TipoPagamento> op = tipoPagamentoRepository.findById(id);

        if (op.isPresent()){
            TipoPagamento obj = op.get();

            if(dto.getDescricao_tipo_pagamento() != null){
                obj.setDescricao_tipo_pagamento(dto.getDescricao_tipo_pagamento());
            }

            tipoPagamentoRepository.save(obj);
            return  businessToDto(obj);

        }
        return null;
    }

    public void deleteById(Long id){
        if(tipoPagamentoRepository.existsById(id)){
            tipoPagamentoRepository.deleteById(id);
        }
    }

    public TipoPagamentoDTO searchTipoPagamentoById(Long id) {
        Optional<TipoPagamento> op = tipoPagamentoRepository.findById(id);

        if (op.isPresent()){
            return businessToDto(op.get());
        }
        return null;
    }


    public TipoPagamento dtoToBusiness(TipoPagamentoDTO dto) {
        TipoPagamento business = new TipoPagamento();
        business.setDescricao_tipo_pagamento(dto.getDescricao_tipo_pagamento());
        return business;
    }

    public TipoPagamentoDTO businessToDto(TipoPagamento business) {
        TipoPagamentoDTO dto = new TipoPagamentoDTO();
        dto.setId_tipo_pagamento(business.getId_tipo_pagamento());
        dto.setDescricao_tipo_pagamento(business.getDescricao_tipo_pagamento());
        return dto;
    }
}
