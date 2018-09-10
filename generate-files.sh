#!/usr/bin/env bash
echo $1
for i in $(seq $1)
do
  dd if=/dev/urandom of=samples/$(date +%s | sha256sum | base64 | head -c 32 ; echo).txt bs=2048 count=$(((RANDOM % 100) + 1))
  echo done
done
cp samples/*.txt in