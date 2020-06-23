package com.amazon.creturns.rex.voc.configuration;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDbConfig {

    @Value("${amazon.end-point.url}")
    private String awsDynamoDBEndPoint;

    private static DynamoDB dynamoDB;
    private AmazonDynamoDB client;

    public DynamoDbConfig(){
        client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8100", "us-west-2"))
                .build();

        dynamoDB = new DynamoDB(client);
    }
    @Bean
    public DynamoDB getDynamoDB(){
        return dynamoDB;
    }
//    @Bean
//    public AmazonDynamoDB amazonDynamoDB() {
////        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
////                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(awsDynamoDBEndPoint, "us-west-2"))
////                .build();
////        return client;
//    }

    @Bean
    public DynamoDBMapper mapper() {
        return new DynamoDBMapper(client);
    }

}
