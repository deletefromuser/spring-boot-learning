package com.example.springboot;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ClusterOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.data.redis.hash.ObjectHashMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HelloController {

	@Autowired
	// @Qualifier("stringRedisTemplate")
	StringRedisTemplate redis;

	// @Resource
	@Autowired
	@Qualifier("redisTemplate")
	// HashOperations<String, String, Product> redisHash;
	RedisTemplate redisHash;

	// @Autowired
	// @Resource(name = "redisTemplate")
	// HashOperations<String, byte[], byte[]> hashOperations;

	@Autowired
	@Resource(name = "redisTemplate")
	HashOperations<String, String, Object> hashOperationsJson;

	// @Resource(name = "redisTemplate")
	// ClusterOperations clusterOperations;

	@Resource(name = "redisTemplate")
	ValueOperations<String, String> valueOperations;

	@GetMapping("/redis/string/set")
	public String index() {
		// ValueOperations<String, String> vo = redis.opsForValue();

		redis.opsForValue().set("hello", "world");

		return "hello";
	}

	@GetMapping("/getRedis")
	public String getRedis() {
		return "redis hello:" + redis.opsForValue().get("hello");
	}

	@GetMapping("/getRedis2")
	public String getRedis2() {
		return "redis hello:" + redis.opsForValue().get("hello2");
	}

	@GetMapping("/getRedis3")
	public String getRedis3() {
		// redisHash.setHashKeySerializer(new StringRedisSerializer());
		// redisHash.setHashValueSerializer(new LdapFailAwareRedisObjectSerializer());

		Product paProduct = new Product();
		paProduct.setId("1");
		paProduct.setName("tomcat");
		// redisHash.opsForHash().put("product", paProduct.getId(), new
		// ObjectHashMapper().toHash(paProduct));
		HashOperations<String, byte[], byte[]> hashOperations = redisHash.opsForHash();
		hashOperations.putAll("product", new ObjectHashMapper().toHash(paProduct));

		hashOperationsJson.putAll("product2", new Jackson2HashMapper(true).toHash(paProduct));
		return "redis hello:" + redis.opsForValue().get("hello2");
	}

	@GetMapping("/getRedis4")
	public String getRedis4() {
		HashOperations<String, byte[], byte[]> hashOperations = redisHash.opsForHash();
		Map<byte[], byte[]> loadedHash = hashOperations.entries("product");
		Product paProduct = (Product) new ObjectHashMapper().fromHash(loadedHash);
		return "redis product:" + paProduct.toString();
	}

	@GetMapping("/getRedis5")
	public String getRedis5() {
		Map<String, Object> loadedHash = hashOperationsJson.entries("product2");
		Product paProduct = (Product) new Jackson2HashMapper(true).fromHash(loadedHash);
		return "redis product:" + paProduct.toString();
	}

	@GetMapping("/getRedis6")
	public String getRedisForClusterSet() {
		valueOperations.set("cluster", "oh");

		return "redis cluster:" + valueOperations.get("cluster");
	}

	@GetMapping("/getRedis7")
	public String getRedisForClusterget() {
		return "redis cluster:" + valueOperations.get("cluster");
	}

}