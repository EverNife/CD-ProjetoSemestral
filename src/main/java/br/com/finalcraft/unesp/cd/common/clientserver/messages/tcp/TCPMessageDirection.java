package br.com.finalcraft.unesp.cd.common.clientserver.messages.tcp;

import java.io.Serializable;

public enum TCPMessageDirection implements Serializable {
    CLIENT_TO_SERVER,
    SERVER_TO_CLIENT;
}
