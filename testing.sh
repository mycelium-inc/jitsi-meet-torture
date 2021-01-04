#!/bin/bash
if [ -z "$1" ] ; then
    PARTYNAME=testparty
else
    PARTYNAME=$1
fi

if [ -z "$2" ] ; then
    INSTANCE=https://dev.myceliuminc.net
else
    INSTANCE=$2
fi

if [ -z "$3" ] ; then
    API=https://dev-api.myceliuminc.net
else
    API=$3
fi

if [ -z "$4" ] ; then
    TEST=MaxUsersTest
else
    TEST=$4
fi

if [ -z "$5" ] ; then
    DURATION=3600
else
    DURATION=$5
fi

sudo chown -R ubuntu:ubuntu ~
cd /home/ubuntu/jitsi-meet-torture

xvfb-run mvn test \
-D org.jitsi.malleus.duration=$DURATION \
-D jitsi-meet.instance.url="$INSTANCE" \
-D jitsi-meet.instance.backend-url="$API" \
-D jitsi-meet.tests.toRun="$TEST" \
-D jitsi-meet.instance.party-name="$PARTYNAME"

