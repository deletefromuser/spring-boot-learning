`~$ more ~/.bash_profile`
# export http_proxy="127.0.0.1:1080"
# export https_proxy="127.0.0.1:1080"
# export no_proxy="localhost,127.0.0.1,::1"
export ARTEMIS_HOME=/opt/apache-artemis-2.19.1

`/var/lib# ${ARTEMIS_HOME}/bin/artemis create mybroker`
Creating ActiveMQ Artemis instance at: /var/lib/mybroker

--user:
Please provide the default username:
admin

--password: is mandatory with this configuration:
Please provide the default password:

Invalid Entry!
--password: is mandatory with this configuration:
Please provide the default password:


--allow-anonymous | --require-login:
Allow anonymous access?, valid values are Y,N,True,False
y

Auto tuning journal ...
done! Your system can make 31.25 writes per millisecond, your journal-buffer-timeout will be 32000

You can now start the broker by executing:  

   "/var/lib/mybroker/bin/artemis" run

Or you can run the broker in the background using:

   "/var/lib/mybroker/bin/artemis-service" start

------------------------------------------------------

## start RabblitMQ ##
`systemctl start rabbitmq-server`

[How to enable RabbitMQ management interface](https://www.codementor.io/@bosunbolawa/how-to-enable-rabbitmq-management-interface-owc5lzg7f)

`rabbitmq-plugins enable rabbitmq_management`


## start Apache Kafka ##

https://kafka.apache.org/documentation/#quickstart
