# poc-b3-market-data-api project

## Example of a http GET to consume the B3 API

https://api-marketdata-sandbox.b3.com.br/api/orders/v1.0/QuantityOrders?SessionStartDate=2020-01-10&SessionEndDate=2020-01-10&Symbol=PETR4


## Import the B3 ssl certificate to your Java platform

### Create the root.crt file

```
$ openssl s_client -showcerts -connect api-marketdata-sandbox.b3.com.br:443
```

Copy the content between `-----BEGIN CERTIFICATE-----` and `-----END CERTIFICATE-----` and save it into a file called `b3-api.crt`.

Or just use [this crt file](./certificates/b3-api.crt) already created.

### Import the file to the trusted root certificate of the Java platform

Find the cacerts file. It could be like `/etc/ssl/certs/java/cacerts`.

Execute the keytool command to import it:
```
sudo keytool -importcert -keystore /etc/ssl/certs/java/cacerts -storepass changeit -file /your-path/b3-api.crt -alias "b3-api-root"
```

### Website with complete instructions
https://jfrog.com/knowledge-base/how-to-resolve-unable-to-find-valid-certification-path-to-requested-target-error/    
