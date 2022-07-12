package com.example.springboot.shell;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShellCallTest {
    @Test
    public void testShell() throws IOException {
        ProcessBuilder pb = new ProcessBuilder("bash", "-c","git --version; eval '$(ssh-agent -s)'; ssh-add /home/vu18/.ssh/id_ed25519_gh_delete; git pull");
        pb.directory(new File("/home/vu18/eclipse-workspace/hugo-source"));
        Process process = pb.start();

        log.info(new String(process.getInputStream().readAllBytes()));
    }
}
