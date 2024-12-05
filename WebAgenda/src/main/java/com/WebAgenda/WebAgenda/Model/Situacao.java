package com.WebAgenda.WebAgenda.Model;

public enum Situacao {
    AGENDADO(0),
    CANCELADO(1),
    REALIZADO(2);

    private final int value;

    Situacao(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Situacao fromInteger(int value) {
        for (Situacao situacao : Situacao.values()) {
            if (situacao.getValue() == value) {
                return situacao;
            }
        }
        throw new IllegalArgumentException("Invalid Situacao value: " + value);
    }
}
