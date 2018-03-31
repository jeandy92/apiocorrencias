/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webserviceocorrencia.ws.controller;

import com.webserviceocorrencia.ws.entidades.MDOcorrencia;
import com.webserviceocorrencia.ws.entidades.MDUsuario;
import com.webserviceocorrencia.ws.servico.OcorrenciaService;
import java.util.List;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jeanderson
 */
@RestController
public class OcorrenciaController {

    @Autowired
    OcorrenciaService ocorrenciaService;
    
    @RequestMapping(value = "/cadastrarOcorrencia", method = RequestMethod.POST)
    public String cadastrarOcorrencia(@RequestBody MDOcorrencia ocorrencia) {
        return this.ocorrenciaService.cadastrarOcorrencia(ocorrencia);
    }

    @RequestMapping(value = "/buscarUsuarioOcorrencia/{cpf}", method = RequestMethod.GET)
    public List<MDUsuario> buscarUsuarioOcorrencia(@PathVariable("cpf") String cpf) throws JSONException {
        return this.ocorrenciaService.buscarUsuarioOcorrencia(cpf);
    }

    @RequestMapping(value = "/buscarOcorBairro/{bairro}", method = RequestMethod.GET)
    public List<MDOcorrencia> listaOcorrencias(@PathVariable("bairro") String bairro) throws JSONException {
        return this.ocorrenciaService.listaOcorrencias(bairro);

    }
}
