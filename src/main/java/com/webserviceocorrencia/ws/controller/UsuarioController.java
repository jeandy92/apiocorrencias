/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webserviceocorrencia.ws.controller;

import com.webserviceocorrencia.ws.entidades.MDUsuario;
import com.webserviceocorrencia.ws.servico.UsuarioService;
import java.io.IOException;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jeanderson Almeida   <jeandymalmeida@gmail.com>
 */

@RestController
public class UsuarioController {

    @Autowired
    public UsuarioService usuarioService;

    @RequestMapping(value = "/cadastrarUsuario", method = RequestMethod.POST)
    public String cadastrarUsuario(@RequestBody MDUsuario usuario) {
        return this.usuarioService.cadastrarUsuario(usuario);

    }

    @RequestMapping(value = "/deletarUsuario", method = RequestMethod.POST)
    public String deletarUsuario() {
        return this.usuarioService.deletarUsuario();

    }

    @RequestMapping(value = "/alterarUsuario", method = RequestMethod.POST)
    public String alterarUsuario() {
        return  this.usuarioService.alterarUsuario();

    }

    @RequestMapping(value = "/cadastrarImagemPerfil", method = RequestMethod.POST)
    public String armazenarImagemPerfil(@RequestBody MDUsuario usuario) throws IOException {
        return this.usuarioService.armazenarImagemPerfil(usuario);
    }

    @RequestMapping(value = "/buscarUsuario/{cpf}", method = RequestMethod.GET)
    public String buscarUsuario(@PathVariable("cpf") String cpf) throws JSONException{
        return this.usuarioService.buscarUsuario(cpf);

    }

    @RequestMapping(value = "/logarUsuario/{cpf}/{senha}", method = RequestMethod.POST)
    public String logarUsuario(@PathVariable("cpf") String pCpf,@PathVariable("senha") String pSenha) {
        return this.usuarioService.logarUsuario(pCpf, pSenha);

    }

}
