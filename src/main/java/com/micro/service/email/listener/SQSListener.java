package com.micro.service.email.listener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class SQSListener {
	
	//private static final Logger log = LoggerFactory.getLogger(SQSListener.class);

    @Value("${aws.sqs.inbound.queue.name}")
    private String inboundQueueName;

    @Value("${application.groupId}")
    private String applicationGroupId;

    @Value("${application.version}")
    private String applicationVersion;

    @JmsListener(destination = "${aws.sqs.inbound.queue.name}")
    public void receberMensagem(String message) throws Exception {
        try {
            //TransacaoEntradaDTO dto = JSONConvertable.createFromJsonStatic(message, TransacaoEntradaDTO.class);
            //String json = dto.toJSON();
            //log.info("Mensagem {} recebida da fila {}, enviando para processamento", json, inboundQueueName);
        	
        	System.out.println("Message: " + message);
            
        } catch (Exception e) {
            //log.error("Falha de conexão! A mensagem continuará na fila para ser reprocessada. Mensagem: ({}), Erro: ({})", message,
                    
            throw e;
        } 
    }

}
