# Spinup RabbitMQ locally
You can use Docker to run the RabbitMQ broker. If you use Windows, install the [DockerToolbox](https://download.docker.com/win/stable/DockerToolbox.exe) first.
* to start RabbitMQ run following command from this directory:
  ```
  docker-compose up -d
  ```   
* to stop Rabbit MQ run:
  ```
  docker-compose stop
  ```
  
## RabbiotMQ Admin console
RabbitMq console is available at http://192.168.99.100:15672 (user/password)
  
## Useful RabbitMQ API
* to create virtualhost run:
  ```
  curl -i -s -u ${RABBITMQ_USER}:${RABBITMQ_PWD} -H "content-type:application/json" -X PUT http://${RABBITMQ_IP}:15672/api/vhosts/<vhost_name>
  ```
  
  ----
  * https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-messaging.html
