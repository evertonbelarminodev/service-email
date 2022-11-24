package com.micro.service.email.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Everton Luiz Belarmino
 */
public class Client{

    private Long clientCode;
    private String clientName;
    private String cpf;
    private Date birthDate;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getClientCode() {
        return clientCode;
    }

    public void setClientCode(Long clientCode) {
        this.clientCode = clientCode;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}