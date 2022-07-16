package com.example.springboot.controller;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.nio.file.Files;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RestController
@Slf4j
public class ProxyController {

    OkHttpClient client = new OkHttpClient.Builder()
            .proxy(new Proxy(Proxy.Type.SOCKS,
                    new InetSocketAddress("localhost", 1080)))
            .build();

    @GetMapping(value = "/nyaa/", produces = "application/xml")
    @ResponseBody
    String proxyNyaaRss(HttpServletRequest req) throws IOException {
        log.info("req.toString() -" + req.toString());
        log.info("req.getRequestURL() -" + req.getRequestURL().toString());
        log.info("req.getRequestURI() -" + req.getRequestURI());
        log.info("req.getContextPath() -" + req.getContextPath());
        log.info("req.getServerName() -" + req.getServerName());
        log.info("req.getServerPort() -" + req.getServerPort());
        log.info("req.getScheme() -" + req.getScheme());

        String url = "https://nyaa.si/?" + req.getQueryString();
        log.info("accessing " + url);
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string().replace("https://nyaa.si/download/",
                    req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath()
                            + "/nyaa/download/");
        }
    }

    // https://stackoverflow.com/a/35683261/19120213
    @SneakyThrows
    @GetMapping(value = "/nyaa/download/{torrent}", produces = "application/x-bittorrent")
    @ResponseBody
    ResponseEntity<Resource> proxyNyaaRssDownload(HttpServletRequest req, @PathVariable String torrent) {
        String url = "https://nyaa.si/download/" + torrent;
        log.info("accessing " + url);
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + torrent);

            byte[] file = response.body().bytes();
            ByteArrayResource resource = new ByteArrayResource(file);

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.length)
                    .contentType(new MediaType("application", "x-bittorrent"))
                    .body(resource);
        }
    }
}
