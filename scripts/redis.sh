redis-server /etc/redis/redis_6487.conf
redis-server /etc/redis/redis_6486.conf
redis-server /etc/redis/redis_6485.conf
redis-server /etc/redis/redis_6484.conf
redis-server /etc/redis/redis_6483.conf
redis-server /etc/redis/redis_6482.conf
redis-server /etc/redis/redis_6481.conf

redis-cli -p 6481 -a a12345678 shutdown
redis-cli -p 6482 -a a12345678 shutdown
redis-cli -p 6483 -a a12345678 shutdown
redis-cli -p 6484 -a a12345678 shutdown
redis-cli -p 6485 -a a12345678 shutdown
redis-cli -p 6486 -a a12345678 shutdown
redis-cli -p 6487 -a a12345678 shutdown

# clear log
/home/vu18/Documents/redis/logs

