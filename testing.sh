#!/bin/bash
sudo chown -R ubuntu:ubuntu ~
cd /home/ubuntu/jitsi-meet-torture
mvn test -D max_users_tests.max_users=4 -D org.jitsi.malleus.duration=3600 -D jitsi-meet.instance.url="https://dev.myceliuminc.net" -D jitsi-meet.instance.backend-url="https://dev-api.myceliuminc.net" -D jitsi-meet.tests.toRun="MaxUsersTest"
