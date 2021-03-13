# POC of B3 Market Data API 

[B3](http://www.b3.com.br/en_us/) is a Brazilian stock exchange. Quoting their [website](http://www.b3.com.br/en_us/b3/about/who-we-are/):
> B3 S.A. – Brasil, Bolsa, Balcão is one of the world’s largest financial market infrastructure companies, providing trading services in an exchange and OTC environment. B3 is a public company traded under ticker symbol B3SA3 on the Novo Mercado premium listing segment and its stock is tracked by the Ibovespa, IBrX-50, IBrX and Itag indices, among others. It also has a solid tradition of innovation in products and technology and is one of the largest companies in market value holding a prominent global position in the stock market industry.

They are creating APIs that will allow partners, as brokers and banks, to consume their information.

The documentation is available at `https://developers.b3.com.br`.

There are three groups of APIs: `Market Data`, `B3 Investor` and `UP to data`.

Until this moment - March 13, 2021 - these APIs aren't on production yet.

But, there's a [sandbox](https://developers.b3.com.br/sandbox) that allows us to make some tests with the `Market Data` APIs.

The Market Data APIs are divided in two groups: 
- ![image](./docs/images/market-data-trade-api.png)
- ![image](./docs/images/market-data-orders-api.png)

This project allows us to execute one particular endpoint - `/api/orders/v1.0/QuantityOrders`:
> This API returns the consolidated of orders to buy and / or sell for a given date range. The data are available for consultation as of 01/02/2020, and at Sandbox data available are exclusively from the month 01/2020.

# Obtaining the KeyId to access the B3 sandbox

It's necessary to pass a "KeyId" parameter on the HTTP headers to consume the B3 Sandbox.

# How to run this project

It's possible to run this project with a local `mock-server`, that is contained in this project, or consuming the `B3 sandbox` directly.

The B3 sandbox is a https web server, so if you want to run this project in your local JVM, it will be necessary to import the [b3 sandbox certificate](./api-client/certificates/b3-api.crt) to your java cacerts following [this instructions](#Import-the-B3-ssl-certificate-to-your-Java-platform).

But, if you prefer you can run the docker images that are already with the ssl certificate imported.

# Running the project from the Docker image from Docker Hub

Just run:
```
$ docker run --name poc-b3-market-data -p 8080:8080 felipewind/poc-b3-market-data-api:1.0
```




# Import the B3 ssl certificate to your Java platform

## Create the root.crt file

```
$ openssl s_client -showcerts -connect api-marketdata-sandbox.b3.com.br:443
```

Copy the content between `-----BEGIN CERTIFICATE-----` and `-----END CERTIFICATE-----` and save it into a file called `b3-api.crt`.

Or just use [this crt file](./certificates/b3-api.crt) already created.

## Import the file to the trusted root certificate of the Java platform

Find the cacerts file. It could be like `/etc/ssl/certs/java/cacerts`.

Execute the keytool command to import it:
```
sudo keytool -importcert -keystore /etc/ssl/certs/java/cacerts -storepass changeit -file /your-path/b3-api.crt -alias "b3-api-root"
```

## Website with complete instructions
https://jfrog.com/knowledge-base/how-to-resolve-unable-to-find-valid-certification-path-to-requested-target-error/    


# Example of a http GET to consume the B3 API

```
https://api-marketdata-sandbox.b3.com.br/api/orders/v1.0/QuantityOrders?SessionStartDate=2020-01-10&SessionEndDate=2020-01-10&Symbol=PETR4
```
