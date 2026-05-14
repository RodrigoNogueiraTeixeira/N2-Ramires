package com.fatesg.web_api.services;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fatesg.biblioteca.dtos.FuncionarioDto;
import com.fatesg.biblioteca.interfaces.ServidorDeDadosFuncionarioInterface;
import com.fatesg.web_api.apis.ServidorDeDadosFuncionarioApi;
import com.fatesg.web_api.configs.RmiConfig;


@Service
public class FuncionariosService {
    private ServidorDeDadosFuncionarioApi stub;

    public FuncionariosService() {
        this.stub = new ServidorDeDadosFuncionarioApi();
        this.stub.Conectar();
    }

    public List<FuncionarioDto> getFuncionarios(int limite, int offset) {
        try {
            List<FuncionarioDto> funcionarios = stub.listarFuncionarios(limite, offset);
            return funcionarios;
        } catch (Exception e) {
            System.err.println("Erro na comunicação com o servidor no getFuncionarios:");
            e.printStackTrace();
            throw new RuntimeException("Erro na comunicação com o servidor ao obter funcionários", e);
        }
    }

    public FuncionarioDto getFuncionarioById(int id) {
        try {
            FuncionarioDto funcionario = stub.obterFuncionarioPorId(id);
            return funcionario;
        } catch (Exception e) {
            System.err.println("Erro na comunicação com o servidor no getFuncionarioById:");
            e.printStackTrace();
            throw new RuntimeException("Erro na comunicação com o servidor ao obter funcionário", e);
        }
    }

}
