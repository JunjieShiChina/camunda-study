package me.shijunjiee.camundastudy.config;

import connectjar.org.apache.http.impl.client.CloseableHttpClient;
import connectjar.org.apache.http.impl.client.HttpClients;
import org.camunda.connect.httpclient.HttpConnector;
import org.camunda.connect.httpclient.impl.AbstractHttpConnector;
import org.camunda.connect.spi.ConnectorConfigurator;

/**
 * 自定义的 ConnectorConfigurator 实现，
 * 用于配置 Camunda Connect HTTP 连接器（HttpConnector）。
 * 在 Camunda 中，Connectors 是用于与外部系统进行通信的组件，
 * 而 HttpConnector 是用于通过 HTTP 请求与外部系统进行通信的一种 Connector。
 */
public class HttpConnectorConfigurator implements ConnectorConfigurator<HttpConnector> {

    /**
     * 告知 Camunda Connect 框架这个 Configurator 是为哪个 Connector 进行配置的
     * @return
     */
    @Override
    public Class<HttpConnector> getConnectorClass() {
        return HttpConnector.class;
    }

    /**
     * 为 Camunda Connect HTTP 连接器提供了自定义的配置，使其在执行 HTTP 请求时可以使用指定的 HttpClient
     * @param httpConnector
     */
    @Override
    public void configure(HttpConnector httpConnector) {
        /**
         * 创建了一个 CloseableHttpClient 对象，
         * 这是 Apache HttpClient 的一个实例。
         * 在这里使用了 Apache HttpClient 提供的 Fluent API 来配置 HttpClient，
         * 设置了最大连接数和每个路由的最大连接数
         */
        CloseableHttpClient client = HttpClients.custom()
                .setMaxConnPerRoute(10)
                .setMaxConnTotal(200)
                .build();

        /**
         * 将创建的 CloseableHttpClient 对象设置给了 HttpConnector。
         * 由于 HttpConnector 是一个接口，而 setHttpClient() 方法是 AbstractHttpConnector 类中的方法，
         * 因此需要将 HttpConnector 对象转换为 AbstractHttpConnector 对象。
         * 这样，Camunda Connect 在执行 HTTP 请求时将会使用这个自定义配置的 HttpClient
         */
        ((AbstractHttpConnector)httpConnector).setHttpClient(client);
    }
}
