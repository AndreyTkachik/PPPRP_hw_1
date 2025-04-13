#!/bin/bash

kubectl apply -f kuber/config-map.yaml
kubectl apply -f kuber/pod.yaml 
kubectl apply -f kuber/deployment.yaml
kubectl apply -f kuber/service-cluster.yaml
kubectl apply -f kuber/daemon-set.yaml
kubectl apply -f kuber/cronejob.yaml
