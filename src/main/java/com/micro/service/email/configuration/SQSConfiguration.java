package com.micro.service.email.configuration;

import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.destination.DynamicDestinationResolver;

import javax.jms.ConnectionFactory;
import javax.jms.Session;

/**  
 * @author wagner-aos
 *
 */

@Configuration
public class SQSConfiguration {
	
    @Value("${aws.access.key}")
    private String awsAccessKey;

    @Value("${aws.secret.key}")
    private String awsSecretKey;
    
    @Value("${aws.profile}")
    private String awsProfile;

    @Value("${aws.region}")
    private String awsRegion;

    @Value("${aws.sqs.endpoint.host}")
    private String sqsHost;

    @Value("${aws.sqs.endpoint.port}")
    private Integer sqsPort;

    @Value("${aws.sqs.inbound.queue.name}")
    private String sqsQueueInbound;

    @Value("${aws.sqs.queue.concurrency}")
    private String sqsConcurrency;


    /**
     * Returns a DefaultJmsListenerContainerFactory for SQS Listener
     * 
     * @param clientAcknowledge
     * @return
     */
    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {

        if (this.sqsConcurrency == null) {
            throw new IllegalArgumentException("Concurrency não pode ser nulo");
        }

        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setDestinationResolver(new DynamicDestinationResolver());
        factory.setConcurrency(sqsConcurrency);
        factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
        return factory;
    }

    public ConnectionFactory connectionFactory() {

        return SQSConnectionFactory.builder()
        		.withRegionName("us-east-1")
                .withEndpoint("http://"+sqsHost+":"+sqsPort)
                .withAWSCredentialsProvider(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
                .build();
    }
    
    
	@Bean
	public AmazonSQS getClient() {
		String endpoint = "http://"+sqsHost+":"+sqsPort;
		String region = "elasticmq";
		String accessKey = "x";
		String secretKey = "x";
		AmazonSQS client = AmazonSQSClientBuilder.standard()
		    .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
		    .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, region))
		    .build();
		return client;
		
	}


}
