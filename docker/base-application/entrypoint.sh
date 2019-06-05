#!/usr/bin/env bash

sleep 1000000000000
exec java -cp 'mpc-1.jar:libs/*' org.springframework.boot.loader.JarLauncher \
    -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap ${MEMORY_LIMITS}
