#!/usr/bin/env bash
find out/camel1/ -type f | wc -l && find out/camel2/ -type f | wc -l
rm out/camel1/* out/camel2/*
cp samples/* in/